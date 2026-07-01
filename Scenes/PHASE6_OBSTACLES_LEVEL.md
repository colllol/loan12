# Phase 6: Enemy AI, Obstacles & Level Editor - Complete Guide

## Cấu trúc thư mục đã tạo

```
loan12/
├── Scripts/
│   ├── Models/
│   │   ├── GemType.cs         # Thêm: ObstacleType, AttackPattern, EnemyTier
│   │   ├── Gem.cs            # Gem model
│   │   ├── ElementSystem.cs   # Ngũ Hành tương khắc
│   │   └── Obstacle.cs       # Obstacle model (Ice, Chain, Block, Cage)
│   ├── Data/
│   │   └── LevelData.cs      # ScriptableObject cho Level & Level Pack
│   ├── Managers/
│   │   ├── GridManager.cs    # Cập nhật: Obstacle management
│   │   ├── MatchSolver.cs    # Cập nhật: Obstacle handling
│   │   └── LevelManager.cs   # Level loading & progression
│   ├── Combat/
│   │   ├── CombatManager.cs
│   │   ├── CharacterAnimator.cs
│   │   ├── EnemyAI.cs        # Cập nhật: Attack patterns & Boss phases
│   │   ├── SpecialGemEffect.cs
│   │   ├── SpecialGemVisual.cs
│   │   └── SkillVFX.cs
│   ├── Editor/
│   │   └── LevelEditorWindow.cs # Editor window cho tạo level
│   └── UI/
│       ├── DamagePopup.cs
│       └── UIManager.cs
└── Scenes/
```

---

## Obstacle System

### Obstacle Types

| Type | Màu | HP | Phá bằng | Mô tả |
|------|------|-----|----------|--------|
| **Ice** | Xanh băng nhạt | 2 | Match thường | Cần match 2 lần để phá |
| **Chain** | Xám | 1 | Special Gem (Bomb/Line) | Cần LineClear hoặc Bomb |
| **Block** | Đen xám | ∞ | Không phá được | Chướng ngại vật cố định |
| **Cage** | Nâu vàng | 1 | Match bất kỳ | Giữ gem bên trong |

### Obstacle Model

```csharp
public class Obstacle
{
    public ObstacleType Type { get; set; }
    public Vector2Int GridPosition { get; set; }
    public int CurrentHP { get; set; }
    public int MaxHP { get; set; }
    public bool IsDestroyed { get; private set; }
    
    // Ice: phá bằng match thường
    // Chain: cần special gem
    // Block: không phá được
    // Cage: mở bằng match
}
```

### GridManager Obstacle API

```csharp
// Thêm obstacle
gridManager.AddObstacle(ObstacleType.Ice, new Vector2Int(3, 4));

// Kiểm tra obstacle
gridManager.HasObstacleAt(pos);

// Damage obstacle (trả về true nếu bị phá)
gridManager.DamageObstacleAt(pos, SpecialType.LineClear_H);

// Xóa obstacle
gridManager.RemoveObstacleAt(pos);

// Load từ level data
gridManager.LoadObstaclesFromData(levelData.obstacles);
```

---

## Enemy AI System

### Enemy Tiers

| Tier | Special Chance | Mô tả |
|------|---------------|--------|
| **Normal** | 10% | Enemy thường |
| **Elite** | 25% | Enemy精英 |
| **Boss** | 40% | Boss với phase |
| **FinalBoss** | 50% | Boss cuối game |

### Attack Patterns

| Pattern | Damage | Effect |
|---------|--------|--------|
| **Normal** | 100% | Đánh thường |
| **Heavy** | 150% | Đánh mạnh, luôn crit visual |
| **AOE** | 75% | Đánh player + phá gems trên grid |
| **Debuff** | 50% | Đánh ít + giảm player defense |
| **Buff** | 0% | Heal 10% HP + tăng damage 25% |

### Boss System

```csharp
[Header("Boss Settings")]
[SerializeField] private bool isBoss = false;
[SerializeField] private int bossPhaseThreshold = 50; // HP% để vào phase 2
[SerializeField] private float enrageMultiplier = 1.5f;

// Boss Phase 2:
// - +20% chance dùng special attack
// - Thêm AOE attack pattern
// - Damage multiplier x1.5
```

### AI Flow

```
Enemy Turn Start
     │
     ▼
┌──────────────┐
│ Check Boss   │── HP ≤ 50%? ──► Phase 2 (Enrage)
│   Phase      │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│ Choose       │── Roll < SpecialChance?
│  Pattern     │
└──────┬───────┘
       │
       ▼
┌──────────────────────────────────────┐
│ Pattern:                              │
│ ├─ Normal (60%) → PerformNormalAttack │
│ ├─ Heavy (20%) → PerformHeavyAttack  │
│ ├─ AOE (10%)  → PerformAOEAttack     │
│ ├─ Debuff (5%) → PerformDebuffAttack │
│ └─ Buff (5%)  → PerformBuffAttack    │
└──────────────────────────────────────┘
       │
       ▼
    Turn End
```

---

## Level Editor

### Mở Editor

```
Window → KyTran → Level Editor
```

### Tính năng

| Tính năng | Mô tả |
|-----------|--------|
| Grid Preview | Xem trước obstacles trên grid 8x8 |
| Click to Place | Click để thêm obstacle |
| Obstacle Types | Ice, Chain, Block, Cage |
| Place Mode | Toggle chế độ đặt liên tục |
| Enemy Waves | Thêm nhiều enemies |
| Level Settings | Objective, target score, difficulty |

