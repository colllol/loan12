using UnityEngine;

public static class GameConfig
{
    public const int VirtualWidth = 240;
    public const int VirtualHeight = 320;
    public const int BoardSize = 8;
    public const int EmptyPiece = -1;
    public const int MaxLevel = 36;
    public const int MaxHeroLevel = 99;
    public const int TargetFPS = 25;
    public const int GridCellSize = 21;
    public const int GridOffsetX = 36;
    public const int GridOffsetY = 62;
    public const int HudTopY = 54;

    public static readonly string[] PieceNames =
    {
        "sword", "rice", "heart", "yinyang", "gold", "book", "swordred"
    };

    public static readonly int[] PieceCount = { 0, 1, 2, 3, 4, 5, 6 };

    public static readonly string[] SkillNames =
    {
        "Quả Cầu Lửa", "Mưa Thiên Thạch", "Lửa Địa Ngục", "Chuỗi Sét", "Khiên Sét",
        "Sấm Sét", "Mũi Tên Băng", "Cam Lộ Thủy", "Băng Phong"
    };

    public static readonly int[] SkillCosts = { 10, 16, 22, 14, 18, 24, 12, 15, 20 };

    public static readonly string[] SkillDescriptions =
    {
        "Gây sát thương, phá hủy 3x3.",
        "Gây sát thương, phá hủy 2-5 vùng 2x2.",
        "Gây sát thương, phá hủy 2 vùng 4x4.",
        "Gây sát thương, phá hủy 4-8 ô.",
        "Hóa giải tấn công 6 lượt.",
        "Gây sát thương, phá hủy 3-6 vùng 3x3.",
        "Gây sát thương, giảm ATK địch.",
        "Hồi 20% máu, xóa tim.",
        "Đóng băng 2 lượt, giảm ATK."
    };

    public static readonly string[] ItemNames =
    {
        "Long Thần Kiếm", "Nhân Sâm", "Ngân Lượng", "Quỷ Diện Giáp", "Bình Thuốc", "Ngọc Ấn"
    };

    public static readonly string[] ItemDescriptions =
    {
        "Nhân đôi tấn công 1 lượt.",
        "Nhân đôi máu trong trận.",
        "Nhận 1000 vàng.",
        "Chặn 3/4 sát thương 1 lượt.",
        "Hồi 10% sinh lực.",
        "Ưu tiên đi trước."
    };

    public static readonly int[] ItemPrices = { 0, 0, 0, 1000, 250, 500 };
    public static readonly int[] ItemAmounts = { 3, 3, 1000, 3, 3, 3 };

    public static readonly string[] HeroNames =
    {
        "Hỏa Hổ", "Lôi Thần", "Thủy Long", "Hỏa Phụng", "Nữ Lôi", "Bắc Hải"
    };

    public static readonly string[] HeroDescriptions =
    {
        "Sức mạnh hủy diệt của Lửa.",
        "Điều khiển sấm sét.",
        "Máu thần, hồi phục và đóng băng.",
        "Sức mạnh hủy diệt của Lửa.",
        "Sấm sét và bảo vệ.",
        "Trị thương, đóng băng."
    };

    public static readonly int[] HeroBaseHealth = { 100, 95, 120, 100, 95, 120 };
    public static readonly int[] HeroBaseAttack = { 11, 9, 8, 12, 9, 8 };
    public static readonly int[] HeroBaseDefense = { 2, 2, 4, 2, 2, 4 };
    public static readonly int[] HeroBaseSkillPower = { 4, 5, 3, 4, 6, 4 };

    public static readonly string[] EnemyNames =
    {
        "Ngô Quyền", "Dương Tam Kha", "Kiều Công Hãn", "Kiều Thuận",
        "Đỗ Cảnh Thạc", "Nguyễn Khoan", "Nguyễn Thủ Tiệp", "Phạm Bạch Hổ",
        "Trần Lãm", "Lý Khuê", "Ngô Xương Xí", "Đinh Bộ Lĩnh"
    };

    public static readonly string[] BossNames =
    {
        "Thiên Tướng Hoa Lư", "Thần Long Hộ Vệ", "Lôi Kiếm Tiên Nhân",
        "Bắc Hải Long Vương", "Đinh Tiên Hoàng"
    };

    public static readonly string[] MenuItems =
    {
        "Tiếp tục", "Game mới", "Màn chơi", "Kỷ lục", "Cửa hàng",
        "Thông tin", "Tặng game", "Chiến trường", "Hướng dẫn", "Tác giả"
    };

    public static readonly Vector2[] CursorDirections = {
        Vector2.left, Vector2.right, Vector2.up, Vector2.down
    };

    public static Color IntToColor(int rgb)
    {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return new Color(r / 255f, g / 255f, b / 255f);
    }

    public static readonly Color ColorBorder = IntToColor(0x773311);
    public static readonly Color ColorPanel = IntToColor(0x773311);
    public static readonly Color ColorFocus = IntToColor(0xFFFF00);
}
