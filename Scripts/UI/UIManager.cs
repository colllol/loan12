using UnityEngine;
using UnityEngine.UI;
using TMPro;
using KyTran.Combat;
using DG.Tweening;

namespace KyTran.UI
{
    /// <summary>
    /// UIManager - Quản lý UI chính của game.
    /// Lắng nghe events từ CombatManager để cập nhật UI.
    /// </summary>
    public class UIManager : MonoBehaviour
    {
        public static UIManager Instance { get; private set; }

        [Header("Health Bars")]
        [SerializeField] private Slider playerHealthSlider;
        [SerializeField] private TextMeshProUGUI playerHealthText;
        [SerializeField] private Slider enemyHealthSlider;
        [SerializeField] private TextMeshProUGUI enemyHealthText;

        [Header("Character Names")]
        [SerializeField] private TextMeshProUGUI playerNameText;
        [SerializeField] private TextMeshProUGUI enemyNameText;

        [Header("Score")]
        [SerializeField] private TextMeshProUGUI scoreText;
        private int currentScore = 0;

        [Header("Combat Info")]
        [SerializeField] private TextMeshProUGUI combatLogText;
        [SerializeField] private GameObject victoryPanel;
        [SerializeField] private GameObject defeatPanel;

        [Header("Dependencies")]
        [SerializeField] private DamagePopupPool damagePopupPool;

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
            SubscribeToCombatManager();
            InitializeUI();
        }

        private void OnDestroy()
        {
            UnsubscribeFromCombatManager();
        }

        private void SubscribeToCombatManager()
        {
            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.OnPlayerHealthChanged += UpdatePlayerHealth;
                CombatManager.Instance.OnEnemyHealthChanged += UpdateEnemyHealth;
                CombatManager.Instance.OnDamagePopup += ShowDamagePopup;
                CombatManager.Instance.OnCombatEnd += HandleCombatEnd;
                CombatManager.Instance.OnCombatStart += HandleCombatStart;
            }
        }

        private void UnsubscribeFromCombatManager()
        {
            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.OnPlayerHealthChanged -= UpdatePlayerHealth;
                CombatManager.Instance.OnEnemyHealthChanged -= UpdateEnemyHealth;
                CombatManager.Instance.OnDamagePopup -= ShowDamagePopup;
                CombatManager.Instance.OnCombatEnd -= HandleCombatEnd;
                CombatManager.Instance.OnCombatStart -= HandleCombatStart;
            }
        }

        /// <summary>
        /// Khởi tạo UI ban đầu.
        /// </summary>
        private void InitializeUI()
        {
            // Ẩn panels
            if (victoryPanel != null) victoryPanel.SetActive(false);
            if (defeatPanel != null) defeatPanel.SetActive(false);

            // Set tên
            if (CombatManager.Instance != null && CombatManager.Instance.Player != null)
            {
                playerNameText.text = CombatManager.Instance.Player.Data.characterName;
            }

            if (CombatManager.Instance != null && CombatManager.Instance.Enemy != null)
            {
                enemyNameText.text = CombatManager.Instance.Enemy.Data.characterName;
            }

            UpdateScore(0);
        }

        /// <summary>
        /// Cập nhật thanh máu player.
        /// </summary>
        private void UpdatePlayerHealth(int currentHealth)
        {
            if (CombatManager.Instance == null || CombatManager.Instance.Player == null) return;

            var player = CombatManager.Instance.Player;
            float healthPercent = player.GetHealthPercent();

            if (playerHealthSlider != null)
            {
                playerHealthSlider.DOValue(healthPercent, 0.3f).SetEase(Ease.OutQuad);
            }

            if (playerHealthText != null)
            {
                playerHealthText.text = $"{currentHealth}/{player.Data.maxHealth}";
            }
        }

        /// <summary>
        /// Cập nhật thanh máu enemy.
        /// </summary>
        private void UpdateEnemyHealth(int currentHealth)
        {
            if (CombatManager.Instance == null || CombatManager.Instance.Enemy == null) return;

            var enemy = CombatManager.Instance.Enemy;
            float healthPercent = enemy.GetHealthPercent();

            if (enemyHealthSlider != null)
            {
                enemyHealthSlider.DOValue(healthPercent, 0.3f).SetEase(Ease.OutQuad);
            }

            if (enemyHealthText != null)
            {
                enemyHealthText.text = $"{currentHealth}/{enemy.Data.maxHealth}";
            }
        }

        /// <summary>
        /// Hiện damage popup.
        /// </summary>
        private void ShowDamagePopup(DamageInfo info)
        {
            if (damagePopupPool != null)
            {
                damagePopupPool.ShowDamage(info);
            }
        }

        /// <summary>
        /// Cập nhật điểm số.
        /// </summary>
        public void UpdateScore(int score)
        {
            currentScore = score;
            if (scoreText != null)
            {
                scoreText.text = $"Score: {score}";
            }
        }

        /// <summary>
        /// Thêm điểm.
        /// </summary>
        public void AddScore(int amount)
        {
            UpdateScore(currentScore + amount);
        }

        /// <summary>
        /// Xử lý khi combat bắt đầu.
        /// </summary>
        private void HandleCombatStart()
        {
            if (victoryPanel != null) victoryPanel.SetActive(false);
            if (defeatPanel != null) defeatPanel.SetActive(false);
        }

        /// <summary>
        /// Xử lý khi combat kết thúc.
        /// </summary>
        private void HandleCombatEnd()
        {
            if (CombatManager.Instance == null) return;

            if (CombatManager.Instance.Enemy != null && CombatManager.Instance.Enemy.IsDead)
            {
                ShowVictory();
            }
            else if (CombatManager.Instance.Player != null && CombatManager.Instance.Player.IsDead)
            {
                ShowDefeat();
            }
        }

        private void ShowVictory()
        {
            if (victoryPanel != null)
            {
                victoryPanel.SetActive(true);
                victoryPanel.transform.localScale = Vector3.zero;
                victoryPanel.transform.DOScale(1f, 0.5f).SetEase(Ease.OutBack);
            }

            AddCombatLog("VICTORY! Bạn đã chiến thắng!");
        }

        private void ShowDefeat()
        {
            if (defeatPanel != null)
            {
                defeatPanel.SetActive(true);
                defeatPanel.transform.localScale = Vector3.zero;
                defeatPanel.transform.DOScale(1f, 0.5f).SetEase(Ease.OutBack);
            }

            AddCombatLog("DEFEAT! Bạn đã thua trận...");
        }

        /// <summary>
        /// Thêm log vào combat log.
        /// </summary>
        private void AddCombatLog(string message)
        {
            if (combatLogText != null)
            {
                combatLogText.text = message;
            }
        }

        /// <summary>
        /// Restart game (gọi từ button).
        /// </summary>
        public void RestartGame()
        {
            if (victoryPanel != null) victoryPanel.SetActive(false);
            if (defeatPanel != null) defeatPanel.SetActive(false);

            if (CombatManager.Instance != null)
            {
                CombatManager.Instance.ResetCombat();
            }

            currentScore = 0;
            UpdateScore(0);
        }
    }
}
