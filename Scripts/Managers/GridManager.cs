using UnityEngine;
using KyTran.Models;
using DG.Tweening;
using System;
using System.Collections.Generic;

namespace KyTran.Managers
{
    /// <summary>
    /// GridManager - Quản lý bàn cờ 8x8 Linh Thạch.
    /// Chịu trách nhiệm: Khởi tạo grid, spawn gems, cung cấp API truy vấn grid.
    /// </summary>
    public class GridManager : MonoBehaviour
    {
        public static GridManager Instance { get; private set; }

        [Header("Grid Settings")]
        [SerializeField] private int gridWidth = 8;
        [SerializeField] private int gridHeight = 8;
        [SerializeField] private float gemSize = 1f;
        [SerializeField] private float gridSpacing = 0.1f;

        [Header("Prefab References")]
        [SerializeField] private GameObject[] gemPrefabs; // 5 prefab cho 5 loại Ngũ Hành
        [SerializeField] private Transform gridContainer;  // Parent object chứa tất cả gem

        [Header("Grid Origin")]
        [SerializeField] private Vector2 gridOrigin = new Vector2(-3.5f, -3.5f);

        // Events
        public event Action<Vector2Int, Vector2Int> OnSwapInitiated;  // (from, to)
        public event Action OnGridReady;

        // Internal data
        private Gem[,] grid;
        private Dictionary<Vector2Int, Gem> gemLookup;
        private Vector2 cellSize;

        // Obstacles
        private Dictionary<Vector2Int, Obstacle> obstacles;
        [SerializeField] private GameObject[] obstaclePrefabs; // Ice, Chain, Block, Cage
        [SerializeField] private Transform obstacleContainer;

        // Properties
        public int Width => gridWidth;
        public int Height => gridHeight;
        public Gem[,] Grid => grid;
        public Vector2 CellSize => cellSize;
        public Vector2 GridOrigin => gridOrigin;
        public Dictionary<Vector2Int, Obstacle> Obstacles => obstacles;

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

            InitializeGridData();
            obstacles = new Dictionary<Vector2Int, Obstacle>();
        }

        private void Start()
        {
            SpawnInitialGrid();
            OnGridReady?.Invoke();
        }

        /// <summary>
        /// Khởi tạo mảng 2D và dictionary để lookup nhanh.
        /// </summary>
        private void InitializeGridData()
        {
            grid = new Gem[gridWidth, gridHeight];
            gemLookup = new Dictionary<Vector2Int, Gem>();
            cellSize = new Vector2(gemSize + gridSpacing, gemSize + gridSpacing);
        }

        /// <summary>
        /// Sinh ra toàn bộ bàn cờ 8x8 khi game bắt đầu.
        /// Đảm bảo không sinh ra Match-3 ngay từ đầu.
        /// </summary>
        public void SpawnInitialGrid()
        {
            for (int x = 0; x < gridWidth; x++)
            {
                for (int y = 0; y < gridHeight; y++)
                {
                    SpawnGemAt(x, y, true); // true = kiểm tra tránh match
                }
            }
        }

        /// <summary>
        /// Sinh một viên gem tại vị trí (x, y).
        /// </summary>
        private void SpawnGemAt(int x, int y, bool avoidMatch)
        {
            GemType type = GetRandomGemType(x, y, avoidMatch);
            Vector2Int pos = new Vector2Int(x, y);

            // Tạo model
            Gem gem = new Gem(type, pos);

            // Spawn visual từ prefab
            GameObject gemObj = GetGemPrefab(type);
            if (gemObj != null)
            {
                Vector3 worldPos = GridToWorldPosition(x, y);
                gem.Visual = Instantiate(gemObj, worldPos, Quaternion.identity, gridContainer);
                gem.Visual.name = $"Gem_{x}_{y}";

                // Set sprite renderer color tương ứng
                SpriteRenderer sr = gem.Visual.GetComponent<SpriteRenderer>();
                if (sr != null)
                {
                    sr.sprite = GetGemSprite(type);
                }
            }

            // Lưu vào grid
            grid[x, y] = gem;
            gemLookup[pos] = gem;
        }

