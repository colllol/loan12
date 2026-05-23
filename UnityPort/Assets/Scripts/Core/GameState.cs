using System;
using System.Collections.Generic;
using UnityEngine;

public class GameState
{
    public int[,] board;
    public int menuIndex;
    public int selectedHero;
    public int heroIndex;
    public int selectedStage = 1;
    public int shopIndex;
    public int level = 1;
    public int movesLeft = 20;
    public int score;
    public int gold = 20;
    public int mana;
    public int heroLevel = 1;
    public int heroXp;
    public int heroAttack;
    public int heroDefense;
    public int heroSkillPower;
    public int health = 100;
    public int maxHealth = 100;
    public int enemyHealth;
    public int enemyMaxHealth;
    public int enemyAttack;
    public int enemyDefense;
    public int targetScore;
    public bool bossBattle;
    public bool endlessMode;
    public bool selectingEndless;
    public int shieldTurns;
    public int frozenTurns;
    public int powerAttackTurns;
    public bool ginsengUsed;
    public int cursorX = 4;
    public int cursorY = 4;
    public int selX = -1;
    public int selY = -1;
    public string enemyName = "Giặc";
    public string message = "";
    public string pageTitle = "";
    public string pageBody = "";
    public string resultTitle = "";
    public string resultBody = "";
    public int bestScore;
    public int bestLevel;
    public int unlockedLevel = 1;
    public int[] inventory = new int[6];
    public int tutorialStep = -1;

    private const string SP = "L12.";

    public struct TutorialInfo
    {
        public string Text;
        public int HighlightX;
        public int HighlightY;
        public TutorialInfo(string text, int hx = -1, int hy = -1)
        { Text = text; HighlightX = hx; HighlightY = hy; }
    }

    public static readonly TutorialInfo[] TutorialSteps = new[]
    {
        new TutorialInfo("Chào mừng! Hãy bấm phím 5 hoặc OK để chọn thanh kiếm.", 4, 4),
        new TutorialInfo("Bấm phím 8 xuống để di chuyển."),
        new TutorialInfo("Khá lắm! Xếp 3 kiếm cùng loại để tấn công."),
        new TutorialInfo("Bạn bị thương! Xếp 3 trái tim để hồi máu."),
        new TutorialInfo("Phía dưới có thanh lương thực. Xếp 3 bánh chưng để lấy lương thực."),
        new TutorialInfo("Thanh xanh ngọc là năng lượng. Xếp 3 âm dương để lấy năng lượng."),
        new TutorialInfo("Nhấn phím 1-9 để dùng tuyệt chiêu khi có đủ năng lượng."),
        new TutorialInfo("Thăng cấp khi đủ kinh nghiệm. Thu thập vàng để mua đồ."),
        new TutorialInfo("Bấm # để chọn vật phẩm."),
        new TutorialInfo("Mỗi lượt phải ghép 3 biểu tượng. Sai sẽ mất lượt và hao máu!")
    };

    public TutorialInfo? GetTutorialStep()
    {
        if (tutorialStep < 0 || tutorialStep >= TutorialSteps.Length) return null;
        return TutorialSteps[tutorialStep];
    }

    public void TutorialAdvance()
    {
        tutorialStep++;
        if (tutorialStep >= TutorialSteps.Length)
        {
            tutorialStep = -1;
            message = "Kết thúc hướng dẫn! Chúc may mắn!";
        }
    }

    public bool TutorialDone => tutorialStep >= TutorialSteps.Length;

    public void CreateBoard()
    {
        board = new int[GameConfig.BoardSize, GameConfig.BoardSize];
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
            {
                do { board[x, y] = UnityEngine.Random.Range(0, GameConfig.PieceNames.Length); }
                while (CreatesImmediateMatch(x, y));
            }
        EnsurePlayable();
    }

    private bool CreatesImmediateMatch(int x, int y)
    {
        int p = board[x, y];
        if (x >= 2 && board[x - 1, y] == p && board[x - 2, y] == p) return true;
        if (y >= 2 && board[x, y - 1] == p && board[x, y - 2] == p) return true;
        return false;
    }

    public void EnsurePlayable()
    {
        int guard = 0;
        while (!HasAvailableMove() && guard < 20) { CreateBoard(); guard++; }
    }

