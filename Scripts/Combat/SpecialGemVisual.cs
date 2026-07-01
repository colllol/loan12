using UnityEngine;
using DG.Tweening;
using KyTran.Models;

namespace KyTran.Combat
{
    /// <summary>
    /// SpecialGemVisual - Component để hiển thị animation đặc biệt cho Special Gems.
    /// Attach vào mỗi Special Gem GameObject.
    /// </summary>
    [RequireComponent(typeof(SpriteRenderer))]
    public class SpecialGemVisual : MonoBehaviour
    {
        [Header("Special Gem Settings")]
        [SerializeField] private SpecialType specialType = SpecialType.None;

        [Header("Idle Animation")]
        [SerializeField] private bool enableIdleAnimation = true;
        [SerializeField] private float idleFloatHeight = 0.1f;
        [SerializeField] private float idleFloatDuration = 1f;
        [SerializeField] private float idleRotateSpeed = 30f;

        [Header("Glow Effect")]
        [SerializeField] private bool enableGlow = true;
        [SerializeField] private Color glowColor = new Color(1f, 0.5f, 0f);
        [SerializeField] private float glowIntensity = 1.5f;
        [SerializeField] private float glowPulseSpeed = 2f;

        [Header("Line Effect (for LineClear)")]
        [SerializeField] private LineRenderer linePrefab;
        [SerializeField] private float lineWidth = 0.1f;

        private SpriteRenderer spriteRenderer;
        private Tween idleFloatTween;
        private Tween glowTween;
        private Color originalColor;
        private Color originalEmission;

        public SpecialType SpecialType => specialType;

        private void Awake()
        {
            spriteRenderer = GetComponent<SpriteRenderer>();
            originalColor = spriteRenderer.color;
        }

        private void Start()
        {
            SetupVisuals();
            StartIdleAnimation();
            if (enableGlow)
            {
                StartGlowEffect();
            }
        }

        private void OnDestroy()
        {
            StopAllAnimations();
        }

        /// <summary>
        /// Thiết lập visuals dựa trên loại special.
        /// </summary>
        private void SetupVisuals()
        {
            switch (specialType)
            {
                case SpecialType.LineClear_H:
                    spriteRenderer.color = new Color(1f, 0.5f, 0f); // Cam - Hỏa Tiễn
                    break;

                case SpecialType.LineClear_V:
                    spriteRenderer.color = new Color(0.5f, 0.8f, 1f); // Xanh dương nhạt - Tên Súng
                    break;

                case SpecialType.Bomb_3x3:
                    spriteRenderer.color = new Color(1f, 0.2f, 0.2f); // Đỏ - Thuốc Súng
                    break;

                case SpecialType.CrossClear:
                    spriteRenderer.color = new Color(0.8f, 0f, 0.8f); // Tím - Bẫy Chông
                    break;

                case SpecialType.ColorBomb:
                    spriteRenderer.color = new Color(1f, 1f, 0f); // Vàng - Ngũ Hành Trận
                    break;
            }

            // Thêm icon hoặc effect đặc biệt tùy theo loại
            AddSpecialEffect();
        }

        /// <summary>
        /// Thêm effect đặc biệt (particle, icon, etc).
        /// </summary>
        private void AddSpecialEffect()
        {
            // TODO: Thêm particle system hoặc icon tùy theo loại special
            // Ví dụ: thêm child object với icon tương ứng
        }

        /// <summary>
        /// Bắt đầu animation idle (float + rotate).
        /// </summary>
        private void StartIdleAnimation()
        {
            if (!enableIdleAnimation) return;

            // Float up and down
            idleFloatTween = transform.DOLocalMoveY(idleFloatHeight, idleFloatDuration)
                .SetEase(Ease.InOutSine)
                .SetLoops(-1, LoopType.Yoyo);

            // Rotate
            transform.DOLocalRotate(new Vector3(0, 0, 360), idleRotateSpeed, RotateMode.FastBeyond360)
                .SetEase(Ease.Linear)
                .SetLoops(-1, LoopType.Restart);
        }

