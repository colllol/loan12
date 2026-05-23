using System.Collections.Generic;
using UnityEngine;
using System;

public enum GameScreen
{
    Splash, MainMenu, HeroSelect, WorldMap, Battle, Shop,
    Settings, Records, Guide, Info, Author, Result, Tutorial
}

public class GameManager : MonoBehaviour
{
    public static GameManager Instance { get; private set; }

    public GameScreen CurrentScreen { get; private set; } = GameScreen.Splash;
    public BoardManager Board { get; private set; }

    private float _screenStartTime;
    private float _lastFrameTime;

    private float _canvasScale;
    private Rect _canvasRect;

    private GUIStyle _styleCenter;
    private GUIStyle _styleSmall;
    private GUIStyle _styleLeft;
    private GUIStyle _styleButton;

    public GameState State { get; private set; }

    private void Awake()
    {
        if (Instance != null) { Destroy(gameObject); return; }
        Instance = this;
        DontDestroyOnLoad(gameObject);
        Application.targetFrameRate = GameConfig.TargetFPS;
        Board = new BoardManager();
        State = new GameState();
        State.LoadAll();
        _screenStartTime = Time.realtimeSinceStartup;
    }

    private void EnsureStyles()
    {
        if (_styleCenter != null) return;
        _styleCenter = new GUIStyle(GUI.skin.label)
        {
            alignment = TextAnchor.MiddleCenter, fontSize = 11,
            normal = { textColor = Color.white }, wordWrap = false
        };
        _styleSmall = new GUIStyle(_styleCenter) { fontSize = 9, wordWrap = true };
        _styleLeft = new GUIStyle(_styleSmall) { alignment = TextAnchor.UpperLeft };
        _styleButton = new GUIStyle(GUI.skin.button)
        {
            fontSize = 9, alignment = TextAnchor.MiddleCenter,
            normal = { textColor = Color.white }
        };
    }

    public GUIStyle Center => _styleCenter;
    public GUIStyle Small => _styleSmall;
    public GUIStyle Left => _styleLeft;

    private void Update()
    {
        float now = Time.realtimeSinceStartup;
        float dt = now - _lastFrameTime;
        _lastFrameTime = now;

        if (CurrentScreen == GameScreen.Splash)
        {
            if (now - _screenStartTime > 3.0f) SwitchTo(GameScreen.MainMenu);
        }
        else if (CurrentScreen == GameScreen.MainMenu)
        {
            HandleMenuInput();
        }
        else if (CurrentScreen == GameScreen.HeroSelect)
        {
            HandleHeroSelectInput();
        }
        else if (CurrentScreen == GameScreen.WorldMap)
        {
            HandleWorldMapInput();
        }
        else if (CurrentScreen == GameScreen.Battle)
        {
            HandleBattleInput();
        }
        else if (CurrentScreen == GameScreen.Shop)
        {
            HandleShopInput();
        }
        else if (CurrentScreen == GameScreen.Tutorial)
        {
            if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
                State.TutorialAdvance();
            if (State.TutorialDone) SwitchTo(GameScreen.Battle);
        }
        else if (CurrentScreen == GameScreen.Result)
        {
            if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
                SwitchTo(GameScreen.MainMenu);
        }
        else
        {
            if (Input.GetKeyDown(KeyCode.Escape) || Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
                SwitchTo(GameScreen.MainMenu);
        }

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (CurrentScreen == GameScreen.Battle) { State.SaveGame(); SwitchTo(GameScreen.MainMenu); }
            else if (CurrentScreen != GameScreen.Splash && CurrentScreen != GameScreen.MainMenu)
                SwitchTo(GameScreen.MainMenu);
        }
    }

    public void SwitchTo(GameScreen screen)
    {
        CurrentScreen = screen;
        _screenStartTime = Time.realtimeSinceStartup;

        if (screen == GameScreen.Battle)
        {
            if (State.board == null) State.CreateBoard();
            if (State.movesLeft <= 0) State.movesLeft = 20;
        }

        AudioManager am = AudioManager.Instance;
        if (am != null)
        {
            am.StopMusic();
            if (screen == GameScreen.Battle) am.PlayMusic("m");
            else if (screen == GameScreen.MainMenu) am.PlayMusic("menu");
            else if (screen == GameScreen.Splash) am.PlayMusic("intro");
        }
    }