    private bool HasAvailableMove()
    {
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
            {
                if (x + 1 < GameConfig.BoardSize && WouldMatch(x, y, x + 1, y)) return true;
                if (y + 1 < GameConfig.BoardSize && WouldMatch(x, y, x, y + 1)) return true;
            }
        return false;
    }

    private bool WouldMatch(int ax, int ay, int bx, int by)
    {
        Swap(ax, ay, bx, by);
        bool r = HasMatch();
        Swap(ax, ay, bx, by);
        return r;
    }

    private void Swap(int ax, int ay, int bx, int by)
    {
        int t = board[ax, ay]; board[ax, ay] = board[bx, by]; board[bx, by] = t;
    }

    private bool HasMatch()
    {
        for (int y = 0; y < GameConfig.BoardSize; y++)
        {
            int run = 0;
            for (int x = 1; x <= GameConfig.BoardSize; x++)
            {
                if (x < GameConfig.BoardSize && board[x, y] == board[run, y] && board[x, y] != GameConfig.EmptyPiece) continue;
                if (x - run >= 3) return true;
                run = x;
            }
        }
        for (int x = 0; x < GameConfig.BoardSize; x++)
        {
            int run = 0;
            for (int y = 1; y <= GameConfig.BoardSize; y++)
            {
                if (y < GameConfig.BoardSize && board[x, y] == board[x, run] && board[x, y] != GameConfig.EmptyPiece) continue;
                if (y - run >= 3) return true;
                run = y;
            }
        }
        return false;
    }

    public void SelectCell(int x, int y)
    {
        if (selX < 0) { selX = x; selY = y; message = "Chọn quân kế bên."; return; }
        if (selX == x && selY == y) { selX = -1; selY = -1; message = "Bỏ chọn."; return; }
        if (Mathf.Abs(selX - x) + Mathf.Abs(selY - y) != 1)
        { selX = x; selY = y; message = "Phải chọn quân cạnh nhau."; return; }

        TrySwap(selX, selY, x, y);
        selX = -1; selY = -1;
    }

    private void TrySwap(int ax, int ay, int bx, int by)
    {
        Swap(ax, ay, bx, by);
        if (!HasMatch())
        {
            Swap(ax, ay, bx, by);
            message = "Không tạo được liên kết.";
            return;
        }
        movesLeft--;
        if (powerAttackTurns > 0) powerAttackTurns--;
        ResolveBoard();
        EnemyTurn();
        CheckEndConditions();
    }

    private void ResolveBoard()
    {
        int total = 0, chain = 0, dmg = 0, heal = 0;
        while (true)
        {
            var marks = new bool[GameConfig.BoardSize, GameConfig.BoardSize];
            var removedByType = new int[GameConfig.PieceNames.Length];
            var groups = new List<MatchGroup>();
            FindAllMatches(marks, removedByType, groups);
            int count = CountMarked(marks);
            if (count == 0) break;

            chain++;
            total += count;
            ApplyRewards(removedByType, groups, chain, ref dmg, ref heal);
            RemoveMarked(marks);
            Collapse();
        }
        EnsurePlayable();
        message = $"Phá {total} quân.";
        if (dmg > 0) message += $" ST {dmg}.";
        if (heal > 0) message += $" Hồi {heal}.";
    }

    private void FindAllMatches(bool[,] marks, int[] removedByType, List<MatchGroup> groups)
    {
        for (int y = 0; y < GameConfig.BoardSize; y++)
        {
            int rs = 0;
            for (int x = 1; x <= GameConfig.BoardSize; x++)
            {
                if (x < GameConfig.BoardSize && board[x, y] == board[rs, y] && board[x, y] != GameConfig.EmptyPiece) continue;
                int len = x - rs;
                if (len >= 3) MarkRun(marks, removedByType, groups, rs, y, len, true);
                rs = x;
            }
        }
        for (int x = 0; x < GameConfig.BoardSize; x++)
        {
            int rs = 0;
            for (int y = 1; y <= GameConfig.BoardSize; y++)
            {
                if (y < GameConfig.BoardSize && board[x, y] == board[x, rs] && board[x, y] != GameConfig.EmptyPiece) continue;
                int len = y - rs;
                if (len >= 3) MarkRun(marks, removedByType, groups, x, rs, len, false);
                rs = y;
            }
        }
    }