        /// <summary>
        /// Lấy ngẫu nhiên loại gem, có thể tránh match nếu cần.
        /// </summary>
        private GemType GetRandomGemType(int x, int y, bool avoidMatch)
        {
            List<GemType> validTypes = new List<GemType>
            {
                GemType.Metal,
                GemType.Wood,
                GemType.Water,
                GemType.Fire,
                GemType.Earth
            };

            if (!avoidMatch)
            {
                return validTypes[UnityEngine.Random.Range(0, validTypes.Count)];
            }

            // Tránh tạo match 3 bằng cách loại bỏ các loại đang match ở trái/trên
            List<GemType> safeTypes = new List<GemType>(validTypes);

            // Kiểm tra 2 ô bên trái
            if (x >= 2)
            {
                Gem left1 = grid[x - 1, y];
                Gem left2 = grid[x - 2, y];
                if (left1 != null && left2 != null && left1.Type == left2.Type)
                {
                    safeTypes.Remove(left1.Type);
                }
            }

            // Kiểm tra 2 ô bên trên
            if (y >= 2)
            {
                Gem up1 = grid[x, y - 1];
                Gem up2 = grid[x, y - 2];
                if (up1 != null && up2 != null && up1.Type == up2.Type)
                {
                    safeTypes.Remove(up1.Type);
                }
            }

            // Nếu tất cả đều bị loại (hiếm khi xảy ra), chọn ngẫu nhiên
            if (safeTypes.Count == 0)
            {
                safeTypes = validTypes;
            }

            return safeTypes[UnityEngine.Random.Range(0, safeTypes.Count)];
        }

        /// <summary>
        /// Chuyển tọa độ grid (x, y) sang world position.
        /// </summary>
        public Vector3 GridToWorldPosition(int x, int y)
        {
            return new Vector3(
                gridOrigin.x + x * cellSize.x,
                gridOrigin.y + y * cellSize.y,
                0f
            );
        }

        /// <summary>
        /// Chuyển world position sang grid position (x, y).
        /// </summary>
        public Vector2Int WorldToGridPosition(Vector3 worldPos)
        {
            int x = Mathf.RoundToInt((worldPos.x - gridOrigin.x) / cellSize.x);
            int y = Mathf.RoundToInt((worldPos.y - gridOrigin.y) / cellSize.y);
            return new Vector2Int(x, y);
        }

        /// <summary>
        /// Lấy Gem tại vị trí (x, y).
        /// </summary>
        public Gem GetGemAt(int x, int y)
        {
            if (x < 0 || x >= gridWidth || y < 0 || y >= gridHeight)
            {
                return null;
            }
            return grid[x, y];
        }

        /// <summary>
        /// Lấy Gem tại vị trí Vector2Int.
        /// </summary>
        public Gem GetGemAt(Vector2Int pos)
        {
            return GetGemAt(pos.x, pos.y);
        }

        /// <summary>
        /// Kiểm tra vị trí có hợp lệ trong grid không.
        /// </summary>
        public bool IsValidPosition(int x, int y)
        {
            return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
        }

        /// <summary>
        /// Kiểm tra 2 vị trí có kề nhau không (trái/phải/trên/dưới).
        /// </summary>
        public bool IsAdjacent(Vector2Int pos1, Vector2Int pos2)
        {
            int dx = Mathf.Abs(pos1.x - pos2.x);
            int dy = Mathf.Abs(pos1.y - pos2.y);
            return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
        }

