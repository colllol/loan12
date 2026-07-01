using UnityEngine;
using KyTran.Models;

namespace KyTran.Data
{
    /// <summary>
    /// LevelData - ScriptableObject lưu trữ cấu hình của một level.
    /// Dùng cho LevelEditor để tạo level và LevelManager để load level.
    /// </summary>
    [CreateAssetMenu(fileName = "NewLevel", menuName = "KyTran/Level Data")]
    public class LevelData : ScriptableObject
    {
        [Header("Level Info")]
        public int levelNumber = 1;
        public string levelName = "Level 1";
        public string levelDescription = "";

        [Header("Grid Settings")]
        public int gridWidth = 8;
        public int gridHeight = 8;

        [Header("Objective")]
        public ObjectiveType objective = ObjectiveType.DefeatEnemy;
        public int targetScore = 1000;
        public int targetMoves = 30;
        public int targetEnemies = 1;

        [Header("Initial Obstacles")]
        public ObstacleData[] obstacles;

        [Header("Enemy Configuration")]
        public EnemyWave[] enemyWaves;

        [Header("Rewards")]
        public int goldReward = 100;
        public int experienceReward = 50;
        public string[] itemRewards;

        [Header("Difficulty")]
        public Difficulty difficulty = Difficulty.Normal;
        public float scoreMultiplier = 1.0f;
        public float enemyDamageMultiplier = 1.0f;

        /// <summary>
        /// Khởi tạo mảng obstacles với kích thước mặc định.
        /// </summary>
        private void OnEnable()
        {
            if (obstacles == null || obstacles.Length == 0)
            {
                obstacles = new ObstacleData[0];
            }
            if (enemyWaves == null || enemyWaves.Length == 0)
            {
                enemyWaves = new EnemyWave[1];
                enemyWaves[0] = new EnemyWave();
            }
        }

        /// <summary>
        /// Tạo level mới với cấu hình mặc định.
        /// </summary>
        public static LevelData CreateDefault(int levelNum)
        {
            LevelData data = CreateInstance<LevelData>();
            data.levelNumber = levelNum;
            data.levelName = $"Level {levelNum}";
            data.objective = ObjectiveType.DefeatEnemy;
            data.targetScore = 1000 + (levelNum * 200);
            data.targetMoves = Mathf.Max(15, 30 - levelNum / 5);
            data.difficulty = (Difficulty)Mathf.Min((int)Difficulty.Hard, levelNum / 5);
            return data;
        }
    }

    /// <summary>
    /// Loại mục tiêu của level.
    /// </summary>
    public enum ObjectiveType
    {
        DefeatEnemy,    // Đánh bại enemy
        ScoreTarget,    // Đạt điểm số
        CollectGems,    // Thu thập gem nào đó
        Survive,        // Sống sót qua moves
        ClearObstacles  // Phá obstacles
    }

    /// <summary>
    /// Độ khó của level.
    /// </summary>
    public enum Difficulty
    {
        Easy = 0,
        Normal = 1,
        Hard = 2,
        Expert = 3,
        Master = 4
    }

    /// <summary>
    /// Data cho một obstacle trên grid.
    /// </summary>
    [System.Serializable]
    public class ObstacleData
    {
        public int X;
        public int Y;
        public ObstacleType Type = ObstacleType.None;
        public int HP = 1;

        public ObstacleData() { }

        public ObstacleData(int x, int y, ObstacleType type, int hp = 1)
        {
            X = x;
            Y = y;
            Type = type;
            HP = hp;
        }
    }

    /// <summary>
    /// Data cho một enemy trong wave.
    /// </summary>
    [System.Serializable]
    public class EnemyWave
    {
        public string enemyId = "Enemy_Basic";
        public int healthMultiplier = 1;
        public int attackMultiplier = 1;
        public float spawnDelay = 0f;
        public EnemyTier tier = EnemyTier.Normal;
        public bool isBoss = false;

        public EnemyWave()
        {
            enemyId = "Enemy_Basic";
            tier = EnemyTier.Normal;
        }

        public EnemyWave(string id, EnemyTier tier, int healthMult = 1, int attackMult = 1)
        {
            enemyId = id;
            this.tier = tier;
            healthMultiplier = healthMult;
            attackMultiplier = attackMult;
            isBoss = tier >= EnemyTier.Boss;
        }
    }

    /// <summary>
    /// Level Pack - ScriptableObject chứa nhiều levels.
    /// </summary>
    [CreateAssetMenu(fileName = "LevelPack", menuName = "KyTran/Level Pack")]
    public class LevelPack : ScriptableObject
    {
        public string packName = "Chapter 1";
        public int chapterNumber = 1;
        public LevelData[] levels;
        public bool isUnlocked = true;
        public string nextPackId = "";
    }
}