    private void MarkRun(bool[,] marks, int[] removedByType, List<MatchGroup> groups, int sx, int sy, int len, bool horz)
    {
        int p = board[sx, sy];
        if (groups != null && p != GameConfig.EmptyPiece) groups.Add(new MatchGroup(p, len));
        for (int i = 0; i < len; i++)
        {
            int x = horz ? sx + i : sx;
            int y = horz ? sy : sy + i;
            if (!marks[x, y]) { marks[x, y] = true; removedByType[board[x, y]]++; }
        }
    }

    private int CountMarked(bool[,] marks)
    {
        int c = 0;
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
                if (marks[x, y]) c++;
        return c;
    }

    private void RemoveMarked(bool[,] marks)
    {
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
                if (marks[x, y])
                {
                    if (GameManager.Instance != null)
                        GameManager.Instance.AddEffect(
                            GetExplosionForPiece(board[x, y]),
                            GameConfig.GridOffsetX + x * GameConfig.GridCellSize,
                            GameConfig.GridOffsetY + y * GameConfig.GridCellSize, 12);
                    board[x, y] = GameConfig.EmptyPiece;
                }
    }

    private string GetExplosionForPiece(int piece)
    {
        var names = new[] { "explodesword", "exploderice", "explodeheart", "explodeyinyang",
                           "explodegold", "explodebook", "explodesword" };
        return piece >= 0 && piece < names.Length ? names[piece] : "explodesword";
    }

    private void Collapse()
    {
        for (int x = 0; x < GameConfig.BoardSize; x++)
        {
            int wy = GameConfig.BoardSize - 1;
            for (int y = GameConfig.BoardSize - 1; y >= 0; y--)
            {
                if (board[x, y] == GameConfig.EmptyPiece) continue;
                board[x, wy] = board[x, y];
                if (wy != y) board[x, y] = GameConfig.EmptyPiece;
                wy--;
            }
            for (int y = wy; y >= 0; y--)
                board[x, y] = UnityEngine.Random.Range(0, GameConfig.PieceNames.Length);
        }
    }

    private void ApplyRewards(int[] removedByType, List<MatchGroup> groups, int chain, ref int dmg, ref int heal)
    {
        int bonus = (chain - 1) * 5;
        int atkMul = powerAttackTurns > 0 ? 2 : 1;

        for (int i = 0; i < removedByType.Length; i++)
        {
            int cnt = removedByType[i];
            if (cnt == 0) continue;
            score += cnt * 10 + bonus;
            switch (i)
            {
                case 1: score += cnt * 6; break;
                case 3:
                    mana = Mathf.Min(99, mana + cnt * (heroIndex == 1 ? 3 : 2));
                    int yd = cnt * Mathf.Max(2, heroAttack / 3) * atkMul;
                    DamageEnemy(yd); dmg += yd;
                    break;
                case 4: gold += cnt; break;
                case 5: mana = Mathf.Min(99, mana + cnt * 3); score += cnt * 8; break;
            }
        }

        foreach (var g in groups)
        {
            if (g.Piece == 0)
            {
                int sd = swordDamage(g.Count) * atkMul;
                DamageEnemy(sd); dmg += sd;
            }
            else if (g.Piece == 2)
            {
                int hl = heartHeal(g.Count);
                health = Mathf.Min(maxHealth, health + hl); heal += hl;
            }
        }
        enemyHealth = Mathf.Max(0, enemyHealth);
    }

    private int swordDamage(int cnt)
    {
        if (cnt <= 3) return Mathf.Max(1, heroAttack);
        return Mathf.Max(1, cnt * heroAttack + heroAttack - enemyDefense);
    }

    private int heartHeal(int cnt)
    {
        switch (Mathf.Min(cnt, 7))
        {
            case 3: return 9; case 4: return 13; case 5: return 22;
            case 6: return 35; default: return 58;
        }
    }

    private void DamageEnemy(int amt) { enemyHealth = Mathf.Max(0, enemyHealth - Mathf.Max(1, amt)); }
    private void DamagePlayer(int amt)
    {
        int d = Mathf.Max(1, amt - heroDefense);
        health = Mathf.Max(0, health - d);
    }

