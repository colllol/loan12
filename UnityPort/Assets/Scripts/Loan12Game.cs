using System.Collections.Generic;
using System.IO;
using UnityEngine;

public sealed class Loan12Game : MonoBehaviour
{
    private const int VirtualWidth = 240;
    private const int VirtualHeight = 320;
    private const int BoardSize = 7;
    private const int EmptyPiece = -1;
    private const int MaxLevel = 12;
    private const string SavePrefix = "Loan12.";

    private enum ScreenState
    {
        MgLogo,
        PartnerLogo,
        MainMenu,
        HeroSelect,
        Board,
        Shop,
        Records,
        Guide,
        Information,
        Author,
        LinkPage,
        Result
    }

    private readonly Dictionary<string, Texture2D> textures = new Dictionary<string, Texture2D>();
    private readonly string[] menuItems =
    {
        "strcontinuegame",
        "strnewgame",
        "strrecord",
        "strshop",
        "strinformation",
        "strgivegame",
        "strchatola",
        "strothergame",
        "strguide",
        "strauthor"
    };

    private readonly string[] shopNames =
    {
        "Long Than Kiem",
        "Nhan Sam",
        "Ngan Luong",
        "Quy Dien Giap",
        "Binh Thuoc",
        "Ngoc An"
    };

    private readonly string[] shopDescriptions =
    {
        "Nhan doi suc tan cong trong 1 luot.",
        "Nhan doi mau co ban trong tran.",
        "Nhan them 1000 vang.",
        "Chan 3/4 sat thuong trong 1 luot.",
        "Hoi ngay 10% sinh luc.",
        "Uu tien di truoc khi vao tran."
    };

    private readonly int[] shopPrices = { 0, 0, 0, 1000, 250, 500 };
    private readonly int[] shopAmounts = { 3, 3, 1000, 3, 3, 3 };

    private readonly string[] skillNames =
    {
        "Qua Cau Lua",
        "Mua Thien Thach",
        "Lua Dia Nguc",
        "Chui Set",
        "Khien Set",
        "Sam Set",
        "Mui Ten Bang",
        "Cam Lo Thuy",
        "Bang Phong"
    };

    private readonly int[] skillCosts = { 10, 16, 22, 14, 18, 24, 12, 15, 20 };

    private readonly string[] enemyNames =
    {
        "Ngo Quyen",
        "Duong Tam Kha",
        "Kieu Cong Han",
        "Kieu Thuan",
        "Do Canh Thac",
        "Nguyen Khoan",
        "Nguyen Thu Tiep",
        "Pham Bach Ho",
        "Tran Lam",
        "Ly Khue",
        "Ngo Xuong Xi",
        "Dinh Bo Linh"
    };

    private readonly string[] heroNames =
    {
        "Hoa hau",
        "Loi than",
        "Thuy long",
        "Hoa phuong",
        "Nu loi",
        "Bac hai"
    };

    private readonly string[] heroDescriptions =
    {
        "Sat thuong lua cao.",
        "Mana tang nhanh hon.",
        "Sinh ton va hoi mau tot.",
        "Danh thuong manh hon.",
        "Ky nang set re hon.",
        "Bang gia khong che lau hon."
    };

    private readonly string[] pieces =
    {
        "sword",
        "rice",
        "heart",
        "yinyang",
        "gold",
        "book"
    };

    private ScreenState state;
    private float stateStartedAt;
    private int selectedMenuItem;
    private int selectedShopItem;
    private int selectedHero;
    private int heroIndex;
    private int[,] board;
    private int cursorX;
    private int cursorY;
    private int selectedX = -1;
    private int selectedY = -1;
    private int level = 1;
    private int movesLeft;
    private int score;
    private int gold;
    private int mana;
    private int health;
    private int maxHealth = 100;
    private int enemyHealth;
    private int enemyMaxHealth;
    private int targetScore;
    private int enemyAttack;
    private int shieldTurns;
    private int frozenTurns;
    private int powerAttackTurns;
    private bool ginsengUsed;
    private readonly List<Effect> effects = new List<Effect>();
    private int bestScore;
    private int bestLevel;
    private int[] inventory = new int[4];
    private string enemyName = "Tuong giac";
    private string message = "Chon 2 quan canh nhau de doi cho.";
    private string pageTitle;
    private string pageBody;
    private string resultTitle;
    private string resultBody;
    private Rect canvasRect;
    private float canvasScale;
    private GUIStyle labelStyle;
    private GUIStyle smallLabelStyle;
    private GUIStyle leftLabelStyle;

    private void Awake()
    {
        Application.targetFrameRate = 25;
        state = ScreenState.MainMenu;
        stateStartedAt = Time.realtimeSinceStartup;
        LoadRecords();
        StartNewGame(false);
        Debug.Log("Loan12 game started.");
    }

    private void Update()
    {
        if (state == ScreenState.MgLogo && Time.realtimeSinceStartup - stateStartedAt > 2.5f)
        {
            SwitchTo(ScreenState.PartnerLogo);
        }
        else if (state == ScreenState.PartnerLogo && Time.realtimeSinceStartup - stateStartedAt > 2.0f)
        {
            SwitchTo(ScreenState.MainMenu);
        }

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (state == ScreenState.Board)
            {
                SaveGame();
                SwitchTo(ScreenState.MainMenu);
            }
            else if (state == ScreenState.HeroSelect || state == ScreenState.Shop || state == ScreenState.Records || state == ScreenState.Guide ||
                state == ScreenState.Information || state == ScreenState.Author || state == ScreenState.LinkPage ||
                state == ScreenState.Result)
            {
                SwitchTo(ScreenState.MainMenu);
            }
            else
            {
                SwitchTo(ScreenState.MgLogo);
            }
        }

