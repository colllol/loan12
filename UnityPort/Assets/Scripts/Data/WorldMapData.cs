using UnityEngine;

public static class WorldMapData
{
    public static readonly string[] LocationNames =
    {
        "Hoa Lư", "Kỳ Bố", "Bình Kiều", "Đằng Châu", "Đỗ Động",
        "Tế Giang", "Siêu Loại", "Tây Phù Liệt", "Đường Lâm", "Cổ Loa",
        "Tiên Du", "Tam Đái", "Phong Châu", "Hồi Hồ", "Bình Lục",
        "Chùa Bảo Thái", "Bảo Thái", "Trà Hương", "Ô Man", "Dương Xá",
        "Chùa Non Nước", "Tam Đảo", "Ái Châu", "Đông Lỗ", "Tam Điệp",
        "Bảo Đà", "Đông Phù Liệt"
    };

    public static readonly Vector2Int[] LocationPositions =
    {
        new Vector2Int(170, 295), new Vector2Int(303, 287), new Vector2Int(60, 338), new Vector2Int(313, 228),
        new Vector2Int(186, 229), new Vector2Int(243, 205), new Vector2Int(265, 160), new Vector2Int(178, 170),
        new Vector2Int(108, 183), new Vector2Int(207, 144), new Vector2Int(318, 107), new Vector2Int(148, 116),
        new Vector2Int(103, 76), new Vector2Int(29, 88), new Vector2Int(215, 250), new Vector2Int(56, 227),
        new Vector2Int(97, 233), new Vector2Int(352, 177), new Vector2Int(149, 237), new Vector2Int(304, 147),
        new Vector2Int(236, 87), new Vector2Int(225, 55), new Vector2Int(153, 347), new Vector2Int(86, 307),
        new Vector2Int(119, 274), new Vector2Int(179, 250), new Vector2Int(206, 177)
    };

    public static readonly int[][] Connections =
    {
        new[] { 7, 4, 5, 6 }, new[] { 10, 9, 9, -1 }, new[] { 1, -1, -1, 1 },
        new[] { 13, 11, 12, -1 }, new[] { 22, 21, -1, -1 }, new[] { -1, 14, -1, 14 },
        new[] { 28, 26, -1, 27 }, new[] { 40, 23, 38, 24 }, new[] { -1, 20, -1, 20 },
        new[] { 33, 40, 34, 33 }, new[] { -1, 29, 29, -1 }, new[] { 36, 39, 36, 35 },
        new[] { 37, 36, 37, 36 }, new[] { -1, -1, -1, -1 }, new[] { -1, -1, 15, -1 },
        new[] { -1, -1, -1, 18 }, new[] { 19, 41, 18, 17 }, new[] { -1, -1, 25, -1 },
        new[] { -1, 7, 17, 16 }, new[] { -1, -1, 27, -1 }, new[] { 32, 31, -1, -1 },
        new[] { -1, 32, -1, -1 }, new[] { -1, -1, 3, -1 }, new[] { -1, -1, -1, 2 },
        new[] { 42, -1, -1, 5 }, new[] { 21, -1, 16, 15 }, new[] { -1, -1, 24, -1 }
    };

    public static readonly int[][] EnemyLevels =
    {
        new[] { 30, 13, 80, 85 }, new[] { 2, 31, 5, 10 }, new[] { 31, 23, 4, 9 },
        new[] { 31, 22, 4, 9 }, new[] { 0, 31, 3, 8 }, new[] { 0, 24, 1, 6 },
        new[] { 0, 32, 1, 6 }, new[] { 0, 18, 7, 12 }, new[] { 32, 33, 3, 8 },
        new[] { 33, 1, 4, 9 }, new[] { 1, 34, 5, 10 }, new[] { 34, 3, 5, 10 },
        new[] { 3, 27, 15, 20 }, new[] { 3, 37, 30, 35 }, new[] { 27, 5, 17, 22 },
        new[] { 14, 25, 22, 27 }, new[] { 25, 18, 20, 25 }, new[] { 18, 16, 8, 14 },
        new[] { 16, 15, 10, 15 }, new[] { 16, 36, 17, 22 }, new[] { 36, 8, 18, 23 },
        new[] { 25, 4, 20, 25 }, new[] { 4, 35, 25, 30 }, new[] { 35, 7, 27, 32 },
        new[] { 7, 26, 29, 34 }, new[] { 37, 17, 32, 37 }, new[] { 37, 6, 35, 40 }
    };

    public static string GetLocationName(int idx)
    {
        if (idx >= 0 && idx < LocationNames.Length) return LocationNames[idx];
        return "Unknown";
    }

    public static Vector2Int GetLocationPos(int idx)
    {
        if (idx >= 0 && idx < LocationPositions.Length) return LocationPositions[idx];
        return Vector2Int.zero;
    }
}
