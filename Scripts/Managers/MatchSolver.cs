using UnityEngine;
using KyTran.Models;
using KyTran.Combat;
using DG.Tweening;
using System;
using System.Collections;
using System.Collections.Generic;

namespace KyTran.Managers
{
    /// <summary>
    /// MatchSolver - Thuật toán quét và xử lý Match-3.
    /// Quản lý: Scan grid, phát hiện match, xử lý cascade.
    /// </summary>
    public class MatchSolver : MonoBehaviour
    {
        public static MatchSolver Instance { get; private set; }

        [Header("Settings")]
        [SerializeField] private int minMatchCount = 3;
        [SerializeField] private float dropDuration = 0.3f;
        [SerializeField] private float dropInterval = 0.05f;
        [SerializeField] private float destroyDelay = 0.1f;

        [Header("Dependencies")]
        [SerializeField] private GridManager gridManager;

        // Events
        public event Action<List<MatchInfo>> OnMatchesFound;      // Bắn khi tìm thấy matches
        public event Action<int> OnScoreAdded;                     // Bắn khi cộng điểm
        public event Action OnCascadeComplete;                   // Bắn khi cascade hoàn tất
        public event Action<SpecialGemTriggerInfo> OnSpecialGemTriggered; // Bắn khi special gem được trigger

        // Match info data class
        public class MatchInfo
        {
            public List<Vector2Int> Positions { get; set; }
            public GemType Type { get; set; }
            public int Count { get; set; }
            public bool IsHorizontal { get; set; }
            public bool IsVertical { get; set; }
            public bool IsMatch5 { get; set; }
            public bool IsMatch4 { get; set; }
            public Vector2Int CenterPosition { get; set; }  // Vị trí trung tâm để tạo special gem
            public SpecialType CreatedSpecial { get; set; } // Loại special gem được tạo từ match này

            // T/L Shape detection
            public bool IsPartOfTLShape { get; set; }        // Là một phần của T/L shape
            public Vector2Int IntersectionPoint { get; set; } // Điểm giao của T/L
            public bool ShouldCreateBomb { get; set; }       // Nên tạo Bomb thay vì LineClear
            public MatchInfo IntersectionMatch { get; set; } // Match giao với match này

            public MatchInfo()
            {
                Positions = new List<Vector2Int>();
                CreatedSpecial = SpecialType.None;
                IntersectionPoint = Vector2Int.zero;
                IsPartOfTLShape = false;
                ShouldCreateBomb = false;
            }
        }

        // State
        private bool isResolving = false;
        private int currentCascadeLevel = 0;

        // Properties
        public bool IsResolving => isResolving;

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

            if (gridManager == null)
            {
                gridManager = GridManager.Instance;
            }
        }

        /// <summary>
        /// Bắt đầu quá trình resolve: scan → destroy → drop → cascade.
        /// Gọi từ SwapController sau khi swap thành công.
        /// </summary>
        public void StartResolve()
        {
            if (isResolving) return;

            isResolving = true;
            currentCascadeLevel = 0;

            StartCoroutine(ResolveCoroutine());
        }

        /// <summary>
        /// Coroutine chính để resolve matches với cascade.
        /// </summary>
        private IEnumerator ResolveCoroutine()
        {
            while (true)
            {
                // 1. Scan grid tìm tất cả matches
                List<MatchInfo> matches = ScanGrid();

                if (matches.Count == 0)
                {
                    // Không còn match nào → Cascade kết thúc
                    break;
                }

                currentCascadeLevel++;

                // 2. Xử lý matches (destroy + score + special)
                yield return StartCoroutine(ProcessMatchesCoroutine(matches));

                // 3. Drop gems xuống lấp chỗ trống
                yield return StartCoroutine(DropGemsCoroutine());

                // 4. Fill empty spaces từ trên
                yield return StartCoroutine(FillEmptySpacesCoroutine());

                // 5. Đợi animation hoàn tất
                yield return new WaitForSeconds(0.1f);

                // 6. Tiếp tục loop nếu có combo mới
            }

            // Cascade hoàn tất
            isResolving = false;
            OnCascadeComplete?.Invoke();

            Debug.Log($"Cascade completed at level {currentCascadeLevel}");
        }

        #region SCAN_GRID