### Tạo Level Mới

1. Click **New Level**
2. Chọn vị trí lưu
3. Đặt obstacles bằng cách click vào grid
4. Cấu hình enemy waves
5. Click **Save**

### Level Settings

```csharp
[Header("Level Info")]
levelNumber = 1
levelName = "Level 1"

[Header("Objective")]
objective = ObjectiveType.DefeatEnemy
targetScore = 1000
targetMoves = 30

[Header("Difficulty")]
difficulty = Difficulty.Normal
scoreMultiplier = 1.0f
enemyDamageMultiplier = 1.0f
```

---

## Level Data

### ScriptableObject Structure

```csharp
[CreateAssetMenu(fileName = "NewLevel", menuName = "KyTran/Level Data")]
public class LevelData : ScriptableObject
{
    // Level Info
    public int levelNumber;
    public string levelName;
    
    // Grid
    public int gridWidth = 8;
    public int gridHeight = 8;
    
    // Objectives
    public ObjectiveType objective;  // DefeatEnemy, ScoreTarget, Survive, etc.
    public int targetScore;
    public int targetMoves;
    
    // Obstacles
    public ObstacleData[] obstacles;
    
    // Enemies
    public EnemyWave[] enemyWaves;
    
    // Rewards
    public int goldReward;
    public int experienceReward;
}
```

### ObstacleData

```csharp
[System.Serializable]
public class ObstacleData
{
    public int X;
    public int Y;
    public ObstacleType Type;
    public int HP;
}
```

### EnemyWave

```csharp
[System.Serializable]
public class EnemyWave
{
    public string enemyId;
    public EnemyTier tier;
    public int healthMultiplier;
    public int attackMultiplier;
    public float spawnDelay;
    public bool isBoss;
}
```

---

## Level Manager

### Load Level

```csharp
// Load từ ScriptableObject
levelManager.LoadLevel(levelData);

// Load từ Resources
levelManager.LoadLevel(1); // Level_1.asset
```

### Level Progression

```csharp
// Save khi hoàn thành
PlayerPrefs.SetInt($"Level_{levelNumber}_Completed", 1);
PlayerPrefs.SetInt($"Level_{levelNumber}_Stars", stars);

// Unlock next level
PlayerPrefs.SetInt($"Level_{levelNumber + 1}_Unlocked", 1);
```

### Star Calculation

| Stars | Điều kiện |
|-------|-----------|
| ⭐ | Hoàn thành level |
| ⭐⭐ | Đạt target score |
| ⭐⭐⭐ | Đạt target score + ≤50% moves |

---

## MatchSolver Obstacle Integration

### DestroyMatchedGems với Obstacles

```csharp
private void DestroyMatchedGems(HashSet<Vector2Int> positions)
{
    foreach (Vector2Int pos in positions)
    {
        Gem gem = gridManager.GetGemAt(pos);
        if (gem != null)
        {
            gridManager.RemoveGemAt(pos);
        }
        
        // Xử lý obstacle tại vị trí này
        ProcessObstacleAtPosition(pos);
    }
}

private void ProcessObstacleAtPosition(Vector2Int pos)
{
    Obstacle obstacle = gridManager.GetObstacleAt(pos);
    if (obstacle == null || obstacle.IsDestroyed) return;
    
    if (obstacle.Type == ObstacleType.Block)
    {
        Debug.Log("Block cannot be destroyed!");
        return;
    }
    
    gridManager.DamageObstacleAt(pos);
}
```

### Special Gem vs Obstacle

```csharp
private void ProcessObstacleAffectedBySpecial(Vector2Int pos, SpecialType specialType)
{
    Obstacle obstacle = gridManager.GetObstacleAt(pos);
    
    // Chain cần special gem
    if (obstacle.Type == ObstacleType.Chain)
    {
        if (specialType == SpecialType.Bomb_3x3 ||
            specialType == SpecialType.LineClear_H ||
            specialType == SpecialType.LineClear_V)
        {
            gridManager.DamageObstacleAt(pos, specialType);
        }
    }
    // Ice phá bằng bất kỳ special nào
    else if (obstacle.Type == ObstacleType.Ice)
    {
        gridManager.DamageObstacleAt(pos);
    }
}
```

---

## Tổng hợp Project

| Phase | Status | Scripts |
|-------|--------|---------|
| Phase 1 | ✅ | GridManager, InputController, SwapController |
| Phase 2 | ✅ | MatchSolver (Scan, Destroy, Drop, Cascade) |
| Phase 3 | ✅ | SpecialGemEffect, SpecialGemVisual |
| Phase 4 | ✅ | CombatManager, ElementSystem, DamagePopup, UIManager |
| Phase 5 | ✅ | CharacterAnimator, EnemyAI, SkillVFX |
| Phase 6 | ✅ | Obstacle, LevelData, LevelEditor, LevelManager |

### Tổng cộng: **22 Scripts**

```
Scripts/
├── Models/          (4 files)
├── Data/            (1 file)
├── Managers/        (3 files)
├── Controllers/     (2 files)
├── Combat/          (7 files)
├── Editor/          (1 file)
└── UI/              (2 files)
```

---

## Tiếp theo có thể làm

- **Phase 7**: Power-ups, Boosters & Items
- **Phase 8**: Save/Load System & Player Profile
- **Phase 9**: Sound Effects & Music
- **Phase 10**: Tutorial & Onboarding
