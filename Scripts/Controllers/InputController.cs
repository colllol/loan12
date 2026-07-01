using UnityEngine;
using KyTran.Models;

namespace KyTran.Controllers
{
    /// <summary>
    /// InputController - Xử lý Swipe gesture trên Mobile và Click chuột trên PC.
    /// Chịu trách nhiệm: Detect swipe direction, xác định ô nguồn và ô đích.
    /// </summary>
    public class InputController : MonoBehaviour
    {
        public static InputController Instance { get; private set; }

        [Header("Input Settings")]
        [SerializeField] private float swipeThreshold = 30f;      // Pixel threshold để nhận diện swipe
        [SerializeField] private float clickThreshold = 0.5f;       // Max distance (world) cho click

        [Header("Input Targets")]
        [SerializeField] private Camera mainCamera;
        [SerializeField] private LayerMask gemLayerMask;            // Layer chứa các Gem

        // Events
        public event System.Action<Vector2Int, Vector2Int> OnSwipeAttempt;  // (sourcePos, targetPos)

        // Internal state
        private Vector2 touchStartPos;
        private Vector2 touchCurrentPos;
        private bool isDragging = false;
        private Vector2Int selectedGemPos;
        private bool hasSwiped = false;

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
            if (mainCamera == null)
            {
                mainCamera = Camera.main;
            }
        }

        private void Update()
        {
#if UNITY_EDITOR || UNITY_STANDALONE
            HandleMouseInput();
#else
            HandleTouchInput();
#endif
        }

        /// <summary>
        /// Xử lý input chuột (PC).
        /// </summary>
        private void HandleMouseInput()
        {
            if (Input.GetMouseButtonDown(0))
            {
                OnPointerDown(Input.mousePosition);
            }
            else if (Input.GetMouseButton(0))
            {
                OnPointerMove(Input.mousePosition);
            }
            else if (Input.GetMouseButtonUp(0))
            {
                OnPointerUp(Input.mousePosition);
            }
        }

        /// <summary>
        /// Xử lý input cảm ứng (Mobile).
        /// </summary>
        private void HandleTouchInput()
        {
            if (Input.touchCount == 0) return;

            Touch touch = Input.GetTouch(0);

            switch (touch.phase)
            {
                case TouchPhase.Began:
                    OnPointerDown(touch.position);
                    break;

                case TouchPhase.Moved:
                case TouchPhase.Stationary:
                    OnPointerMove(touch.position);
                    break;

                case TouchPhase.Ended:
                case TouchPhase.Canceled:
                    OnPointerUp(touch.position);
                    break;
            }
        }

        /// <summary>
        /// Khi bấm xuống (Begin).
        /// </summary>
        private void OnPointerDown(Vector2 screenPos)
        {
            touchStartPos = screenPos;
            touchCurrentPos = screenPos;
            isDragging = true;
            hasSwiped = false;

            // Raycast để chọn gem
            Vector2Int gemPos = GetGemPositionAtScreen(screenPos);
            if (gemPos != Vector2Int.one * -1)
            {
                selectedGemPos = gemPos;
                HighlightGem(gemPos, true);
            }
        }

        /// <summary>
        /// Khi di chuyển (Move).
        /// </summary>
        private void OnPointerMove(Vector2 screenPos)
        {
            if (!isDragging || hasSwiped) return;

            touchCurrentPos = screenPos;
            Vector2 delta = touchCurrentPos - touchStartPos;

            // Kiểm tra nếu đã vuốt đủ xa (vượt threshold)
            if (delta.magnitude > swipeThreshold)
            {
                SwipeDirection direction = GetSwipeDirection(delta);
                if (direction != SwipeDirection.None)
                {
                    ProcessSwipe(direction);
                    hasSwiped = true;
                }
            }
        }

        /// <summary>
        /// Khi thả ra (End).
        /// </summary>
        private void OnPointerUp(Vector2 screenPos)
        {
            if (!isDragging) return;

            // Nếu không swiped mà chỉ click
            if (!hasSwiped && selectedGemPos != Vector2Int.one * -1)
            {
                // Click vào ô kề - thử swap ngang
                TrySwapToAdjacent(selectedGemPos, screenPos);
            }

            // Cleanup
            if (selectedGemPos != Vector2Int.one * -1)
            {
                HighlightGem(selectedGemPos, false);
            }

            isDragging = false;
            selectedGemPos = Vector2Int.one * -1;
            touchStartPos = Vector2.zero;
            touchCurrentPos = Vector2.zero;
        }

