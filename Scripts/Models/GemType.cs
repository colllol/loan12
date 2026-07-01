using UnityEngine;

namespace KyTran.Models
{
    /// <summary>
    /// Ngũ Hành Linh Thạch - 5 loại đá cơ bản theo ngũ hành tương sinh.
    /// </summary>
    public enum GemType
    {
        None = 0,   // Ô trống
        Metal = 1,  // Kim - Màu Vàng
        Wood = 2,   // Mộc - Màu Xanh lá
        Water = 3,  // Thủy - Màu Xanh dương
        Fire = 4,   // Hỏa - Màu Đỏ
        Earth = 5,  // Thổ - Màu Nâu
        Obstacle = 6, // Chướng ngại vật (Băng, Xiềng, Độc)
        Empty = 7   // Ô trống thực sự
    }

    /// <summary>
    /// Loại đá đặc biệt - Sinh ra khi Match 4/5.
    /// </summary>
    public enum SpecialType
    {
        None = 0,
        LineClear_H = 1,    // Hỏa Tiễn - Xóa hàng ngang
        LineClear_V = 2,    // Tên Súng - Xóa hàng dọc
        Bomb_3x3 = 3,       // Thuốc Súng - Nổ 3x3
        CrossClear = 4,     // Bẫy Chông - Nổ 4 hướng chéo
        ColorBomb = 5       // Ngũ Hành Trận - Xóa toàn bộ 1 màu
    }

    /// <summary>
    /// Trạng thái của Game Board.
    /// </summary>
    public enum GameState
    {
        Idle,           // Chờ user vuốt
        Swapping,       // Đang đổi chỗ 2 gem
        Resolving,      // Đang nổ, rơi gem, check match mới
        Animating,      // Đang chạy animation
        EnemyTurn,      // Lượt địch
        Win,
        Lose
    }

    /// <summary>
    /// Direction của Swipe input.
    /// </summary>
    public enum SwipeDirection
    {
        None,
        Up,
        Down,
        Left,
        Right
    }

    /// <summary>
    /// Loại Chướng Ngại Vật trên grid.
    /// </summary>
    public enum ObstacleType
    {
        None = 0,
        Ice = 1,       // Băng - Cần match 2 lần để phá
        Chain = 2,     // Xiềng - Cần match đặc biệt để phá
        Block = 3,     // Đá chắn - Không thể phá bằng match thường
        Cage = 4       // Lồng - Giữ gem bên trong
    }

    /// <summary>
    /// Cấp độ khó của enemy attack.
    /// </summary>
    public enum AttackPattern
    {
        Normal,      // Đánh thường
        Heavy,       // Đánh mạnh - 1.5x damage
        AOE,         // Đánh diện rộng - Damage tất cả gems
        Buff,        // Tăng attack của bản thân
        Debuff       // Giảm attack của player
    }

    /// <summary>
    /// Enemy difficulty tiers.
    /// </summary>
    public enum EnemyTier
    {
        Normal,      // Tier 1: 10% chance special attack
        Elite,       // Tier 2: 25% chance special attack
        Boss,        // Tier 3: 40% chance special attack, unique patterns
        FinalBoss    // Tier 4: 50% chance, multiple phases
    }
}
