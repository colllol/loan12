# KỲ TRẬN: LOẠN THẾ SỨ QUÂN
### Game Match-3 RPG - Cổ Trang Việt Nam

---

## Kiến Trúc Dự Án (Project Architecture)

### Cấu Trúc Thư Mục

```
loan12/
├── Assets/
│   ├── _KyTran/                    ← Thư mục gốc của game
│   │   ├── Scripts/
│   │   │   ├── Core/               ← Nền tảng: GameManager, EventBroker, ServiceLocator
│   │   │   ├── Board/              ← Grid, Gem, Match, SpecialGem
│   │   │   ├── Combat/             ← Combat, Enemy AI, Element System
│   │   │   ├── Controllers/        ← Input, Swap, Animation
│   │   │   ├── Data/               ← LevelData, CharacterData
│   │   │   ├── Managers/           ← GridManager, LevelManager, UIManager
│   │   │   ├── Models/             ← GemModel, Obstacle, Character
│   │   │   ├── UI/                 ← Popups, HUD, Menus
│   │   │   └── Editor/             ← Level Editor, Custom Inspectors
│   │   │
│   │   ├── Prefabs/
│   │   │   ├── Gems/               ← 5 gem prefabs + special gems
│   │   │   ├── Characters/         ← Player, Enemy prefabs
│   │   │   ├── VFX/                ← Particles, Effects
│   │   │   └── UI/                ← Canvas, Buttons, Popups
│   │   │
│   │   ├── Art/
│   │   │   ├── Sprites/
│   │   │   │   ├── Gems/           ← Linh Thạch sprites
│   │   │   │   ├── Characters/     ← Tướng, Enemy
│   │   │   │   ├── Backgrounds/    ← Map backgrounds
│   │   │   │   ├── Obstacles/      ← Ice, Chain, Block, Cage
│   │   │   │   └── UI/            ← Buttons, Icons
│   │   │   ├── Animations/         ← Gem animations, Character anims
│   │   │   └── Materials/          ← Shader materials
│   │   │
│   │   ├── Audio/
│   │   │   ├── SFX/               ← Match, Attack, Hurt sounds
│   │   │   └── Music/              ← Background music
│   │   │
│   │   ├── Scenes/
│   │   │   ├── Bootstrapper/       ← Scene khởi động
│   │   │   ├── Menu/               ← Main Menu
│   │   │   └── Game/               ← Gameplay
│   │   │
│   │   ├── ScriptableObjects/
│   │   │   ├── Gems/               ← GemData (5 elements)
│   │   │   ├── Characters/         ← PlayerData, EnemyData
│   │   │   ├── Levels/             ← LevelData
│   │   │   ├── Items/              ← ItemData, BuffData
│   │   │   └── Configs/            ← GameConfig, AudioConfig
│   │   │
│   │   └── Resources/
│   │       ├── Levels/             ← Level assets
│   │       └── Configs/            ← Runtime configs
│   │
│   └── ThirdParty/                  ← Assets mua/tải ngoài
│       ├── Plugins/
│       ├── AssetPacks/
│       └── FreeAssets/
│
├── ProjectSettings/
├── Packages/
├── README.md
└── LICENSE
```

---

## Core Systems (Boilerplate)

### 1. GameManager.cs
- **Singleton Pattern** - Quản lý GameState (Menu, Playing, Paused, Win, Lose)
- **Event System** - Bắn events khi state thay đổi
- **Time Control** - Pause/Resume bằng Time.timeScale

### 2. EventBroker.cs
- **Decoupled Communication** - Các Manager giao tiếp qua events
- **Type-Safe** - Generic events với compile-time checking
- **Example:**
```csharp
// Board phát event
EventBroker.Instance.Emit<MatchData>(EventNames.GEM_MATCHED, matchData);

// CombatManager lắng nghe
EventBroker.Instance.Listen<MatchData>(EventNames.GEM_MATCHED, OnGemMatched);
```

### 3. ServiceLocator.cs
- **Dependency Injection** - Truy cập Manager không cần FindObjectOfType
- **Register/Get Pattern** - Loose coupling
- **Example:**
```csharp
ServiceLocator.Register(gridManager);
var grid = ServiceLocator.Get<GridManager>();
```

