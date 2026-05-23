using System.Collections.Generic;
using UnityEngine;

public class BoardManager
{
    public int[,] Grid { get; private set; }
    public int Rows => GameConfig.BoardSize;
    public int Cols => GameConfig.BoardSize;
    public int PieceTypes => GameConfig.PieceNames.Length;

    public BoardManager()
    {
        Grid = new int[Cols, Rows];
    }

    public void CreateBoard()
    {
        for (int y = 0; y < Rows; y++)
        {
            for (int x = 0; x < Cols; x++)
            {
                do { Grid[x, y] = Random.Range(0, PieceTypes); }
                while (CreatesImmediateMatch(x, y));
            }
        }
    }

    public bool CreatesImmediateMatch(int x, int y)
    {
        int piece = Grid[x, y];
        if (x >= 2 && Grid[x - 1, y] == piece && Grid[x - 2, y] == piece) return true;
        if (y >= 2 && Grid[x, y - 1] == piece && Grid[x, y - 2] == piece) return true;
        return false;
    }

    public void Swap(int ax, int ay, int bx, int by)
    {
        int tmp = Grid[ax, ay];
        Grid[ax, ay] = Grid[bx, by];
        Grid[bx, by] = tmp;
    }

    public bool AreAdjacent(int ax, int ay, int bx, int by)
    {
        return Mathf.Abs(ax - bx) + Mathf.Abs(ay - by) == 1;
    }

    public bool HasAnyMatch()
    {
        return FindMatches(out _, null) > 0;
    }

    public int FindMatches(out int[] removedByType, List<MatchGroup> groups)
    {
        var marks = new bool[Cols, Rows];
        removedByType = new int[PieceTypes];
        int totalMarked = 0;

        for (int y = 0; y < Rows; y++)
        {
            int runStart = 0;
            for (int x = 1; x <= Cols; x++)
            {
                if (x < Cols && Grid[x, y] == Grid[runStart, y] && Grid[x, y] != GameConfig.EmptyPiece)
                    continue;
                totalMarked += MarkRun(marks, removedByType, groups, runStart, y, x - runStart, true);
                runStart = x;
            }
        }

        for (int x = 0; x < Cols; x++)
        {
            int runStart = 0;
            for (int y = 1; y <= Rows; y++)
            {
                if (y < Rows && Grid[x, y] == Grid[x, runStart] && Grid[x, y] != GameConfig.EmptyPiece)
                    continue;
                totalMarked += MarkRun(marks, removedByType, groups, x, runStart, y - runStart, false);
                runStart = y;
            }
        }

        return totalMarked;
    }

    private int MarkRun(bool[,] marks, int[] removedByType, List<MatchGroup> groups, int startX, int startY, int length, bool horizontal)
    {
        if (length < 3) return 0;
        int piece = Grid[startX, startY];
        if (groups != null && piece != GameConfig.EmptyPiece)
            groups.Add(new MatchGroup(piece, length));

        int count = 0;
        for (int i = 0; i < length; i++)
        {
            int x = horizontal ? startX + i : startX;
            int y = horizontal ? startY : startY + i;
            if (!marks[x, y])
            {
                marks[x, y] = true;
                removedByType[Grid[x, y]]++;
                count++;
            }
        }
        return count;
    }

    public void RemoveMarked(bool[,] marks)
    {
        for (int y = 0; y < Rows; y++)
            for (int x = 0; x < Cols; x++)
                if (marks[x, y])
                    Grid[x, y] = GameConfig.EmptyPiece;
    }

    public int CollapseBoard()
    {
        int filled = 0;
        for (int x = 0; x < Cols; x++)
        {
            int writeY = Rows - 1;
            for (int y = Rows - 1; y >= 0; y--)
            {
                if (Grid[x, y] == GameConfig.EmptyPiece) continue;
                Grid[x, writeY] = Grid[x, y];
                if (writeY != y) Grid[x, y] = GameConfig.EmptyPiece;
                writeY--;
                filled++;
            }
            for (int y = writeY; y >= 0; y--)
            {
                Grid[x, y] = Random.Range(0, PieceTypes);
                filled++;
            }
        }
        return filled;
    }

    public bool HasAvailableMove()
    {
        for (int y = 0; y < Rows; y++)
            for (int x = 0; x < Cols; x++)
            {
                if (x + 1 < Cols && WouldCreateMatch(x, y, x + 1, y)) return true;
                if (y + 1 < Rows && WouldCreateMatch(x, y, x, y + 1)) return true;
            }
        return false;
    }

    public bool WouldCreateMatch(int ax, int ay, int bx, int by)
    {
        Swap(ax, ay, bx, by);
        bool result = HasAnyMatch();
        Swap(ax, ay, bx, by);
        return result;
    }

    public void EnsurePlayableBoard()
    {
        int guard = 0;
        while (!HasAvailableMove() && guard < 20)
        {
            CreateBoard();
            guard++;
        }
    }

    public void ClearArea(int startX, int startY, int w, int h, int[] removedByType)
    {
        for (int y = Mathf.Max(0, startY); y < Mathf.Min(Rows, startY + h); y++)
            for (int x = Mathf.Max(0, startX); x < Mathf.Min(Cols, startX + w); x++)
                ClearCell(x, y, removedByType);
    }

    public void ClearRandomCells(int count, int[] removedByType)
    {
        for (int i = 0; i < count; i++)
            ClearCell(Random.Range(0, Cols), Random.Range(0, Rows), removedByType);
    }

    public void ClearAllOfType(int pieceType, int[] removedByType)
    {
        for (int y = 0; y < Rows; y++)
            for (int x = 0; x < Cols; x++)
                if (Grid[x, y] == pieceType)
                    ClearCell(x, y, removedByType);
    }

    public void ClearCell(int x, int y, int[] removedByType)
    {
        if (x < 0 || x >= Cols || y < 0 || y >= Rows) return;
        int piece = Grid[x, y];
        if (piece == GameConfig.EmptyPiece) return;
        removedByType[piece]++;
        Grid[x, y] = GameConfig.EmptyPiece;
    }

    public void GetGridPosition(int pixelX, int pixelY, out int gx, out int gy)
    {
        gx = (pixelX - GameConfig.GridOffsetX) / GameConfig.GridCellSize;
        gy = (pixelY - GameConfig.GridOffsetY) / GameConfig.GridCellSize;
    }

    public Rect GetCellRect(int x, int y)
    {
        return new Rect(GameConfig.GridOffsetX + x * GameConfig.GridCellSize + 1,
                       GameConfig.GridOffsetY + y * GameConfig.GridCellSize + 1,
                       GameConfig.GridCellSize - 2, GameConfig.GridCellSize - 2);
    }
}

public class MatchGroup
{
    public int Piece { get; }
    public int Count { get; }
    public MatchGroup(int piece, int count) { Piece = piece; Count = count; }
}
