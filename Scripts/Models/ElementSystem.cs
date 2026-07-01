using UnityEngine;
using KyTran.Models;

namespace KyTran.Models
{
    /// <summary>
    /// ElementType - Map từ GemType sang Element để tính damage và hiệu ứng.
    /// Kim (Metal) → Mộc (Wood) → Hỏa (Fire) → Thổ (Earth) → Thủy (Water) → Kim (Metal)
    /// Tương khắc: Kim khắc Mộc, Mộc khắc Thổ, Thổ khắc Thủy, Thủy khắc Hỏa, Hỏa khắc Kim.
    /// </summary>
    public enum ElementType
    {
        None = 0,
        Metal = 1,   // Kim - Thắng: Wood(Mộc), Thua: Fire(Hỏa)
        Wood = 2,    // Mộc - Thắng: Earth(Thổ), Thua: Metal(Kim)
        Fire = 3,    // Hỏa - Thắng: Metal(Kim), Thua: Water(Thủy)
        Earth = 4,   // Thổ - Thắng: Water(Thủy), Thua: Wood(Mộc)
        Water = 5    // Thủy - Thắng: Fire(Hỏa), Thua: Earth(Thổ)
    }

    /// <summary>
    /// Loại kết quả khi tính damage theo Ngũ Hành.
    /// </summary>
    public enum DamageResult
    {
        Normal,      // Damage thường x1.0
        Super,       // Tương khắc x1.5 (Critical)
        Weak,        // Tương sinh bị khắc x0.5
        Resist       // Immune x0
    }

    /// <summary>
    /// Data chứa thông tin damage để gửi cho UI.
    /// </summary>
    public struct DamageInfo
    {
        public int BaseDamage;           // Damage gốc từ match
        public int FinalDamage;          // Damage sau khi tính tương khắc
        public ElementType AttackerElement;  // Hệ của đòn đánh
        public ElementType DefenderElement;  // Hệ của mục tiêu
        public DamageResult Result;      // Loại kết quả
        public bool IsCritical;          // Có phải Critical không
        public Vector3 TargetPosition;   // Vị trí hiện damage popup
        public string SkillName;         // Tên skill (nếu có)

        public DamageInfo(int baseDamage, ElementType attacker, ElementType defender, Vector3 targetPos)
        {
            BaseDamage = baseDamage;
            AttackerElement = attacker;
            DefenderElement = defender;
            TargetPosition = targetPos;
            SkillName = "";
            Result = DamageResult.Normal;
            FinalDamage = baseDamage;
            IsCritical = false;
        }
    }

    /// <summary>
    /// Static class chứa logic Ngũ Hành tương khắc.
    /// </summary>
    public static class ElementCounter
    {
        /// <summary>
        /// Bảng tương khắc: Key = Attacker, Value = What it beats.
        /// Kim(K) khắc Mộc(M), Mộc(M) khắc Thổ(E), Thổ(E) khắc Thủy(W), Thủy(W) khắc Hỏa(F), Hỏa(F) khắc Kim(K)
        /// </summary>
        private static readonly Dictionary<ElementType, ElementType> CounterChart = new Dictionary<ElementType, ElementType>
        {
            { ElementType.Metal, ElementType.Wood },    // Kim khắc Mộc
            { ElementType.Wood, ElementType.Earth },    // Mộc khắc Thổ
            { ElementType.Fire, ElementType.Metal },    // Hỏa khắc Kim
            { ElementType.Earth, ElementType.Water },   // Thổ khắc Thủy
            { ElementType.Water, ElementType.Fire }      // Thủy khắc Hỏa
        };

        /// <summary>
        /// Chuyển đổi GemType sang ElementType.
        /// </summary>
        public static ElementType GemToElement(GemType gemType)
        {
            switch (gemType)
            {
                case GemType.Metal: return ElementType.Metal;
                case GemType.Wood: return ElementType.Wood;
                case GemType.Fire: return ElementType.Fire;
                case GemType.Earth: return ElementType.Earth;
                case GemType.Water: return ElementType.Water;
                default: return ElementType.None;
            }
        }

        /// <summary>
        /// Kiểm tra xem attacker có khắc defender không.
        /// </summary>
        public static bool IsCounter(ElementType attacker, ElementType defender)
        {
            if (attacker == ElementType.None || defender == ElementType.None)
                return false;

            return CounterChart.ContainsKey(attacker) && CounterChart[attacker] == defender;
        }

        /// <summary>
        /// Kiểm tra xem attacker có bị defender khắc không.
        /// </summary>
        public static bool IsWeak(ElementType attacker, ElementType defender)
        {
            if (attacker == ElementType.None || defender == ElementType.None)
                return false;

            return CounterChart.ContainsKey(defender) && CounterChart[defender] == attacker;
        }

        /// <summary>
        /// Lấy multiplier dựa trên tương khắc.
        /// </summary>
        public static float GetMultiplier(ElementType attacker, ElementType defender)
        {
            if (IsCounter(attacker, defender))
            {
                return 1.5f; // Tương khắc - Critical
            }
            else if (IsWeak(attacker, defender))
            {
                return 0.5f; // Bị khắc - Weak
            }
            return 1.0f; // Normal
        }

        /// <summary>
        /// Lấy DamageResult dựa trên tương khắc.
        /// </summary>
        public static DamageResult GetDamageResult(ElementType attacker, ElementType defender)
        {
            if (IsCounter(attacker, defender))
            {
                return DamageResult.Super;
            }
            else if (IsWeak(attacker, defender))
            {
                return DamageResult.Weak;
            }
            return DamageResult.Normal;
        }

        /// <summary>
        /// Lấy màu tương ứng với DamageResult cho UI.
        /// </summary>
        public static Color GetDamageColor(DamageResult result)
        {
            switch (result)
            {
                case DamageResult.Super:
                    return new Color(1f, 0.2f, 0.2f);   // Đỏ - Critical
                case DamageResult.Weak:
                    return new Color(0.5f, 0.5f, 1f);   // Xanh dương nhạt - Weak
                case DamageResult.Resist:
                    return new Color(0.5f, 0.5f, 0.5f); // Xám - Resist
                default:
                    return Color.white;                  // Trắng - Normal
            }
        }

        /// <summary>
        /// Lấy tên hiển thị cho Element.
        /// </summary>
        public static string GetElementName(ElementType element)
        {
            switch (element)
            {
                case ElementType.Metal: return "Kim";
                case ElementType.Wood: return "Mộc";
                case ElementType.Fire: return "Hỏa";
                case ElementType.Earth: return "Thổ";
                case ElementType.Water: return "Thủy";
                default: return "None";
            }
        }
    }
}