        /// <summary>
        /// Đổi chỗ 2 gem trong data model (không animate).
        /// </summary>
        public void SwapGemsInData(Vector2Int pos1, Vector2Int pos2)
        {
            Gem gem1 = GetGemAt(pos1);
            Gem gem2 = GetGemAt(pos2);

            if (gem1 == null || gem2 == null) return;

            // Swap trong grid 2D
            grid[pos1.x, pos1.y] = gem2;
            grid[pos2.x, pos2.y] = gem1;

            // Update position của gem model
            gem1.GridPosition = pos2;
            gem2.GridPosition = pos1;

            // Update lookup
            gemLookup[pos1] = gem2;
            gemLookup[pos2] = gem1;
        }

        /// <summary>
        /// Xóa gem khỏi grid (khi bị match và nổ).
        /// </summary>
        public void RemoveGemAt(Vector2Int pos)
        {
            Gem gem = GetGemAt(pos);
            if (gem != null)
            {
                if (gem.Visual != null)
                {
                    Destroy(gem.Visual);
                }
                grid[pos.x, pos.y] = null;
                gemLookup.Remove(pos);
            }
        }

        /// <summary>
        /// Set gem vào vị trí (x, y).
        /// </summary>
        public void SetGemAt(int x, int y, Gem gem)
        {
            if (!IsValidPosition(x, y)) return;

            gem.GridPosition = new Vector2Int(x, y);
            grid[x, y] = gem;
            gemLookup[new Vector2Int(x, y)] = gem;
        }

        /// <summary>
        /// Lấy prefab tương ứng với loại gem.
        /// </summary>
        private GameObject GetGemPrefab(GemType type)
        {
            int index = (int)type - 1; // Metal=1 -> index 0
            if (index >= 0 && index < gemPrefabs.Length && gemPrefabs[index] != null)
            {
                return gemPrefabs[index];
            }
            // Fallback: trả về prefab đầu tiên
            return gemPrefabs.Length > 0 ? gemPrefabs[0] : null;
        }

        /// <summary>
        /// Lấy Sprite tương ứng với loại gem (hoặc dùng màu).
        /// </summary>
        private Sprite GetGemSprite(GemType type)
        {
            // Có thể return sprite từ atlas, hoặc trả về null để dùng default
            return null;
        }

        /// <summary>
        /// Lấy màu tương ứng với loại gem (dùng khi không có sprite).
        /// </summary>
        public Color GetGemColor(GemType type)
        {
            switch (type)
            {
                case GemType.Metal: return new Color(1f, 0.84f, 0f);     // Vàng
                case GemType.Wood: return new Color(0.2f, 0.8f, 0.2f);   // Xanh lá
                case GemType.Water: return new Color(0.2f, 0.6f, 1f);     // Xanh dương
                case GemType.Fire: return new Color(1f, 0.2f, 0.2f);      // Đỏ
                case GemType.Earth: return new Color(0.6f, 0.4f, 0.2f);   // Nâu
                default: return Color.white;
            }
        }

        #region OBSTACLE_MANAGEMENT

        /// <summary>
        /// Thêm obstacle vào vị trí.
        /// </summary>
        public void AddObstacle(ObstacleType type, Vector2Int position)
        {
            if (!IsValidPosition(position.x, position.y)) return;

            Obstacle obstacle = new Obstacle(type, position);
            obstacles[position] = obstacle;

            // Spawn visual
            GameObject obstacleObj = GetObstaclePrefab(type);
            if (obstacleObj != null)
            {
                Vector3 worldPos = GridToWorldPosition(position.x, position.y);
                obstacle.Visual = Instantiate(obstacleObj, worldPos, Quaternion.identity, obstacleContainer);
                obstacle.Visual.name = $"Obstacle_{type}_{position.x}_{position.y}";

                // Set color
                SpriteRenderer sr = obstacle.Visual.GetComponent<SpriteRenderer>();
                if (sr != null)
                {
                    sr.color = obstacle.GetDisplayColor();
                }
            }

            Debug.Log($"Added obstacle {type} at {position}");
        }

        /// <summary>
        /// Lấy obstacle tại vị trí.
        /// </summary>
        public Obstacle GetObstacleAt(Vector2Int position)
        {
            if (obstacles.ContainsKey(position))
            {
                return obstacles[position];
            }
            return null;
        }

