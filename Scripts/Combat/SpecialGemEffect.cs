using UnityEngine;
using KyTran.Models;
using KyTran.Managers;
using System.Collections.Generic;

namespace KyTran.Combat
{
    /// <summary>
    /// SpecialGemEffect - Xử lý logic effect của từng loại Special Gem.
    /// Mỗi loại special gem có effect riêng khi được trigger.
    /// </summary>
    public static class SpecialGemEffect
    {
        /// <summary>
        /// Trigger effect của special gem.
        /// Trả về danh sách vị trí cần xóa.
        /// </summary>
        public static List<Vector2Int> TriggerEffect(Gem specialGem, GridManager grid)
        {
            List<Vector2Int> affectedPositions = new List<Vector2Int>();

            switch (specialGem.Special)
            {
                case SpecialType.LineClear_H:
                    affectedPositions = GetLineClearHorizontal(specialGem.GridPosition, grid);
                    break;

                case SpecialType.LineClear_V:
                    affectedPositions = GetLineClearVertical(specialGem.GridPosition, grid);
                    break;

                case SpecialType.Bomb_3x3:
                    affectedPositions = GetBomb3x3(specialGem.GridPosition, grid);
                    break;

                case SpecialType.CrossClear:
                    affectedPositions = GetCrossClear(specialGem.GridPosition, grid);
                    break;

                case SpecialType.ColorBomb:
                    affectedPositions = GetColorBomb(specialGem.GridPosition, specialGem.Type, grid);
                    break;

                default:
                    Debug.LogWarning($"Unknown special type: {specialGem.Special}");
                    break;
            }

            return affectedPositions;
        }

        /// <summary>
        /// Hỏa Tiễn - Xóa toàn bộ hàng ngang.
        /// </summary>
        private static List<Vector2Int> GetLineClearHorizontal(Vector2Int pos, GridManager grid)
        {
            List<Vector2Int> positions = new List<Vector2Int>();

            for (int x = 0; x < grid.Width; x++)
            {
                Vector2Int checkPos = new Vector2Int(x, pos.y);
                Gem gem = grid.GetGemAt(checkPos);
                if (gem != null && gem.IsMovable())
                {
                    positions.Add(checkPos);
                }
            }

            Debug.Log($"LineClear_H triggered at row {pos.y}: {positions.Count} gems affected");
            return positions;
        }

        /// <summary>
        /// Tên Súng - Xóa toàn bộ hàng dọc.
        /// </summary>
        private static List<Vector2Int> GetLineClearVertical(Vector2Int pos, GridManager grid)
        {
            List<Vector2Int> positions = new List<Vector2Int>();

            for (int y = 0; y < grid.Height; y++)
            {
                Vector2Int checkPos = new Vector2Int(pos.x, y);
                Gem gem = grid.GetGemAt(checkPos);
                if (gem != null && gem.IsMovable())
                {
                    positions.Add(checkPos);
                }
            }

            Debug.Log($"LineClear_V triggered at column {pos.x}: {positions.Count} gems affected");
            return positions;
        }

        /// <summary>
        /// Thuốc Súng - Nổ diện tích 3x3.
        /// </summary>
        private static List<Vector2Int> GetBomb3x3(Vector2Int pos, GridManager grid)
        {
            List<Vector2Int> positions = new List<Vector2Int>();

            for (int dx = -1; dx <= 1; dx++)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    Vector2Int checkPos = new Vector2Int(pos.x + dx, pos.y + dy);
                    if (grid.IsValidPosition(checkPos.x, checkPos.y))
                    {
                        Gem gem = grid.GetGemAt(checkPos);
                        if (gem != null && gem.IsMovable())
                        {
                            positions.Add(checkPos);
                        }
                    }
                }
            }

