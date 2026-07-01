using UnityEngine;
using KyTran.Models;
using DG.Tweening;
using System;
using System.Collections;
using System.Collections.Generic;

namespace KyTran.Combat
{
    /// <summary>
    /// EnemyAI - Quản lý lượt đánh của Enemy.
    /// AI sẽ chọn attack pattern dựa trên element và health.
    /// </summary>
    public class EnemyAI : MonoBehaviour
    {
        [Header("AI Settings")]
        [SerializeField] private float thinkingDelay = 1f;
        [SerializeField] private float attackInterval = 2f;
        [SerializeField] private int minDamage = 20;
        [SerializeField] private int maxDamage = 50;

        [Header("Attack Patterns")]
        [SerializeField] private bool useElementAttack = true;
        [SerializeField] private float criticalChance = 0.2f;
        [SerializeField] private EnemyTier tier = EnemyTier.Normal;

        [Header("Boss Settings")]
        [SerializeField] private bool isBoss = false;
        [SerializeField] private int bossPhaseThreshold = 50; // HP% để vào phase 2
        [SerializeField] private float enrageMultiplier = 1.5f;

        [Header("References")]
        [SerializeField] private CharacterAnimator enemyAnimator;

        // State
        private enum AIState
        {
            Idle,
            Thinking,
            Attacking,
            UsingSkill,
            Healing,
            Enraged,
            Dead
        }

        private AIState currentState = AIState.Idle;
        private bool isActive = false;
        private Coroutine attackCoroutine;
        private int currentPhase = 1;
        private float currentDamageMultiplier = 1f;

        // Events
        public event Action OnEnemyTurnStart;
        public event Action OnEnemyTurnEnd;
        public event Action<int, bool> OnEnemyAttack;
        public event Action<AttackPattern> OnPatternUsed;
        public event Action OnBossPhaseChange;

        // Properties
        public bool IsActive => isActive;
        public bool IsAttacking => currentState == AIState.Attacking || currentState == AIState.UsingSkill;
        public EnemyTier Tier => tier;
        public int CurrentPhase => currentPhase;

        private void Start()
        {
            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.OnAllMatchesResolved += OnPlayerTurnEnd;
            }
        }

