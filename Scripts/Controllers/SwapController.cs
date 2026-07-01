using UnityEngine;
using DG.Tweening;
using KyTran.Models;
using KyTran.Managers;
using System;
using System.Collections.Generic;

namespace KyTran.Controllers
{
    /// <summary>
    /// SwapController - Xử lý swap 2 gem và animation.
    /// Chịu trách nhiệm: Animate swap, kiểm tra match, Undo nếu không match.
    /// </summary>
    public class SwapController : MonoBehaviour
    {
        public static SwapController Instance { get; private set; }

        [Header("Animation Settings")]
        [SerializeField] private float swapDuration = 0.25f;
        [SerializeField] private float undoShakeDuration = 0.15f;
        [SerializeField] private float undoShakeStrength = 0.1f;
        [SerializeField] private int undoShakeVibrato = 3;

        [Header("Dependencies")]
        [SerializeField] private GridManager gridManager;

        // Events
        public event Action OnSwapComplete;
        public event Action<Vector2Int, Vector2Int> OnSwapSuccessful;  // Bắn khi swap tạo match
        public event Action<Vector2Int, Vector2Int> OnSwapFailed;       // Bắn khi swap không tạo match

        // State
        private bool isSwapping = false;
        private Gem gem1 = null;
        private Gem gem2 = null;
        private Vector2Int pos1, pos2;

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

        private void Start()
        {
            // Subscribe to Input events
            if (InputController.Instance != null)
            {
                InputController.Instance.OnSwipeAttempt += HandleSwapAttempt;
            }
        }

        private void OnDestroy()
        {
            if (InputController.Instance != null)
            {
                InputController.Instance.OnSwipeAttempt -= HandleSwapAttempt;
            }

            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnCascadeComplete -= OnCascadeComplete;
            }
        }

        /// <summary>
        /// Callback khi cascade hoàn tất.
        /// </summary>
        private void OnCascadeComplete()
        {
            if (MatchSolver.Instance != null)
            {
                MatchSolver.Instance.OnCascadeComplete -= OnCascadeComplete;
            }

            isSwapping = false;
            SetInputEnabled(true);
            OnSwapComplete?.Invoke();
            OnAllMatchesResolved?.Invoke();
        }

        // Event khi tất cả matches đã được resolve (dùng cho Combat System)
        public event Action OnAllMatchesResolved;

        /// <summary>
        /// Xử lý yêu cầu swap từ InputController.
        /// </summary>
        private void HandleSwapAttempt(Vector2Int source, Vector2Int target)
        {
            if (isSwapping) return;

            // Kiểm tra cả 2 vị trí đều có gem và không phải obstacle/empty
            Gem g1 = gridManager.GetGemAt(source);
            Gem g2 = gridManager.GetGemAt(target);

            if (g1 == null || g2 == null) return;
            if (!g1.IsMovable() || !g2.IsMovable()) return;

            pos1 = source;
            pos2 = target;
            gem1 = g1;
            gem2 = g2;

            StartSwapAnimation();
        }

        /// <summary>
        /// Bắt đầu animation swap.
        /// </summary>
        private void StartSwapAnimation()
        {
            isSwapping = true;

            // Disable input during swap
            SetInputEnabled(false);

            // Animate cả 2 gem cùng lúc
            Sequence swapSequence = DOTween.Sequence();

            // Gem 1 di chuyển đến vị trí gem 2
            swapSequence.Append(gem1.Visual.transform
                .DOMove(gridManager.GridToWorldPosition(pos2), swapDuration)
                .SetEase(Ease.OutQuad));

            // Gem 2 di chuyển đến vị trí gem 1
            swapSequence.Join(gem2.Visual.transform
                .DOMove(gridManager.GridToWorldPosition(pos1), swapDuration)
                .SetEase(Ease.OutQuad));

            // Khi animation xong
            swapSequence.OnComplete(() =>
            {
                // Cập nhật data model
                gridManager.SwapGemsInData(pos1, pos2);

                // Kiểm tra xem swap này có tạo match không
                bool hasMatch = CheckForMatch();

                if (hasMatch)
                {
                    // Swap thành công - bắn event và bắt đầu resolve
                    OnSwapSuccessful?.Invoke(pos1, pos2);

                    // Disable input trong khi resolve
                    SetInputEnabled(false);

                    // Bắt đầu cascade resolve
                    if (MatchSolver.Instance != null)
                    {
                        MatchSolver.Instance.OnCascadeComplete += OnCascadeComplete;
                        MatchSolver.Instance.StartResolve();
                    }
                    else
                    {
                        OnSwapComplete?.Invoke();
                        isSwapping = false;
                        SetInputEnabled(true);
                    }
                }
                else
                {
                    // Swap không tạo match - Undo!
                    OnSwapFailed?.Invoke(pos1, pos2);
                    StartUndoAnimation();
                }
            });
        }

