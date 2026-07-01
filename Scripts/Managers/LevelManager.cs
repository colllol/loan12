using UnityEngine;
using KyTran.Data;
using KyTran.Models;
using KyTran.Combat;
using System;
using System.Collections;

namespace KyTran.Managers
{
    /// <summary>
    /// LevelManager - Quản lý việc load và chơi level.
    /// </summary>
    public class LevelManager : MonoBehaviour
    {
        public static LevelManager Instance { get; private set; }

        [Header("Level Data")]
        [SerializeField] private LevelData currentLevelData;
        [SerializeField] private LevelPack currentPack;

        [Header("Dependencies")]
        [SerializeField] private GridManager gridManager;
        [SerializeField] private CombatManager combatManager;
        [SerializeField] private EnemyAI enemyAI;

        [Header("UI")]
        [SerializeField] private UIManager uiManager;

        // Events
        public event Action<LevelData> OnLevelLoaded;
        public event Action OnLevelCompleted;
        public event Action OnLevelFailed;
        public event Action<int> OnMovesUsed;
        public event Action<int> OnScoreChanged;

        // State
        private int movesUsed = 0;
        private int currentScore = 0;
        private int enemiesDefeated = 0;
        private int enemiesRemaining = 0;
        private bool isLevelActive = false;
        private bool isPaused = false;

        // Properties
        public LevelData CurrentLevel => currentLevelData;
        public int MovesUsed => movesUsed;
        public int CurrentScore => currentScore;
        public int EnemiesDefeated => enemiesDefeated;
        public bool IsLevelActive => isLevelActive;
        public bool IsPaused => isPaused;

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
            SubscribeToEvents();
        }

        private void OnDestroy()
        {
            UnsubscribeFromEvents();
        }