        if (state == ScreenState.MainMenu)
        {
            HandleMenuInput();
        }
        else if (state == ScreenState.Board)
        {
            HandleBoardInput();
        }
        else if (state == ScreenState.HeroSelect)
        {
            HandleHeroInput();
        }
        else if (state == ScreenState.Shop)
        {
            HandleShopInput();
        }
        else if (state == ScreenState.Records || state == ScreenState.Guide || state == ScreenState.Information ||
            state == ScreenState.Author || state == ScreenState.LinkPage || state == ScreenState.Result)
        {
            if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
            {
                SwitchTo(ScreenState.MainMenu);
            }
        }
    }

    private void OnGUI()
    {
        EnsureStyle();
        CalculateCanvasTransform();

        GUI.color = Color.black;
        GUI.DrawTexture(new Rect(0, 0, Screen.width, Screen.height), Texture2D.whiteTexture);
        GUI.color = Color.white;
        GUI.Box(canvasRect, GUIContent.none);
        var matrix = GUI.matrix;
        GUI.matrix = Matrix4x4.TRS(canvasRect.position, Quaternion.identity, new Vector3(canvasScale, canvasScale, 1f));
        switch (state)
        {
            case ScreenState.MgLogo:
                DrawSplash("_mglogo", true);
                break;
            case ScreenState.PartnerLogo:
                DrawSplash("_partnerLogo", false);
                break;
            case ScreenState.MainMenu:
                DrawMenu();
                break;
            case ScreenState.HeroSelect:
                DrawHeroSelect();
                break;
            case ScreenState.Board:
                DrawBoard();
                break;
            case ScreenState.Shop:
                DrawShop();
                break;
            case ScreenState.Records:
                DrawRecords();
                break;
            case ScreenState.Guide:
                DrawTextPage();
                break;
            case ScreenState.Information:
                DrawTextPage();
                break;
            case ScreenState.Author:
                DrawTextPage();
                break;
            case ScreenState.LinkPage:
                DrawTextPage();
                break;
            case ScreenState.Result:
                DrawResult();
                break;
        }
        GUI.matrix = matrix;
    }

    private void HandleMenuInput()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            selectedMenuItem = (selectedMenuItem + menuItems.Length - 1) % menuItems.Length;
        }
        else if (Input.GetKeyDown(KeyCode.DownArrow))
        {
            selectedMenuItem = (selectedMenuItem + 1) % menuItems.Length;
        }
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
        {
            ActivateMenuItem(selectedMenuItem);
        }
    }

    private void HandleHeroInput()
    {
        if (Input.GetKeyDown(KeyCode.LeftArrow))
        {
            selectedHero = (selectedHero + heroNames.Length - 1) % heroNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.RightArrow))
        {
            selectedHero = (selectedHero + 1) % heroNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            selectedHero = (selectedHero + heroNames.Length - 3) % heroNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.DownArrow))
        {
            selectedHero = (selectedHero + 3) % heroNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
        {
            heroIndex = selectedHero;
            StartNewGame();
            SwitchTo(ScreenState.Board);
        }
    }

    private void DrawSplash(string name, bool drawUrl)
    {
        GUI.color = Color.white;
        GUI.DrawTexture(new Rect(0, 0, VirtualWidth, VirtualHeight), Texture2D.whiteTexture, ScaleMode.StretchToFill);
        var texture = Load(name);
        if (texture != null)
        {
            DrawCentered(texture, VirtualWidth / 2, VirtualHeight / 2 - (drawUrl ? texture.height / 10 : 0));
        }
        if (drawUrl)
        {
            GUI.color = new Color32(102, 102, 102, 255);
            GUI.Label(new Rect(0, VirtualHeight - 24, VirtualWidth, 20), "www.giaitri321.pro", labelStyle);
        }
        GUI.color = Color.white;
    }

    private void DrawMenu()
    {
        DrawFull("bkmenu");
        DrawCentered(Load("title"), VirtualWidth / 2, 42);
        if (Load("title") == null)
        {
            GUI.Label(new Rect(0, 28, VirtualWidth, 28), "LOAN 12 SU QUAN", labelStyle);
        }

        for (var i = 0; i < menuItems.Length; i++)
        {
            var focused = i == selectedMenuItem;
            var texture = Load(menuItems[i] + (focused ? "focus" : string.Empty));
            var y = 78 + i * 21;
            if (texture != null)
            {
                var rect = CenteredRect(texture, VirtualWidth / 2, y);
                GUI.DrawTexture(rect, texture, ScaleMode.ScaleToFit, true);
                if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
                {
                    selectedMenuItem = i;
                    ActivateMenuItem(i);
                }
            }
            else
            {
                GUI.Label(new Rect(20, y - 10, 200, 22), menuItems[i], labelStyle);
            }
        }
    }

    private void DrawHeroSelect()
    {
        DrawFull("bkmenu");
        GUI.Label(new Rect(0, 24, VirtualWidth, 22), "Chon tuong", labelStyle);
        for (var i = 0; i < heroNames.Length; i++)
        {
            var col = i % 3;
            var row = i / 3;
            var rect = new Rect(34 + col * 60, 62 + row * 72, 52, 58);
            if (i == selectedHero)
            {
                DrawFocus(new Rect(rect.x - 3, rect.y - 3, rect.width + 6, rect.height + 6), "focusitem");
            }

            DrawAvatar(i, new Rect(rect.x + 11, rect.y + 4, 30, 30));
            GUI.Label(new Rect(rect.x - 5, rect.y + 37, 62, 18), heroNames[i], smallLabelStyle);
            if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            {
                selectedHero = i;
                heroIndex = selectedHero;
                StartNewGame();
                SwitchTo(ScreenState.Board);
            }
        }

        GUI.Label(new Rect(20, 218, 200, 34), heroDescriptions[selectedHero], smallLabelStyle);
        GUI.Label(new Rect(0, 268, VirtualWidth, 18), "Enter chon, Esc ve menu", smallLabelStyle);
    }

    private void DrawBoard()
    {
        DrawFull("bkboard");
        var top = 54;
        var left = 24;
        var cell = 27;
        DrawHud();

        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                var rect = new Rect(left + x * cell, top + y * cell, 24, 24);
                if (x == cursorX && y == cursorY)
                {
                    DrawFocus(rect, "focus1");
                }

                if (x == selectedX && y == selectedY)
                {
                    DrawFocus(rect, "focus2");
                }

                var piece = board[x, y];
                if (piece == EmptyPiece)
                {
                    continue;
                }

                var texture = Load(pieces[piece]);
                if (texture == null)
                {
                    continue;
                }

                GUI.DrawTexture(rect, texture, ScaleMode.ScaleToFit, true);
                if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
                {
                    cursorX = x;
                    cursorY = y;
                    SelectCell(x, y);
                }
            }
        }

        GUI.Label(new Rect(8, 242, 224, 18), message, smallLabelStyle);
        GUI.Label(new Rect(8, 258, 224, 14), "Skill: phim 1-9", smallLabelStyle);
        DrawBoardAction(6, 274, 0, "K");
        DrawBoardAction(44, 274, 1, "S");
        DrawBoardAction(82, 274, 2, "G");
        DrawBoardAction(120, 274, 3, "A");
        DrawBoardAction(158, 274, 4, "P");
        DrawBoardAction(196, 274, 5, "N");
        GUI.Label(new Rect(6, 298, 72, 16), "Man " + level, smallLabelStyle);
        GUI.Label(new Rect(84, 298, 72, 16), "Di " + movesLeft, smallLabelStyle);
        GUI.Label(new Rect(162, 298, 72, 16), "Vang " + gold, smallLabelStyle);
        DrawEffects();
    }

    private void ActivateMenuItem(int index)
    {
        switch (index)
        {
            case 0:
                if (LoadGame())
                {
                    SwitchTo(ScreenState.Board);
                }
                else
                {
                    message = "Chua co du lieu choi tiep.";
                }

                break;
            case 1:
                selectedHero = heroIndex;
                SwitchTo(ScreenState.HeroSelect);
                break;
            case 2:
                SwitchTo(ScreenState.Records);
                break;
            case 3:
                SwitchTo(ScreenState.Shop);
                break;
            case 4:
                OpenTextPage(ScreenState.Information, "Thong tin", "Loan 12 Su Quan ban Unity port. Tai nguyen da convert tu J2ME, gameplay duoc dung lai cho man hinh 240x320 va iOS hien dai.");
                break;
            case 5:
                OpenTextPage(ScreenState.LinkPage, "Tang game", "Chuc nang tang game qua SMS/J2ME khong con phu hop tren Unity. Ban co the chia se ban build iOS/APK sau khi build.");
                break;
            case 6:
                OpenTextPage(ScreenState.LinkPage, "Chat Ola", "Dich vu Chat Ola trong ban Java goc da cu. Unity port giu man nay nhu trang thong tin.");
                break;
            case 7:
                OpenTextPage(ScreenState.LinkPage, "Game khac", "Danh sach game khac cua nha phat hanh goc khong co du lieu offline trong repo nay.");
                break;
            case 8:
                OpenTextPage(ScreenState.Guide, "Huong dan", "Doi 2 quan canh nhau de tao hang 3 tro len. Phim 1-9 dung 9 ky nang. K/S/G/A/P/N dung 6 vat pham goc. Kiem va am duong gay sat thuong, tim hoi mau, vang cong tien, sach tang mana.");
                break;
            case 9:
                OpenTextPage(ScreenState.Author, "Tac gia", "Game Java goc: Loan 12 Su Quan. Unity port trong repo nay giu lai asset goc va phuc dung gameplay offline.");
                break;
        }
    }

    private void SwitchTo(ScreenState next)
    {
        state = next;
        stateStartedAt = Time.realtimeSinceStartup;
    }

    private Texture2D Load(string path)
    {
        if (string.IsNullOrEmpty(path))
        {
            return null;
        }

        Texture2D texture;
        if (!textures.TryGetValue(path, out texture))
        {
            texture = Resources.Load<Texture2D>("Loan12/" + path);
            textures[path] = texture;
        }

        return texture;
    }

    private void DrawFull(string name)
    {
        var texture = Load(name);
        if (texture != null)
        {
            GUI.DrawTexture(new Rect(0, 0, VirtualWidth, VirtualHeight), texture, ScaleMode.StretchToFill, true);
        }
    }

    private void DrawCentered(Texture2D texture, float x, float y)
    {
        if (texture == null)
        {
            return;
        }

        GUI.DrawTexture(CenteredRect(texture, x, y), texture, ScaleMode.ScaleToFit, true);
    }

    private static Rect CenteredRect(Texture texture, float x, float y)
    {
        return new Rect(x - texture.width / 2f, y - texture.height / 2f, texture.width, texture.height);
    }

    private void CalculateCanvasTransform()
    {
        canvasScale = Mathf.Min(Screen.width / (float)VirtualWidth, Screen.height / (float)VirtualHeight);
        var width = VirtualWidth * canvasScale;
        var height = VirtualHeight * canvasScale;
        canvasRect = new Rect((Screen.width - width) / 2f, (Screen.height - height) / 2f, width, height);
    }

    private void CreateBoard()
    {
        board = new int[BoardSize, BoardSize];
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                do
                {
                    board[x, y] = Random.Range(0, pieces.Length);
                }
                while (CreatesImmediateMatch(x, y));
            }
        }
    }

    private void StartNewGame(bool persist = true)
    {
        level = 1;
        score = 0;
        gold = 20;
        mana = 0;
        maxHealth = heroIndex == 2 || heroIndex == 5 ? 120 : 100;
        health = maxHealth;
        shieldTurns = 0;
        frozenTurns = 0;
        inventory = new[] { 1, 0, 0, 1, 1, 0 };
        effects.Clear();
        powerAttackTurns = inventory[5] > 0 ? 1 : 0;
        ginsengUsed = false;
        StartLevel(level);
        if (persist)
        {
            SaveGame();
        }
    }

    private void StartLevel(int nextLevel)
    {
        level = nextLevel;
        enemyName = enemyNames[ClampIndex(level - 1, enemyNames.Length)];
        enemyMaxHealth = 45 + (level - 1) * 28;
        enemyHealth = enemyMaxHealth;
        targetScore = 180 + (level - 1) * 120;
        movesLeft = Mathf.Max(16, 25 - level);
        enemyAttack = 6 + level * 2;
        cursorX = BoardSize / 2;
        cursorY = BoardSize / 2;
        ClearSelection();
        CreateBoard();
        EnsurePlayableBoard();
        message = "Danh bai " + enemyName + ".";
        effects.Clear();
    }

    private void HandleBoardInput()
    {
        if (Input.GetKeyDown(KeyCode.LeftArrow))
        {
            cursorX = Mathf.Max(0, cursorX - 1);
        }
        else if (Input.GetKeyDown(KeyCode.RightArrow))
        {
            cursorX = Mathf.Min(BoardSize - 1, cursorX + 1);
        }
        else if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            cursorY = Mathf.Max(0, cursorY - 1);
        }
        else if (Input.GetKeyDown(KeyCode.DownArrow))
        {
            cursorY = Mathf.Min(BoardSize - 1, cursorY + 1);
        }
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
        {
            SelectCell(cursorX, cursorY);
        }
        else if (Input.GetKeyDown(KeyCode.K))
        {
            UseItem(0);
        }
        else if (Input.GetKeyDown(KeyCode.S))
        {
            UseItem(1);
        }
        else if (Input.GetKeyDown(KeyCode.G))
        {
            UseItem(2);
        }
        else if (Input.GetKeyDown(KeyCode.A))
        {
            UseItem(3);
        }
        else if (Input.GetKeyDown(KeyCode.P))
        {
            UseItem(4);
        }
        else if (Input.GetKeyDown(KeyCode.N))
        {
            UseItem(5);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha1) || Input.GetKeyDown(KeyCode.Keypad1))
        {
            UseSkill(0);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha2) || Input.GetKeyDown(KeyCode.Keypad2))
        {
            UseSkill(1);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha3) || Input.GetKeyDown(KeyCode.Keypad3))
        {
            UseSkill(2);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha4) || Input.GetKeyDown(KeyCode.Keypad4))
        {
            UseSkill(3);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha5) || Input.GetKeyDown(KeyCode.Keypad5))
        {
            UseSkill(4);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha6) || Input.GetKeyDown(KeyCode.Keypad6))
        {
            UseSkill(5);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha7) || Input.GetKeyDown(KeyCode.Keypad7))
        {
            UseSkill(6);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha8) || Input.GetKeyDown(KeyCode.Keypad8))
        {
            UseSkill(7);
        }
        else if (Input.GetKeyDown(KeyCode.Alpha9) || Input.GetKeyDown(KeyCode.Keypad9))
        {
            UseSkill(8);
        }
    }

    private void SelectCell(int x, int y)
    {
        if (selectedX < 0)
        {
            selectedX = x;
            selectedY = y;
            message = "Chon quan ke ben de doi.";
            return;
        }

        if (selectedX == x && selectedY == y)
        {
            ClearSelection();
            message = "Da bo chon.";
            return;
        }

        if (!AreAdjacent(selectedX, selectedY, x, y))
        {
            selectedX = x;
            selectedY = y;
            message = "Hai quan phai nam canh nhau.";
            return;
        }

        TrySwap(selectedX, selectedY, x, y);
        ClearSelection();
    }

    private void TrySwap(int ax, int ay, int bx, int by)
    {
        Swap(ax, ay, bx, by);
        if (!HasAnyMatch())
        {
            Swap(ax, ay, bx, by);
            message = "Nuoc nay khong tao lien ket.";
            return;
        }

        movesLeft--;
        var removed = ResolveMatches();
        if (powerAttackTurns > 0)
        {
            powerAttackTurns--;
        }

        EnemyTurn();
        CheckLevelState(removed);
        SaveGame();
    }

    private int ResolveMatches()
    {
        var totalRemoved = 0;
        var chain = 0;
        while (true)
        {
            int[] removedByType;
            var matches = FindMatches(out removedByType);
            var removed = CountMarked(matches);
            if (removed == 0)
            {
                break;
            }

            chain++;
            totalRemoved += removed;
            ApplyRewards(removedByType, chain);
            RemoveMarked(matches);
            CollapseBoard();
        }

        EnsurePlayableBoard();
        message = "Pha " + totalRemoved + " quan. Sat thuong " + Mathf.Max(1, totalRemoved * 2) + ".";
        return totalRemoved;
    }

    private void ApplyRewards(int[] removedByType, int chain)
    {
        var chainBonus = chain - 1;
        for (var i = 0; i < removedByType.Length; i++)
        {
            var count = removedByType[i];
            if (count == 0)
            {
                continue;
            }

            var attackMultiplier = powerAttackTurns > 0 ? 2 : 1;
            score += count * 10 + chainBonus * 5;
            switch (i)
            {
                case 0:
                    enemyHealth -= count * (4 + chain + (heroIndex == 3 ? 1 : 0)) * attackMultiplier;
                    break;
                case 1:
                    score += count * 6;
                    break;
                case 2:
                    health = Mathf.Min(maxHealth, health + count * (heroIndex == 2 ? 5 : 3));
                    break;
                case 3:
                    mana = Mathf.Min(99, mana + count * (heroIndex == 1 ? 3 : 2));
                    enemyHealth -= count * 2 * attackMultiplier;
                    break;
                case 4:
                    gold += count;
                    break;
                case 5:
                    mana = Mathf.Min(99, mana + count * 3);
                    score += count * 8;
                    break;
            }
        }

        enemyHealth = Mathf.Max(0, enemyHealth);
    }

    private void EnemyTurn()
    {
        if (enemyHealth <= 0)
        {
            return;
        }

        if (frozenTurns > 0)
        {
            frozenTurns--;
            message += " Doi thu bi dong bang.";
            return;
        }

        var damage = enemyAttack;
        if (shieldTurns > 0)
        {
            damage = Mathf.Max(1, damage / 4);
            shieldTurns--;
        }

        health = Mathf.Max(0, health - damage);
    }

    private void CheckLevelState(int removed)
    {
        UpdateRecords();
        if (enemyHealth <= 0)
        {
            if (level >= MaxLevel)
            {
                DeleteSave();
                ShowResult("Thang loi", "Da hoan thanh 12 man. Diem " + score + ", vang " + gold + ".");
                return;
            }

            StartLevel(level + 1);
            message = "Qua man. Bat dau man " + level + ".";
            SaveGame();
            return;
        }

        if (health <= 0 || movesLeft <= 0)
        {
            DeleteSave();
            ShowResult("That bai", "Dung o man " + level + ". Diem " + score + ", vang " + gold + ".");
            return;
        }

        if (removed > 0)
        {
            message += " Doi thu con " + enemyHealth + "/" + enemyMaxHealth + ".";
        }
    }

    private void ShowResult(string title, string body)
    {
        UpdateRecords();
        SaveRecords();
        resultTitle = title;
        resultBody = body;
        SwitchTo(ScreenState.Result);
    }

    private void HandleShopInput()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            selectedShopItem = (selectedShopItem + shopNames.Length - 1) % shopNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.DownArrow))
        {
            selectedShopItem = (selectedShopItem + 1) % shopNames.Length;
        }
        else if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.Space))
        {
            BuyShopItem(selectedShopItem);
        }
    }

    private void DrawShop()
    {
        DrawFull("bkmenu");
        GUI.Label(new Rect(0, 30, VirtualWidth, 22), "Cua hang", labelStyle);
        GUI.Label(new Rect(16, 55, 100, 16), "Vang: " + gold, leftLabelStyle);
        GUI.Label(new Rect(126, 55, 100, 16), "Tui do", smallLabelStyle);

        for (var i = 0; i < shopNames.Length; i++)
        {
            var y = 82 + i * 42;
            var focused = selectedShopItem == i;
            var rect = new Rect(18, y, 204, 36);
            if (focused)
            {
                DrawFocus(rect, "focusitem");
            }

            DrawItemIcon(i, new Rect(24, y + 4, 28, 28));
            GUI.Label(new Rect(56, y + 2, 86, 14), shopNames[i] + " x" + inventory[i], leftLabelStyle);
            GUI.Label(new Rect(148, y + 2, 64, 14), shopPrices[i] > 0 ? shopPrices[i] + " vang" : "SMS", smallLabelStyle);
            GUI.Label(new Rect(56, y + 17, 154, 16), shopDescriptions[i], leftLabelStyle);
            if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
            {
                selectedShopItem = i;
                BuyShopItem(i);
            }
        }

        GUI.Label(new Rect(16, 258, 208, 34), message, smallLabelStyle);
        GUI.Label(new Rect(0, 292, VirtualWidth, 18), "Enter mua, Esc ve menu", smallLabelStyle);
    }

    private void DrawRecords()
    {
        DrawFull("bkmenu");
        GUI.Label(new Rect(0, 42, VirtualWidth, 24), "Ky luc", labelStyle);
        GUI.Label(new Rect(28, 92, 184, 28), "Diem cao: " + bestScore, labelStyle);
        GUI.Label(new Rect(28, 128, 184, 28), "Man cao nhat: " + bestLevel + "/" + MaxLevel, labelStyle);
        GUI.Label(new Rect(24, 178, 192, 50), HasSave() ? "Co du lieu choi tiep." : "Chua co du lieu choi tiep.", smallLabelStyle);
        GUI.Label(new Rect(0, 260, VirtualWidth, 20), "Enter de quay lai", smallLabelStyle);
    }

    private void DrawTextPage()
    {
        DrawFull("bkmenu");
        GUI.Label(new Rect(0, 34, VirtualWidth, 24), pageTitle, labelStyle);
        GUI.Label(new Rect(18, 78, 204, 132), pageBody, leftLabelStyle);
        GUI.Label(new Rect(0, 250, VirtualWidth, 24), "Enter de quay lai", smallLabelStyle);
    }

    private void OpenTextPage(ScreenState next, string title, string body)
    {
        pageTitle = title;
        pageBody = body;
        SwitchTo(next);
    }

    private void DrawHud()
    {
        DrawAvatar(heroIndex, new Rect(6, 6, 24, 24));
        GUI.Label(new Rect(32, 5, 84, 13), "Minh", leftLabelStyle);
        DrawBar(new Rect(32, 20, 84, 8), health, maxHealth, new Color32(40, 175, 75, 255));
        GUI.Label(new Rect(32, 30, 84, 12), health + "/" + maxHealth + "  M" + mana, smallLabelStyle);

        DrawEnemyFace(new Rect(210, 6, 24, 24));
        GUI.Label(new Rect(124, 5, 82, 13), enemyName, leftLabelStyle);
        DrawBar(new Rect(124, 20, 82, 8), enemyHealth, enemyMaxHealth, new Color32(190, 40, 40, 255));
        GUI.Label(new Rect(124, 30, 82, 12), "Dich " + enemyHealth + "/" + enemyMaxHealth, smallLabelStyle);

        GUI.Label(new Rect(58, 42, 124, 12), "Diem " + score + "/" + targetScore, smallLabelStyle);
    }

    private void DrawBoardAction(int x, int y, int itemIndex, string key)
    {
        var rect = new Rect(x, y, 36, 20);
        GUI.Box(rect, GUIContent.none);
        DrawItemIcon(itemIndex, new Rect(x + 2, y + 2, 16, 16));
        GUI.Label(new Rect(x + 16, y + 2, 18, 16), inventory[itemIndex].ToString(), smallLabelStyle);
        if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
        {
            UseItem(itemIndex);
        }
    }

    private void DrawResult()
    {
        DrawFull("bkmenu");
        GUI.Label(new Rect(0, 70, VirtualWidth, 28), resultTitle, labelStyle);
        GUI.Label(new Rect(18, 120, 204, 80), resultBody, labelStyle);
        GUI.Label(new Rect(0, 236, VirtualWidth, 24), "Enter de ve menu", smallLabelStyle);
    }

    private void BuyShopItem(int itemIndex)
    {
        if (itemIndex <= 2)
        {
            if (itemIndex == 2)
            {
                gold += shopAmounts[itemIndex];
                message = "Da nhan " + shopAmounts[itemIndex] + " vang.";
                SaveGame();
                return;
            }

            inventory[itemIndex] += shopAmounts[itemIndex];
            message = "Da nhan " + shopNames[itemIndex] + " x" + shopAmounts[itemIndex] + ".";
            SaveGame();
            return;
        }

        if (gold < shopPrices[itemIndex])
        {
            message = "Khong du vang de mua " + shopNames[itemIndex] + ".";
            return;
        }

        gold -= shopPrices[itemIndex];
        inventory[itemIndex] += shopAmounts[itemIndex];
        message = "Da mua " + shopNames[itemIndex] + ".";
        SaveGame();
    }

    private void UseItem(int itemIndex)
    {
        if (inventory[itemIndex] <= 0)
        {
            message = "Khong co " + shopNames[itemIndex] + ".";
            return;
        }

        switch (itemIndex)
        {
            case 0:
                powerAttackTurns = 1;
                message = "Long Than Kiem san sang.";
                break;
            case 1:
                if (ginsengUsed)
                {
                    message = "Nhan Sam chi dung 1 lan moi tran.";
                    return;
                }

                ginsengUsed = true;
                maxHealth *= 2;
                health = maxHealth;
                message = "Nhan Sam tang gap doi sinh luc.";
                break;
            case 2:
                gold += 1000;
                message = "Ngan Luong cong 1000 vang.";
                break;
            case 3:
                shieldTurns = Mathf.Max(shieldTurns, 1);
                message = "Quy Dien Giap chan don tiep theo.";
                break;
            case 4:
                if (health >= maxHealth)
                {
                    message = "Mau da day.";
                    return;
                }

                health = Mathf.Min(maxHealth, health + Mathf.Max(1, maxHealth / 10));
                message = "Binh Thuoc hoi 10% sinh luc.";
                break;
            case 5:
                powerAttackTurns = Mathf.Max(powerAttackTurns, 1);
                message = "Ngoc An giup uu tien tan cong.";
                break;
        }

        inventory[itemIndex]--;
        CheckLevelState(1);
        SaveGame();
    }

    private void UseSkill(int skillIndex)
    {
        var costs = (int[])skillCosts.Clone();
        if (heroIndex == 1 || heroIndex == 4)
        {
            costs[skillIndex] = Mathf.Max(5, costs[skillIndex] - 3);
        }

        if (mana < costs[skillIndex])
        {
            message = "Khong du mana.";
            return;
        }

        mana -= costs[skillIndex];
        switch (skillIndex)
        {
            case 0:
                enemyHealth = Mathf.Max(0, enemyHealth - (20 + level * 4));
                ClearArea(cursorX - 1, cursorY - 1, 3, 3);
                AddScreenEffect("fireball", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 1:
                enemyHealth = Mathf.Max(0, enemyHealth - (22 + level * 4));
                for (var i = 0; i < Random.Range(3, 6); i++)
                {
                    ClearArea(Random.Range(0, BoardSize - 1), Random.Range(0, BoardSize - 1), 2, 2);
                }
                AddScreenEffect("meteoricon", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 2:
                enemyHealth = Mathf.Max(0, enemyHealth - (30 + level * 5));
                ClearArea(0, 0, 4, 4);
                ClearArea(BoardSize - 4, BoardSize - 4, 4, 4);
                AddScreenEffect("hellfire", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 3:
                enemyHealth = Mathf.Max(0, enemyHealth - (16 + level * 3));
                ClearRandomCells(Random.Range(4, 9));
                AddScreenEffect("chainlighting", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 4:
                shieldTurns = 6;
                AddScreenEffect("shieldlighting", 48);
                message = skillNames[skillIndex] + " trong 6 luot.";
                break;
            case 5:
                enemyHealth = Mathf.Max(0, enemyHealth - (28 + level * 4));
                for (var i = 0; i < Random.Range(3, 7); i++)
                {
                    ClearArea(Random.Range(0, BoardSize - 2), Random.Range(0, BoardSize - 2), 3, 3);
                }
                AddScreenEffect("blastlighting", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 6:
                enemyHealth = Mathf.Max(0, enemyHealth - (14 + level * 3));
                enemyAttack = Mathf.Max(1, enemyAttack - 2);
                AddScreenEffect("icebolt", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 7:
                health = Mathf.Min(maxHealth, health + Mathf.Max(1, maxHealth / 5));
                ClearAllPiece(2);
                ResolveMatches();
                AddScreenEffect("healing", 48);
                message = skillNames[skillIndex] + ".";
                break;
            case 8:
                frozenTurns = heroIndex == 5 ? 3 : 2;
                enemyHealth = Mathf.Max(0, enemyHealth - (18 + level * 4));
                enemyAttack = Mathf.Max(1, enemyAttack - 4);
                AddScreenEffect("frozen", 48);
                message = skillNames[skillIndex] + ".";
                break;
        }

        CollapseBoard();
        ResolveMatches();
        CheckLevelState(1);
        SaveGame();
    }

    private void ClearCross(int centerX, int centerY)
    {
        var removedByType = new int[pieces.Length];
        for (var i = 0; i < BoardSize; i++)
        {
            CountAndClear(centerX, i, removedByType);
            CountAndClear(i, centerY, removedByType);
        }

        ApplyRewards(removedByType, 1);
        CollapseBoard();
        EnsurePlayableBoard();
    }

    private void ClearArea(int startX, int startY, int width, int height)
    {
        var removedByType = new int[pieces.Length];
        for (var y = Mathf.Max(0, startY); y < Mathf.Min(BoardSize, startY + height); y++)
        {
            for (var x = Mathf.Max(0, startX); x < Mathf.Min(BoardSize, startX + width); x++)
            {
                CountAndClear(x, y, removedByType);
            }
        }

        ApplyRewards(removedByType, 1);
    }

    private void ClearRandomCells(int count)
    {
        var removedByType = new int[pieces.Length];
        for (var i = 0; i < count; i++)
        {
            CountAndClear(Random.Range(0, BoardSize), Random.Range(0, BoardSize), removedByType);
        }

        ApplyRewards(removedByType, 1);
    }

    private void ClearAllPiece(int pieceType)
    {
        var removedByType = new int[pieces.Length];
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                if (board[x, y] == pieceType)
                {
                    CountAndClear(x, y, removedByType);
                }
            }
        }

        ApplyRewards(removedByType, 1);
    }

    private void CountAndClear(int x, int y, int[] removedByType)
    {
        var piece = board[x, y];
        if (piece == EmptyPiece)
        {
            return;
        }

        removedByType[piece]++;
        AddPieceEffect(piece, x, y);
        board[x, y] = EmptyPiece;
    }

    private void AddPieceEffect(int piece, int x, int y)
    {
        var effectNames = new[] { "explodesword", "exploderice", "explodeheart", "explodeyinyang", "explodegold", "explodebook" };
        AddEffect(effectNames[ClampIndex(piece, effectNames.Length)], 24 + x * 27, 54 + y * 27, 18);
    }

    private void AddScreenEffect(string textureName, int frames)
    {
        AddEffect(textureName, VirtualWidth / 2 - 32, 92, frames);
    }

    private void AddEffect(string textureName, float x, float y, int frames)
    {
        effects.Add(new Effect(textureName, new Rect(x, y, 64, 64), frames));
        if (effects.Count > 24)
        {
            effects.RemoveAt(0);
        }
    }

    private void DrawEffects()
    {
        for (var i = effects.Count - 1; i >= 0; i--)
        {
            var effect = effects[i];
            var texture = Load(effect.TextureName);
            if (texture != null)
            {
                var alpha = Mathf.Clamp01(effect.FramesLeft / (float)effect.TotalFrames);
                GUI.color = new Color(1f, 1f, 1f, alpha);
                GUI.DrawTexture(effect.Rect, texture, ScaleMode.ScaleToFit, true);
                GUI.color = Color.white;
            }

            effect.FramesLeft--;
            if (effect.FramesLeft <= 0)
            {
                effects.RemoveAt(i);
            }
        }
    }

    private void DrawAvatar(int index, Rect rect)
    {
        var avatar = Load("avatar");
        if (avatar == null)
        {
            GUI.Box(rect, GUIContent.none);
            return;
        }

        GUI.DrawTexture(rect, avatar, ScaleMode.ScaleToFit, true);
    }

    private void DrawEnemyFace(Rect rect)
    {
        var texture = Load("swordred");
        if (texture == null)
        {
            texture = Load("sword");
        }

        if (texture != null)
        {
            GUI.DrawTexture(rect, texture, ScaleMode.ScaleToFit, true);
        }
        else
        {
            GUI.Box(rect, GUIContent.none);
        }
    }

    private void DrawItemIcon(int itemIndex, Rect rect)
    {
        var fallbackNames = new[] { "sword", "heart", "gold", "defenceshield", "healingicon", "star" };
        var fallback = Load(fallbackNames[ClampIndex(itemIndex, fallbackNames.Length)]);
        if (fallback != null)
        {
            GUI.DrawTexture(rect, fallback, ScaleMode.ScaleToFit, true);
        }
    }

    private void DrawBar(Rect rect, int value, int max, Color color)
    {
        GUI.color = new Color32(35, 35, 35, 255);
        GUI.DrawTexture(rect, Texture2D.whiteTexture);
        GUI.color = color;
        var width = max <= 0 ? 0f : rect.width * Mathf.Clamp01(value / (float)max);
        GUI.DrawTexture(new Rect(rect.x, rect.y, width, rect.height), Texture2D.whiteTexture);
        GUI.color = Color.white;
    }

    private void DrawFocus(Rect rect, string textureName)
    {
        var focus = Load(textureName);
        if (focus != null)
        {
            GUI.DrawTexture(new Rect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4), focus, ScaleMode.StretchToFill, true);
            return;
        }

        GUI.color = new Color32(255, 255, 255, 90);
        GUI.DrawTexture(rect, Texture2D.whiteTexture);
        GUI.color = Color.white;
    }

    private bool[,] FindMatches(out int[] removedByType)
    {
        var marks = new bool[BoardSize, BoardSize];
        removedByType = new int[pieces.Length];

        for (var y = 0; y < BoardSize; y++)
        {
            var runStart = 0;
            for (var x = 1; x <= BoardSize; x++)
            {
                if (x < BoardSize && board[x, y] == board[runStart, y] && board[x, y] != EmptyPiece)
                {
                    continue;
                }

                MarkRun(marks, removedByType, runStart, y, x - runStart, true);
                runStart = x;
            }
        }

        for (var x = 0; x < BoardSize; x++)
        {
            var runStart = 0;
            for (var y = 1; y <= BoardSize; y++)
            {
                if (y < BoardSize && board[x, y] == board[x, runStart] && board[x, y] != EmptyPiece)
                {
                    continue;
                }

                MarkRun(marks, removedByType, x, runStart, y - runStart, false);
                runStart = y;
            }
        }

        return marks;
    }

    private void MarkRun(bool[,] marks, int[] removedByType, int startX, int startY, int length, bool horizontal)
    {
        if (length < 3)
        {
            return;
        }

        for (var i = 0; i < length; i++)
        {
            var x = horizontal ? startX + i : startX;
            var y = horizontal ? startY : startY + i;
            if (!marks[x, y])
            {
                removedByType[board[x, y]]++;
            }

            marks[x, y] = true;
        }
    }

    private bool HasAnyMatch()
    {
        int[] removedByType;
        var matches = FindMatches(out removedByType);
        return CountMarked(matches) > 0;
    }

    private int CountMarked(bool[,] marks)
    {
        var count = 0;
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                if (marks[x, y])
                {
                    count++;
                }
            }
        }

        return count;
    }

    private void RemoveMarked(bool[,] marks)
    {
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                if (marks[x, y])
                {
                    AddPieceEffect(board[x, y], x, y);
                    board[x, y] = EmptyPiece;
                }
            }
        }
    }

    private void CollapseBoard()
    {
        for (var x = 0; x < BoardSize; x++)
        {
            var writeY = BoardSize - 1;
            for (var y = BoardSize - 1; y >= 0; y--)
            {
                if (board[x, y] == EmptyPiece)
                {
                    continue;
                }

                board[x, writeY] = board[x, y];
                if (writeY != y)
                {
                    board[x, y] = EmptyPiece;
                }

                writeY--;
            }

            for (var y = writeY; y >= 0; y--)
            {
                board[x, y] = Random.Range(0, pieces.Length);
            }
        }
    }

    private void EnsurePlayableBoard()
    {
        var guard = 0;
        while (!HasAvailableMove() && guard < 20)
        {
            CreateBoard();
            guard++;
        }
    }

    private bool HasAvailableMove()
    {
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                if (x + 1 < BoardSize && WouldCreateMatch(x, y, x + 1, y))
                {
                    return true;
                }

                if (y + 1 < BoardSize && WouldCreateMatch(x, y, x, y + 1))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private bool WouldCreateMatch(int ax, int ay, int bx, int by)
    {
        Swap(ax, ay, bx, by);
        var result = HasAnyMatch();
        Swap(ax, ay, bx, by);
        return result;
    }

    private bool CreatesImmediateMatch(int x, int y)
    {
        var piece = board[x, y];
        if (x >= 2 && board[x - 1, y] == piece && board[x - 2, y] == piece)
        {
            return true;
        }

        return y >= 2 && board[x, y - 1] == piece && board[x, y - 2] == piece;
    }

    private static bool AreAdjacent(int ax, int ay, int bx, int by)
    {
        return Mathf.Abs(ax - bx) + Mathf.Abs(ay - by) == 1;
    }

    private static int ClampIndex(int value, int length)
    {
        if (value < 0)
        {
            return 0;
        }

        return value >= length ? length - 1 : value;
    }

    private void Swap(int ax, int ay, int bx, int by)
    {
        var temp = board[ax, ay];
        board[ax, ay] = board[bx, by];
        board[bx, by] = temp;
    }

    private void ClearSelection()
    {
        selectedX = -1;
        selectedY = -1;
    }

    private void SaveGame()
    {
        if (board == null)
        {
            return;
        }

        PlayerPrefs.SetInt(SavePrefix + "HasSave", 1);
        PlayerPrefs.SetInt(SavePrefix + "Level", level);
        PlayerPrefs.SetInt(SavePrefix + "Moves", movesLeft);
        PlayerPrefs.SetInt(SavePrefix + "Score", score);
        PlayerPrefs.SetInt(SavePrefix + "Gold", gold);
        PlayerPrefs.SetInt(SavePrefix + "Mana", mana);
        PlayerPrefs.SetInt(SavePrefix + "Hero", heroIndex);
        PlayerPrefs.SetInt(SavePrefix + "MaxHealth", maxHealth);
        PlayerPrefs.SetInt(SavePrefix + "Health", health);
        PlayerPrefs.SetInt(SavePrefix + "EnemyHealth", enemyHealth);
        PlayerPrefs.SetInt(SavePrefix + "EnemyMaxHealth", enemyMaxHealth);
        PlayerPrefs.SetInt(SavePrefix + "TargetScore", targetScore);
        PlayerPrefs.SetInt(SavePrefix + "EnemyAttack", enemyAttack);
        PlayerPrefs.SetInt(SavePrefix + "ShieldTurns", shieldTurns);
        PlayerPrefs.SetInt(SavePrefix + "FrozenTurns", frozenTurns);
        PlayerPrefs.SetInt(SavePrefix + "PowerAttackTurns", powerAttackTurns);
        PlayerPrefs.SetInt(SavePrefix + "GinsengUsed", ginsengUsed ? 1 : 0);
        for (var i = 0; i < inventory.Length; i++)
        {
            PlayerPrefs.SetInt(SavePrefix + "Item" + i, inventory[i]);
        }

        PlayerPrefs.SetString(SavePrefix + "Board", SerializeBoard());
        SaveRecords();
        PlayerPrefs.Save();
    }

    private bool LoadGame()
    {
        if (!HasSave())
        {
            return false;
        }

        level = PlayerPrefs.GetInt(SavePrefix + "Level", 1);
        movesLeft = PlayerPrefs.GetInt(SavePrefix + "Moves", 20);
        score = PlayerPrefs.GetInt(SavePrefix + "Score", 0);
        gold = PlayerPrefs.GetInt(SavePrefix + "Gold", 0);
        mana = PlayerPrefs.GetInt(SavePrefix + "Mana", 0);
        heroIndex = PlayerPrefs.GetInt(SavePrefix + "Hero", 0);
        maxHealth = PlayerPrefs.GetInt(SavePrefix + "MaxHealth", heroIndex == 2 || heroIndex == 5 ? 120 : 100);
        health = PlayerPrefs.GetInt(SavePrefix + "Health", maxHealth);
        enemyMaxHealth = PlayerPrefs.GetInt(SavePrefix + "EnemyMaxHealth", 45);
        enemyHealth = PlayerPrefs.GetInt(SavePrefix + "EnemyHealth", enemyMaxHealth);
        targetScore = PlayerPrefs.GetInt(SavePrefix + "TargetScore", 180);
        enemyAttack = PlayerPrefs.GetInt(SavePrefix + "EnemyAttack", 8);
        shieldTurns = PlayerPrefs.GetInt(SavePrefix + "ShieldTurns", 0);
        frozenTurns = PlayerPrefs.GetInt(SavePrefix + "FrozenTurns", 0);
        powerAttackTurns = PlayerPrefs.GetInt(SavePrefix + "PowerAttackTurns", 0);
        ginsengUsed = PlayerPrefs.GetInt(SavePrefix + "GinsengUsed", 0) == 1;
        enemyName = enemyNames[ClampIndex(level - 1, enemyNames.Length)];
        for (var i = 0; i < inventory.Length; i++)
        {
            inventory[i] = PlayerPrefs.GetInt(SavePrefix + "Item" + i, 0);
        }

        if (!DeserializeBoard(PlayerPrefs.GetString(SavePrefix + "Board", string.Empty)))
        {
            CreateBoard();
            EnsurePlayableBoard();
        }

        ClearSelection();
        cursorX = BoardSize / 2;
        cursorY = BoardSize / 2;
        message = "Da tai du lieu man " + level + ".";
        return true;
    }

    private bool HasSave()
    {
        return PlayerPrefs.GetInt(SavePrefix + "HasSave", 0) == 1;
    }

    private void DeleteSave()
    {
        PlayerPrefs.DeleteKey(SavePrefix + "HasSave");
        PlayerPrefs.DeleteKey(SavePrefix + "Board");
        PlayerPrefs.Save();
    }

    private void LoadRecords()
    {
        bestScore = PlayerPrefs.GetInt(SavePrefix + "BestScore", 0);
        bestLevel = PlayerPrefs.GetInt(SavePrefix + "BestLevel", 0);
    }

    private void UpdateRecords()
    {
        bestScore = Mathf.Max(bestScore, score);
        bestLevel = Mathf.Max(bestLevel, level);
    }

    private void SaveRecords()
    {
        UpdateRecords();
        PlayerPrefs.SetInt(SavePrefix + "BestScore", bestScore);
        PlayerPrefs.SetInt(SavePrefix + "BestLevel", bestLevel);
    }

    private string SerializeBoard()
    {
        var chars = new char[BoardSize * BoardSize];
        var index = 0;
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                chars[index++] = (char)('0' + Mathf.Clamp(board[x, y], 0, pieces.Length - 1));
            }
        }

        return new string(chars);
    }

    private bool DeserializeBoard(string data)
    {
        if (data.Length != BoardSize * BoardSize)
        {
            return false;
        }

        board = new int[BoardSize, BoardSize];
        var index = 0;
        for (var y = 0; y < BoardSize; y++)
        {
            for (var x = 0; x < BoardSize; x++)
            {
                var value = data[index++] - '0';
                if (value < 0 || value >= pieces.Length)
                {
                    return false;
                }

                board[x, y] = value;
            }
        }

        return true;
    }

    private void EnsureStyle()
    {
        if (labelStyle != null)
        {
            return;
        }

        labelStyle = new GUIStyle(GUI.skin.label)
        {
            alignment = TextAnchor.MiddleCenter,
            fontSize = 12,
            normal = { textColor = Color.white },
            wordWrap = false
        };

        smallLabelStyle = new GUIStyle(labelStyle)
        {
            fontSize = 10,
            wordWrap = true
        };

        leftLabelStyle = new GUIStyle(smallLabelStyle)
        {
            alignment = TextAnchor.UpperLeft,
            wordWrap = true
        };
    }

    private static class PlayerPrefs
    {
        private static readonly Dictionary<string, string> Values = new Dictionary<string, string>();
        private static bool loaded;

        public static void SetInt(string key, int value)
        {
            EnsureLoaded();
            Values[key] = value.ToString();
        }

        public static int GetInt(string key, int defaultValue)
        {
            EnsureLoaded();
            string value;
            int parsed;
            return Values.TryGetValue(key, out value) && int.TryParse(value, out parsed) ? parsed : defaultValue;
        }

        public static void SetString(string key, string value)
        {
            EnsureLoaded();
            Values[key] = value ?? string.Empty;
        }

        public static string GetString(string key, string defaultValue)
        {
            EnsureLoaded();
            string value;
            return Values.TryGetValue(key, out value) ? value : defaultValue;
        }

        public static void DeleteKey(string key)
        {
            EnsureLoaded();
            Values.Remove(key);
        }

        public static void Save()
        {
            EnsureLoaded();
            var lines = new List<string>();
            foreach (var pair in Values)
            {
                lines.Add(pair.Key + "=" + pair.Value);
            }

            using (var writer = new StreamWriter(PrefsPath, false))
            {
                for (var i = 0; i < lines.Count; i++)
                {
                    writer.WriteLine(lines[i]);
                }
            }
        }

        private static void EnsureLoaded()
        {
            if (loaded)
            {
                return;
            }

            loaded = true;
            if (!File.Exists(PrefsPath))
            {
                return;
            }

            var lines = new List<string>();
            using (var reader = new StreamReader(PrefsPath))
            {
                while (!reader.EndOfStream)
                {
                    lines.Add(reader.ReadLine());
                }
            }

            for (var i = 0; i < lines.Count; i++)
            {
                var line = lines[i];
                var separator = line.IndexOf('=');
                if (separator <= 0)
                {
                    continue;
                }

                Values[line.Substring(0, separator)] = line.Substring(separator + 1);
            }
        }

        private static string PrefsPath
        {
            get
            {
                var root = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);
                if (string.IsNullOrEmpty(root))
                {
                    root = Directory.GetCurrentDirectory();
                }

                return Path.Combine(root, "loan12-save.txt");
            }
        }
    }

    private sealed class Effect
    {
        public readonly string TextureName;
        public readonly Rect Rect;
        public readonly int TotalFrames;
        public int FramesLeft;

        public Effect(string textureName, Rect rect, int frames)
        {
            TextureName = textureName;
            Rect = rect;
            TotalFrames = frames;
            FramesLeft = frames;
        }
    }
}