        /// <summary>
        /// Lấy obstacle tại (x, y).
        /// </summary>
        public Obstacle GetObstacleAt(int x, int y)
        {
            return GetObstacleAt(new Vector2Int(x, y));
        }

        /// <summary>
        /// Kiểm tra có obstacle tại vị trí không.
        /// </summary>
        public bool HasObstacleAt(Vector2Int position)
        {
            return obstacles.ContainsKey(position) && !obstacles[position].IsDestroyed;
        }

        /// <summary>
        /// Xóa obstacle tại vị trí.
        /// </summary>
        public void RemoveObstacleAt(Vector2Int position)
        {
            if (obstacles.ContainsKey(position))
            {
                Obstacle obstacle = obstacles[position];
                if (obstacle.Visual != null)
                {
                    UnityEngine.Object.Destroy(obstacle.Visual);
                }
                obstacles.Remove(position);
            }
        }

        /// <summary>
        /// Damage obstacle tại vị trí.
        /// </summary>
        public bool DamageObstacleAt(Vector2Int position, SpecialType special = SpecialType.None)
        {
            if (!HasObstacleAt(position)) return false;

            Obstacle obstacle = obstacles[position];
            bool destroyed = obstacle.TakeDamage(1, special);

            if (destroyed || obstacle.CurrentHP < obstacle.MaxHP)
            {
                // Update visual
                UpdateObstacleVisual(obstacle);
            }

            if (destroyed)
            {
                RemoveObstacleAt(position);
                Debug.Log($"Obstacle destroyed at {position}!");
            }

            return destroyed;
        }

        /// <summary>
        /// Cập nhật visual của obstacle (crack effect, etc).
        /// </summary>
        private void UpdateObstacleVisual(Obstacle obstacle)
        {
            if (obstacle.Visual == null) return;

            // Flash effect khi bị damage
            SpriteRenderer sr = obstacle.Visual.GetComponent<SpriteRenderer>();
            if (sr != null)
            {
                sr.DOColor(Color.white, 0.1f).SetLoops(2, LoopType.Yoyo);
            }

            // Scale bump
            obstacle.Visual.transform.DOPunchScale(Vector3.one * 0.2f, 0.2f);
        }

        /// <summary>
        /// Lấy prefab obstacle.
        /// </summary>
        private GameObject GetObstaclePrefab(ObstacleType type)
        {
            int index = (int)type - 1;
            if (obstaclePrefabs != null && index >= 0 && index < obstaclePrefabs.Length)
            {
                return obstaclePrefabs[index];
            }
            return null;
        }

        /// <summary>
        /// Xóa tất cả obstacles.
        /// </summary>
        public void ClearAllObstacles()
        {
            foreach (var kvp in obstacles)
            {
                if (kvp.Value.Visual != null)
                {
                    UnityEngine.Object.Destroy(kvp.Value.Visual);
                }
            }
            obstacles.Clear();
        }

        /// <summary>
        /// Load obstacles từ level data.
        /// </summary>
        public void LoadObstaclesFromData(List<LevelData.ObstacleData> obstacleData)
        {
            ClearAllObstacles();

            if (obstacleData == null) return;

            foreach (var data in obstacleData)
            {
                if (data.Type != ObstacleType.None)
                {
                    AddObstacle(data.Type, new Vector2Int(data.X, data.Y));
                }
            }
        }

        #endregion

        /// <summary>
        /// Debug: In ra trạng thái grid.
        /// </summary>
        public void DebugPrintGrid()
        {
            string debug = "Grid State:\n";
            for (int y = gridHeight - 1; y >= 0; y--)
            {
                for (int x = 0; x < gridWidth; x++)
                {
                    Gem gem = grid[x, y];
                    debug += gem != null ? $"[{(int)gem.Type}]" : "[ ]";
                }
                debug += "\n";
            }
            Debug.Log(debug);
        }
    }
}