        /// <summary>
        /// Quét toàn bộ grid để tìm các cụm match >= 3.
        /// Trả về danh sách các MatchInfo.
        /// </summary>
        public List<MatchInfo> ScanGrid()
        {
            List<MatchInfo> allMatches = new List<MatchInfo>();
            HashSet<Vector2Int> processedPositions = new HashSet<Vector2Int>();

            // Dictionary để track các cụm match để phát hiện T/L shape
            Dictionary<Vector2Int, List<MatchInfo>> positionToMatches = new Dictionary<Vector2Int, List<MatchInfo>>();

            // Quét từng ô trong grid
            for (int x = 0; x < gridManager.Width; x++)
            {
                for (int y = 0; y < gridManager.Height; y++)
                {
                    Vector2Int pos = new Vector2Int(x, y);

                    // Bỏ qua nếu đã được xử lý
                    if (processedPositions.Contains(pos)) continue;

                    // Bỏ qua nếu là obstacle hoặc empty
                    Gem gem = gridManager.GetGemAt(pos);
                    if (gem == null || !gem.IsMovable()) continue;

                    // Tìm match ngang từ vị trí này
                    MatchInfo hMatch = FindLineMatch(pos, true);
                    if (hMatch != null && hMatch.Count >= minMatchCount)
                    {
                        allMatches.Add(hMatch);
                        foreach (var p in hMatch.Positions)
                        {
                            processedPositions.Add(p);
                            // Track vị trí để phát hiện T/L shape
                            if (!positionToMatches.ContainsKey(p))
                                positionToMatches[p] = new List<MatchInfo>();
                            positionToMatches[p].Add(hMatch);
                        }
                    }

                    // Tìm match dọc từ vị trí này
                    MatchInfo vMatch = FindLineMatch(pos, false);
                    if (vMatch != null && vMatch.Count >= minMatchCount)
                    {
                        allMatches.Add(vMatch);
                        foreach (var p in vMatch.Positions)
                        {
                            processedPositions.Add(p);
                            // Track vị trí để phát hiện T/L shape
                            if (!positionToMatches.ContainsKey(p))
                                positionToMatches[p] = new List<MatchInfo>();
                            positionToMatches[p].Add(vMatch);
                        }
                    }
                }
            }

            // Phát hiện và xử lý T/L shape - tạo Bomb ở giao điểm
            allMatches = DetectAndProcessTLShapes(allMatches, positionToMatches);

            return allMatches;
        }

        /// <summary>
        /// Phát hiện T-shape hoặc L-shape và đánh dấu để tạo Bomb.
        /// </summary>
        private List<MatchInfo> DetectAndProcessTLShapes(List<MatchInfo> matches, Dictionary<Vector2Int, List<MatchInfo>> positionToMatches)
        {
            // Tìm các vị trí có nhiều hơn 1 match (điểm giao)
            HashSet<Vector2Int> processedIntersections = new HashSet<Vector2Int>();

            foreach (var kvp in positionToMatches)
            {
                Vector2Int position = kvp.Key;
                List<MatchInfo> intersectingMatches = kvp.Value;

                // Cần ít nhất 2 matches giao nhau tại một vị trí
                if (intersectingMatches.Count >= 2 && !processedIntersections.Contains(position))
                {
                    // Tìm một match ngang và một match dọc
                    MatchInfo hMatch = null, vMatch = null;
                    foreach (var match in intersectingMatches)
                    {
                        if (match.IsHorizontal && hMatch == null) hMatch = match;
                        else if (match.IsVertical && vMatch == null) vMatch = match;
                    }

                    // Nếu có cả ngang và dọc = T hoặc L shape
                    if (hMatch != null && vMatch != null)
                    {
                        // Đánh dấu giao điểm để tạo Bomb
                        hMatch.IntersectionPoint = position;
                        hMatch.ShouldCreateBomb = true;
                        hMatch.IntersectionMatch = vMatch;

                        // Đánh dấu cả 2 matches để không bị loại bỏ
                        hMatch.IsPartOfTLShape = true;
                        vMatch.IsPartOfTLShape = true;

                        processedIntersections.Add(position);
                        Debug.Log($"T/L shape detected at {position}. Will create Bomb!");
                    }
                }
            }

            // Cập nhật center position về giao điểm cho T/L shapes
            foreach (var match in matches)
            {
                if (match.ShouldCreateBomb && match.IntersectionPoint != Vector2Int.zero)
                {
                    match.CenterPosition = match.IntersectionPoint;
                    match.CreatedSpecial = SpecialType.Bomb_3x3; // Bomb cho T/L shape
                }
            }

            return matches;
        }

