using UnityEngine;
using KyTran.Core;

namespace KyTran.Data
{
    #region Enums

    /// <summary>
    /// Ngũ Hành Element Type - 5 nguyên tố chính.
    /// </summary>
    public enum ElementType
    {
        None = 0,

        // Ngũ Hành - 5 nguyên tố
        Metal = 1,   // Kim - Màu Vàng
        Wood = 2,    // Mộc - Màu Xanh lá
        Water = 3,   // Thủy - Màu Xanh dương
        Fire = 4,    // Hỏa - Màu Đỏ
        Earth = 5,   // Thổ - Màu Nâu

        // Đặc biệt
        Light = 6,    // Quang - Màu Trắng
        Dark = 7      // Ám - Màu Đen
    }

    /// <summary>
    /// Loại Special Gem sinh ra khi Match.
    /// </summary>
    public enum SpecialGemType
    {
        None = 0,

        // Match-4
        LineHorizontal = 1,   // Hỏa Tiễn - Xóa hàng ngang
        LineVertical = 2,     // Tên Súng - Xóa hàng dọc

        // Match-T/L
        Bomb = 3,            // Thuốc Súng - Nổ 3x3
        Cross = 4,           // Bẫy Chông - Nổ 4 hướng

        // Match-5
        ColorBomb = 5,       // Ngũ Hành Trận - Xóa toàn bộ 1 màu
        Star = 6             // Ngũ Hành Tinh - Xóa tất cả
    }

    /// <summary>
    /// Tương khắc multiplier khi đánh.
    /// </summary>
    public enum ElementRelation
    {
        Neutral = 0,
        Counter = 1,   // Tương khắc - damage x1.5
        Weak = 2       // Tương sinh - damage x0.75
    }

    #endregion

    #region GemData ScriptableObject

    /// <summary>
    /// GemData - ScriptableObject cấu hình cho mỗi loại Linh Thạch.
    /// Tạo 5 assets cho 5 nguyên tố Ngũ Hành.
    /// </summary>
    [CreateAssetMenu(fileName = "GemData_NewGem", menuName = "KyTran/Gem Data")]
    public class GemData : ScriptableObject
    {
        #region Basic Info

        [Header("=== BASIC INFO ===")]
        [Tooltip("Tên hiển thị của gem")]
        public string gemName = "Gem";

        [Tooltip("Loại nguyên tố")]
        public ElementType elementType = ElementType.Metal;

        [Tooltip("Icon/Sprite hiển thị")]
        public Sprite icon;

        [Tooltip("Màu sắc chính của gem")]
        public Color gemColor = Color.yellow;

        #endregion

        #region Combat Stats

        [Header("=== COMBAT STATS ===")]
        [Tooltip("Damage cơ bản khi match gem này")]
        public int baseDamage = 10;

        [Tooltip("Mana tiêu tốn khi dùng gem này tấn công")]
        public int manaCost = 0;

        [Tooltip("Bonus damage khi tương khắc (Kim chặt Mộc)")]
        [Range(1f, 2f)]
        public float counterBonus = 1.5f;

        [Tooltip("Penalty khi tương sinh (Kim nuôi Mộc)")]
        [Range(0.5f, 1f)]
        public float weaknessPenalty = 0.75f;

        [Tooltip("Damage multiplier khi dùng Special Gem này tấn công")]
        [Range(1f, 5f)]
        public float specialDamageMultiplier = 1f;

        #endregion

        #region Prefabs

        [Header("=== PREFABS ===")]
        [Tooltip("Prefab cho gem thường")]
        public GameObject normalPrefab;

        [Tooltip("Prefab cho Match-4 (Line Clear)")]
        public GameObject match4Prefab;

        [Tooltip("Prefab cho Match-5 (Color Bomb)")]
        public GameObject match5Prefab;

        [Tooltip("Prefab cho Special Attack")]
        public GameObject specialPrefab;

        #endregion

        #region VFX

        [Header("=== VFX ===")]
        [Tooltip("Particle effect khi match thường")]
        public GameObject matchVFX;