        private void SubscribeToEvents()
        {
            if (combatManager != null)
            {
                combatManager.OnCombatEnd += HandleCombatEnd;
            }

            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnMatchesFound += HandleMatchesFound;
                MatchSolver.Instance.OnCascadeComplete += HandleCascadeComplete;
            }
        }

        private void UnsubscribeFromEvents()
        {
            if (combatManager != null)
            {
                combatManager.OnCombatEnd -= HandleCombatEnd;
            }

            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnMatchesFound -= HandleMatchesFound;
                MatchSolver.Instance.OnCascadeComplete -= HandleCascadeComplete;
            }
        }

        #region LEVEL_LOADING

        /// <summary>
        /// Load và bắt đầu một level.
        /// </summary>
        public void LoadLevel(LevelData levelData)
        {
            if (levelData == null)
            {
                Debug.LogError("Level data is null!");
                return;
            }

            currentLevelData = levelData;
            ResetLevelState();

            Debug.Log($"Loading level: {levelData.levelName}");

            // Load obstacles lên grid
            if (gridManager != null)
            {
                gridManager.ClearAllObstacles();
                gridManager.LoadObstaclesFromData(levelData.obstacles != null
                    ? new System.Collections.Generic.List<ObstacleData>(levelData.obstacles)
                    : null);
            }

            // Setup enemies
            SetupEnemies();

            // Reset player stats
            if (combatManager != null)
            {
                combatManager.ResetCombat();
            }

            // Update UI
            if (uiManager != null)
            {
                uiManager.UpdateLevelInfo(levelData);
                uiManager.UpdateScore(currentScore);
                uiManager.UpdateMoves(movesUsed, levelData.targetMoves);
            }

            isLevelActive = true;
            OnLevelLoaded?.Invoke(levelData);

            Debug.Log($"Level loaded: {levelData.levelName}");
        }

        /// <summary>
        /// Load level theo số thứ tự.
        /// </summary>
        public void LoadLevel(int levelNumber)
        {
            // Tìm level data với số thứ tự tương ứng
            LevelData level = Resources.Load<LevelData>($"Levels/Level_{levelNumber}");
            if (level == null)
            {
                Debug.LogError($"Level {levelNumber} not found in Resources!");
                return;
            }

            LoadLevel(level);
        }

        /// <summary>
        /// Setup enemies cho level.
        /// </summary>
        private void SetupEnemies()
        {
            if (currentLevelData.enemyWaves == null || currentLevelData.enemyWaves.Length == 0)
            {
                enemiesRemaining = 1;
                return;
            }

            enemiesRemaining = currentLevelData.enemyWaves.Length;

            // Setup enemy AI
            if (enemyAI != null)
            {
                enemyAI.ResetAI();
            }
        }

        /// <summary>
        /// Reset trạng thái level.
        /// </summary>
        private void ResetLevelState()
        {
            movesUsed = 0;
            currentScore = 0;
            enemiesDefeated = 0;
            isPaused = false;
        }

        #endregion

        #region GAMEPLAY

        /// <summary>
        /// Xử lý khi user thực hiện một swap thành công.
        /// </summary>
        public void OnSwapExecuted()
        {
            if (!isLevelActive || isPaused) return;

            movesUsed++;
            OnMovesUsed?.Invoke(movesUsed);

            if (uiManager != null)
            {
                uiManager.UpdateMoves(movesUsed, currentLevelData.targetMoves);
            }

            Debug.Log($"Swap executed. Moves: {movesUsed}/{currentLevelData.targetMoves}");
        }

        /// <summary>
        /// Xử lý khi tìm thấy matches.
        /// </summary>
        private void HandleMatchesFound(System.Collections.Generic.List<MatchSolver.MatchInfo> matches)
        {
            // Calculate score from matches
            // Score sẽ được cập nhật bởi CombatManager
        }

        /// <summary>
        /// Xử lý khi cascade hoàn tất.
        /// </summary>
        private void HandleCascadeComplete()
        {
            if (!isLevelActive) return;

            // Kiểm tra objective
            CheckLevelObjective();
        }

        /// <summary>
        /// Cập nhật điểm số.
        /// </summary>
        public void AddScore(int score)
        {
            if (!isLevelActive) return;

            // Áp dụng multiplier từ difficulty
            int finalScore = Mathf.RoundToInt(score * currentLevelData.scoreMultiplier);
            currentScore += finalScore;

            OnScoreChanged?.Invoke(currentScore);

            if (uiManager != null)
            {
                uiManager.UpdateScore(currentScore);
            }

            Debug.Log($"Score: {currentScore}/{currentLevelData.targetScore}");
        }

        /// <summary>
        /// Kiểm tra objective của level.
        /// </summary>
        private void CheckLevelObjective()
        {
            switch (currentLevelData.objective)
            {
                case ObjectiveType.DefeatEnemy:
                    // Kiểm tra enemy đã bị đánh bại chưa
                    if (combatManager != null && combatManager.Enemy != null && combatManager.Enemy.IsDead)
                    {
                        CompleteLevel();
                    }
                    break;

                case ObjectiveType.ScoreTarget:
                    if (currentScore >= currentLevelData.targetScore)
                    {
                        CompleteLevel();
                    }
                    break;

                case ObjectiveType.Survive:
                    if (movesUsed >= currentLevelData.targetMoves)
                    {
                        if (combatManager != null && combatManager.Player != null && !combatManager.Player.IsDead)
                        {
                            CompleteLevel();
                        }
                        else
                        {
                            FailLevel();
                        }
                    }
                    break;

                case ObjectiveType.ClearObstacles:
                    if (gridManager != null && gridManager.Obstacles.Count == 0)
                    {
                        CompleteLevel();
                    }
                    break;
            }

            // Kiểm tra fail condition
            if (combatManager != null && combatManager.Player != null && combatManager.Player.IsDead)
            {
                FailLevel();
            }

            // Kiểm tra hết moves
            if (movesUsed >= currentLevelData.targetMoves && currentLevelData.objective != ObjectiveType.Survive)
            {
                // Không fail ngay, đợi cascade hoàn tất
            }
        }

        /// <summary>
        /// Hoàn thành level.
        /// </summary>
        public void CompleteLevel()
        {
            if (!isLevelActive) return;

            isLevelActive = false;

            Debug.Log($"LEVEL COMPLETED! Score: {currentScore}");

            // Calculate stars
            int stars = CalculateStars();

            // Save progress
            SaveLevelProgress();

            // Show victory UI
            if (uiManager != null)
            {
                uiManager.ShowVictory(currentScore, stars, currentLevelData.goldReward);
            }

            OnLevelCompleted?.Invoke();
        }

        /// <summary>
        /// Thất bại level.
        /// </summary>
        public void FailLevel()
        {
            if (!isLevelActive) return;

            isLevelActive = false;

            Debug.Log($"LEVEL FAILED! Score: {currentScore}");

            if (uiManager != null)
            {
                uiManager.ShowDefeat(currentScore);
            }

            OnLevelFailed?.Invoke();
        }

        /// <summary>
        /// Tính số sao nhận được.
        /// </summary>
        private int CalculateStars()
        {
            int stars = 1;

            // 2 sao: đạt target score
            if (currentScore >= currentLevelData.targetScore)
            {
                stars = 2;
            }

            // 3 sao: đạt target score + dưới 50% moves
            if (currentScore >= currentLevelData.targetScore && movesUsed <= currentLevelData.targetMoves / 2)
            {
                stars = 3;
            }

            return stars;
        }

        #endregion

        #region PROGRESS

        /// <summary>
        /// Lưu tiến độ level.
        /// </summary>
        private void SaveLevelProgress()
        {
            string key = $"Level_{currentLevelData.levelNumber}_Completed";
            PlayerPrefs.SetInt(key, 1);

            // Save stars
            string starsKey = $"Level_{currentLevelData.levelNumber}_Stars";
            int currentStars = PlayerPrefs.GetInt(starsKey, 0);
            PlayerPrefs.SetInt(starsKey, Mathf.Max(currentStars, CalculateStars()));

            // Unlock next level
            string nextKey = $"Level_{currentLevelData.levelNumber + 1}_Unlocked";
            PlayerPrefs.SetInt(nextKey, 1);

            PlayerPrefs.Save();
        }

        /// <summary>
        /// Kiểm tra level đã hoàn thành chưa.
        /// </summary>
        public bool IsLevelCompleted(int levelNumber)
        {
            return PlayerPrefs.GetInt($"Level_{levelNumber}_Completed", 0) == 1;
        }

        /// <summary>
        /// Lấy số sao của level.
        /// </summary>
        public int GetLevelStars(int levelNumber)
        {
            return PlayerPrefs.GetInt($"Level_{levelNumber}_Stars", 0);
        }

        /// <summary>
        /// Kiểm tra level đã unlock chưa.
        /// </summary>
        public bool IsLevelUnlocked(int levelNumber)
        {
            if (levelNumber == 1) return true;
            return PlayerPrefs.GetInt($"Level_{levelNumber}_Unlocked", 0) == 1;
        }

        #endregion

        #region PAUSE

        /// <summary>
        /// Tạm dừng level.
        /// </summary>
        public void PauseLevel()
        {
            if (!isLevelActive) return;
            isPaused = true;
            Time.timeScale = 0;
        }

        /// <summary>
        /// Tiếp tục level.
        /// </summary>
        public void ResumeLevel()
        {
            isPaused = false;
            Time.timeScale = 1;
        }

        /// <summary>
        /// Restart level hiện tại.
        /// </summary>
        public void RestartLevel()
        {
            Time.timeScale = 1;
            if (currentLevelData != null)
            {
                LoadLevel(currentLevelData);
            }
        }

        #endregion

        #region COMBAT_INTEGRATION

        /// <summary>
        /// Xử lý khi combat kết thúc.
        /// </summary>
        private void HandleCombatEnd()
        {
            if (combatManager.Enemy != null && combatManager.Enemy.IsDead)
            {
                enemiesDefeated++;
                enemiesRemaining--;

                // Check nếu còn enemy tiếp theo
                if (enemiesRemaining > 0 && currentLevelData.enemyWaves != null)
                {
                    int nextWaveIndex = currentLevelData.enemyWaves.Length - enemiesRemaining;
                    if (nextWaveIndex < currentLevelData.enemyWaves.Length)
                    {
                        // Spawn enemy tiếp theo sau delay
                        StartCoroutine(SpawnNextEnemyCoroutine(currentLevelData.enemyWaves[nextWaveIndex]));
                    }
                }
            }

            CheckLevelObjective();
        }

        /// <summary>
        /// Spawn enemy tiếp theo.
        /// </summary>
        private IEnumerator SpawnNextEnemyCoroutine(EnemyWave wave)
        {
            yield return new WaitForSeconds(wave.spawnDelay);

            Debug.Log($"Spawning next enemy: {wave.enemyId}");

            // Setup enemy với wave config
            if (combatManager != null && combatManager.Enemy != null)
            {
                // Áp dụng multiplier
                combatManager.Enemy.Data.maxHealth *= wave.healthMultiplier;
                combatManager.Enemy.Data.attack *= wave.attackMultiplier;
            }

            if (enemyAI != null)
            {
                enemyAI.Activate();
            }
        }

        #endregion
    }
}
