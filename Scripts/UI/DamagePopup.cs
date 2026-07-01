using UnityEngine;
using UnityEngine.UI;
using DG.Tweening;
using TMPro;
using KyTran.Models;

namespace KyTran.UI
{
    /// <summary>
    /// DamagePopup - Hiện số damage bay lên với animation.
    /// Sử dụng Object Pooling để tránh tạo/destroy object liên tục.
    /// </summary>
    public class DamagePopup : MonoBehaviour
    {
        [Header("UI References")]
        [SerializeField] private TextMeshProUGUI damageText;
        [SerializeField] private Image backgroundImage;
        [SerializeField] private Image elementIcon;

        [Header("Animation Settings")]
        [SerializeField] private float flyDuration = 1.0f;
        [SerializeField] private float flyHeight = 2.0f;
        [SerializeField] private float fadeStartTime = 0.6f;
        [SerializeField] private float scaleUpAmount = 1.3f;

        [Header("Colors")]
        [SerializeField] private Color normalColor = Color.white;
        [SerializeField] private Color criticalColor = new Color(1f, 0.2f, 0.2f);  // Đỏ
        [SerializeField] private Color weakColor = new Color(0.5f, 0.5f, 1f);
        [SerializeField] private Color healColor = new Color(0.2f, 1f, 0.2f);

        private RectTransform rectTransform;
        private CanvasGroup canvasGroup;

        private void Awake()
        {
            rectTransform = GetComponent<RectTransform>();
            canvasGroup = GetComponent<CanvasGroup>();
            if (canvasGroup == null)
            {
                canvasGroup = gameObject.AddComponent<CanvasGroup>();
            }
        }

        /// <summary>
        /// Hiện popup với thông tin damage.
        /// </summary>
        public void Show(DamageInfo info)
        {
            // Set text
            string prefix = info.IsCritical ? "CRITICAL! " : "";
            if (info.Result == DamageResult.Weak)
            {
                prefix = "WEAK! ";
            }
            damageText.text = $"{prefix}{info.FinalDamage}";

            // Set color
            Color textColor = GetColorForResult(info.Result);
            damageText.color = textColor;

            // Set background color (lighter)
            if (backgroundImage != null)
            {
                Color bgColor = textColor;
                bgColor.a = 0.3f;
                backgroundImage.color = bgColor;
            }

            // Set position ban đầu tại target
            rectTransform.position = info.TargetPosition;

            // Reset transforms
            transform.localScale = Vector3.one;
            canvasGroup.alpha = 1f;

            // Play animation
            PlayFlyAnimation();
        }

        /// <summary>
        /// Hiện popup heal.
        /// </summary>
        public void ShowHeal(int healAmount, Vector3 position)
        {
            damageText.text = $"+{healAmount}";
            damageText.color = healColor;

            if (backgroundImage != null)
            {
                Color bgColor = healColor;
                bgColor.a = 0.3f;
                backgroundImage.color = bgColor;
            }

            rectTransform.position = position;
            transform.localScale = Vector3.one;
            canvasGroup.alpha = 1f;

            PlayFlyAnimation();
        }

        /// <summary>
        /// Animation bay lên với DOTween.
        /// </summary>
        private void PlayFlyAnimation()
        {
            // Reset any existing tweens
            rectTransform.DOKill();
            canvasGroup.DOKill();
            transform.DOKill();

            Sequence seq = DOTween.Sequence();

            // Phase 1: Scale up nhanh (pop effect)
            seq.Append(transform.DOScale(scaleUpAmount, 0.1f).SetEase(Ease.OutQuad));

            // Phase 2: Di chuyển lên trên
            Vector3 startPos = rectTransform.position;
            Vector3 endPos = startPos + Vector3.up * flyHeight;

            seq.Append(rectTransform.DOMove(endPos, flyDuration).SetEase(Ease.OutQuad));

            // Phase 3: Fade out
            seq.Insert(flyDuration * fadeStartTime,
                canvasGroup.DOFade(0f, flyDuration * (1f - fadeStartTime)).SetEase(Ease.InQuad));

            // Phase 4: Scale down khi fade
            seq.Insert(flyDuration * fadeStartTime,
                transform.DOScale(0.5f, flyDuration * (1f - fadeStartTime)).SetEase(Ease.InQuad));

            // Return to pool khi hoàn tất
            seq.OnComplete(() =>
            {
                ReturnToPool();
            });
        }

        /// <summary>
        /// Lấy màu dựa trên DamageResult.
        /// </summary>
        private Color GetColorForResult(DamageResult result)
        {
            switch (result)
            {
                case DamageResult.Super:
                    return criticalColor;
                case DamageResult.Weak:
                    return weakColor;
                case DamageResult.Resist:
                    return Color.gray;
                default:
                    return normalColor;
            }
        }

        /// <summary>
        /// Trả về pool (override trong subclass hoặc gọi từ pool manager).
        /// </summary>
        private void ReturnToPool()
        {
            // Nếu có DamagePopupPool, trả về pool
            if (DamagePopupPool.Instance != null)
            {
                DamagePopupPool.Instance.ReturnToPool(this);
            }
            else
            {
                Destroy(gameObject);
            }
        }
    }

    /// <summary>
    /// DamagePopupPool - Object Pooling cho DamagePopup.
    /// </summary>
    public class DamagePopupPool : MonoBehaviour
    {
        public static DamagePopupPool Instance { get; private set; }

        [Header("Pool Settings")]
        [SerializeField] private GameObject popupPrefab;
        [SerializeField] private int initialPoolSize = 10;
        [SerializeField] private Transform poolContainer;

        private Queue<DamagePopup> availablePopups = new Queue<DamagePopup>();
        private List<DamagePopup> activePopups = new List<DamagePopup>();

        private void Awake()
        {
            if (Instance == null)
            {
                Instance = this;
                InitializePool();
            }
            else
            {
                Destroy(gameObject);
            }
        }

        private void InitializePool()
        {
            if (poolContainer == null)
            {
                poolContainer = transform;
            }

            for (int i = 0; i < initialPoolSize; i++)
            {
                CreateNewPopup();
            }
        }

        private DamagePopup CreateNewPopup()
        {
            GameObject obj = Instantiate(popupPrefab, poolContainer);
            obj.SetActive(false);
            DamagePopup popup = obj.GetComponent<DamagePopup>();
            availablePopups.Enqueue(popup);
            return popup;
        }

        /// <summary>
        /// Lấy một popup từ pool và hiện damage.
        /// </summary>
        public void ShowDamage(DamageInfo info)
        {
            DamagePopup popup = GetFromPool();
            popup.Show(info);
            activePopups.Add(popup);
        }

        /// <summary>
        /// Lấy một popup từ pool và hiện heal.
        /// </summary>
        public void ShowHeal(int healAmount, Vector3 position)
        {
            DamagePopup popup = GetFromPool();
            popup.ShowHeal(healAmount, position);
            activePopups.Add(popup);
        }

        private DamagePopup GetFromPool()
        {
            if (availablePopups.Count == 0)
            {
                CreateNewPopup();
            }

            DamagePopup popup = availablePopups.Dequeue();
            popup.gameObject.SetActive(true);
            return popup;
        }

        /// <summary>
        /// Trả popup về pool.
        /// </summary>
        public void ReturnToPool(DamagePopup popup)
        {
            popup.gameObject.SetActive(false);
            activePopups.Remove(popup);
            availablePopups.Enqueue(popup);
        }
    }
}
