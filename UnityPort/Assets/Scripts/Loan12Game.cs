using System.Collections.Generic;
using UnityEngine;

public sealed class Loan12Game : MonoBehaviour
{
    private const int VirtualWidth = 240;
    private const int VirtualHeight = 320;

    private enum ScreenState
    {
        MgLogo,
        PartnerLogo,
        MainMenu,
        Board
    }

    private readonly Dictionary<string, Texture2D> textures = new Dictionary<string, Texture2D>();
    private readonly string[] menuItems =
    {
        "strnewgame",
        "strcontinuegame",
        "strguide",
        "strshop",
        "strrecord",
        "strinformation"
    };

    private readonly string[] pieces =
    {
        "sword",
        "yinyang",
        "rice",
        "gold",
        "book",
        "heart"
    };

    private ScreenState state;
    private float stateStartedAt;
    private int selectedMenuItem;
    private int[,] board;
    private Rect canvasRect;
    private float canvasScale;
    private GUIStyle labelStyle;

    private void Awake()
    {
        Application.targetFrameRate = 25;
        state = ScreenState.MainMenu;
        stateStartedAt = Time.realtimeSinceStartup;
        CreateBoard();
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
            SwitchTo(state == ScreenState.Board ? ScreenState.MainMenu : ScreenState.MgLogo);
        }

        if (state == ScreenState.MainMenu)
        {
            HandleMenuInput();
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
            case ScreenState.Board:
                DrawBoard();
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
            var y = 108 + i * 27;
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

    private void DrawBoard()
    {
        DrawFull("bkboard");
        var top = 54;
        var left = 24;
        var cell = 27;
        for (var y = 0; y < 7; y++)
        {
            for (var x = 0; x < 7; x++)
            {
                var texture = Load(pieces[board[x, y]]);
                if (texture == null)
                {
                    continue;
                }

                var rect = new Rect(left + x * cell, top + y * cell, 24, 24);
                GUI.DrawTexture(rect, texture, ScaleMode.ScaleToFit, true);
                if (Event.current.type == EventType.MouseDown && rect.Contains(Event.current.mousePosition))
                {
                    board[x, y] = (board[x, y] + 1) % pieces.Length;
                }
            }
        }

        GUI.Label(new Rect(0, 282, VirtualWidth, 26), "Mang 3    Vang 0", labelStyle);
    }

    private void ActivateMenuItem(int index)
    {
        if (index == 0 || index == 1)
        {
            SwitchTo(ScreenState.Board);
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

        if (!textures.TryGetValue(path, out var texture))
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
        board = new int[7, 7];
        for (var y = 0; y < 7; y++)
        {
            for (var x = 0; x < 7; x++)
            {
                board[x, y] = Random.Range(0, pieces.Length);
            }
        }
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
    }
}