        /// <summary>
        /// Xác định hướng swipe từ delta vector.
        /// </summary>
        private SwipeDirection GetSwipeDirection(Vector2 delta)
        {
            // Xác định hướng chính (horizontal hoặc vertical)
            if (Mathf.Abs(delta.x) > Mathf.Abs(delta.y))
            {
                // Horizontal swipe
                return delta.x > 0 ? SwipeDirection.Right : SwipeDirection.Left;
            }
            else
            {
                // Vertical swipe
                return delta.y > 0 ? SwipeDirection.Up : SwipeDirection.Down;
            }
        }

        /// <summary>
        /// Xử lý swipe - tính toán vị trí nguồn và đích.
        /// </summary>
        private void ProcessSwipe(SwipeDirection direction)
        {
            if (selectedGemPos == Vector2Int.one * -1) return;

            Vector2Int targetPos = GetAdjacentPosition(selectedGemPos, direction);

            // Kiểm tra target có hợp lệ không
            if (GridManager.Instance != null && GridManager.Instance.IsValidPosition(targetPos.x, targetPos.y))
            {
                // Bắn event để SwapController xử lý
                OnSwipeAttempt?.Invoke(selectedGemPos, targetPos);
            }
        }

        /// <summary>
        /// Thử swap sang ô kề nếu user chỉ click (không vuốt).
        /// </summary>
        private void TrySwapToAdjacent(Vector2Int sourcePos, Vector2 screenPos)
        {
            // Convert screen pos sang grid pos
            Vector3 worldPos = mainCamera.ScreenToWorldPoint(screenPos);
            Vector2Int targetPos = GridManager.Instance.WorldToGridPosition(worldPos);

            // Kiểm tra có kề nhau không
            if (GridManager.Instance != null && GridManager.Instance.IsAdjacent(sourcePos, targetPos))
            {
                OnSwipeAttempt?.Invoke(sourcePos, targetPos);
            }
        }

        /// <summary>
        /// Lấy vị trí grid từ screen position bằng Raycast.
        /// </summary>
        private Vector2Int GetGemPositionAtScreen(Vector2 screenPos)
        {
            Ray ray = mainCamera.ScreenToRay(screenPos);
            RaycastHit2D hit = Physics2D.Raycast(ray.origin, ray.direction, 100f, gemLayerMask);

            if (hit.collider != null)
            {
                // Hit một gem - lấy vị trí grid từ world pos
                Vector3 worldPos = hit.collider.transform.position;
                return GridManager.Instance.WorldToGridPosition(worldPos);
            }

            return Vector2Int.one * -1; // Invalid
        }

        /// <summary>
        /// Lấy vị trí kề theo hướng swipe.
        /// </summary>
        private Vector2Int GetAdjacentPosition(Vector2Int pos, SwipeDirection direction)
        {
            switch (direction)
            {
                case SwipeDirection.Up:
                    return new Vector2Int(pos.x, pos.y + 1);
                case SwipeDirection.Down:
                    return new Vector2Int(pos.x, pos.y - 1);
                case SwipeDirection.Left:
                    return new Vector2Int(pos.x - 1, pos.y);
                case SwipeDirection.Right:
                    return new Vector2Int(pos.x + 1, pos.y);
                default:
                    return pos;
            }
        }

        /// <summary>
        /// Highlight/Unhighlight gem khi được chọn.
        /// </summary>
        private void HighlightGem(Vector2Int pos, bool highlight)
        {
            Gem gem = GridManager.Instance.GetGemAt(pos);
            if (gem != null && gem.Visual != null)
            {
                // Scale animation khi select
                if (highlight)
                {
                    gem.Visual.transform.localScale = Vector3.one * 1.1f;
                }
                else
                {
                    gem.Visual.transform.localScale = Vector3.one;
                }
            }
        }

        /// <summary>
        /// Lấy delta pixel của lần swipe hiện tại (dùng cho animation).
        /// </summary>
        public Vector2 GetCurrentDelta()
        {
            return touchCurrentPos - touchStartPos;
        }
    }
}