---

## Ngũ Hành System

### 5 Linh Thạch Cơ Bản

| Element | Màu | Tương Khắc | Tương Sinh |
|---------|------|-------------|------------|
| Kim | Vàng | Mộc | Thổ |
| Mộc | Xanh lá | Thổ | Thủy |
| Thủy | Xanh dương | Hỏa | Kim |
| Hỏa | Đỏ | Kim | Mộc |
| Thổ | Nâu | Thủy | Hỏa |

### Special Gems

| Match | Special | Effect |
|-------|---------|--------|
| 4 ngang | Hỏa Tiễn | Xóa hàng ngang |
| 4 dọc | Tên Súng | Xóa hàng dọc |
| T/L Shape | Bẫy Chông | Nổ 4 hướng chéo |
| 5 thẳng | Thuận Thiên Kiếm | Xóa 1 element |
| 5 cùng màu | Ngũ Hành Trận | Xóa toàn bộ bàn cờ |

---

## Game Modes

1. **Chiến Trận (Campaign)** - Đánh theo level, từ dễ đến khó
2. **Loạn Thế Tháp (Roguelike)** - Leo tháp, chọn Buff ngẫu nhiên
3. **Đại Chiến Thủy Quái (Co-op)** - 4 người đánh Boss
4. **Giáo Trường Điểm Tướng (PvP)** - Đấu real-time

---

## Progression System

- **Xây Phủ Chúa** - Base Building
- **Doanh Trại** - Level up Tướng
- **Lò Rèn** - Nâng cấp Binh Khí
- **Tửu Quán** - Daily Quests
- **Hồn Thú** - Thú Cưỡi

---

## Setup Instructions

### 1. Clone/Download Project
```bash
git clone https://github.com/your-repo/loan12.git
cd loan12
```

### 2. Mở trong Unity
- Unity 2022.3 LTS hoặc mới hơn
- Import project vào Unity Hub

### 3. Import Assets
- **Fantasy Gems 2D** (Free) - Unity Asset Store
- **RPG Maker RTP** (Free) - Kenney
- **Sound Effects** - Kenney.nl

### 4. Tạo GemData ScriptableObjects
```
1. Right-click Assets/_KyTran/ScriptableObjects/Gems
2. Create > KyTran > Gem Data
3. Tạo 5 assets: Kim, Mộc, Thủy, Hỏa, Thổ
```

### 5. Setup Bootstrapper
- Mở `Assets/_KyTran/Scenes/BOOTSTRAPPER_GUIDE.md`
- Làm theo hướng dẫn

### 6. Build & Test
```
File > Build Settings > Build
```

---

## Scripts Summary

| Script | Folder | Mô tả |
|--------|--------|--------|
| GameManager | Core | Singleton, GameState |
| EventBroker | Core | Event System |
| ServiceLocator | Core | DI Container |
| GridManager | Managers | 8x8 Grid |
| MatchSolver | Board | Match-3 Logic |
| SpecialGemEffect | Board | Special Gem Effects |
| CombatManager | Combat | Damage Calculation |
| ElementCounter | Combat | Ngũ Hành System |
| EnemyAI | Combat | Boss AI |
| LevelManager | Managers | Level Progression |
| LevelEditor | Editor | Level Creator |
| UIManager | UI | HUD, Popups |
| InputController | Controllers | Touch/Swipe |

---

## Art Style

- **2.5D Chibi / Low-poly 3D**
- **Âm hưởng Cổ Trang Việt Nam + Wuxia/Fantasy**
- **UI:** Trống Đồng Đông Sơn, Thẻ Tre, Chiếu Chỉ
- **VFX:** Mực tàu, vệt lửa khi swipe

## Audio

- **Nhạc cụ dân tộc:** Đàn Tranh, Sáo Trúc, Trống Trận
- **Phong cách:** Epic Orchestral / Trap Remix cho combat

---

## License

MIT License - Free to use and modify

---

**Version:** 0.1.0  
**Unity:** 2022.3 LTS+  
**Last Updated:** 2026-07-01