            Debug.Log($"Bomb_3x3 triggered at {pos}: {positions.Count} gems affected");
            return positions;
        }

        /// <summary>
        /// Bẫy Chông - Nổ 4 hướng chéo (X shape).
        /// </summary>
        private static List<Vector2Int> GetCrossClear(Vector2Int pos, GridManager grid)
        {
            List<Vector2Int> positions = new List<Vector2Int>();

            // 4 directions: NE, SE, SW, NW
            Vector2Int[] diagonals = {
                new Vector2Int(1, 1),   // NE
                new Vector2Int(1, -1),  // SE
                new Vector2Int(-1, -1), // SW
                new Vector2Int(-1, 1)   // NW
            };

            foreach (Vector2Int dir in diagonals)
            {
                Vector2Int currentPos = pos;
                while (true)
                {
                    currentPos = new Vector2Int(currentPos.x + dir.x, currentPos.y + dir.y);

                    if (!grid.IsValidPosition(currentPos.x, currentPos.y))
                        break;

                    Gem gem = grid.GetGemAt(currentPos);
                    if (gem == null || !gem.IsMovable())
                        break;

                    positions.Add(currentPos);

                    // Giới hạn độ dài để tránh xóa cả bàn
                    if (positions.Count > 15) break;
                }
            }

            Debug.Log($"CrossClear triggered at {pos}: {positions.Count} gems affected");
            return positions;
        }

        /// <summary>
        /// Ngũ Hành Trận - Xóa toàn bộ gem cùng màu trên bàn.
        /// </summary>
        private static List<Vector2Int> GetColorBomb(Vector2Int pos, GemType targetType, GridManager grid)
        {
            List<Vector2Int> positions = new List<Vector2Int>();

            for (int x = 0; x < grid.Width; x++)
            {
                for (int y = 0; y < grid.Height; y++)
                {
                    Gem gem = grid.GetGemAt(x, y);
                    if (gem != null && gem.IsMovable() && gem.Type == targetType)
                    {
                        positions.Add(new Vector2Int(x, y));
                    }
                }
            }

            Debug.Log($"ColorBomb triggered for type {targetType}: {positions.Count} gems affected");
            return positions;
        }

        /// <summary>
        /// Tính damage bonus từ special gem effect.
        /// </summary>
        public static int CalculateBonusDamage(SpecialType special, int baseDamage)
        {
            switch (special)
            {
                case SpecialType.LineClear_H:
                case SpecialType.LineClear_V:
                    return baseDamage * 2; // Double damage

                case SpecialType.Bomb_3x3:
                    return baseDamage * 3; // Triple damage

                case SpecialType.CrossClear:
                    return baseDamage * 4; // Quad damage

                case SpecialType.ColorBomb:
                    return baseDamage * 5; // Massive damage

                default:
                    return baseDamage;
            }
        }

        /// <summary>
        /// Lấy tên hiển thị của special gem.
        /// </summary>
        public static string GetSpecialGemName(SpecialType special)
        {
            switch (special)
            {
                case SpecialType.LineClear_H: return "Hỏa Tiễn";
                case SpecialType.LineClear_V: return "Tên Súng";
                case SpecialType.Bomb_3x3: return "Thuốc Súng";
                case SpecialType.CrossClear: return "Bẫy Chông";
                case SpecialType.ColorBomb: return "Ngũ Hành Trận";
                default: return "Unknown";
            }
        }

        /// <summary>
        /// Lấy màu effect của special gem.
        /// </summary>
        public static Color GetSpecialGemColor(SpecialType special)
        {
            switch (special)
            {
                case SpecialType.LineClear_H:
                case SpecialType.LineClear_V:
                    return new Color(1f, 0.5f, 0f); // Cam

                case SpecialType.Bomb_3x3:
                    return new Color(1f, 0.2f, 0.2f); // Đỏ

                case SpecialType.CrossClear:
                    return new Color(0.8f, 0f, 0.8f); // Tím

                case SpecialType.ColorBomb:
                    return new Color(1f, 1f, 0f); // Vàng

                default:
                    return Color.white;
            }
        }
    }

    /// <summary>
    /// Special Gem Data - Lưu thông tin special gem để xử lý.
    /// </summary>
    public class SpecialGemTriggerInfo
    {
        public Gem Gem { get; set; }
        public List<Vector2Int> AffectedPositions { get; set; }
        public int BonusDamage { get; set; }
        public string EffectName { get; set; }

        public SpecialGemTriggerInfo()
        {
            AffectedPositions = new List<Vector2Int>();
        }
    }
}