    private void EnemyTurn()
    {
        if (enemyHealth <= 0 || frozenTurns > 0)
        {
            if (frozenTurns > 0) { frozenTurns--; message += " Địch bị đóng băng."; }
            return;
        }
        int dmg = enemyAttack;
        if (shieldTurns > 0) { dmg = Mathf.Max(1, dmg / 4); shieldTurns--; }
        DamagePlayer(dmg);
    }

    private void CheckEndConditions()
    {
        UpdateRecords();
        if (enemyHealth <= 0)
        {
            int xp = 45 + level * 18 + (bossBattle ? 100 : 0) + (endlessMode ? level * 4 : 0);
            int rg = 12 + level * 3 + (bossBattle ? 80 : 0);
            gold += rg;
            AddXP(xp);
            unlockedLevel = Mathf.Max(unlockedLevel, Mathf.Min(GameConfig.MaxLevel, level + 1));
            if (!endlessMode && level >= GameConfig.MaxLevel)
            {
                ClearSave();
                resultTitle = "THẮNG LỢI!";
                resultBody = $"Hoàn thành {GameConfig.MaxLevel} màn.\nĐiểm {score}, Vàng {gold}.";
                GameManager.Instance.SwitchTo(GameScreen.Result);
                return;
            }
            level++;
            StartLevel(level);
            message = $"Qua {(endlessMode ? "đợt" : "màn")} {level - 1}! +{xp}XP +{rg}vàng.";
        }
        else if (health <= 0 || movesLeft <= 0)
        {
            ClearSave();
            resultTitle = "THẤT BẠI";
            resultBody = $"Dừng ở màn {level}.\nĐiểm {score}, Vàng {gold}.";
            GameManager.Instance.SwitchTo(GameScreen.Result);
        }
    }

    private void AddXP(int amt)
    {
        if (heroLevel >= 99) { heroXp = 0; return; }
        heroXp += amt;
        bool leveled = false;
        while (heroLevel < 99 && heroXp >= XpNeeded())
        { heroXp -= XpNeeded(); heroLevel++; leveled = true; }
        if (heroLevel >= 99) heroXp = 0;
        if (leveled)
        {
            heroAttack = GameConfig.HeroBaseAttack[heroIndex] + (heroLevel - 1) * 2;
            heroDefense = GameConfig.HeroBaseDefense[heroIndex] + (heroLevel - 1);
            heroSkillPower = GameConfig.HeroBaseSkillPower[heroIndex] + (heroLevel - 1) * 2;
            maxHealth = GameConfig.HeroBaseHealth[heroIndex] + (heroLevel - 1) * 14;
            health = maxHealth;
            message += $" Lên cấp {heroLevel}!";
        }
    }

    private int XpNeeded()
    {
        if (heroLevel >= 99) return 0;
        int[] tbl = { 80, 120, 180, 260, 360, 500, 700, 1000, 1500, 2000 };
        int idx = heroLevel / 10;
        return idx < tbl.Length ? tbl[idx] + heroLevel * 10 : 3000;
    }

    public void StartNewGame(bool endless)
    {
        endlessMode = endless;
        level = 1; score = 0; gold = endless ? 40 : 20; mana = endless ? 20 : 0;
        heroLevel = 1; heroXp = 0;
        shieldTurns = 0; frozenTurns = 0;
        inventory = endless ? new[] { 2, 1, 0, 1, 2, 1 } : new[] { 1, 0, 0, 1, 1, 0 };
        powerAttackTurns = inventory[5] > 0 ? 1 : 0;
        ginsengUsed = false;
        ApplyHeroStats(true);
        StartLevel(1);
    }

    public void StartReplayStage(int stage)
    {
        endlessMode = false;
        level = Mathf.Clamp(stage, 1, GameConfig.MaxLevel);
        score = 0; mana = Mathf.Min(99, mana + 10);
        shieldTurns = 0; frozenTurns = 0;
        powerAttackTurns = inventory[5] > 0 ? 1 : 0;
        ginsengUsed = false;
        StartLevel(level);
        message = $"Chơi lại màn {level}.";
    }

