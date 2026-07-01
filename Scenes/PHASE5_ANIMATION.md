# Phase 5: Character Animation & State Machine - Complete Guide

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
│   │   ├── CombatManager.cs    # Combat logic + Turn System
│   │   ├── CharacterData.cs   # ScriptableObject cho Tướng/Quái
│   │   ├── CharacterAnimator.cs # State Machine cho character
│   │   ├── EnemyAI.cs         # AI Turn System
│   │   ├── SpecialGemEffect.cs # Logic effect từng loại Special
│   │   ├── SpecialGemVisual.cs # Animation đặc biệt
│   │   └── SkillVFX.cs       # Visual Effects
│   └── UI/
│       ├── DamagePopup.cs      # Damage popup với animation
│       └── UIManager.cs       # Quản lý UI
└── Scenes/
```

---

## CharacterAnimator.cs - State Machine

### States

```
┌─────────┐
│  Idle   │ ←──┐
└────┬────┘    │
     │          │
     ▼          │
┌─────────┐    │
│ Attack  │────┤
└────┬────┘    │
     │          │
     ▼          │
┌─────────┐    │
│  Hurt  │────┤
└────┬────┘    │
     │          │
     ▼          │
┌─────────┐    │
│   Die   │────┘
└─────────┘
     │
     ▼
┌─────────┐
│ Victory │
└─────────┘
```

### Animation Sequence

#### Idle
- Float up/down (sine wave)
- Scale bounce nhẹ

#### Attack
```
1. Di chuyển về phía trước (0.15s)
2. Scale bump (1.2x)
3. Spawn VFX
4. Di chuyển về vị trí ban đầu (0.15s)
5. Return to Idle
```

#### Hurt
```
1. DOShakePosition (0.3s)
2. Sprite flash đỏ (3 lần)
3. Return to Idle
```

#### Die
```
1. Fade out (1s)
2. Fall down
3. Scale down (0.5x)
4. Trigger OnDieComplete
```

---

## EnemyAI.cs - Turn System

### AI Flow

```
Player Turn End
     │
     ▼
┌──────────┐
│ Thinking │─── DOShake (1s)
└────┬─────┘
     │
     ▼
┌──────────┐     ┌──────────┐
│ 70% Roll │────►│ Perform  │
└────┬─────┘     │  Attack  │
     │           └──────────┘
     │ 30% Roll
     ▼
┌──────────┐     ┌─────────────────┐
│ 30% Roll │────►│ Perform Special │
└──────────┘     │  (2x damage)    │
                 └─────────────────┘
                       │
                       ▼
               ┌──────────────┐
               │  Turn End   │───► Player Turn
               └─────────────┘
```

### Attack Calculation

```csharp
int baseDamage = Random(minDamage, maxDamage);

// Critical chance
bool isCritical = Random.value < criticalChance; // 20%
finalDamage = isCritical ? baseDamage * 1.5 : baseDamage;

// Element bonus (ngũ hành tương khắc)
float counter = GetMultiplier(enemyElement, playerElement);
if (counter > 1f) {
    finalDamage *= counter;
    isCritical = true;
}
```

---

## SkillVFX.cs - Visual Effects

### VFX Types

| Type | Description |
|------|-------------|
| Element VFX | Particles cho từng ngũ hành |
| Attack Slash | Đường cắt bay đến enemy |
| Explosion | Ring explosion effect |
| Line Clear | Particles dọc theo đường thẳng |
| Cross Clear | X-shaped explosion |
| Color Bomb | Rainbow ring expansion |
| Critical Hit | "CRITICAL!" text + extra particles |

### Element Colors

| Element | Color |
|---------|-------|
| Fire | (1, 0.3, 0) - Đỏ cam |
| Water | (0.2, 0.5, 1) - Xanh dương |
| Metal | (1, 0.9, 0.2) - Vàng |
| Wood | (0.2, 0.8, 0.2) - Xanh lá |
| Earth | (0.6, 0.4, 0.2) - Nâu |

---

## Combat Flow hoàn chỉnh

### Player Turn
```
User Swap Gems
     │
     ▼
┌─────────────┐
│ Swap Valid? │─── No ──► Undo Animation
└──────┬──────┘
       │ Yes
       ▼
┌─────────────┐
│ MatchSolver │
│ Scan Grid   │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Matches    │
│   Found?    │─── No ──► End Player Turn
└──────┬──────┘
       │ Yes
       ▼
┌─────────────────┐
│ Process Matches  │
│ • Calculate DMG │
│ • Player Attack │
│ • Play VFX      │
│ • Enemy Hurt    │
│ • Show Popup    │
└──────┬──────────┘
       │
       ▼
┌─────────────┐
│ Drop Gems   │
│ Fill Empty  │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ Cascade     │◄──┐
│  Check?    │   │ Yes
└──────┬──────┘   │
       │ No       │
       ▼          │
┌─────────────┐   │
│ End Player  │───┘
│   Turn      │
└──────┬──────┘
       │
       ▼
```

### Enemy Turn
```
┌─────────────┐
│ Enemy Turn  │
│   Start     │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Thinking   │─── Shake animation
│   (1s)      │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ 70% Normal  │ 30% Special
│   Attack    │   Attack
└──────┬──────┘
       │
       ▼
