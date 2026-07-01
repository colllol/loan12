# Phase 1 & 2: Grid Setup & Match System - Complete Guide

## Cấu trúc thư mục đã tạo

```
loan12/
├── Scripts/
│   ├── Models/
│   │   ├── GemType.cs         # Enum: GemType, SpecialType, GameState, SwipeDirection
│   │   └── Gem.cs             # Class: Gem model
│   ├── Managers/
│   │   ├── GridManager.cs     # Quản lý grid 8x8, spawn gems
│   │   └── MatchSolver.cs     # Thuật toán quét Match-3, Cascade
│   └── Controllers/
│       ├── InputController.cs  # Xử lý Swipe (Mobile) & Click (PC)
│       └── SwapController.cs   # Animation swap, Undo logic, Cascade trigger
├── Prefabs/
│   └── (Gem prefabs sẽ đặt ở đây)
└── Scenes/
```

## Hướng dẫn Setup Scene trong Unity

### 1. Import DOTween
- Asset Store: **DOTween** (free)
- Nếu chưa có, tải và import vào project

### 2. Tạo Gem Prefabs

Tạo 5 prefab cho 5 loại Linh Thạch (hoặc dùng 1 prefab duy nhất với SpriteRenderer thay đổi màu):

```
Prefabs/
├── Gem_Metal.prefab    (màu vàng)
├── Gem_Wood.prefab     (màu xanh lá)
├── Gem_Water.prefab    (màu xanh dương)
├── Gem_Fire.prefab     (màu đỏ)
└── Gem_Earth.prefab    (màu nâu)
```

**Mỗi Prefab cần có:**
- SpriteRenderer component
- BoxCollider2D (để raycast)
- **Layer:** Đặt layer mới "Gem" và gán cho tất cả prefabs

### 3. Setup Scene

```
Main Camera (Orthographic)
    └── Canvas
    │   └── GridContainer (Transform rỗng)
    │
    └── GameManager (Empty GameObject)
        ├── GridManager.cs
        ├── InputController.cs
        └── SwapController.cs
```

**Chi tiết từng bước:**

#### A. Main Camera
- Projection: **Orthographic**
- Size: **5** (hoặc điều chỉnh theo grid)
- Position: **(0, 0, -10)**

#### B. GridContainer
- Position: **(0, 0, 0)**
- Scale: **(1, 1, 1)**

#### C. GameManager (Empty GameObject)
- Position: **(0, 0, 0)**
- Add Component: **GridManager**
- Add Component: **InputController**
- Add Component: **SwapController**

#### D. Cấu hình GridManager
```
Grid Width: 8
Grid Height: 8
Gem Size: 1
Grid Spacing: 0.1
Grid Origin: (-3.5, -3.5)
Gem Prefabs: [Kéo 5 prefab vào đây]
Grid Container: [Kéo GridContainer vào đây]
```

#### E. Cấu hình InputController
```
Swipe Threshold: 30 (pixel)
Click Threshold: 0.5
Main Camera: [Kéo Main Camera vào]
Gem Layer Mask: [Chọn layer "Gem"]
```

#### F. Cấu hình SwapController
```
Swap Duration: 0.25
Undo Shake Duration: 0.15
Undo Shake Strength: 0.1
Grid Manager: [Auto-reference từ scene]
```

### 4. Tạo Layer "Gem"

1. **Edit > Project Settings > Tags and Layers**
2. Thêm Layer mới: **"Gem"**
3. Gán layer "Gem" cho tất cả gem prefabs

### 5. Physics2D Settings

**Edit > Project Settings > Physics 2D:**
- Có thể để mặc định

### 6. Build Settings

- Platform: **Android** hoặc **iOS** (Mobile) / **PC** (Editor test)
- Resolution: Portrait (9:16) cho mobile

## Test Phase 1

### Trong Unity Editor:
1. Nhấn **Play**
2. Grid 8x8 sẽ spawn với 5 màu ngẫu nhiên
3. **Click chuột** vào 1 gem rồi kéo sang gem bên cạnh → Swap
4. Nếu swap **không tạo Match-3** → Undo animation + shake
5. Nếu swap **tạo Match-3** → Tiếp tục (Phase 2 sẽ xử lý)

### Debug:
- Mở Console để xem Debug.Log từ SwapController
- GridManager có method `DebugPrintGrid()` để in ra trạng thái grid

---

# Phase 2: Match Detection & Cascade System

## MatchSolver.cs - Thuật toán quét Match-3

### MatchInfo Class
```csharp
public class MatchInfo
{
    public List<Vector2Int> Positions { get; set; }  // Danh sách vị trí các gem trong match
    public GemType Type { get; set; }                // Loại gem (Kim/Mộc/Thủy/Hỏa/Thổ)
    public int Count { get; set; }                   // Số lượng gem trong match
    public bool IsMatch4 { get; set; }              // Match-4 tạo Special gem
    public bool IsMatch5 { get; set; }              // Match-5 tạo Thần Binh
    public Vector2Int CenterPosition { get; set; }  // Vị trí tạo Special gem
}
```

### Thuật toán quét (O(N²))