        /// <summary>
        /// Animation Undo: swap về vị trí cũ + rung nhẹ.
        /// </summary>
        private void StartUndoAnimation()
        {
            Sequence undoSequence = DOTween.Sequence();

            // Đầu tiên, swap về vị trí cũ
            undoSequence.Append(gem1.Visual.transform
                .DOMove(gridManager.GridToWorldPosition(pos1), swapDuration)
                .SetEase(Ease.OutQuad));

            undoSequence.Join(gem2.Visual.transform
                .DOMove(gridManager.GridToWorldPosition(pos2), swapDuration)
                .SetEase(Ease.OutQuad));

            // Sau khi swap về, thêm hiệu ứng rung nhẹ (shake)
            undoSequence.AppendCallback(() =>
            {
                // Cập nhật lại data model sau khi undo
                gridManager.SwapGemsInData(pos1, pos2);

                // Shake cả 2 gem
                ShakeGem(gem1.Visual);
                ShakeGem(gem2.Visual);
            });

            // Khi shake xong, hoàn tất
            undoSequence.AppendInterval(undoShakeDuration + 0.05f);

            undoSequence.OnComplete(() =>
            {
                OnSwapComplete?.Invoke();
                isSwapping = false;
                SetInputEnabled(true);
            });
        }

        /// <summary>
        /// Hiệu ứng rung nhẹ (DOTween Shake).
        /// </summary>
        private void ShakeGem(GameObject gemObj)
        {
            if (gemObj == null) return;

            gemObj.transform.DOShakePosition(
                undoShakeDuration,
                undoShakeStrength,
                undoShakeVibrato,
                90f,
                false,
                true
            );
        }

        /// <summary>
        /// Kiểm tra xem swap vừa rồi có tạo match không.
        /// Sử dụng MatchSolver để kiểm tra.
        /// </summary>
        private bool CheckForMatch()
        {
            if (MatchSolver.Instance == null)
            {
                Debug.LogWarning("MatchSolver not found!");
                return false;
            }

            bool hasMatch = MatchSolver.Instance.DoesSwapCreateMatch(pos1, pos2);
            Debug.Log($"Swap checked: {pos1} <-> {pos2}, HasMatch: {hasMatch}");

            return hasMatch;
        }

        /// <summary>
        /// Bật/tắt input.
        /// </summary>
        private void SetInputEnabled(bool enabled)
        {
            // Có thể enable/disable InputController component
            if (InputController.Instance != null)
            {
                InputController.Instance.enabled = enabled;
            }
        }

        /// <summary>
        /// Kiểm tra có đang swap không.
        /// </summary>
        public bool IsSwapping => isSwapping;

        /// <summary>
        /// Force swap 2 gem (dùng cho debug hoặc skill).
        /// </summary>
        public void ForceSwap(Vector2Int source, Vector2Int target)
        {
            if (isSwapping) return;

            Gem g1 = gridManager.GetGemAt(source);
            Gem g2 = gridManager.GetGemAt(target);

            if (g1 == null || g2 == null) return;

            pos1 = source;
            pos2 = target;
            gem1 = g1;
            gem2 = g2;

            StartSwapAnimation();
        }
    }
}
