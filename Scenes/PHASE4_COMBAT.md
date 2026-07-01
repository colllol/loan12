# Phase 4: Combat System - Complete Guide

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
│   │   └── MatchSolver.cs     # Thuật toán Match-3
│   ├── Controllers/
│   │   ├── InputController.cs  # Swipe/Click input
│   │   └── SwapController.cs   # Swap animation, Undo
│   ├── Combat/
│   │   ├── CombatManager.cs    # Combat logic, damage calculation
│   │   └── CharacterData.cs   # ScriptableObject cho Tướng/Quái
│   └── UI/
│       ├── DamagePopup.cs      # Damage popup với animation
│       └── UIManager.cs       # Quản lý UI
└── Scenes/
```

---

## Ngũ Hành Tương Khắc

### Bảng Tương Khắc

```
Kim (Metal) → Mộc (Wood) → Thổ (Earth) → Thủy (Water) → Hỏa (Fire) → Kim (Metal)

Tương khắc:
- Kim khắc Mộc     → x1.5 Critical
- Mộc khắc Thổ     → x1.5 Critical
- Thổ khắc Thủy    → x1.5 Critical
- Thủy khắc Hỏa    → x1.5 Critical
- Hỏa khắc Kim     → x1.5 Critical

Bị khắc:
- Mộc bị Kim khắc  → x0.5 Weak
- Thổ bị Mộc khắc  → x0.5 Weak
- Thủy bị Thổ khắc → x0.5 Weak
- Hỏa bị Thủy khắc → x0.5 Weak
- Kim bị Hỏa khắc  → x0.5 Weak
```

### Màu sắc Damage Popup

| Result | Màu | Giá trị |
|--------|-----|---------|
| Normal | Trắng | x1.0 |
| Critical (Tương khắc) | Đỏ | x1.5 |
| Weak (Bị khắc) | Xanh dương nhạt | x0.5 |
| Resist | Xám | x0 |

---

## CombatManager.cs

### Tính năng

1. **Lắng nghe MatchSolver Events**
   - `OnMatchesFound` → Xử lý từng match

2. **CalculateDamage()**
   - Base damage = `gemCount × baseDamagePerGem`
   - Match-4: x2 damage
   - Match-5: x3 damage
   - Ngũ Hành multiplier

3. **Skill Trigger**
   - Match-3: `{Element} Công` (VD: "Hỏa Công")
   - Match-4: `Tứ Tượng Kiếm ({Element})`
   - Match-5: `Ngũ Hành Trận ({Element})`

### Events

```csharp
OnDamagePopup      // Bắn DamageInfo để UI hiện popup
OnPlayerHealthChanged
OnEnemyHealthChanged
OnCharacterDied
OnCombatEnd
```

---

## DamagePopup System

### Animation Sequence

1. **Pop Effect** (0.1s): Scale 1.0 → 1.3
2. **Fly Up** (1.0s): Di chuyển lên + fade out
3. **Fade & Shrink** (0.4s): Alpha 1 → 0, Scale 1.3 → 0.5

### Object Pooling

- Pool size ban đầu: 10 popups
- Tự động expand nếu cần
- Tái sử dụng để tránh GC

---

## Cập nhật Scene Setup

### Thêm Combat Components

1. **GameManager** (đã có GridManager, MatchSolver)
   - Add Component: **CombatManager**
   ```
   Base Damage Per Gem: 10
   Damage Multiplier: 1.0
   Critical Multiplier: 1.5
   ```

2. **Tạo Player GameObject**
   - Sprite/Model cho Tướng
   - Position: trên bàn cờ (VD: y = 4)
   - Add Component: **CharacterDisplay.cs** (tùy chọn)

3. **Tạo Enemy GameObject**
   - Sprite/Model cho Quái
   - Position: đối diện player (VD: y = 6)

4. **Tạo UI Canvas**
   - Canvas (Screen Space - Overlay)
   - **Health Bar Player**: Slider + Text
   - **Health Bar Enemy**: Slider + Text
   - **Score Text**: TextMeshPro
   - **Damage Popup Container**: Empty GameObject
   - **Victory Panel**: Canvas Group
   - **Defeat Panel**: Canvas Group

5. **Tạo DamagePopup Prefab**
   ```
   GameObject (DamagePopup)
   ├── RectTransform
   ├── CanvasGroup
   ├── Image (Background) - tùy chọn
   └── TextMeshProUGUI (Damage Text)
   ```

### Cấu hình UIManager

1. Add Component: **UIManager**
2. Kéo các UI elements vào inspector
3. Damage Popup Pool:
   - Popup Prefab: [DamagePopup prefab]
   - Initial Pool Size: 10

### Cấu hình CombatManager

1. Kéo Player Transform vào `Player Transform`
2. Kéo Enemy Transform vào `Enemy Transform`
3. (Tùy chọn) Tạo CharacterData ScriptableObject

---

## Tạo CharacterData

1. **Project Window** → Right Click
2. **Create → KyTran → Character Data**
3. Đặt tên: `Player_Fire`, `Enemy_Wood`, etc.
4. Cấu hình:
   ```
   Character Name: Đinh Bộ Lĩnh
   Element: Fire
   Max Health: 1000
   Attack: 150
   Defense: 50
   Character Color: Red
   ```

---

## Test Phase 4

### Trong Unity Editor:

1. **Play game**
2. **Swap tạo Match-3 Fire** (đỏ)
   - Console: "Hỏa Công" skill triggered
   - Enemy nhận damage
   - Damage popup bay lên màu trắng

3. **Swap tạo Match-3 Fire vs Wood Enemy**
   - Console: "CRITICAL!" (Hỏa khắc Kim → Kim khắc Mộc? Sai!)
   - Cần Enemy có Element = Metal để Hỏa khắc
   - Damage popup màu **Đỏ** với text "CRITICAL!"

4. **Swap tạo Match-4 Fire**
   - Console: "Tứ Tượng Kiếm (Hỏa)"
   - Damage x2

5. **Swap tạo Match-5 Fire**
   - Console: "Ngũ Hành Trận (Hỏa)"
   - Damage x3

---

## Combat Flow

```
User Swaps Gems
       ↓
Swap Successful
       ↓
MatchSolver.ScanGrid()
       ↓
MatchSolver.ProcessMatches()
       ↓
[MatchSolver.OnMatchesFound] → CombatManager.HandleMatchesFound()
       ↓
For Each Match:
  ├── CalculateDamage() → Element Counter
  ├── TriggerPlayerSkill() → Animation
  ├── DealDamageToEnemy()
  └── ShowDamagePopup() → UIManager
       ↓
Enemy HP = 0?
  ├── Yes → Victory Panel
  └── No → Continue
```

---

## Sẵn sàng cho Phase 5?

**Phase 5: Character Animation & State Machine** sẽ bao gồm:
- Character animation triggers (Idle, Attack, Hurt, Die)
- Animation Controller setup
- Enemy AI Turn system
- Attack patterns và skill effects