    private void HandleMenuInput()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow) || Input.GetKeyDown(KeyCode.LeftArrow))
            State.menuIndex = (State.menuIndex + GameConfig.MenuItems.Length - 1) % GameConfig.MenuItems.Length;
        else if (Input.GetKeyDown(KeyCode.DownArrow) || Input.GetKeyDown(KeyCode.RightArrow))
            State.menuIndex = (State.menuIndex + 1) % GameConfig.MenuItems.Length;
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
            ActivateMenu();
    }

    private void ActivateMenu()
    {
        switch (State.menuIndex)
        {
            case 0:
                if (State.HasSave()) { State.LoadGame(); SwitchTo(GameScreen.Battle); }
                else State.message = "Chưa có dữ liệu.";
                break;
            case 1: State.menuIndex = 0; SwitchTo(GameScreen.HeroSelect); break;
            case 2:
                State.selectedStage = Mathf.Clamp(State.level, 1, GameConfig.MaxLevel);
                SwitchTo(GameScreen.WorldMap);
                break;
            case 3: SwitchTo(GameScreen.Records); break;
            case 4: SwitchTo(GameScreen.Shop); break;
            case 5:
                State.pageTitle = "Thông tin";
                State.pageBody = "Loan 12 Sứ Quân - Unity Port từ J2ME.\n240x320, gameplay gốc giữ lại đầy đủ.";
                SwitchTo(GameScreen.Info);
                break;
            case 6:
                State.pageTitle = "Tặng game";
                State.pageBody = "Chia sẻ file build iOS/APK cho bạn bè.";
                SwitchTo(GameScreen.Info);
                break;
            case 7:
                State.selectingEndless = true;
                SwitchTo(GameScreen.HeroSelect);
                break;
            case 8:
                State.pageTitle = "Hướng dẫn";
                State.pageBody = "Xếp 3+ quân cùng loại để tấn công.\nKiếm = tấn công\nTim = hồi máu\nÂm dương = năng lượng\nVàng = tiền\nSách = năng lượng thêm\nGạo = điểm\nKiếm đỏ = tấn công mạnh\nPhím mũi tên di chuyển, Enter/5 chọn.";
                SwitchTo(GameScreen.Info);
                break;
            case 9:
                State.pageTitle = "Tác giả";
                State.pageBody = "Game Java gốc: MicroGame Corp 2010.\nUnity Port: bot-nosense/colllol.";
                SwitchTo(GameScreen.Info);
                break;
        }
    }

    private void HandleHeroSelectInput()
    {
        if (Input.GetKeyDown(KeyCode.LeftArrow)) State.selectedHero = (State.selectedHero + 6 - 1) % 6;
        else if (Input.GetKeyDown(KeyCode.RightArrow)) State.selectedHero = (State.selectedHero + 1) % 6;
        else if (Input.GetKeyDown(KeyCode.UpArrow)) State.selectedHero = (State.selectedHero + 6 - 3) % 6;
        else if (Input.GetKeyDown(KeyCode.DownArrow)) State.selectedHero = (State.selectedHero + 3) % 6;
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
        {
            State.heroIndex = State.selectedHero;
            State.StartNewGame(State.selectingEndless);
            if (State.tutorialStep < 0 && !State.selectingEndless)
            {
                State.tutorialStep = 0;
                SwitchTo(GameScreen.Tutorial);
            }
            else
            {
                SwitchTo(GameScreen.Battle);
            }
        }
    }

    private void HandleWorldMapInput()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow))
            State.selectedStage = Mathf.Clamp(State.selectedStage - 1, 1, GameConfig.MaxLevel + 1);
        else if (Input.GetKeyDown(KeyCode.DownArrow))
            State.selectedStage = Mathf.Clamp(State.selectedStage + 1, 1, GameConfig.MaxLevel + 1);
        else if (Input.GetKeyDown(KeyCode.LeftArrow))
            State.selectedStage = Mathf.Clamp(State.selectedStage - 6, 1, GameConfig.MaxLevel + 1);
        else if (Input.GetKeyDown(KeyCode.RightArrow))
            State.selectedStage = Mathf.Clamp(State.selectedStage + 6, 1, GameConfig.MaxLevel + 1);
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
        {
            if (State.selectedStage == GameConfig.MaxLevel + 1)
            {
                State.selectingEndless = true;
                SwitchTo(GameScreen.HeroSelect);
            }
            else if (State.selectedStage <= State.unlockedLevel)
            {
                State.StartReplayStage(State.selectedStage);
                SwitchTo(GameScreen.Battle);
            }
            else State.message = "Chưa mở màn này.";
        }
    }

    private void HandleBattleInput()
    {
        if (Input.GetKeyDown(KeyCode.LeftArrow)) State.cursorX = Mathf.Max(0, State.cursorX - 1);
        else if (Input.GetKeyDown(KeyCode.RightArrow)) State.cursorX = Mathf.Min(Board.Cols - 1, State.cursorX + 1);
        else if (Input.GetKeyDown(KeyCode.UpArrow)) State.cursorY = Mathf.Max(0, State.cursorY - 1);
        else if (Input.GetKeyDown(KeyCode.DownArrow)) State.cursorY = Mathf.Min(Board.Rows - 1, State.cursorY + 1);
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
            State.SelectCell(State.cursorX, State.cursorY);
        else if (Input.GetKeyDown(KeyCode.Alpha1)) UseSkill(0);
        else if (Input.GetKeyDown(KeyCode.Alpha2)) UseSkill(1);
        else if (Input.GetKeyDown(KeyCode.Alpha3)) UseSkill(2);
        else if (Input.GetKeyDown(KeyCode.Alpha4)) UseSkill(3);
        else if (Input.GetKeyDown(KeyCode.Alpha5)) UseSkill(4);
        else if (Input.GetKeyDown(KeyCode.Alpha6)) UseSkill(5);
        else if (Input.GetKeyDown(KeyCode.Alpha7)) UseSkill(6);
        else if (Input.GetKeyDown(KeyCode.Alpha8)) UseSkill(7);
        else if (Input.GetKeyDown(KeyCode.Alpha9)) UseSkill(8);
        else if (Input.GetKeyDown(KeyCode.Q)) State.UseItem(0);
        else if (Input.GetKeyDown(KeyCode.W)) State.UseItem(1);
        else if (Input.GetKeyDown(KeyCode.E)) State.UseItem(2);
        else if (Input.GetKeyDown(KeyCode.R)) State.UseItem(3);
        else if (Input.GetKeyDown(KeyCode.T)) State.UseItem(4);
        else if (Input.GetKeyDown(KeyCode.Y)) State.UseItem(5);
    }

    private void UseSkill(int idx)
    {
        int cost = GameConfig.SkillCosts[idx];
        if (State.heroIndex == 1 || State.heroIndex == 4) cost = Mathf.Max(5, cost - 3);
        if (State.mana < cost) { State.message = "Không đủ mana."; return; }
        State.mana -= cost;
        State.UseSkillOnBoard(idx);
    }

    private void HandleShopInput()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow))
            State.shopIndex = (State.shopIndex + GameConfig.ItemNames.Length - 1) % GameConfig.ItemNames.Length;
        else if (Input.GetKeyDown(KeyCode.DownArrow))
            State.shopIndex = (State.shopIndex + 1) % GameConfig.ItemNames.Length;
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space) || Input.GetKeyDown(KeyCode.Keypad5))
            State.BuyItem(State.shopIndex);
    }

    private void OnGUI()
    {
        EnsureStyles();
        CalculateCanvas();
        GUI.color = Color.black;
        GUI.DrawTexture(new Rect(0, 0, Screen.width, Screen.height), Texture2D.whiteTexture);
        GUI.color = Color.white;
        GUI.Box(_canvasRect, GUIContent.none);
        var matrix = GUI.matrix;
        GUI.matrix = Matrix4x4.TRS(_canvasRect.position, Quaternion.identity, new Vector3(_canvasScale, _canvasScale, 1));
        DrawCurrentScreen();
        GUI.matrix = matrix;
    }

    private void CalculateCanvas()
    {
        _canvasScale = Mathf.Min(Screen.width / (float)GameConfig.VirtualWidth, Screen.height / (float)GameConfig.VirtualHeight);
        float w = GameConfig.VirtualWidth * _canvasScale;
        float h = GameConfig.VirtualHeight * _canvasScale;
        _canvasRect = new Rect((Screen.width - w) / 2f, (Screen.height - h) / 2f, w, h);
    }

    private void DrawCurrentScreen()
    {
        switch (CurrentScreen)
        {
            case GameScreen.Splash: DrawSplashScreen(); break;
            case GameScreen.MainMenu: DrawMainMenu(); break;
            case GameScreen.HeroSelect: DrawHeroSelect(); break;
            case GameScreen.WorldMap: DrawWorldMap(); break;
            case GameScreen.Battle: DrawBattle(); break;
            case GameScreen.Shop: DrawShop(); break;
            case GameScreen.Settings: DrawSettings(); break;
            case GameScreen.Records: DrawRecords(); break;
            case GameScreen.Info:
            case GameScreen.Guide:
            case GameScreen.Author: DrawInfoPage(); break;
            case GameScreen.Result: DrawResult(); break;
            case GameScreen.Tutorial: DrawTutorial(); break;
        }
    }

    private void DrawSplashScreen()
    {
        AssetManager.DrawFull("bksplashscreen");
    }

    private void DrawMainMenu()
    {
        AssetManager.DrawFull("bkmenu");
        AssetManager.DrawTextureCentered(GameConfig.VirtualWidth / 2, 38, "title");
        for (int i = 0; i < GameConfig.MenuItems.Length; i++)
        {
            int y = 76 + i * 21;
            var rect = new Rect(34, y, 172, 18);
            if (i == State.menuIndex)
                DrawFocus(new Rect(30, y - 2, 180, 22), "focus1");
            GUI.Label(rect, GameConfig.MenuItems[i], _styleCenter);
            if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            { State.menuIndex = i; ActivateMenu(); }
        }
        if (!string.IsNullOrEmpty(State.message))
        {
            GUI.Label(new Rect(10, GameConfig.VirtualHeight - 28, 220, 20), State.message, _styleSmall);
        }
    }

    private void DrawHeroSelect()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 22, GameConfig.VirtualWidth, 22), State.selectingEndless ? "Chiến trường vô tận" : "Chọn tướng", _styleCenter);
        for (int i = 0; i < 6; i++)
        {
            int col = i % 3;
            int row = i / 3;
            int ix = 30 + col * 70;
            int iy = 58 + row * 78;
            var rect = new Rect(ix, iy, 60, 68);
            if (i == State.selectedHero)
                DrawFocus(new Rect(ix - 2, iy - 2, 64, 72), "focus1");
            DrawAvatar(i, new Rect(ix + 15, iy + 4, 30, 30));
            GUI.Label(new Rect(ix, iy + 38, 60, 16), GameConfig.HeroNames[i], _styleSmall);
            if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            {
                State.selectedHero = i; State.heroIndex = i;
                State.StartNewGame(State.selectingEndless);
                SwitchTo(GameScreen.Battle);
            }
        }
        int h = State.selectedHero;
        GUI.Label(new Rect(12, 210, 216, 30), GameConfig.HeroDescriptions[h], _styleSmall);
        GUI.Label(new Rect(12, 240, 216, 14),
            $"HP {GameConfig.HeroBaseHealth[h]} ATK {GameConfig.HeroBaseAttack[h]} DEF {GameConfig.HeroBaseDefense[h]} SK {GameConfig.HeroBaseSkillPower[h]}",
            _styleSmall);
        GUI.Label(new Rect(0, GameConfig.VirtualHeight - 24, GameConfig.VirtualWidth, 18),
            "Enter chọn", _styleSmall);
    }

    private void DrawWorldMap()
    {
        AssetManager.DrawFull("bkboard");
        GUI.Label(new Rect(0, 6, GameConfig.VirtualWidth, 16), "BẢN ĐỒ", _styleCenter);
        GUI.Label(new Rect(4, 22, 120, 12), $"Mở: {State.unlockedLevel}/{GameConfig.MaxLevel}", _styleSmall);
        GUI.Label(new Rect(124, 22, 112, 12), $"Màn {State.selectedStage}", _styleSmall);

        int mapScale = 1;
        int mapOffsetX = GameConfig.VirtualWidth / 2 - 120;
        int mapOffsetY = 40;

        for (int i = 0; i < WorldMapData.LocationNames.Length; i++)
        {
            int levelNum = i + 1;
            if (levelNum > State.unlockedLevel + 5) break;
            var pos = WorldMapData.GetLocationPos(i);
            int sx = mapOffsetX + pos.x / 3;
            int sy = mapOffsetY + pos.y / 3;
            bool unlocked = levelNum <= State.unlockedLevel;
            bool selected = State.selectedStage == levelNum;

            if (unlocked && i > 0)
            {
                var prevPos = WorldMapData.GetLocationPos(i - 1);
                DrawMapLine(mapOffsetX + prevPos.x / 3, mapOffsetY + prevPos.y / 3, sx, sy);
            }

            var rect = new Rect(sx - 8, sy - 8, 16, 16);
            if (selected)
                DrawFocus(new Rect(sx - 11, sy - 11, 22, 22), "focus1");
            GUI.color = unlocked ? Color.white : new Color(0.4f, 0.4f, 0.4f);
            GUI.Box(rect, levelNum.ToString(), _styleSmall);
            GUI.color = Color.white;

            if (unlocked && levelNum == State.unlockedLevel && i > 0)
            {
                GUI.Label(new Rect(sx - 20, sy - 22, 40, 12), "▶", _styleSmall);
            }

            if (unlocked && Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            {
                State.selectedStage = levelNum;
                State.StartReplayStage(levelNum);
                SwitchTo(GameScreen.Battle);
            }
        }

        if (State.selectedStage == GameConfig.MaxLevel + 1)
        {
            var er = new Rect(80, GameConfig.VirtualHeight - 60, 80, 20);
            DrawFocus(new Rect(78, GameConfig.VirtualHeight - 62, 84, 24), "focus1");
            GUI.Label(er, "VÔ TẬN", _styleCenter);
        }
        GUI.Label(new Rect(70, GameConfig.VirtualHeight - 40, 100, 16), "↑↓ chọn, Enter đánh", _styleSmall);

        if (!string.IsNullOrEmpty(State.message))
            GUI.Label(new Rect(4, GameConfig.VirtualHeight - 20, 232, 16), State.message, _styleSmall);
    }

    private void DrawMapLine(int x1, int y1, int x2, int y2)
    {
        var c = GUI.color;
        GUI.color = new Color(0.9f, 0.8f, 0.2f, 0.3f);
        DrawLineSimple(x1, y1, x2, y2);
        GUI.color = c;
    }

    private void DrawLineSimple(int x1, int y1, int x2, int y2)
    {
        int steps = Mathf.Max(Mathf.Abs(x2 - x1), Mathf.Abs(y2 - y1));
        if (steps < 2) steps = 2;
        for (int i = 0; i < steps; i++)
        {
            float t = i / (float)steps;
            int x = Mathf.RoundToInt(Mathf.Lerp(x1, x2, t));
            int y = Mathf.RoundToInt(Mathf.Lerp(y1, y2, t));
            GUI.DrawTexture(new Rect(x, y, 2, 2), Texture2D.whiteTexture);
        }
    }

    private void DrawBattle()
    {
        AssetManager.DrawFull("bkboard");
        DrawHUD();
        DrawGrid();
        DrawStatusPanel();
        DrawItemSlot(170, 224, 0, "K");
        DrawItemSlot(186, 224, 1, "S");
        DrawItemSlot(202, 224, 2, "G");
        DrawItemSlot(170, 240, 3, "A");
        DrawItemSlot(186, 240, 4, "P");
        DrawItemSlot(202, 240, 5, "N");
        DrawEffects();

        if (State.bossBattle)
        {
            GUI.Label(new Rect(12, GameConfig.VirtualHeight - 108, 216, 12),
                $"★ BOSS {State.enemyName} ★", _styleCenter);
        }
    }

    private void DrawHUD()
    {
        DrawAvatar(State.heroIndex, new Rect(4, 6, 20, 20));
        GUI.Label(new Rect(26, 4, 70, 12), State.enemyName, _styleSmall);
        DrawBar(new Rect(26, 18, 70, 6), State.health, State.maxHealth, new Color32(40, 175, 75, 255));
        GUI.Label(new Rect(26, 26, 70, 10), $"HP {State.health}/{State.maxHealth} M{State.mana}", _styleSmall);
        GUI.Label(new Rect(26, 36, 70, 10), $"LV{State.heroLevel}", _styleSmall);

        DrawEnemyFace(new Rect(GameConfig.VirtualWidth - 24, 6, 20, 20));
        GUI.Label(new Rect(GameConfig.VirtualWidth - 96, 4, 70, 12), State.enemyName, _styleSmall);
        DrawBar(new Rect(GameConfig.VirtualWidth - 96, 18, 70, 6), State.enemyHealth, State.enemyMaxHealth, new Color32(190, 40, 40, 255));
        GUI.Label(new Rect(GameConfig.VirtualWidth - 96, 26, 70, 10), $"{State.enemyHealth}/{State.enemyMaxHealth}", _styleSmall);
        GUI.Label(new Rect(GameConfig.VirtualWidth - 96, 36, 70, 10), $"ATK {State.heroAttack} DEF {State.heroDefense}", _styleSmall);
    }

    private void DrawGrid()
    {
        for (int y = 0; y < Board.Rows; y++)
        {
            for (int x = 0; x < Board.Cols; x++)
            {
                var rect = Board.GetCellRect(x, y);
                int piece = Board.Grid[x, y];
                if (piece == GameConfig.EmptyPiece) continue;
                if (x == State.cursorX && y == State.cursorY)
                    DrawFocus(rect, "focus1");
                if (x == State.selX && y == State.selY)
                    DrawFocus(rect, "focus2");
                AssetManager.DrawTexture(rect, GameConfig.PieceNames[piece]);
                if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
                { State.cursorX = x; State.cursorY = y; State.SelectCell(x, y); }
            }
        }
    }

    private void DrawStatusPanel()
    {
        GUI.Label(new Rect(6, GameConfig.VirtualHeight - 120, 228, 36), State.message, _styleSmall);
        GUI.Label(new Rect(6, GameConfig.VirtualHeight - 84, 80, 12),
            $"{(State.endlessMode ? "Đợt" : "Màn")} {State.level}", _styleSmall);
        GUI.Label(new Rect(86, GameConfig.VirtualHeight - 84, 80, 12),
            $"Lượt {State.movesLeft}", _styleSmall);
        GUI.Label(new Rect(166, GameConfig.VirtualHeight - 84, 80, 12),
            $"Vàng {State.gold}", _styleSmall);
    }

    private void DrawItemSlot(int x, int y, int idx, string key)
    {
        var rect = new Rect(x, y, 14, 14);
        AssetManager.DrawTexture(rect, GameConfig.ItemNames[idx] switch
        {
            "Long Thần Kiếm" => "sword",
            "Nhân Sâm" => "heart",
            "Ngân Lượng" => "gold",
            "Quỷ Diện Giáp" => "defenceshield",
            "Bình Thuốc" => "healingicon",
            _ => "star"
        });
        GUI.Label(new Rect(x + 13, y, 14, 14), State.inventory[idx].ToString(), _styleSmall);
        if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            State.UseItem(idx);
    }

    private List<Effect> _effects = new List<Effect>();

    public void AddEffect(string texName, float x, float y, int frames)
    {
        _effects.Add(new Effect(texName, new Rect(x, y, 48, 48), frames));
        if (_effects.Count > 24) _effects.RemoveAt(0);
    }

    private void DrawEffects()
    {
        for (int i = _effects.Count - 1; i >= 0; i--)
        {
            var e = _effects[i];
            var tex = AssetManager.LoadTexture(e.TextureName);
            if (tex != null)
            {
                float alpha = Mathf.Clamp01(e.FramesLeft / (float)e.TotalFrames);
                GUI.color = new Color(1, 1, 1, alpha);
                GUI.DrawTexture(e.Rect, tex, ScaleMode.ScaleToFit, true);
                GUI.color = Color.white;
            }
            e.FramesLeft--;
            if (e.FramesLeft <= 0) _effects.RemoveAt(i);
        }
    }

    private void DrawShop()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 24, GameConfig.VirtualWidth, 22), "CỬA HÀNG", _styleCenter);
        GUI.Label(new Rect(12, 50, 100, 14), "Vàng: " + State.gold, _styleLeft);

        for (int i = 0; i < GameConfig.ItemNames.Length; i++)
        {
            int y = 72 + i * 36;
            var rect = new Rect(14, y, 212, 32);
            if (i == State.shopIndex)
                DrawFocus(new Rect(12, y - 1, 216, 34), "focus1");
            AssetManager.DrawTexture(new Rect(18, y + 2, 24, 24), GameConfig.ItemNames[i] switch
            {
                "Long Thần Kiếm" => "sword",
                "Nhân Sâm" => "heart",
                "Ngân Lượng" => "gold",
                "Quỷ Diện Giáp" => "defenceshield",
                "Bình Thuốc" => "healingicon",
                _ => "star"
            });
            GUI.Label(new Rect(46, y + 2, 80, 14), GameConfig.ItemNames[i], _styleLeft);
            GUI.Label(new Rect(46, y + 16, 120, 14), GameConfig.ItemDescriptions[i], _styleSmall);
            GUI.Label(new Rect(170, y + 2, 50, 14), GameConfig.ItemPrices[i] > 0 ? GameConfig.ItemPrices[i] + " vàng" : "SMS", _styleSmall);
            if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            { State.shopIndex = i; State.BuyItem(i); }
        }

        if (!string.IsNullOrEmpty(State.message))
            GUI.Label(new Rect(12, GameConfig.VirtualHeight - 28, 216, 20), State.message, _styleSmall);
    }

    private void DrawSettings()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 24, GameConfig.VirtualWidth, 22), "CÀI ĐẶT", _styleCenter);
        var am = AudioManager.Instance;
        bool music = am != null && am.IsMusicEnabled();
        bool sfx = am != null && am.IsSFXEnabled();
        GUI.Label(new Rect(20, 70, 100, 18), "Nhạc nền: " + (music ? "Bật" : "Tắt"), _styleLeft);
        GUI.Label(new Rect(20, 100, 100, 18), "Hiệu ứng: " + (sfx ? "Bật" : "Tắt"), _styleLeft);
        if (GUI.Button(new Rect(20, 140, 100, 24), "Menu", _styleButton))
            SwitchTo(GameScreen.MainMenu);
    }

    private void DrawRecords()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 40, GameConfig.VirtualWidth, 24), "KỶ LỤC", _styleCenter);
        GUI.Label(new Rect(30, 80, 180, 24), "Điểm cao: " + State.bestScore, _styleCenter);
        GUI.Label(new Rect(30, 110, 180, 24), $"Màn cao: {State.bestLevel}/{GameConfig.MaxLevel}", _styleCenter);
        GUI.Label(new Rect(30, 150, 180, 40), State.HasSave() ? "Có dữ liệu chơi tiếp." : "Chưa có dữ liệu.", _styleSmall);
        GUI.Label(new Rect(0, GameConfig.VirtualHeight - 30, GameConfig.VirtualWidth, 18),
            "Enter quay lại", _styleSmall);
    }

    private void DrawInfoPage()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 30, GameConfig.VirtualWidth, 22), State.pageTitle, _styleCenter);
        GUI.Label(new Rect(16, 70, 208, 140), State.pageBody, _styleLeft);
        GUI.Label(new Rect(0, GameConfig.VirtualHeight - 30, GameConfig.VirtualWidth, 18),
            "Enter quay lại", _styleSmall);
    }

    private void DrawResult()
    {
        AssetManager.DrawFull("bkmenu");
        GUI.Label(new Rect(0, 70, GameConfig.VirtualWidth, 28), State.resultTitle, _styleCenter);
        GUI.Label(new Rect(16, 120, 208, 80), State.resultBody, _styleLeft);
        GUI.Label(new Rect(0, GameConfig.VirtualHeight - 30, GameConfig.VirtualWidth, 18),
            "Enter quay lại", _styleSmall);
    }

    private void DrawTutorial()
    {
        AssetManager.DrawFull("bkboard");
        var t = State.GetTutorialStep();
        if (t == null) { SwitchTo(GameScreen.Battle); return; }
        GUI.Label(new Rect(10, 6, 220, 48), t.Value.Text, _styleLeft);
        DrawGrid();
        if (t.Value.HighlightX >= 0)
        {
            int hx = GameConfig.GridOffsetX + t.Value.HighlightX * GameConfig.GridCellSize;
            int hy = GameConfig.GridOffsetY + t.Value.HighlightY * GameConfig.GridCellSize;
            DrawFocus(new Rect(hx, hy, GameConfig.GridCellSize, GameConfig.GridCellSize), "zoomfocus1");
        }
        GUI.Label(new Rect(10, GameConfig.VirtualHeight - 30, 220, 18),
            $"Bước {State.tutorialStep + 1}/{GameState.TutorialSteps.Length}", _styleSmall);
        GUI.Label(new Rect(10, GameConfig.VirtualHeight - 16, 220, 14),
            "Enter tiếp", _styleSmall);
    }

    private void DrawAvatar(int index, Rect rect)
    {
        var tex = AssetManager.LoadTexture("faces/hero_" + index.ToString("00"));
        if (tex == null) tex = AssetManager.LoadTexture("avatar");
        if (tex != null) GUI.DrawTexture(rect, tex, ScaleMode.ScaleToFit, true);
        else GUI.Box(rect, GUIContent.none);
    }

    private void DrawEnemyFace(Rect rect)
    {
        Texture2D tex = null;
        if (State.endlessMode) tex = AssetManager.LoadTexture("faces/event_endless");
        else if (State.bossBattle) tex = AssetManager.LoadTexture("faces/boss_" + (State.level / 5).ToString("00"));
        if (tex == null) tex = AssetManager.LoadTexture("swordred");
        if (tex != null) GUI.DrawTexture(rect, tex, ScaleMode.ScaleToFit, true);
        else GUI.Box(rect, GUIContent.none);
    }

    public void DrawFocus(Rect rect, string name)
    {
        var tex = AssetManager.LoadTexture(name);
        if (tex != null) GUI.DrawTexture(new Rect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4), tex, ScaleMode.StretchToFill, true);
        else { GUI.color = new Color32(255, 255, 0, 80); GUI.DrawTexture(rect, Texture2D.whiteTexture); GUI.color = Color.white; }
    }

    private void DrawBar(Rect rect, int value, int max, Color color)
    {
        GUI.color = new Color32(35, 35, 35, 200);
        GUI.DrawTexture(rect, Texture2D.whiteTexture);
        GUI.color = color;
        float w = max <= 0 ? 0 : rect.width * Mathf.Clamp01(value / (float)max);
        GUI.DrawTexture(new Rect(rect.x, rect.y, w, rect.height), Texture2D.whiteTexture);
        GUI.color = Color.white;
    }
}

public class Effect
{
    public string TextureName;
    public Rect Rect;
    public int TotalFrames;
    public int FramesLeft;
    public Effect(string tex, Rect rect, int frames)
    { TextureName = tex; Rect = rect; TotalFrames = frames; FramesLeft = frames; }
}