        [Tooltip("Particle effect khi match special")]
        public GameObject specialVFX;

        [Tooltip("Âm thanh khi match")]
        public AudioClip matchSound;

        [Tooltip("Âm thanh khi tạo special gem")]
        public AudioClip specialSound;

        #endregion

        #region Editor Helpers

#if UNITY_EDITOR
        [Header("=== EDITOR ONLY ===")]
        [Tooltip("Preview màu trong Inspector")]
        [SerializeField] private bool showColorPreview = true;
#endif

        #endregion

        #region Methods

        /// <summary>
        /// Tính damage với element relation.
        /// </summary>
        public int CalculateDamage(ElementType targetElement, ElementRelation relation)
        {
            float multiplier = 1f;

            switch (relation)
            {
                case ElementRelation.Counter:
                    multiplier = counterBonus;
                    break;
                case ElementRelation.Weak:
                    multiplier = weaknessPenalty;
                    break;
            }

            return Mathf.RoundToInt(baseDamage * multiplier * specialDamageMultiplier);
        }

        /// <summary>
        /// Lấy tên hiển thị của element.
        /// </summary>
        public static string GetElementName(ElementType element)
        {
            switch (element)
            {
                case ElementType.Metal: return "Kim";
                case ElementType.Wood: return "Mộc";
                case ElementType.Water: return "Thủy";
                case ElementType.Fire: return "Hỏa";
                case ElementType.Earth: return "Thổ";
                case ElementType.Light: return "Quang";
                case ElementType.Dark: return "Ám";
                default: return "None";
            }
        }

        /// <summary>
        /// Lấy mô tả element.
        /// </summary>
        public static string GetElementDescription(ElementType element)
        {
            switch (element)
            {
                case ElementType.Metal: return "Kim - Sắt đao - Tương khắc Mộc";
                case ElementType.Wood: return "Mộc - Gai góc - Tương khắc Thổ";
                case ElementType.Water: return "Thủy - Sóng biển - Tương khắc Hỏa";
                case ElementType.Fire: return "Hỏa - Lửa thiêu - Tương khắc Kim";
                case ElementType.Earth: return "Thổ - Đất đai - Tương khắc Thủy";
                case ElementType.Light: return "Quang - Ánh sáng - Tương khắc Ám";
                case ElementType.Dark: return "Ám - Bóng tối - Tương khắc Quang";
                default: return "None";
            }
        }

        /// <summary>
        /// Tạo GemData mới với giá trị mặc định.
        /// </summary>
        public static GemData CreateDefault(ElementType element)
        {
            GemData data = CreateInstance<GemData>();
            data.elementType = element;
            data.gemName = GetElementName(element);
            data.gemColor = GetElementColor(element);
            data.baseDamage = GetBaseDamage(element);

            // Tự đặt tên theo element
            data.name = $"GemData_{element}";

            return data;
        }

        /// <summary>
        /// Lấy màu theo element.
        /// </summary>
        public static Color GetElementColor(ElementType element)
        {
            switch (element)
            {
                case ElementType.Metal: return new Color(1f, 0.84f, 0f);      // Vàng
                case ElementType.Wood: return new Color(0.2f, 0.8f, 0.2f);   // Xanh lá
                case ElementType.Water: return new Color(0.2f, 0.6f, 1f);     // Xanh dương
                case ElementType.Fire: return new Color(1f, 0.2f, 0.2f);      // Đỏ
                case ElementType.Earth: return new Color(0.6f, 0.4f, 0.2f);  // Nâu
                case ElementType.Light: return new Color(1f, 1f, 0.9f);       // Trắng
                case ElementType.Dark: return new Color(0.2f, 0.2f, 0.3f);     // Đen
                default: return Color.gray;
            }
        }

        /// <summary>
        /// Lấy base damage theo element.
        /// </summary>
        public static int GetBaseDamage(ElementType element)
        {
            switch (element)
            {
                case ElementType.Metal: return 12;
                case ElementType.Wood: return 10;
                case ElementType.Water: return 11;
                case ElementType.Fire: return 15;
                case ElementType.Earth: return 8;
                case ElementType.Light: return 13;
                case ElementType.Dark: return 14;
                default: return 10;
            }
        }