        /// <summary>
        /// Tìm các viên cùng loại liên tiếp theo chiều ngang hoặc dọc.
        /// </summary>
        private MatchInfo FindLineMatch(Vector2Int startPos, bool horizontal)
        {
            Gem startGem = gridManager.GetGemAt(startPos);
            if (startGem == null || !startGem.IsMovable()) return null;

            MatchInfo match = new MatchInfo
            {
                Type = startGem.Type,
                IsHorizontal = horizontal,
                IsVertical = !horizontal
            };

            // Đếm số viên liên tiếp cùng loại
            int dx = horizontal ? 1 : 0;
            int dy = horizontal ? 0 : 1;

            Vector2Int currentPos = startPos;
            while (true)
            {
                Gem currentGem = gridManager.GetGemAt(currentPos);

                if (currentGem != null && currentGem.Type == startGem.Type && currentGem.IsMovable())
                {
                    match.Positions.Add(currentPos);
                    currentPos = new Vector2Int(currentPos.x + dx, currentPos.y + dy);
                }
                else
                {
                    break;
                }
            }

            match.Count = match.Positions.Count;

            // Xác định loại match
            match.IsMatch4 = match.Count == 4;
            match.IsMatch5 = match.Count >= 5;

            // Tính vị trí trung tâm để tạo special gem
            if (match.Count >= 3)
            {
                int midIndex = match.Positions.Count / 2;
                match.CenterPosition = match.Positions[midIndex];
            }

            return match;
        }

        /// <summary>
        /// Loại bỏ các matches chồng chéo, nhưng giữ lại T/L shapes.
        /// </summary>
        private List<MatchInfo> RemoveOverlappingMatches(List<MatchInfo> matches)
        {
            // Ưu tiên match dài hơn, nhưng giữ lại T/L shapes
            matches.Sort((a, b) => {
                // T/L shapes luôn được giữ lại
                if (a.IsPartOfTLShape && !b.IsPartOfTLShape) return -1;
                if (!a.IsPartOfTLShape && b.IsPartOfTLShape) return 1;
                // Sau đó ưu tiên match dài hơn
                return b.Count.CompareTo(a.Count);
            });

            List<MatchInfo> result = new List<MatchInfo>();
            HashSet<Vector2Int> usedPositions = new HashSet<Vector2Int>();

            foreach (var match in matches)
            {
                // T/L shapes được thêm vào dù có overlap
                if (match.IsPartOfTLShape)
                {
                    result.Add(match);
                    foreach (var pos in match.Positions)
                    {
                        usedPositions.Add(pos);
                    }
                    continue;
                }

                // Kiểm tra xem match này có chồng chéo với match đã chọn không
                bool hasOverlap = false;
                foreach (var pos in match.Positions)
                {
                    if (usedPositions.Contains(pos))
                    {
                        hasOverlap = true;
                        break;
                    }
                }

                if (!hasOverlap)
                {
                    result.Add(match);
                    foreach (var pos in match.Positions)
                    {
                        usedPositions.Add(pos);
                    }
                }
            }

            return result;
        }

        #endregion

        #region PROCESS_MATCHES