    public void StartLevel(int lvl)
    {
        level = lvl;
        bossBattle = level % 5 == 0 || (!endlessMode && level == GameConfig.MaxLevel);
        enemyName = GetEnemyName();
        enemyMaxHealth = (45 + (level - 1) * 28 + (endlessMode ? level * 8 : 0)) * (bossBattle ? 3 : 1);
        enemyHealth = enemyMaxHealth;
        targetScore = 180 + (level - 1) * 120;
        movesLeft = endlessMode ? Mathf.Max(14, 24 - level / 4) : Mathf.Max(14, 27 - level / 2);
        enemyAttack = (6 + level * 2 + (endlessMode ? level / 2 : 0)) * (bossBattle ? 2 : 1);
        enemyDefense = Mathf.Max(0, level / 3 + (bossBattle ? level / 2 + 4 : 0));
        cursorX = GameConfig.BoardSize / 2;
        cursorY = GameConfig.BoardSize / 2;
        selX = -1; selY = -1;
        CreateBoard();
        message = $"{(endlessMode ? "Đợt" : "Màn")} {level}: Đánh bại {enemyName}!";
    }

    private string GetEnemyName()
    {
        if (bossBattle)
        {
            int idx = Mathf.Clamp(level / 5, 0, GameConfig.BossNames.Length - 1);
            return GameConfig.BossNames[idx];
        }
        if (endlessMode) return $"Tiên nhân {level}";
        return GameConfig.EnemyNames[(level - 1) % GameConfig.EnemyNames.Length];
    }

    public void ApplyHeroStats(bool fullHeal)
    {
        heroLevel = Mathf.Max(1, Mathf.Min(99, heroLevel));
        heroAttack = GameConfig.HeroBaseAttack[heroIndex] + (heroLevel - 1) * 2;
        heroDefense = GameConfig.HeroBaseDefense[heroIndex] + (heroLevel - 1);
        heroSkillPower = GameConfig.HeroBaseSkillPower[heroIndex] + (heroLevel - 1) * 2;
        maxHealth = GameConfig.HeroBaseHealth[heroIndex] + (heroLevel - 1) * 14;
        health = fullHeal ? maxHealth : Mathf.Min(maxHealth, health + Mathf.Max(8, maxHealth / 5));
    }

    public void UseSkillOnBoard(int idx)
    {
        int atk = heroAttack;
        int sk = heroSkillPower;
        int lv = level;
        switch (idx)
        {
            case 0: DamageEnemy(20 + lv * 4 + sk * 3); ClearArea(cursorX - 1, cursorY - 1, 3, 3); break;
            case 1: DamageEnemy(22 + lv * 4 + sk * 3); for (int i = 0; i < UnityEngine.Random.Range(3, 6); i++) ClearArea(UnityEngine.Random.Range(0, 6), UnityEngine.Random.Range(0, 6), 2, 2); break;
            case 2: DamageEnemy(30 + lv * 5 + sk * 4); ClearArea(0, 0, 4, 4); ClearArea(4, 4, 4, 4); break;
            case 3: DamageEnemy(16 + lv * 3 + sk * 2); ClearRandom(UnityEngine.Random.Range(4, 9)); break;
            case 4: shieldTurns = 6; break;
            case 5: DamageEnemy(28 + lv * 4 + sk * 4); for (int i = 0; i < UnityEngine.Random.Range(3, 7); i++) ClearArea(UnityEngine.Random.Range(0, 5), UnityEngine.Random.Range(0, 5), 3, 3); break;
            case 6: DamageEnemy(14 + lv * 3 + sk * 2); enemyAttack = Mathf.Max(1, enemyAttack - 2); break;
            case 7: health = Mathf.Min(maxHealth, health + Mathf.Max(1, maxHealth / 5)); ClearAllOfType(2); break;
            case 8: frozenTurns = heroIndex == 5 ? 3 : 2; DamageEnemy(18 + lv * 4 + sk * 3); enemyAttack = Mathf.Max(1, enemyAttack - 4); break;
        }
        Collapse();
        ResolveBoard();
        CheckEndConditions();
        if (GameManager.Instance != null)
            GameManager.Instance.AddEffect("star", GameConfig.VirtualWidth / 2 - 24, 80, 30);
    }