        /// <summary>
        /// Bắt đầu hiệu ứng glow/pulse.
        /// </summary>
        private void StartGlowEffect()
        {
            if (spriteRenderer == null) return;

            // Pulsing glow
            glowTween = DOTween.To(
                () => spriteRenderer.color,
                x => spriteRenderer.color = x,
                glowColor,
                glowPulseSpeed
            ).SetEase(Ease.InOutSine).SetLoops(-1, LoopType.Yoyo);
        }

        /// <summary>
        /// Dừng tất cả animations.
        /// </summary>
        public void StopAllAnimations()
        {
            idleFloatTween?.Kill();
            glowTween?.Kill();
            DOTween.Kill(transform);
        }

        /// <summary>
        /// Animation khi special gem được trigger.
        /// </summary>
        public void PlayTriggerAnimation(System.Action onComplete)
        {
            StopAllAnimations();

            Sequence seq = DOTween.Sequence();

            // Phase 1: Scale up nhanh
            seq.Append(transform.DOScale(1.5f, 0.1f).SetEase(Ease.OutQuad));

            // Phase 2: Shake
            seq.Append(transform.DOShakePosition(0.3f, 0.2f, 10, 90f, false, true));

            // Phase 3: Scale down và fade
            seq.Append(transform.DOScale(0f, 0.2f).SetEase(Ease.InQuad));

            seq.OnComplete(() => onComplete?.Invoke());
        }

        /// <summary>
        /// Animation cho LineClear - vẽ đường line khi trigger.
        /// </summary>
        public void PlayLineClearAnimation(bool horizontal, GridManager grid, System.Action onComplete)
        {
            Vector2Int pos = Vector2Int.zero;
            Gem gem = GetComponent<Gem>();
            if (gem != null)
            {
                pos = gem.GridPosition;
            }

            // Tạo line renderer
            LineRenderer line = Instantiate(linePrefab, transform.parent);
            line.startWidth = lineWidth;
            line.endWidth = lineWidth;
            line.material = new Material(Shader.Find("Sprites/Default"));
            line.startColor = glowColor;
            line.endColor = glowColor;

            Vector3 startPos = grid.GridToWorldPosition(pos.x, pos.y);
            Vector3 endPos;

            if (horizontal)
            {
                endPos = grid.GridToWorldPosition(grid.Width - 1, pos.y);
            }
            else
            {
                endPos = grid.GridToWorldPosition(pos.x, grid.Height - 1);
            }

            line.SetPosition(0, startPos);
            line.SetPosition(1, endPos);

            // Animate line
            Sequence seq = DOTween.Sequence();
            seq.Append(line.DOColor(new Color(1, 1, 1, 0), 0.5f).SetEase(Ease.InQuad));
            seq.OnComplete(() =>
            {
                Destroy(line.gameObject);
                onComplete?.Invoke();
            });
        }

        /// <summary>
        /// Animation cho Bomb - explosion effect.
        /// </summary>
        public void PlayBombAnimation(System.Action onComplete)
        {
            // Tạo ring effect
            GameObject ring = GameObject.CreatePrimitive(PrimitiveType.Ring);
            ring.transform.position = transform.position;
            ring.transform.localScale = Vector3.zero;

            SpriteRenderer ringSR = ring.GetComponent<SpriteRenderer>();
            if (ringSR != null)
            {
                ringSR.color = new Color(1f, 0.2f, 0.2f, 0.8f);
            }

            // Animate expansion
            ring.transform.DOScale(5f, 0.3f).SetEase(Ease.OutQuad);
            ringSR.DOFade(0f, 0.3f).SetEase(Ease.InQuad).OnComplete(() =>
            {
                Destroy(ring);
                onComplete?.Invoke();
            });
        }

        /// <summary>
        /// Animation cho ColorBomb - rainbow pulse.
        /// </summary>
        public void PlayColorBombAnimation(System.Action onComplete)
        {
            Sequence seq = DOTween.Sequence();

            Color[] rainbowColors = {
                Color.red, Color.yellow, Color.green, Color.cyan, Color.blue, Color.magenta
            };

            foreach (Color color in rainbowColors)
            {
                seq.Append(spriteRenderer.DOColor(color, 0.1f).SetEase(Ease.Linear));
            }

            seq.Append(transform.DOScale(0f, 0.2f));
            seq.OnComplete(() => onComplete?.Invoke());
        }
    }
}