        private void OnDestroy()
        {
            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.OnAllMatchesResolved -= OnPlayerTurnEnd;
            }
        }

        /// <summary>
        /// Bắt đầu lượt của Enemy.
        /// </summary>
        public void StartEnemyTurn()
        {
            if (!isActive || currentState == AIState.Dead) return;

            Debug.Log("Enemy turn started");
            OnEnemyTurnStart?.Invoke();

            attackCoroutine = StartCoroutine(EnemyTurnCoroutine());
        }

        /// <summary>
        /// Coroutine cho lượt đánh của Enemy.
        /// </summary>
        private IEnumerator EnemyTurnCoroutine()
        {
            // Kiểm tra boss phase
            CheckBossPhase();

            // Phase 1: Thinking
            currentState = AIState.Thinking;
            AttackPattern chosenPattern = ChooseAttackPattern();
            yield return StartCoroutine(ThinkingCoroutine(chosenPattern));

            // Phase 2: Execute attack based on pattern
            yield return StartCoroutine(ExecutePattern(chosenPattern));

            // Phase 3: End turn
            currentState = AIState.Idle;
            OnEnemyTurnEnd?.Invoke();

            Debug.Log("Enemy turn ended");
        }

        /// <summary>
        /// Chọn attack pattern dựa trên tier và tình huống.
        /// </summary>
        private AttackPattern ChooseAttackPattern()
        {
            float roll = UnityEngine.Random.value;

            // Base chance từ tier
            float specialChance = GetSpecialAttackChance();

            // Adjust based on boss state
            if (isBoss && currentPhase >= 2)
            {
                specialChance += 0.2f; // Boss phase 2 đánh nhiều hơn
            }

            if (roll < specialChance)
            {
                // Chọn special attack
                return ChooseSpecialPattern();
            }

            return AttackPattern.Normal;
        }

        /// <summary>
        /// Lấy chance dùng special attack dựa trên tier.
        /// </summary>
        private float GetSpecialAttackChance()
        {
            switch (tier)
            {
                case EnemyTier.Normal: return 0.1f;   // 10%
                case EnemyTier.Elite: return 0.25f;  // 25%
                case EnemyTier.Boss: return 0.4f;    // 40%
                case EnemyTier.FinalBoss: return 0.5f; // 50%
                default: return 0.1f;
            }
        }

        /// <summary>
        /// Chọn loại special attack.
        /// </summary>
        private AttackPattern ChooseSpecialPattern()
        {
            float roll = UnityEngine.Random.value;

            if (isBoss && currentPhase >= 2)
            {
                // Boss phase 2 có thêm AOE
                if (roll < 0.25f) return AttackPattern.Heavy;
                if (roll < 0.5f) return AttackPattern.AOE;
                if (roll < 0.75f) return AttackPattern.Debuff;
                return AttackPattern.Buff;
            }

            // Normal enemies
            if (roll < 0.4f) return AttackPattern.Heavy;
            if (roll < 0.7f) return AttackPattern.AOE;
            return AttackPattern.Debuff;
        }

        /// <summary>
        /// Animation suy nghĩ của Enemy.
        /// </summary>
        private IEnumerator ThinkingCoroutine(AttackPattern pattern)
        {
            Debug.Log($"Enemy is thinking... Pattern: {pattern}");

            string thinkingText = GetPatternThinkingText(pattern);
            // Shake nhẹ để show đang suy nghĩ
            transform.DOShakePosition(thinkingDelay, 0.05f, 5, 90f, false, true);

            yield return new WaitForSeconds(thinkingDelay);
        }

        private string GetPatternThinkingText(AttackPattern pattern)
        {
            switch (pattern)
            {
                case AttackPattern.Heavy: return "Preparing heavy strike...";
                case AttackPattern.AOE: return "Charging area attack...";
                case AttackPattern.Debuff: return "Casting debuff...";
                case AttackPattern.Buff: return "Empowering self...";
                default: return "Choosing attack...";
            }
        }

        /// <summary>
        /// Thực hiện attack pattern.
        /// </summary>
        private IEnumerator ExecutePattern(AttackPattern pattern)
        {
            switch (pattern)
            {
                case AttackPattern.Normal:
                    yield return StartCoroutine(PerformNormalAttack());
                    break;
                case AttackPattern.Heavy:
                    yield return StartCoroutine(PerformHeavyAttack());
                    break;
                case AttackPattern.AOE:
                    yield return StartCoroutine(PerformAOEAttack());
                    break;
                case AttackPattern.Debuff:
                    yield return StartCoroutine(PerformDebuffAttack());
                    break;
                case AttackPattern.Buff:
                    yield return StartCoroutine(PerformBuffAttack());
                    break;
            }

            OnPatternUsed?.Invoke(pattern);
        }

        /// <summary>
        /// Thực hiện đòn đánh thường.
        /// </summary>
        private IEnumerator PerformNormalAttack()
        {
            currentState = AIState.Attacking;

            // Trigger attack animation
            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerAttack(ElementType.None);
            }

            yield return new WaitForSeconds(0.3f);

            // Calculate damage
            int baseDamage = UnityEngine.Random.Range(minDamage, maxDamage + 1);
            bool isCritical = UnityEngine.Random.value < criticalChance;
            int finalDamage = CalculateFinalDamage(baseDamage, isCritical);

            // Deal damage to player
            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.DealDamageToPlayer(finalDamage);
            }

            // Trigger player hurt animation
            CharacterAnimator playerAnim = FindPlayerAnimator();
            playerAnim?.TriggerHurt(finalDamage);

            // Fire event
            OnEnemyAttack?.Invoke(finalDamage, isCritical);

            Debug.Log($"Enemy attacks: {finalDamage} damage ({(isCritical ? "CRITICAL!" : "Normal")})");

            yield return new WaitForSeconds(0.5f);
        }

        /// <summary>
        /// Thực hiện đòn đánh nặng (1.5x damage).
        /// </summary>
        private IEnumerator PerformHeavyAttack()
        {
            currentState = AIState.UsingSkill;

            Debug.Log("Enemy using HEAVY attack!");

            // Dramatic animation
            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerSpecial(SpecialType.Bomb_3x3);
            }

            // Charge up effect
            transform.DOPunchScale(Vector3.one * 0.3f, 0.5f);

            yield return new WaitForSeconds(0.8f);

            // Heavy damage = 1.5x base
            int baseDamage = UnityEngine.Random.Range(minDamage, maxDamage + 1);
            int finalDamage = Mathf.RoundToInt(baseDamage * 1.5f * currentDamageMultiplier);
            bool isCritical = true; // Heavy luôn là crit visual

            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.DealDamageToPlayer(finalDamage);
            }

            CharacterAnimator playerAnim = FindPlayerAnimator();
            playerAnim?.TriggerHurt(finalDamage);

            OnEnemyAttack?.Invoke(finalDamage, true);

            Debug.Log($"Enemy HEAVY attack: {finalDamage} damage!");

            yield return new WaitForSeconds(0.5f);
        }

        /// <summary>
        /// Thực hiện đòn đánh AOE (damage to all gems + player).
        /// </summary>
        private IEnumerator PerformAOEAttack()
        {
            currentState = AIState.UsingSkill;

            Debug.Log("Enemy using AOE attack!");

            // AOE visual
            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerSpecial(SpecialType.CrossClear);
            }

            // Screen shake
            Camera.main?.DOShakePosition(0.3f, 0.2f, 10, 90f, false, true);

            yield return new WaitForSeconds(0.5f);

            // AOE damage = 0.75x base nhưng hit tất cả
            int baseDamage = UnityEngine.Random.Range(minDamage, maxDamage + 1);
            int finalDamage = Mathf.RoundToInt(baseDamage * 0.75f * currentDamageMultiplier);

            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.DealDamageToPlayer(finalDamage);
            }

            // Gây damage cho một số gems (trigger cascades)
            TriggerAOEOnGrid();

            CharacterAnimator playerAnim = FindPlayerAnimator();
            playerAnim?.TriggerHurt(finalDamage);

            OnEnemyAttack?.Invoke(finalDamage, false);

            Debug.Log($"Enemy AOE attack: {finalDamage} damage!");

            yield return new WaitForSeconds(0.5f);
        }

        /// <summary>
        /// Trigger AOE effect lên grid.
        /// </summary>
        private void TriggerAOEOnGrid()
        {
            // Random destroy some gems in grid
            if (GridManager.Instance != null)
            {
                int destroyed = 0;
                int targetDestroy = UnityEngine.Random.Range(3, 6);

                for (int x = 0; x < GridManager.Instance.Width && destroyed < targetDestroy; x++)
                {
                    for (int y = 0; y < GridManager.Instance.Height && destroyed < targetDestroy; y++)
                    {
                        if (UnityEngine.Random.value < 0.3f) // 30% chance per cell
                        {
                            Gem gem = GridManager.Instance.GetGemAt(x, y);
                            if (gem != null && gem.IsMovable())
                            {
                                gem.MarkAsMatched();
                                destroyed++;
                            }
                        }
                    }
                }

                Debug.Log($"AOE destroyed {destroyed} gems");
            }
        }

        /// <summary>
        /// Thực hiện debuff attack (giảm player attack).
        /// </summary>
        private IEnumerator PerformDebuffAttack()
        {
            currentState = AIState.UsingSkill;

            Debug.Log("Enemy using DEBUFF attack!");

            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerSpecial(SpecialType.LineClear_V);
            }

            yield return new WaitForSeconds(0.5f);

            // Debuff = ít damage hơn nhưng giảm player defense
            int baseDamage = UnityEngine.Random.Range(minDamage, maxDamage + 1);
            int finalDamage = Mathf.RoundToInt(baseDamage * 0.5f * currentDamageMultiplier);

            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.DealDamageToPlayer(finalDamage);
                // TODO: Apply debuff to player
            }

            CharacterAnimator playerAnim = FindPlayerAnimator();
            playerAnim?.TriggerHurt(finalDamage);

            OnEnemyAttack?.Invoke(finalDamage, false);

            Debug.Log($"Enemy DEBUFF attack: {finalDamage} damage! Player defense reduced.");

            yield return new WaitForSeconds(0.5f);
        }

        /// <summary>
        /// Thực hiện buff attack (tăng enemy stats).
        /// </summary>
        private IEnumerator PerformBuffAttack()
        {
            currentState = AIState.Healing;

            Debug.Log("Enemy using BUFF attack!");

            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerAttack(ElementType.Water);
            }

            yield return new WaitForSeconds(0.5f);

            // Buff = heal + tăng damage cho lượt sau
            currentDamageMultiplier *= 1.25f;

            if (CombatManager.Instance != null && CombatManager.Instance.Enemy != null)
            {
                int healAmount = Mathf.RoundToInt(CombatManager.Instance.Enemy.Data.maxHealth * 0.1f);
                CombatManager.Instance.Enemy.TakeDamage(-healAmount); // Negative = heal
                Debug.Log($"Enemy healed {healAmount} HP. Damage multiplier: {currentDamageMultiplier}x");
            }

            Debug.Log($"Enemy BUFF: Damage increased to {currentDamageMultiplier}x");

            yield return new WaitForSeconds(0.5f);
        }

        /// <summary>
        /// Tính final damage với tất cả multipliers.
        /// </summary>
        private int CalculateFinalDamage(int baseDamage, bool isCritical)
        {
            int finalDamage = baseDamage;

            if (isCritical)
            {
                finalDamage = Mathf.RoundToInt(finalDamage * 1.5f);
            }

            // Apply current damage multiplier
            finalDamage = Mathf.RoundToInt(finalDamage * currentDamageMultiplier);

            // Element bonus
            if (useElementAttack && CombatManager.Instance != null)
            {
                ElementType enemyElement = CombatManager.Instance.Enemy?.Data.element ?? ElementType.Metal;
                ElementType playerElement = CombatManager.Instance.Player?.Data.element ?? ElementType.Fire;

                float counterMultiplier = ElementCounter.GetMultiplier(enemyElement, playerElement);
                if (counterMultiplier > 1f)
                {
                    finalDamage = Mathf.RoundToInt(finalDamage * counterMultiplier);
                }
            }

            return finalDamage;
        }

        /// <summary>
        /// Kiểm tra và cập nhật boss phase.
        /// </summary>
        private void CheckBossPhase()
        {
            if (!isBoss) return;

            if (CombatManager.Instance?.Enemy != null)
            {
                float hpPercent = (float)CombatManager.Instance.Enemy.CurrentHealth / CombatManager.Instance.Enemy.Data.maxHealth * 100f;

                if (hpPercent <= bossPhaseThreshold && currentPhase == 1)
                {
                    currentPhase = 2;
                    currentDamageMultiplier = enrageMultiplier;

                    Debug.Log($"BOSS PHASE 2! Enraged! Damage x{enrageMultiplier}");

                    if (enemyAnimator != null)
                    {
                        enemyAnimator.TriggerSpecial(SpecialType.ColorBomb);
                    }

                    OnBossPhaseChange?.Invoke();
                }
            }
        }

        private CharacterAnimator FindPlayerAnimator()
        {
            // Find player character in scene
            GameObject player = GameObject.FindGameObjectWithTag("Player");
            if (player != null)
            {
                return player.GetComponent<CharacterAnimator>();
            }
            return null;
        }
            return null;
        }

        /// <summary>
        /// Callback khi player turn kết thúc.
        /// </summary>
        private void OnPlayerTurnEnd()
        {
            if (!isActive || currentState == AIState.Dead) return;
            StartEnemyTurn();
        }

        /// <summary>
        /// Kích hoạt Enemy AI.
        /// </summary>
        public void Activate()
        {
            isActive = true;
            currentState = AIState.Idle;
        }

        /// <summary>
        /// Vô hiệu hóa Enemy AI.
        /// </summary>
        public void Deactivate()
        {
            isActive = false;

            if (attackCoroutine != null)
            {
                StopCoroutine(attackCoroutine);
            }
        }

        /// <summary>
        /// Enemy bị tiêu diệt.
        /// </summary>
        public void SetDead()
        {
            currentState = AIState.Dead;
            Deactivate();

            if (enemyAnimator != null)
            {
                enemyAnimator.TriggerDie();
            }
        }

        /// <summary>
        /// Reset Enemy AI.
        /// </summary>
        public void ResetAI()
        {
            Deactivate();
            currentState = AIState.Idle;
            isActive = false;

            if (enemyAnimator != null)
            {
                enemyAnimator.ResetToIdle();
            }
        }
    }
}
