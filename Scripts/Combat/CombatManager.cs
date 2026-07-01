using UnityEngine;
using KyTran.Models;
using KyTran.Combat;
using DG.Tweening;
using System;
using System.Collections;
using System.Collections.Generic;

namespace KyTran.Combat
{
    /// <summary>
    /// CombatManager - Quản lý combat giữa Player (Tướng) và Enemy (Quái/Sứ Quân).
    /// Lắng nghe events từ MatchSolver để xử lý damage và trigger skills.
    /// </summary>
    public class CombatManager : MonoBehaviour
    {
        public static CombatManager Instance { get; private set; }

        [Header("Combat Settings")]
        [SerializeField] private int baseDamagePerGem = 10;    // Base damage mỗi viên gem
        [SerializeField] private float damageMultiplier = 1.0f;  // Multiplier cho tất cả damage
        [SerializeField] private float criticalMultiplier = 1.5f; // Tương khắc multiplier

        [Header("Characters")]
        [SerializeField] private CharacterData playerData;
        [SerializeField] private CharacterData enemyData;

        [Header("Dependencies")]
        [SerializeField] private Transform playerTransform;
        [SerializeField] private Transform enemyTransform;
        [SerializeField] private CharacterAnimator playerAnimator;
        [SerializeField] private CharacterAnimator enemyAnimator;
        [SerializeField] private EnemyAI enemyAI;

        // Runtime characters
        private Character player;
        private Character enemy;

        // Events
        public event Action<DamageInfo> OnDamageDealt;           // Bắn khi damage được tính
        public event Action<int> OnPlayerHealthChanged;           // Bắn khi máu player thay đổi
        public event Action<int> OnEnemyHealthChanged;            // Bắn khi máu enemy thay đổi
        public event Action<Character> OnCharacterDied;           // Bắn khi character chết
        public event Action OnCombatStart;                       // Bắn khi combat bắt đầu
        public event Action OnCombatEnd;                         // Bắn khi combat kết thúc

        // UnityEvents for UI binding
        public UnityEngine.Events.UnityEvent<DamageInfo> OnDamagePopup;

        // State
        private bool isProcessingMatch = false;
        private Queue<MatchSolver.MatchInfo> pendingMatches = new Queue<MatchSolver.MatchInfo>();
        private bool isPlayerTurnActive = true;

        // Properties
        public Character Player => player;
        public Character Enemy => enemy;
        public bool IsPlayerTurn { get; private set; } = true;

        private void Awake()
        {
            if (Instance == null)
            {
                Instance = this;
            }
            else
            {
                Destroy(gameObject);
                return;
            }
        }

        private void Start()
        {
            InitializeCombat();
            SubscribeToMatchSolver();

            if (enemyAI != null)
            {
                enemyAI.OnEnemyTurnEnd += HandleEnemyTurnEnd;
            }
        }

        private void OnDestroy()
        {
            UnsubscribeFromMatchSolver();

            if (enemyAI != null)
            {
                enemyAI.OnEnemyTurnEnd -= HandleEnemyTurnEnd;
            }
        }

        /// <summary>
        /// Khởi tạo combat: tạo player và enemy từ data.
        /// </summary>
        public void InitializeCombat()
        {
            // Tạo player
            if (playerData != null)
            {
                player = new Character(playerData);
            }
            else
            {
                // Default player nếu không có data
                CreateDefaultPlayer();
            }

            // Tạo enemy
            if (enemyData != null)
            {
                enemy = new Character(enemyData);
            }
            else
            {
                // Default enemy nếu không có data
                CreateDefaultEnemy();
            }

            OnCombatStart?.Invoke();
            Debug.Log($"Combat initialized: Player HP={player.CurrentHealth}, Enemy HP={enemy.CurrentHealth}");
        }

        private void CreateDefaultPlayer()
        {
            var defaultData = ScriptableObject.CreateInstance<CharacterData>();
            defaultData.characterName = "Tướng Lạc";
            defaultData.element = ElementType.Fire;
            defaultData.maxHealth = 1000;
            defaultData.attack = 100;
            defaultData.defense = 50;
            player = new Character(defaultData);
        }

        private void CreateDefaultEnemy()
        {
            var defaultData = ScriptableObject.CreateInstance<CharacterData>();
            defaultData.characterName = "Quái Vật";
            defaultData.element = ElementType.Wood;
            defaultData.maxHealth = 500;
            defaultData.attack = 80;
            defaultData.defense = 30;
            enemy = new Character(defaultData);
        }

        /// <summary>
        /// Đăng ký lắng nghe events từ MatchSolver.
        /// </summary>
        private void SubscribeToMatchSolver()
        {
            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnMatchesFound += HandleMatchesFound;
            }
        }

