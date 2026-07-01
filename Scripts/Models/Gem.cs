using UnityEngine;
using KyTran.Models;

namespace KyTran.Models
{
    /// <summary>
    /// Model đại diện cho một viên Linh Thạch trên bàn cờ.
    /// </summary>
    public class Gem
    {
        public GemType Type { get; set; }
        public SpecialType Special { get; set; }
        public Vector2Int GridPosition { get; set; }
        public GameObject Visual { get; set; }  // Reference đến GameObject prefab
        public bool IsMatched { get; set; }
        public bool IsMoving { get; set; }

        public Gem(GemType type, Vector2Int position)
        {
            Type = type;
            Special = SpecialType.None;
            GridPosition = position;
            IsMatched = false;
            IsMoving = false;
        }

        /// <summary>
        /// Đánh dấu viên đá đã được match và cần xử lý.
        /// </summary>
        public void MarkAsMatched()
        {
            IsMatched = true;
        }

        /// <summary>
        /// Reset trạng thái sau khi resolve xong.
        /// </summary>
        public void Reset()
        {
            IsMatched = false;
            IsMoving = false;
        }

        /// <summary>
        /// Kiểm tra viên đá có phải là loại đặc biệt không.
        /// </summary>
        public bool IsSpecial()
        {
            return Special != SpecialType.None;
        }

        /// <summary>
        /// Kiểm tra viên đá có thể di chuyển được không (không phải Obstacle/Empty).
        /// </summary>
        public bool IsMovable()
        {
            return Type != GemType.Empty && Type != GemType.Obstacle;
        }

        public override string ToString()
        {
            return $"Gem[{GridPosition}] Type={Type}, Special={Special}";
        }
    }
}
