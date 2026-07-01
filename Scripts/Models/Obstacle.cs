using UnityEngine;
using KyTran.Models;

namespace KyTran.Models
{
    /// <summary>
    /// Obstacle - Model cho chướng ngại vật trên grid.
    /// Các loại: Ice, Chain, Block, Cage.
    /// </summary>
    public class Obstacle
    {
        public ObstacleType Type { get; set; }
        public Vector2Int GridPosition { get; set; }
        public GameObject Visual { get; set; }

        // HP cho băng (Ice) - cần match nhiều lần
        private int currentHP;
        public int CurrentHP
        {
            get => currentHP;
            set
            {
                currentHP = value;
                if (currentHP <= 0)
                {
                    IsDestroyed = true;
                }
            }
        }
        public int MaxHP { get; set; }
        public bool IsDestroyed { get; private set; } = false;

        // Chain properties
        public SpecialType RequiredSpecial { get; set; } = SpecialType.None;
        public bool CanBeMatchedDirectly { get; set; } = false;

        // Cage properties
        public bool IsHoldingGem { get; set; } = false;
        public Gem TrappedGem { get; set; }

        public Obstacle(ObstacleType type, Vector2Int position)
        {
            Type = type;
            GridPosition = position;
            IsDestroyed = false;

            InitializeByType();
        }

        private void InitializeByType()
        {
            switch (Type)
            {
                case ObstacleType.Ice:
                    MaxHP = 2; // Cần match 2 lần để phá
                    CurrentHP = MaxHP;
                    CanBeMatchedDirectly = true;
                    break;

                case ObstacleType.Chain:
                    MaxHP = 1;
                    CurrentHP = MaxHP;
                    RequiredSpecial = SpecialType.Bomb_3x3; // Cần Bomb hoặc LineClear
                    CanBeMatchedDirectly = false;
                    break;

                case ObstacleType.Block:
                    MaxHP = 999; // Không thể phá bằng match
                    CurrentHP = MaxHP;
                    CanBeMatchedDirectly = false;
                    break;

                case ObstacleType.Cage:
                    MaxHP = 1;
                    CurrentHP = MaxHP;
                    CanBeMatchedDirectly = false;
                    break;
            }
        }

        /// <summary>
        /// Nhận damage từ match.
        /// </summary>
        public bool TakeDamage(int damage, SpecialType specialType = SpecialType.None)
        {
            if (IsDestroyed) return false;

            switch (Type)
            {
                case ObstacleType.Ice:
                    // Ice phá được bằng match thường
                    CurrentHP -= 1;
                    Debug.Log($"Ice hit! HP: {CurrentHP}/{MaxHP}");
                    return true;

                case ObstacleType.Chain:
                    // Chain cần special gem hoặc LineClear
                    if (specialType != SpecialType.None ||
                        specialType == SpecialType.LineClear_H ||
                        specialType == SpecialType.LineClear_V)
                    {
                        CurrentHP -= 1;
                        Debug.Log($"Chain destroyed with {specialType}!");
                        return true;
                    }
                    Debug.Log($"Chain requires special gem to destroy!");
                    return false;

                case ObstacleType.Block:
                    // Block không phá được
                    Debug.Log($"Block cannot be destroyed by matches!");
                    return false;

                case ObstacleType.Cage:
                    CurrentHP -= 1;
                    Debug.Log($"Cage opened!");
                    return true;

                default:
                    return false;
            }
        }

        /// <summary>
        /// Kiểm tra xem obstacle có ngăn cản gem di chuyển không.
        /// </summary>
        public bool BlocksMovement()
        {
            return !IsDestroyed && Type != ObstacleType.None;
        }

        /// <summary>
        /// Kiểm tra xem obstacle có ngăn cản match không.
        /// </summary>
        public bool BlocksMatch()
        {
            return !IsDestroyed && (Type == ObstacleType.Chain || Type == ObstacleType.Block);
        }

        /// <summary>
        /// Lấy màu hiển thị của obstacle.
        /// </summary>
        public Color GetDisplayColor()
        {
            switch (Type)
            {
                case ObstacleType.Ice:
                    return new Color(0.7f, 0.9f, 1f); // Xanh băng nhạt
                case ObstacleType.Chain:
                    return new Color(0.5f, 0.5f, 0.5f); // Xám
                case ObstacleType.Block:
                    return new Color(0.4f, 0.4f, 0.4f); // Đen xám
                case ObstacleType.Cage:
                    return new Color(0.8f, 0.6f, 0.2f); // Nâu vàng
                default:
                    return Color.white;
            }
        }

        /// <summary>
        /// Reset obstacle (không phá hủy).
        /// </summary>
        public void Reset()
        {
            CurrentHP = MaxHP;
            IsDestroyed = false;
        }
    }
}