        /// <summary>
        /// Coroutine xử lý matches: destroy gems, cộng điểm, tạo special gems.
        /// Xử lý đặc biệt cho T/L shapes.
        /// </summary>
        private IEnumerator ProcessMatchesCoroutine(List<MatchInfo> matches)
        {
            OnMatchesFound?.Invoke(matches);

            // Tính tổng điểm
            int totalScore = 0;

            // Dictionary để track vị trí đã destroy
            HashSet<Vector2Int> destroyedPositions = new HashSet<Vector2Int>();

            // Dictionary để track vị trí tạo special gem
            Dictionary<Vector2Int, SpecialType> specialGemLocations = new Dictionary<Vector2Int, SpecialType>();

            // List để track special gems đã trigger
            List<SpecialGemTriggerInfo> triggeredGems = new List<SpecialGemTriggerInfo>();

            // HashSet để track các vị trí là điểm giao của T/L (sẽ thành Bomb)
            HashSet<Vector2Int> tlIntersectionPoints = new HashSet<Vector2Int>();
            foreach (var match in matches)
            {
                if (match.ShouldCreateBomb && match.IntersectionPoint != Vector2Int.zero)
                {
                    tlIntersectionPoints.Add(match.IntersectionPoint);
                }
            }

            foreach (MatchInfo match in matches)
            {
                // Tính điểm cho match này
                int matchScore = CalculateMatchScore(match);
                totalScore += matchScore;

                // Xác định loại special gem cần tạo
                SpecialType specialToCreate = DetermineSpecialGemType(match);
                match.CreatedSpecial = specialToCreate;

                // Mark và destroy từng gem
                foreach (Vector2Int pos in match.Positions)
                {
                    if (destroyedPositions.Contains(pos)) continue;

                    // Nếu là điểm giao của T/L shape, giữ lại gem này (sẽ thành Bomb)
                    if (tlIntersectionPoints.Contains(pos))
                    {
                        Debug.Log($"T/L intersection gem at {pos} preserved for Bomb creation");
                        continue;
                    }

                    Gem gem = gridManager.GetGemAt(pos);
                    if (gem != null)
                    {
                        // Nếu là viên đặc biệt, xử lý effect trước
                        if (gem.IsSpecial())
                        {
                            HandleSpecialGemEffect(gem, specialGemLocations, triggeredGems);
                        }

                        gem.MarkAsMatched();
                        destroyedPositions.Add(pos);
                    }
                }

                // Đánh dấu vị trí tạo special gem
                if (specialToCreate != SpecialType.None)
                {
                    Vector2Int specialPos = match.CenterPosition;
                    if (!specialGemLocations.ContainsKey(specialPos))
                    {
                        specialGemLocations[specialPos] = specialToCreate;
                        Debug.Log($"Special gem {specialToCreate} will be created at {specialPos}");
                    }
                }
            }

            // Đợi một chút trước khi destroy (cho VFX play)
            yield return new WaitForSeconds(destroyDelay);

            // Destroy tất cả gems đã mark (bao gồm cả gems bị special gem ảnh hưởng)
            DestroyMatchedGems(destroyedPositions);

            // Cộng điểm (cộng thêm bonus từ special gems)
            int specialBonus = 0;
            foreach (var triggered in triggeredGems)
            {
                specialBonus += triggered.BonusDamage;
            }
            totalScore += specialBonus;

            OnScoreAdded?.Invoke(totalScore);
            Debug.Log($"Score added: {totalScore}, Cascade level: {currentCascadeLevel}, Special triggers: {triggeredGems.Count}, T/L Intersections: {tlIntersectionPoints.Count}");

            // Tạo special gems tại các vị trí đã đánh dấu
            CreateSpecialGems(specialGemLocations);

            yield return new WaitForSeconds(0.1f);
        }

        /// <summary>
        /// Tính điểm dựa trên số gem trong match và cascade level.
        /// </summary>
        private int CalculateMatchScore(MatchInfo match)
        {
            // Base score: 10 điểm mỗi viên
            int baseScore = match.Count * 10;

            // Bonus cho match dài
            int lengthBonus = 0;
            if (match.Count == 4) lengthBonus = 20;
            if (match.Count >= 5) lengthBonus = 50;

            // Cascade multiplier (combo càng nhiều, điểm càng cao)
            int cascadeMultiplier = currentCascadeLevel;

            return (baseScore + lengthBonus) * cascadeMultiplier;
        }

        /// <summary>
        /// Xác định loại special gem cần tạo dựa trên match pattern.
        /// Match-4 Hàng ngang/dọc → Hỏa Tiễn/Tên Súng
        /// T/L Shape (giao ngang + dọc) → Thuốc Súng (Bomb_3x3)
        /// Match-5 → Ngũ Hành Trận
        /// </summary>
        private SpecialType DetermineSpecialGemType(MatchInfo match)
        {
            // T/L shape: tạo Bomb_3x3 thay vì LineClear
            if (match.ShouldCreateBomb)
            {
                return SpecialType.Bomb_3x3;
            }

            if (match.Count < 4) return SpecialType.None;

            // Match-5 → Tạo ColorBomb
            if (match.Count >= 5)
            {
                return SpecialType.ColorBomb;
            }

            // Match-4
            if (match.Count == 4)
            {
                if (match.IsHorizontal)
                {
                    return SpecialType.LineClear_H;  // Hỏa Tiễn
                }
                else
                {
                    return SpecialType.LineClear_V;  // Tên Súng
                }
            }

            return SpecialType.None;
        }