1. **Quét từng ô** trong grid 8x8
2. **Tìm match ngang**: Đếm các viên liên tiếp cùng loại theo hàng
3. **Tìm match dọc**: Đếm các viên liên tiếp cùng loại theo cột
4. **Loại bỏ overlaps**: Giữ lại match dài nhất nếu chồng chéo
5. **Ưu tiên**: Match-5 > Match-4 > Match-3

### Cascade Flow

```
Swap thành công
       ↓
ScanGrid() → Tìm tất cả matches
       ↓
ProcessMatches() → Destroy gems + Score + Tạo Special
       ↓
DropGems() → Gravity (gem rơi xuống)
       ↓
FillEmptySpaces() → Tạo gem mới từ trên
       ↓
ScanGrid() lại → Kiểm tra combo mới
       ↓
(Lặp lại cho đến khi không còn match)
```

### Special Gem Creation

| Match Type | Special Gem Created |
|------------|---------------------|
| Match-4 Horizontal | LineClear_H (Hỏa Tiễn) |
| Match-4 Vertical | LineClear_V (Tên Súng) |
| Match-5 | ColorBomb (Ngũ Hành Trận) |

### Score Calculation

```csharp
int baseScore = match.Count * 10;  // 10 điểm mỗi viên
int lengthBonus = match.Count == 4 ? 20 : (match.Count >= 5 ? 50 : 0);
int cascadeMultiplier = currentCascadeLevel;  // Combo càng nhiều, điểm càng cao
totalScore = (baseScore + lengthBonus) * cascadeMultiplier;
```

### Events

| Event | Mô tả |
|-------|-------|
| `OnMatchesFound` | Bắn khi tìm thấy matches |
| `OnScoreAdded` | Bắn khi cộng điểm |
| `OnCascadeComplete` | Bắn khi cascade hoàn tất |

## Cập nhật Scene

### Thêm MatchSolver vào GameManager

1. Chọn **GameManager** GameObject
2. Add Component: **MatchSolver**
3. Cấu hình:
```
Min Match Count: 3
Drop Duration: 0.3
Drop Interval: 0.05
Destroy Delay: 0.1
Grid Manager: [Auto-reference]
```

## Test Phase 2

1. **Play game**
2. **Swap 2 gem cùng loại** tạo Match-3
3. **Kiểm tra Console**:
   - "Swap checked: HasMatch: True"
   - "Score added: 30, Cascade level: 1"
   - "Cascade completed at level 1"
4. **Tạo combo**: Swap sao cho gems rơi xuống tạo match mới
   - "Cascade level: 2, 3, 4..."

## Các Class đã viết

### MatchSolver.cs
- `ScanGrid()` - Quét toàn bộ grid tìm matches
- `ProcessMatchesCoroutine()` - Destroy, score, special gem
- `DropGemsCoroutine()` - Gravity animation với DOTween
- `FillEmptySpacesCoroutine()` - Tạo gem mới từ trên
- `DoesSwapCreateMatch()` - Kiểm tra swap có tạo match không

### SwapController.cs (Updated)
- Kết nối với MatchSolver
- Trigger cascade khi swap thành công
- Event `OnAllMatchesResolved` cho Combat System

## Sẵn sàng cho Phase 3?

**Phase 3: Special Gems System** sẽ bao gồm:
- `LineClear_H`: Xóa toàn bộ hàng ngang
- `LineClear_V`: Xóa toàn bộ hàng dọc
- `Bomb_3x3`: Nổ diện rộng 3x3
- `CrossClear`: Nổ 4 hướng chéo
- `ColorBomb`: Xóa toàn bộ 1 màu

---

## Các Class đã viết

### GemType.cs
- `GemType`: None, Metal, Wood, Water, Fire, Earth, Obstacle, Empty
- `SpecialType`: None, LineClear_H, LineClear_V, Bomb_3x3, CrossClear, ColorBomb
- `GameState`: Idle, Swapping, Resolving, Animating, EnemyTurn, Win, Lose
- `SwipeDirection`: None, Up, Down, Left, Right

### Gem.cs
- Properties: Type, Special, GridPosition, Visual, IsMatched, IsMoving
- Methods: MarkAsMatched(), Reset(), IsSpecial(), IsMovable()

### GridManager.cs
- Singleton pattern
- API chính:
  - `GetGemAt(x, y)` - Lấy gem tại vị trí
  - `GridToWorldPosition(x, y)` - Convert grid → world
  - `WorldToGridPosition(world)` - Convert world → grid
  - `SwapGemsInData(pos1, pos2)` - Swap data (không animate)
  - `IsAdjacent(pos1, pos2)` - Kiểm tra 2 ô kề nhau

### InputController.cs
- Singleton pattern
- Auto-detect: Mobile (Touch) vs PC (Mouse)
- Events:
  - `OnSwipeAttempt(sourcePos, targetPos)` - Khi user swipe thành công

### SwapController.cs
- Singleton pattern
- Subscribe `InputController.OnSwipeAttempt`
- Logic:
  1. Animate swap 2 gem (DOTween)
  2. Check match (placeholder)
  3. Nếu **không match** → Undo + Shake
  4. Nếu **match** → Bắn event (Phase 2 xử lý)

## Phase 2 sắp tới

- **MatchSolver.cs**: Thuật toán quét Match-3 (O(N²) hoặc DFS)
- **Cascade System**: Gravity, Fill new gems, Recursive check
- **Special Gem Creation**: Match-4/5 tạo Special gems