        #endregion
    }

    #endregion

    #region Element Counter (Tương Khắc)

    /// <summary>
    /// Hệ thống Tương Khắc Ngũ Hành.
    /// Kim → Mộc → Thổ → Thủy → Hỏa → Kim
    /// </summary>
    public static class ElementCounter
    {
        // Tương khắc: Key tương khắc Value
        // Kim khắc Mộc, Mộc khắc Thổ, Thổ khắc Thủy, Thủy khắc Hỏa, Hỏa khắc Kim
        private static readonly Dictionary<ElementType, ElementType> CounterRelations = new Dictionary<ElementType, ElementType>
        {
            { ElementType.Metal, ElementType.Wood },   // Kim khắc Mộc
            { ElementType.Wood, ElementType.Earth },  // Mộc khắc Thổ
            { ElementType.Earth, ElementType.Water },  // Thổ khắc Thủy
            { ElementType.Water, ElementType.Fire },  // Thủy khắc Hỏa
            { ElementType.Fire, ElementType.Metal },  // Hỏa khắc Kim
            { ElementType.Light, ElementType.Dark },  // Quang khắc Ám
            { ElementType.Dark, ElementType.Light }   // Ám khắc Quang
        };

        // Tương sinh: Key nuôi Value
        // Kim nuôi Thổ, Thổ nuôi Hỏa, Hỏa nuôi Mộc, Mộc nuôi Thủy, Thủy nuôi Kim
        private static readonly Dictionary<ElementType, ElementType> BoostRelations = new Dictionary<ElementType, ElementType>
        {
            { ElementType.Metal, ElementType.Earth },  // Kim nuôi Thổ
            { ElementType.Earth, ElementType.Fire },  // Thổ nuôi Hỏa
            { ElementType.Fire, ElementType.Wood },   // Hỏa nuôi Mộc
            { ElementType.Wood, ElementType.Water }, // Mộc nuôi Thủy
            { ElementType.Water, ElementType.Metal } // Thủy nuôi Kim
        };

        /// <summary>
        /// Kiểm tra xem attacker có tương khắc target không.
        /// </summary>
        public static bool IsCounter(ElementType attacker, ElementType target)
        {
            if (attacker == ElementType.None || target == ElementType.None)
                return false;

            return CounterRelations.TryGetValue(attacker, out ElementType countered) && countered == target;
        }

        /// <summary>
        /// Kiểm tra xem attacker có tương sinh target không.
        /// </summary>
        public static bool IsBoost(ElementType attacker, ElementType target)
        {
            if (attacker == ElementType.None || target == ElementType.None)
                return false;

            return BoostRelations.TryGetValue(attacker, out ElementType boosted) && boosted == target;
        }

        /// <summary>
        /// Lấy multiplier dựa trên relation.
        /// </summary>
        public static float GetMultiplier(ElementType attacker, ElementType target, float counterBonus = 1.5f, float boostPenalty = 0.75f)
        {
            if (IsCounter(attacker, target))
                return counterBonus;

            if (IsBoost(attacker, target))
                return boostPenalty;

            return 1f;
        }

        /// <summary>
        /// Lấy relation giữa 2 elements.
        /// </summary>
        public static ElementRelation GetRelation(ElementType attacker, ElementType target)
        {
            if (IsCounter(attacker, target))
                return ElementRelation.Counter;

            if (IsBoost(attacker, target))
                return ElementRelation.Weak;

            return ElementRelation.Neutral;
        }

        /// <summary>
        /// Lấy mô tả relation.
        /// </summary>
        public static string GetRelationDescription(ElementType attacker, ElementType target)
        {
            var relation = GetRelation(attacker, target);

            switch (relation)
            {
                case ElementRelation.Counter:
                    return "TƯƠNG KHẮC! Damage x1.5";
                case ElementRelation.Weak:
                    return "TƯƠNG SINH! Damage x0.75";
                default:
                    return "Bình thường";
            }
        }
    }

    #endregion
}