        /// <summary>
        /// Xử lý effect của special gem khi bị match.
        /// </summary>
        private void HandleSpecialGemEffect(Gem gem, Dictionary<Vector2Int, SpecialType> specialLocations, List<SpecialGemTriggerInfo> triggeredGems)
        {
            if (gem == null || !gem.IsSpecial()) return;

            Debug.Log($"Special gem triggered: {gem.Special} at {gem.GridPosition}");

            // Lấy danh sách vị trí bị ảnh hưởng
            List<Vector2Int> affectedPositions = SpecialGemEffect.TriggerEffect(gem, gridManager);

            // Tạo trigger info
            SpecialGemTriggerInfo triggerInfo = new SpecialGemTriggerInfo
            {
                Gem = gem,
                AffectedPositions = affectedPositions,
                BonusDamage = SpecialGemEffect.CalculateBonusDamage(gem.Special, gem.Type == GemType.Fire ? 50 : 30),
                EffectName = SpecialGemEffect.GetSpecialGemName(gem.Special)
            };

            triggeredGems.Add(triggerInfo);

            // Bắn event cho CombatManager
            OnSpecialGemTriggered?.Invoke(triggerInfo);
        }

        /// <summary>
        /// Destroy tất cả gems đã mark và xử lý obstacles.
        /// </summary>
        private void DestroyMatchedGems(HashSet<Vector2Int> positions)
        {
            foreach (Vector2Int pos in positions)
            {
                Gem gem = gridManager.GetGemAt(pos);
                if (gem != null)
                {
                    // Remove khỏi grid
                    gridManager.RemoveGemAt(pos);
                }

                // Xử lý obstacle tại vị trí này
                ProcessObstacleAtPosition(pos);
            }
        }

        /// <summary>
        /// Xử lý obstacle khi gem tại vị trí bị match.
        /// </summary>
        private void ProcessObstacleAtPosition(Vector2Int pos)
        {
            Obstacle obstacle = gridManager.GetObstacleAt(pos);
            if (obstacle == null || obstacle.IsDestroyed) return;

            // Ice: phá bằng match thường
            // Chain: cần special gem
            // Block: không phá được
            // Cage: mở bằng match

            if (obstacle.Type == ObstacleType.Block)
            {
                Debug.Log($"Block at {pos} cannot be destroyed!");
                return;
            }

            bool destroyed = gridManager.DamageObstacleAt(pos);
            if (destroyed)
            {
                // Trigger effect nếu cần
                OnObstacleDestroyed?.Invoke(pos, obstacle.Type);
            }
        }

        /// <summary>
        /// Xử lý obstacle bị ảnh hưởng bởi special gem effect.
        /// </summary>
        private void ProcessObstacleAffectedBySpecial(Vector2Int pos, SpecialType specialType)
        {
            Obstacle obstacle = gridManager.GetObstacleAt(pos);
            if (obstacle == null || obstacle.IsDestroyed) return;

            // Chain cần special gem để phá
            if (obstacle.Type == ObstacleType.Chain)
            {
                if (specialType != SpecialType.None ||
                    specialType == SpecialType.LineClear_H ||
                    specialType == SpecialType.LineClear_V ||
                    specialType == SpecialType.Bomb_3x3)
                {
                    gridManager.DamageObstacleAt(pos, specialType);
                    Debug.Log($"Chain destroyed by {specialType} at {pos}");
                }
            }
            // Ice phá bằng bất kỳ special nào
            else if (obstacle.Type == ObstacleType.Ice)
            {
                gridManager.DamageObstacleAt(pos);
            }
        }