    private void ClearArea(int sx, int sy, int w, int h)
    {
        var rt = new int[GameConfig.PieceNames.Length];
        for (int y = Mathf.Max(0, sy); y < Mathf.Min(GameConfig.BoardSize, sy + h); y++)
            for (int x = Mathf.Max(0, sx); x < Mathf.Min(GameConfig.BoardSize, sx + w); x++)
                ClearCell(x, y, rt);
    }

    private void ClearRandom(int cnt)
    {
        var rt = new int[GameConfig.PieceNames.Length];
        for (int i = 0; i < cnt; i++)
            ClearCell(UnityEngine.Random.Range(0, GameConfig.BoardSize), UnityEngine.Random.Range(0, GameConfig.BoardSize), rt);
    }

    private void ClearAllOfType(int type)
    {
        var rt = new int[GameConfig.PieceNames.Length];
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
                if (board[x, y] == type) ClearCell(x, y, rt);
    }

    private void ClearCell(int x, int y, int[] rt)
    {
        if (x < 0 || x >= GameConfig.BoardSize || y < 0 || y >= GameConfig.BoardSize) return;
        int p = board[x, y];
        if (p == GameConfig.EmptyPiece) return;
        rt[p]++; board[x, y] = GameConfig.EmptyPiece;
    }

    public void UseItem(int idx)
    {
        if (inventory[idx] <= 0) { message = "Không có vật phẩm này."; return; }
        inventory[idx]--;
        switch (idx)
        {
            case 0: powerAttackTurns = 1; message = "Long Thần Kiếm!"; break;
            case 1:
                if (ginsengUsed) { message = "Nhân Sâm chỉ 1 lần/trận."; return; }
                ginsengUsed = true; maxHealth *= 2; health = maxHealth; message = "Nhân Sâm!"; break;
            case 2: gold += 1000; message = "Ngân Lượng +1000!"; break;
            case 3: shieldTurns = Mathf.Max(shieldTurns, 1); message = "Quỷ Diện Giáp!"; break;
            case 4:
                if (health >= maxHealth) { message = "Máu đã đầy."; return; }
                health = Mathf.Min(maxHealth, health + Mathf.Max(1, maxHealth / 10)); message = "Bình Thuốc!"; break;
            case 5: powerAttackTurns = Mathf.Max(powerAttackTurns, 1); message = "Ngọc Ấn!"; break;
        }
        CheckEndConditions();
    }

    public void BuyItem(int idx)
    {
        int price = GameConfig.ItemPrices[idx];
        if (price == 0)
        {
            inventory[idx] += GameConfig.ItemAmounts[idx];
            message = $"Đã nhận {GameConfig.ItemNames[idx]}.";
            return;
        }
        if (gold < price) { message = "Không đủ vàng."; return; }
        gold -= price;
        inventory[idx] += GameConfig.ItemAmounts[idx];
        message = $"Đã mua {GameConfig.ItemNames[idx]}.";
    }

    public void LoadAll()
    {
        bestScore = GetInt(SP + "BestScore", 0);
        bestLevel = GetInt(SP + "BestLevel", 0);
        unlockedLevel = Mathf.Max(1, GetInt(SP + "UnlockedLevel", Mathf.Max(1, bestLevel)));
    }

    public void SaveGame()
    {
        if (board == null) return;
        SetInt(SP + "HasSave", 1);
        SetInt(SP + "Level", level);
        SetInt(SP + "Moves", movesLeft);
        SetInt(SP + "Score", score);
        SetInt(SP + "Gold", gold);
        SetInt(SP + "Mana", mana);
        SetInt(SP + "Hero", heroIndex);
        SetInt(SP + "Endless", endlessMode ? 1 : 0);
        SetInt(SP + "HeroLevel", heroLevel);
        SetInt(SP + "HeroXp", heroXp);
        SetInt(SP + "UnlockedLevel", unlockedLevel);
        SetInt(SP + "MaxHealth", maxHealth);
        SetInt(SP + "Health", health);
        SetInt(SP + "EnemyHealth", enemyHealth);
        SetInt(SP + "EnemyMaxHealth", enemyMaxHealth);
        SetInt(SP + "EnemyAttack", enemyAttack);
        SetInt(SP + "EnemyDefense", enemyDefense);
        SetInt(SP + "ShieldTurns", shieldTurns);
        SetInt(SP + "FrozenTurns", frozenTurns);
        SetInt(SP + "PowerAttackTurns", powerAttackTurns);
        SetInt(SP + "GinsengUsed", ginsengUsed ? 1 : 0);
        for (int i = 0; i < inventory.Length; i++)
            SetInt(SP + "Item" + i, inventory[i]);
        SetString(SP + "Board", SerializeBoard());
        SaveRecords();
        SaveManager.Save();
    }