        private void UnsubscribeFromMatchSolver()
        {
            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnMatchesFound -= HandleMatchesFound;
            }
        }

        /// <summary>
        /// Xử lý khi MatchSolver tìm thấy matches.
        /// </summary>
        private void HandleMatchesFound(List<MatchSolver.MatchInfo> matches)
        {
            if (isProcessingMatch) return;

            StartCoroutine(ProcessMatchesCoroutine(matches));
        }

        /// <summary>
        /// Coroutine xử lý từng match để trigger skill và damage.
        /// </summary>
        private IEnumerator ProcessMatchesCoroutine(List<MatchSolver.MatchInfo> matches)
        {
            isProcessingMatch = true;

            foreach (var match in matches)
            {
                yield return StartCoroutine(ProcessSingleMatch(match));
                yield return new WaitForSeconds(0.2f); // Delay giữa các matches
            }

            isProcessingMatch = false;

            // Kiểm tra win/lose sau khi xử lý xong
            CheckCombatEnd();
        }

        /// <summary>
        /// Xử lý một match: trigger skill, tính damage, show popup.
        /// </summary>
        private IEnumerator ProcessSingleMatch(MatchSolver.MatchInfo match)
        {
            ElementType element = ElementCounter.GemToElement(match.Type);

            // Tính base damage
            int baseDamage = CalculateBaseDamage(match);

            // Tính final damage với Ngũ Hành tương khắc
            DamageInfo damageInfo = CalculateDamage(baseDamage, element, enemy.Data.element);

            // Trigger character skill animation
            yield return StartCoroutine(TriggerPlayerAttack(match, element));

            // Deal damage
            DealDamageToEnemy(damageInfo.FinalDamage);

            // Show damage popup
            ShowDamagePopup(damageInfo);

            yield return new WaitForSeconds(0.3f);
        }

        /// <summary>
        /// Tính base damage dựa trên số gem trong match.
        /// </summary>
        private int CalculateBaseDamage(MatchSolver.MatchInfo match)
        {
            // Base damage = số gem * baseDamagePerGem
            int damage = match.Count * baseDamagePerGem;

            // Bonus cho match dài
            if (match.Count == 4)
            {
                damage *= 2; // Match-4 gấp đôi damage
            }
            else if (match.Count >= 5)
            {
                damage *= 3; // Match-5 gấp 3 damage
            }

            // Áp dụng multiplier
            damage = Mathf.RoundToInt(damage * damageMultiplier);

            return damage;
        }

        /// <summary>
        /// Tính damage với luật Ngũ Hành tương khắc.
        /// Kim khắc Mộc, Mộc khắc Thổ, Thổ khắc Thủy, Thủy khắc Hỏa, Hỏa khắc Kim.
        /// </summary>
        public DamageInfo CalculateDamage(int baseDamage, ElementType attackerElement, ElementType defenderElement)
        {
            DamageInfo info = new DamageInfo
            {
                BaseDamage = baseDamage,
                AttackerElement = attackerElement,
                DefenderElement = defenderElement
            };

            // Tính multiplier từ Ngũ Hành
            float multiplier = ElementCounter.GetMultiplier(attackerElement, defenderElement);
            info.Result = ElementCounter.GetDamageResult(attackerElement, defenderElement);

            // Tính final damage
            info.FinalDamage = Mathf.RoundToInt(baseDamage * multiplier);

            // Đánh dấu Critical nếu tương khắc
            info.IsCritical = info.Result == DamageResult.Super;

            // Đặt vị trí target (enemy position)
            if (enemyTransform != null)
            {
                info.TargetPosition = enemyTransform.position + Vector3.up;
            }
            else
            {
                info.TargetPosition = Vector3.up;
            }

            Debug.Log($"Damage calculated: {baseDamage} × {multiplier} = {info.FinalDamage} " +
                      $"({ElementCounter.GetElementName(attackerElement)} vs {ElementCounter.GetElementName(defenderElement)})" +
                      $"[{(info.IsCritical ? "CRITICAL!" : "Normal")}]");

            return info;
        }

        /// <summary>
        /// Trigger player attack animation dựa trên loại gem match.
        /// VD: Match-3 Hỏa → Tướng tung chiêu "Hỏa Kiếm"
        /// </summary>
        private IEnumerator TriggerPlayerAttack(MatchSolver.MatchInfo match, ElementType element)
        {
            string skillName = GetSkillName(match, element);
            Debug.Log($"Player casting: {skillName}");

            // Trigger attack animation
            if (playerAnimator != null)
            {
                playerAnimator.TriggerAttack(element);

                // Đợi animation attack hoàn tất
                float attackDuration = 0.5f;
                yield return new WaitForSeconds(attackDuration);
            }
            else if (playerTransform != null)
            {
                // Fallback: shake nhẹ player
                playerTransform.DOShakePosition(0.2f, 0.1f, 5, 90f, false, true);
                yield return new WaitForSeconds(0.3f);
            }

            // Spawn attack VFX
            if (SkillVFX.Instance != null && playerTransform != null && enemyTransform != null)
            {
                SkillVFX.Instance.PlayAttackVFX(element, playerTransform.position, enemyTransform.position);
            }
        }

        /// <summary>
        /// Lấy tên skill dựa trên loại match và element.
        /// </summary>
        private string GetSkillName(MatchSolver.MatchInfo match, ElementType element)
        {
            string elementName = ElementCounter.GetElementName(element);

            if (match.Count >= 5)
            {
                return $"Ngũ Hành Trận ({elementName})";
            }
            else if (match.Count == 4)
            {
                return $"Tứ Tượng Kiếm ({elementName})";
            }
            else
            {
                return $"{elementName} Công";
            }
        }

        /// <summary>
        /// Gây damage cho enemy.
        /// </summary>
        public void DealDamageToEnemy(int damage)
        {
            if (enemy == null) return;

            // Tính damage thực tế (có thể thêm defense ở đây)
            int actualDamage = Mathf.Max(1, damage);

            enemy.TakeDamage(actualDamage);
            OnEnemyHealthChanged?.Invoke(enemy.CurrentHealth);

            Debug.Log($"Enemy took {actualDamage} damage. HP: {enemy.CurrentHealth}/{enemy.Data.maxHealth}");
        }

        /// <summary>
        /// Gây damage cho player (dùng cho Enemy Turn).
        /// </summary>
        public void DealDamageToPlayer(int damage)
        {
            if (player == null) return;

            int actualDamage = Mathf.Max(1, damage - player.Data.defense / 10);
            player.TakeDamage(actualDamage);
            OnPlayerHealthChanged?.Invoke(player.CurrentHealth);

            Debug.Log($"Player took {actualDamage} damage. HP: {player.CurrentHealth}/{player.Data.maxHealth}");
        }

        /// <summary>
        /// Hiện damage popup với animation bay lên.
        /// </summary>
        public void ShowDamagePopup(DamageInfo info)
        {
            // Bắn event cho UI Manager
            OnDamagePopup?.Invoke(info);
            OnDamageDealt?.Invoke(info);
        }

        /// <summary>
        /// Kiểm tra trạng thái win/lose.
        /// </summary>
        private void CheckCombatEnd()
        {
            if (enemy != null && enemy.IsDead)
            {
                Debug.Log("VICTORY! Enemy defeated!");

                // Trigger victory animation
                if (playerAnimator != null)
                {
                    playerAnimator.TriggerVictory();
                }

                OnCombatEnd?.Invoke();
            }
            else if (player != null && player.IsDead)
            {
                Debug.Log("DEFEAT! Player died!");

                // Trigger die animation
                if (playerAnimator != null)
                {
                    playerAnimator.TriggerDie();
                }

                if (enemyAnimator != null)
                {
                    enemyAnimator.TriggerVictory();
                }

                OnCombatEnd?.Invoke();
            }
            else
            {
                // Combat continues - switch to enemy turn
                if (enemyAI != null && isPlayerTurnActive)
                {
                    isPlayerTurnActive = false;
                    enemyAI.Activate();
                    enemyAI.StartEnemyTurn();
                }
            }
        }

        /// <summary>
        /// Callback khi enemy turn kết thúc.
        /// </summary>
        private void HandleEnemyTurnEnd()
        {
            isPlayerTurnActive = true;

            // Kiểm tra player có chết không sau enemy turn
            if (player != null && player.IsDead)
            {
                CheckCombatEnd();
            }
        }

        #region PUBLIC_API

        /// <summary>
        /// Đặt enemy data (gọi từ LevelManager).
        /// </summary>
        public void SetEnemy(CharacterData enemyCharacterData)
        {
            enemyData = enemyCharacterData;
            if (enemyData != null)
            {
                enemy = new Character(enemyData);
            }
        }

        /// <summary>
        /// Đặt player transform để animate.
        /// </summary>
        public void SetPlayerTransform(Transform transform)
        {
            playerTransform = transform;
        }

        /// <summary>
        /// Đặt enemy transform để animate.
        /// </summary>
        public void SetEnemyTransform(Transform transform)
        {
            enemyTransform = transform;
        }

        /// <summary>
        /// Reset combat (gọi khi restart level).
        /// </summary>
        public void ResetCombat()
        {
            isPlayerTurnActive = true;

            if (enemyAI != null)
            {
                enemyAI.ResetAI();
            }

            if (playerAnimator != null)
            {
                playerAnimator.ResetToIdle();
            }

            if (enemyAnimator != null)
            {
                enemyAnimator.ResetToIdle();
            }

            InitializeCombat();
        }

        #endregion
    }
}