        // Event khi obstacle bị phá
        public event Action<Vector2Int, ObstacleType> OnObstacleDestroyed;
                    gridManager.RemoveGemAt(pos);
                }
            }
        }

        /// <summary>
        /// Tạo special gems tại các vị trí đã đánh dấu.
        /// </summary>
        private void CreateSpecialGems(Dictionary<Vector2Int, SpecialType> locations)
        {
            foreach (var kvp in locations)
            {
                Vector2Int pos = kvp.Key;
                SpecialType special = kvp.Value;

                // Tạo special gem tại vị trí
                Gem newSpecialGem = CreateSpecialGemAt(pos, special);
                Debug.Log($"Created {SpecialGemEffect.GetSpecialGemName(special)} at {pos}");
            }
        }

        /// <summary>
        /// Tạo một special gem tại vị trí cụ thể.
        /// </summary>
        private Gem CreateSpecialGemAt(Vector2Int position, SpecialType specialType)
        {
            // Chọn ngẫu nhiên loại gem làm base
            GemType[] types = { GemType.Metal, GemType.Wood, GemType.Water, GemType.Fire, GemType.Earth };
            GemType baseType = types[UnityEngine.Random.Range(0, types.Length)];

            // Tạo model
            Gem gem = new Gem(baseType, position)
            {
                Special = specialType
            };

            // Spawn visual
            GameObject gemObj = CreateGemVisual(baseType, specialType);

            if (gemObj != null)
            {
                gem.Visual = gemObj;
                gemObj.name = $"Special_{specialType}_{position.x}_{position.y}";

                // Set màu special
                SpriteRenderer sr = gemObj.GetComponent<SpriteRenderer>();
                if (sr != null)
                {
                    // Màu blend giữa base và special
                    Color baseColor = gridManager.GetGemColor(baseType);
                    Color specialColor = SpecialGemEffect.GetSpecialGemColor(specialType);
                    sr.color = Color.Lerp(baseColor, specialColor, 0.6f);
                }
            }

            // Thêm vào grid
            gridManager.SetGemAt(position.x, position.y, gem);

            // Animation xuất hiện
            if (gem.Visual != null)
            {
                gem.Visual.transform.localScale = Vector3.zero;
                gem.Visual.transform.DOScale(1f, 0.3f).SetEase(Ease.OutBack);
            }

            return gem;
        }

        #endregion

        #region DROP_GEMS

        /// <summary>
        /// Coroutine xử lý gravity: các gem ở trên rơi xuống lấp chỗ trống.
        /// </summary>
        private IEnumerator DropGemsCoroutine()
        {
            List<Gem> gemsToMove = new List<Gem>();
            float maxDuration = 0f;

            // Với mỗi cột, tính toán gem nào cần rơi bao xa
            for (int x = 0; x < gridManager.Width; x++)
            {
                int emptySpaces = 0;

                // Quét từ dưới lên
                for (int y = 0; y < gridManager.Height; y++)
                {
                    Vector2Int pos = new Vector2Int(x, y);
                    Gem gem = gridManager.GetGemAt(pos);

                    if (gem == null)
                    {
                        // Có ô trống - gem ở trên sẽ rơi xuống
                        emptySpaces++;
                    }
                    else if (emptySpaces > 0)
                    {
                        // Gem này cần rơi xuống
                        Vector2Int newPos = new Vector2Int(x, y - emptySpaces);

                        gem.IsMoving = true;
                        gemsToMove.Add(gem);

                        // Tính thời gian rơi dựa trên khoảng cách
                        float duration = emptySpaces * dropInterval + dropDuration;
                        maxDuration = Mathf.Max(maxDuration, duration);
                    }
                }
            }

            if (gemsToMove.Count == 0)
            {
                yield break;
            }

            // Tạo sequence để animate tất cả gems
            Sequence dropSequence = DOTween.Sequence();

            foreach (Gem gem in gemsToMove)
            {
                Vector2Int newPos = gem.GridPosition;
                int dropDistance = 0;

                // Tìm vị trí mới (đếm số ô trống bên dưới)
                for (int y = newPos.y - 1; y >= 0; y--)
                {
                    if (gridManager.GetGemAt(newPos.x, y) == null)
                    {
                        dropDistance++;
                    }
                    else
                    {
                        break;
                    }
                }

                newPos = new Vector2Int(newPos.x, newPos.y - dropDistance);
                Vector3 targetWorldPos = gridManager.GridToWorldPosition(newPos.x, newPos.y);

                // Tính thời gian rơi
                float duration = dropDistance * dropInterval + dropDuration;

                // Cập nhật grid trước khi animate
                gridManager.SetGemAt(newPos.x, newPos.y, gem);

                // Animate với bounce ease
                gem.Visual.transform
                    .DOMove(targetWorldPos, duration)
                    .SetEase(Ease.OutBounce)
                    .OnComplete(() =>
                    {
                        gem.IsMoving = false;
                    });
            }

            yield return new WaitForSeconds(maxDuration);
        }

        #endregion

        #region FILL_EMPTY

        /// <summary>
        /// Coroutine fill các ô trống từ trên bằng gems mới.
        /// </summary>
        private IEnumerator FillEmptySpacesCoroutine()
        {
            List<Gem> newGems = new List<Gem>();
            float maxDuration = 0f;

            // Với mỗi cột, tìm các ô trống từ trên xuống
            for (int x = 0; x < gridManager.Width; x++)
            {
                int emptyCount = 0;

                for (int y = gridManager.Height - 1; y >= 0; y--)
                {
                    Vector2Int pos = new Vector2Int(x, y);
                    Gem gem = gridManager.GetGemAt(pos);

                    if (gem == null)
                    {
                        emptyCount++;
                    }
                    else if (emptyCount > 0)
                    {
                        // Có gem nhưng bên dưới có ô trống - không cần xử lý ở đây
                        // (sẽ được xử lý bởi DropGems)
                    }
                }

                // Fill các ô trống từ trên
                for (int i = 0; i < emptyCount; i++)
                {
                    int y = gridManager.Height - 1 - i;
                    Vector2Int pos = new Vector2Int(x, y);

                    // Tạo gem mới
                    Gem newGem = CreateNewGem(pos);

                    if (newGem != null)
                    {
                        newGems.Add(newGem);

                        // Animate rơi từ trên vào
                        float duration = (emptyCount - i) * dropInterval + dropDuration;
                        maxDuration = Mathf.Max(maxDuration, duration);

                        Vector3 targetPos = gridManager.GridToWorldPosition(x, y);
                        newGem.Visual.transform.position = targetPos + Vector3.up * (gridManager.Height + 1);

                        newGem.Visual.transform
                            .DOMove(targetPos, duration)
                            .SetEase(Ease.OutBounce)
                            .OnComplete(() =>
                            {
                                newGem.IsMoving = false;
                            });
                    }
                }
            }

            yield return new WaitForSeconds(maxDuration);
        }

        /// <summary>
        /// Tạo một gem mới ngẫu nhiên tại vị trí.
        /// </summary>
        private Gem CreateNewGem(Vector2Int position)
        {
            // Chọn ngẫu nhiên loại gem
            GemType[] types = { GemType.Metal, GemType.Wood, GemType.Water, GemType.Fire, GemType.Earth };
            GemType randomType = types[UnityEngine.Random.Range(0, types.Length)];

            // Tạo model
            Gem gem = new Gem(randomType, position);

            // Spawn visual
            GameObject gemObj = CreateGemVisual(randomType);

            if (gemObj != null)
            {
                gem.Visual = gemObj;
                gem.Visual.name = $"Gem_{position.x}_{position.y}";

                // Set màu
                SpriteRenderer sr = gemObj.GetComponent<SpriteRenderer>();
                if (sr != null)
                {
                    sr.color = gridManager.GetGemColor(randomType);
                }
            }

            // Thêm vào grid
            gridManager.SetGemAt(position.x, position.y, gem);

            return gem;
        }

        /// <summary>
        /// Tạo GameObject visual cho gem (placeholder).
        /// </summary>
        private GameObject CreateGemVisual(GemType type, SpecialType special = SpecialType.None)
        {
            // Tạo một sphere đơn giản làm placeholder
            GameObject obj = GameObject.CreatePrimitive(PrimitiveType.Sphere);
            obj.transform.localScale = Vector3.one * 0.9f;
            obj.name = special != SpecialType.None ? $"SpecialGem_{special}" : "Gem_Visual";

            // Xóa collider nếu không cần
            Collider col = obj.GetComponent<Collider>();
            if (col != null) Destroy(col);

            return obj;
        }

        #endregion

        #region PUBLIC_API

        /// <summary>
        /// Kiểm tra xem vị trí có tạo match không (dùng cho SwapController).
        /// </summary>
        public bool WouldCreateMatch(Vector2Int pos)
        {
            List<MatchInfo> matches = ScanGrid();
            foreach (var match in matches)
            {
                if (match.Positions.Contains(pos))
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Kiểm tra xem swap có tạo match không.
        /// </summary>
        public bool DoesSwapCreateMatch(Vector2Int pos1, Vector2Int pos2)
        {
            // Tạm swap trong data
            gridManager.SwapGemsInData(pos1, pos2);

            // Check match
            bool hasMatch = WouldCreateMatch(pos1) || WouldCreateMatch(pos2);

            // Swap lại
            gridManager.SwapGemsInData(pos1, pos2);

            return hasMatch;
        }

        #endregion
    }
}
