# Phase 3: Special Gems System - Complete Guide

## Cấu trúc thư mục đã tạo

```
loan12/
├── Scripts/
│   ├── Models/
│   │   ├── GemType.cs         # Enum: GemType, SpecialType, GameState
│   │   ├── Gem.cs             # Class: Gem model
│   │   └── ElementSystem.cs   # Ngũ Hành tương khắc
│   ├── Managers/
│   │   ├── GridManager.cs     # Quản lý grid 8x8
│   │   └── MatchSolver.cs     # Thuật toán Match-3 + Special Gem
│   ├── Controllers/
│   │   ├── InputController.cs  # Swipe/Click input
│   │   └── SwapController.cs   # Swap animation, Undo
│   ├── Combat/
│   │   ├── CombatManager.cs    # Combat logic, damage calculation
│   │   ├── CharacterData.cs   # ScriptableObject cho Tướng/Quái
│   │   ├── SpecialGemEffect.cs # Logic effect từng loại Special
│   │   └── SpecialGemVisual.cs # Animation đặc biệt
│   └── UI/
│       ├── DamagePopup.cs      # Damage popup với animation
│       └── UIManager.cs       # Quản lý UI
└── Scenes/
```

---

## Special Gem Types

### Bảng tổng hợp

| Special Type | Tên | Effect | Bonus Damage | Màu |
|--------------|------|--------|--------------|------|
| `LineClear_H` | Hỏa Tiễn | Xóa toàn hàng ngang | x2 | Cam |
| `LineClear_V` | Tên Súng | Xóa toàn cột dọc | x2 | Xanh dương nhạt |
| `Bomb_3x3` | Thuốc Súng | Nổ vùng 3x3 | x3 | Đỏ |
| `CrossClear` | Bẫy Chông | Nổ 4 đường chéo | x4 | Tím |
| `ColorBomb` | Ngũ Hành Trận | Xóa tất cả cùng màu | x5 | Vàng |

---

## SpecialGemEffect.cs

### Methods chính

```csharp
// Trigger effect và lấy danh sách vị trí bị ảnh hưởng
public static List<Vector2Int> TriggerEffect(Gem specialGem, GridManager grid)

// Tính bonus damage
public static int CalculateBonusDamage(SpecialType special, int baseDamage)

// Lấy tên hiển thị
public static string GetSpecialGemName(SpecialType special)
```

### Effect Logic

#### LineClear_H (Hỏa Tiễn)
```csharp
// Xóa tất cả gem trong hàng ngang chứa special gem
for (int x = 0; x < grid.Width; x++) {
    // Add (x, pos.y) vào affected list
}
```

#### LineClear_V (Tên Súng)
```csharp
// Xóa tất cả gem trong cột dọc chứa special gem
for (int y = 0; y < grid.Height; y++) {
    // Add (pos.x, y) vào affected list
}
```

#### Bomb_3x3 (Thuốc Súng)
```csharp
// Xóa gem trong vùng 3x3 tâm tại special gem
for (int dx = -1; dx <= 1; dx++) {
    for (int dy = -1; dy <= 1; dy++) {
        // Add (pos.x+dx, pos.y+dy) vào affected list
    }
}
```

#### CrossClear (Bẫy Chông)
```csharp
// Xóa gem theo 4 đường chéo từ vị trí special
Vector2Int[] diagonals = {
    (1, 1),   // NE
    (1, -1),  // SE
    (-1, -1), // SW
    (-1, 1)   // NW
};
// Đi theo mỗi hướng cho đến khi gặp obstacle hoặc edge
```

#### ColorBomb (Ngũ Hành Trận)
```csharp
// Xóa tất cả gem cùng màu với special gem
for (int x = 0; x < grid.Width; x++) {
    for (int y = 0; y < grid.Height; y++) {
        if (gem.Type == targetType) {
            // Add vào affected list
        }
    }
}
```

---

## SpecialGemVisual.cs

### Animation Effects

#### Idle Animation
- Float up/down với sine wave
- Rotate liên tục (30 độ/giây)
- Glow pulse (thay đổi màu nhẹ)

#### Trigger Animation
1. **Scale Up** (0.1s): 1.0 → 1.5
2. **Shake** (0.3s): Shake position
3. **Scale Down** (0.2s): 1.5 → 0.0

#### Special Animations