    public bool LoadGame()
    {
        if (!HasSave()) return false;
        level = GetInt(SP + "Level", 1);
        movesLeft = GetInt(SP + "Moves", 20);
        score = GetInt(SP + "Score", 0);
        gold = GetInt(SP + "Gold", 0);
        mana = GetInt(SP + "Mana", 0);
        heroIndex = GetInt(SP + "Hero", 0);
        endlessMode = GetInt(SP + "Endless", 0) == 1;
        heroLevel = GetInt(SP + "HeroLevel", 1);
        heroXp = GetInt(SP + "HeroXp", 0);
        unlockedLevel = Mathf.Max(1, GetInt(SP + "UnlockedLevel", 1));
        maxHealth = GetInt(SP + "MaxHealth", 100);
        health = GetInt(SP + "Health", maxHealth);
        enemyMaxHealth = GetInt(SP + "EnemyMaxHealth", 45);
        enemyHealth = GetInt(SP + "EnemyHealth", enemyMaxHealth);
        enemyAttack = GetInt(SP + "EnemyAttack", 8);
        enemyDefense = GetInt(SP + "EnemyDefense", 3);
        shieldTurns = GetInt(SP + "ShieldTurns", 0);
        frozenTurns = GetInt(SP + "FrozenTurns", 0);
        powerAttackTurns = GetInt(SP + "PowerAttackTurns", 0);
        ginsengUsed = GetInt(SP + "GinsengUsed", 0) == 1;
        for (int i = 0; i < inventory.Length; i++)
            inventory[i] = GetInt(SP + "Item" + i, 0);
        bossBattle = level % 5 == 0 || (!endlessMode && level == GameConfig.MaxLevel);
        enemyName = GetEnemyName();
        string d = GetString(SP + "Board", "");
        if (!DeserializeBoard(d)) CreateBoard();
        EnsurePlayable();
        selX = -1; selY = -1;
        cursorX = GameConfig.BoardSize / 2;
        cursorY = GameConfig.BoardSize / 2;
        message = $"Đã tải màn {level}.";
        return true;
    }

    public bool HasSave() => GetInt(SP + "HasSave", 0) == 1;
    public void ClearSave() { SaveManager.DeleteKey(SP + "HasSave"); SaveManager.Save(); }

    private void SaveRecords()
    {
        UpdateRecords();
        SetInt(SP + "BestScore", bestScore);
        SetInt(SP + "BestLevel", bestLevel);
        SetInt(SP + "UnlockedLevel", unlockedLevel);
        SaveManager.Save();
    }

    private void UpdateRecords()
    {
        bestScore = Mathf.Max(bestScore, score);
        bestLevel = Mathf.Max(bestLevel, level);
    }

    private string SerializeBoard()
    {
        var c = new char[GameConfig.BoardSize * GameConfig.BoardSize];
        int idx = 0;
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
                c[idx++] = (char)('0' + Mathf.Clamp(board[x, y], 0, GameConfig.PieceNames.Length - 1));
        return new string(c);
    }

    private bool DeserializeBoard(string data)
    {
        if (data.Length != GameConfig.BoardSize * GameConfig.BoardSize) return false;
        board = new int[GameConfig.BoardSize, GameConfig.BoardSize];
        int idx = 0;
        for (int y = 0; y < GameConfig.BoardSize; y++)
            for (int x = 0; x < GameConfig.BoardSize; x++)
            {
                int v = data[idx++] - '0';
                if (v < 0 || v >= GameConfig.PieceNames.Length) return false;
                board[x, y] = v;
            }
        return true;
    }

    private static int GetInt(string k, int d) => SaveManager.GetInt(k, d);
    private static void SetInt(string k, int v) => SaveManager.SetInt(k, v);
    private static string GetString(string k, string d) => SaveManager.GetString(k, d);
    private static void SetString(string k, string v) => SaveManager.SetString(k, v);
}