┌─────────────────┐
│ • Calculate DMG │
│ • Enemy Attack  │
│ • Player Hurt   │
│ • Show Popup    │
└──────┬──────────┘
       │
       ▼
┌─────────────┐
│ End Enemy   │───► Player Turn
│   Turn      │
└─────────────┘
```

---

## Setup trong Unity

### Player Character
```
GameObject: "Player_Character"
├── SpriteRenderer (sprite)
├── CharacterAnimator.cs
│   ├── Is Player: ✓
│   ├── Element: Fire
│   └── Attack Duration: 0.5
└── Tag: "Player"
```

### Enemy Character
```
GameObject: "Enemy_Character"
├── SpriteRenderer (sprite)
├── CharacterAnimator.cs
│   ├── Is Player: ✗
│   ├── Element: Wood
│   └── Attack Duration: 0.5
├── EnemyAI.cs
│   ├── Thinking Delay: 1
│   ├── Min Damage: 20
│   ├── Max Damage: 50
│   └── Critical Chance: 0.2
└── Tag: "Enemy"
```

### SkillVFX
```
GameObject: "SkillVFX_Manager"
├── SkillVFX.cs
├── Fire VFX Prefab: [Kéo vào]
├── Water VFX Prefab: [Kéo vào]
├── Metal VFX Prefab: [Kéo vào]
├── Wood VFX Prefab: [Kéo vào]
├── Earth VFX Prefab: [Kéo vào]
└── Explosion VFX Prefab: [Kéo vào]
```

### CombatManager
```
GameObject: "CombatManager"
├── CombatManager.cs
├── Player Transform: [Kéo Player_Character]
├── Enemy Transform: [Kéo Enemy_Character]
├── Player Animator: [Kéo CharacterAnimator]
├── Enemy Animator: [Kéo CharacterAnimator]
└── Enemy AI: [Kéo EnemyAI component]
```

---

## Test Phase 5

### 1. Player Attack Animation
- Swap tạo Match-3 Fire
- **Console**: "Player casting: Hỏa Công"
- **Player**: Di chuyển → Scale bump → VFX → Return
- **Enemy**: Shake → Flash đỏ

### 2. Enemy Turn
- Sau khi cascade hoàn tất
- **Console**: "Enemy turn started"
- **Enemy**: Shake → Thinking
- **Enemy**: Attack hoặc Special
- **Player**: Nhận damage + Hurt animation

### 3. Critical Hit
- Enemy có 20% chance crit
- **Console**: "Enemy attacks: 45 damage (Critical!)"
- **VFX**: "CRITICAL!" text xuất hiện

### 4. Victory/Defeat
- Enemy HP = 0: Victory animation
- Player HP = 0: Die animation

---

## Tổng hợp Project

| Phase | Files | Status |
|-------|-------|--------|
| Phase 1 | GridManager, InputController, SwapController | ✅ |
| Phase 2 | MatchSolver (Scan, Destroy, Drop, Cascade) | ✅ |
| Phase 3 | SpecialGemEffect, SpecialGemVisual | ✅ |
| Phase 4 | CombatManager, ElementSystem, DamagePopup, UIManager | ✅ |
| Phase 5 | CharacterAnimator, EnemyAI, SkillVFX | ✅ |

---

## Toàn bộ Scripts đã viết

```
Scripts/
├── Models/
│   ├── GemType.cs           # GemType, SpecialType, GameState, SwipeDirection
│   ├── Gem.cs               # Gem model
│   └── ElementSystem.cs      # ElementType, ElementCounter, DamageInfo
├── Managers/
│   ├── GridManager.cs        # Grid 8x8, spawn, swap
│   └── MatchSolver.cs        # Scan, Process, Drop, Cascade
├── Controllers/
│   ├── InputController.cs    # Swipe detection
│   └── SwapController.cs     # Swap animation, Undo
├── Combat/
│   ├── CombatManager.cs      # Combat + Turn System
│   ├── CharacterData.cs     # ScriptableObject
│   ├── CharacterAnimator.cs # State Machine
│   ├── EnemyAI.cs          # Enemy Turn AI
│   ├── SpecialGemEffect.cs # Special Gem Logic
│   ├── SpecialGemVisual.cs # Special Gem Animation
│   └── SkillVFX.cs         # Visual Effects
└── UI/
    ├── DamagePopup.cs        # Damage popup
    └── UIManager.cs         # UI Manager
```

---

## Sẵn sàng cho Phase tiếp theo?

Game cơ bản đã hoàn thành với:
- ✅ Grid 8x8 với 5 Ngũ Hành
- ✅ Swap với Undo animation
- ✅ Match-3 detection + Cascade
- ✅ Special Gems (5 loại)
- ✅ Combat System với Ngũ Hành tương khắc
- ✅ Character Animation State Machine
- ✅ Enemy AI Turn System
- ✅ VFX Effects
- ✅ Damage Popup

**Có thể mở rộng thêm:**
- Level/Stage system
- Boss fights với unique patterns
- Power-ups/Items
- Combo system với energy meter
- Save/Load system