| Type | Animation |
|------|-----------|
| `LineClear_H/V` | Vẽ đường line kéo dài |
| `Bomb_3x3` | Ring explosion mở rộng |
| `CrossClear` | X shape explosion |
| `ColorBomb` | Rainbow color cycling |

---

## MatchSolver Updates

### Tạo Special Gem

```csharp
private Gem CreateSpecialGemAt(Vector2Int position, SpecialType specialType)
{
    // Chọn random base type
    GemType baseType = RandomGemType();
    
    // Tạo gem với special type
    Gem gem = new Gem(baseType, position);
    gem.Special = specialType;
    
    // Spawn visual với màu blend
    Color baseColor = gridManager.GetGemColor(baseType);
    Color specialColor = SpecialGemEffect.GetSpecialGemColor(specialType);
    spriteRenderer.color = Color.Lerp(baseColor, specialColor, 0.6f);
    
    // Animation xuất hiện
    gem.Visual.transform.localScale = Vector3.zero;
    gem.Visual.transform.DOScale(1f, 0.3f).SetEase(Ease.OutBack);
}
```

### Event mới

```csharp
public event Action<SpecialGemTriggerInfo> OnSpecialGemTriggered;
```

---

## CombatManager Integration

### Xử lý Special Gem Trigger

```csharp
// Trong MatchSolver.ProcessMatchesCoroutine:
// Khi match chứa special gem
if (gem.IsSpecial()) {
    HandleSpecialGemEffect(gem, ...);
}

// Bắn event cho CombatManager
OnSpecialGemTriggered?.Invoke(triggerInfo);
```

### Bonus Damage

```csharp
// Từ SpecialGemTriggerInfo
int bonusDamage = SpecialGemEffect.CalculateBonusDamage(
    specialGem.Special,
    baseDamage
);
```

---

## Tạo Special Gem Prefabs

### Prefab Structure

```
SpecialGem_Prefab
├── SpriteRenderer (Main sprite)
├── SpecialGemVisual.cs (Animation component)
└── BoxCollider2D (For raycast)
```

### Color Scheme

| Type | Sprite Color | Glow Color |
|------|--------------|------------|
| LineClear_H | (1, 0.5, 0) - Cam | (1, 0.5, 0) |
| LineClear_V | (0.5, 0.8, 1) - Xanh dương | (0.5, 0.8, 1) |
| Bomb_3x3 | (1, 0.2, 0.2) - Đỏ | (1, 0.2, 0.2) |
| CrossClear | (0.8, 0, 0.8) - Tím | (0.8, 0, 0.8) |
| ColorBomb | (1, 1, 0) - Vàng | (1, 1, 0) |

---

## Test Phase 3

### Tạo Match-4 Fire
1. Arrange gems để tạo 4 Fire liên tiếp ngang
2. Swap tạo Match-4
3. **Console**: "Hỏa Tiễn at (x, y): 8 gems affected"
4. **Grid**: Cả hàng bị xóa

### Tạo Match-5 Fire
1. Arrange gems để tạo 5 Fire liên tiếp
2. Swap tạo Match-5
3. **Console**: "Created Ngũ Hành Trận at (x, y)"
4. **Grid**: Special gem xuất hiện với animation

### Trigger Special Gem
1. Match special gem với 2 gem khác
2. **Console**: "Special gem triggered: Ngũ Hành Trận at (x, y)"
3. **Effect**: Tất cả gem cùng màu bị xóa
4. **Score**: Bonus damage x5

---

## Flow hoàn chỉnh

```
User Swap
    ↓
MatchSolver.ResolveCoroutine()
    ↓
ScanGrid() → Tìm matches
    ↓
ProcessMatchesCoroutine()
    ├── Match chứa Special Gem?
    │   └── HandleSpecialGemEffect()
    │       └── TriggerEffect() → Lấy affected positions
    │       └── OnSpecialGemTriggered() → CombatManager
    ├── DestroyMatchedGems() (bao gồm affected)
    ├── CreateSpecialGems() (tại center)
    └── OnScoreAdded() → Bonus damage
    ↓
DropGemsCoroutine()
    ↓
FillEmptySpacesCoroutine()
    ↓
ScanGrid() lại → Cascade?
    ↓
OnCascadeComplete()
```

---

## Sẵn sàng cho Phase 5?

**Phase 5: Character Animation & State Machine** sẽ bao gồm:
- Character animation triggers (Idle, Attack, Hurt, Die)
- Animation Controller setup
- Enemy AI Turn system
- Attack patterns và skill VFX
