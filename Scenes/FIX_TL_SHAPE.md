# Fix: T/L Shape Detection - Match Solver

## Vấn đề

Khi grid có **T-shape** hoặc **L-shape** (2 cụm Match-3 giao nhau), logic cũ chỉ giữ lại một match và loại bỏ match còn lại, dẫn đến:
- Miss matches
- Không tạo được Special Gem ở vị trí tối ưu
- Sai sót trong tính điểm

## Ví dụ T-shape

```
    A   ← Match ngang (A-A-A)
    A   ← Giao điểm
    A   ← Match dọc (A-A-A)
```

Grid ban đầu:
```
. . . . . . . .
. . . . . . . .
. A A A . . . .  (y=4)
. . A . . . . .  (y=3)
. . A . . . . .  (y=2)
. . . . . . . .
. . . . . . . .
. . . . . . . .
```

## Giải pháp

### 1. Phát hiện T/L Shape

```csharp
Dictionary<Vector2Int, List<MatchInfo>> positionToMatches
```

Track tất cả các vị trí mà có nhiều match đi qua. Khi một vị trí có **2+ matches giao nhau** → đó là T/L shape.

### 2. Đánh dấu T/L Shape

```csharp
// MatchInfo
public bool IsPartOfTLShape { get; set; }
public Vector2Int IntersectionPoint { get; set; }
public bool ShouldCreateBomb { get; set; }
```

### 3. Xử lý trong DetectAndProcessTLShapes()

```csharp
// Nếu có match ngang + match dọc giao nhau
if (hMatch != null && vMatch != null)
{
    // Đánh dấu để tạo Bomb
    hMatch.ShouldCreateBomb = true;
    hMatch.IntersectionPoint = position; // Giao điểm
    
    // Đánh dấu cả 2 matches để giữ lại
    hMatch.IsPartOfTLShape = true;
    vMatch.IsPartOfTLShape = true;
}
```

### 4. Cập nhật DetermineSpecialGemType()

```csharp
private SpecialType DetermineSpecialGemType(MatchInfo match)
{
    // T/L shape: tạo Bomb_3x3 thay vì LineClear
    if (match.ShouldCreateBomb)
    {
        return SpecialType.Bomb_3x3;
    }
    // ...
}
```

### 5. Cập nhật ProcessMatchesCoroutine()

```csharp
// HashSet để track các điểm giao của T/L
HashSet<Vector2Int> tlIntersectionPoints = new HashSet<Vector2Int>();

foreach (var match in matches)
{
    foreach (Vector2Int pos in match.Positions)
    {
        // Nếu là điểm giao của T/L, giữ lại gem này
        if (tlIntersectionPoints.Contains(pos))
        {
            continue; // Không destroy
        }
        // ...
    }
}
```

## Flow hoàn chỉnh

```
ScanGrid()
    ↓
Tìm tất cả matches (ngang + dọc)
    ↓
DetectAndProcessTLShapes()
    ↓
Với mỗi vị trí có 2+ matches:
    → Tìm 1 match ngang + 1 match dọc
    → Đánh dấu cả 2 là T/L Shape
    → Set IntersectionPoint = điểm giao
    → Set ShouldCreateBomb = true
    ↓
RemoveOverlappingMatches()
    ↓
Giữ lại T/L shapes dù có overlap
    ↓
ProcessMatchesCoroutine()
    ↓
Giữ lại gem ở điểm giao (sẽ thành Bomb)
Destroy các gems khác
    ↓
CreateSpecialGems()
    ↓
Tạo Bomb_3x3 tại điểm giao
```

## Kết quả

### Trước khi fix:
```
T-shape bị tách làm đôi:
- Match ngang: 3 gems → destroyed
- Match dọc: bị loại → miss
→ Không tạo Special Gem
```

### Sau khi fix:
```
T-shape được xử lý đúng:
- Match ngang: 3 gems → destroyed
- Match dọc: 3 gems → destroyed  
- Giao điểm: 1 gem → GIỮ LẠI → thành Bomb
→ Tạo Bomb_3x3 tại giao điểm
```

## Console Output

```
T/L shape detected at (2, 3). Will create Bomb!
T/L intersection gem at (2, 3) preserved for Bomb creation
Special gem Bomb_3x3 will be created at (2, 3)
```
