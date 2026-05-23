using System.Collections.Generic;
using UnityEngine;

public static class AssetManager
{
    private static readonly Dictionary<string, Texture2D> Textures = new Dictionary<string, Texture2D>();
    private static readonly Dictionary<string, AudioClip> AudioClips = new Dictionary<string, AudioClip>();

    private const string ResourcePath = "Loan12";

    public static Texture2D LoadTexture(string path)
    {
        if (string.IsNullOrEmpty(path)) return null;
        if (!Textures.TryGetValue(path, out var tex))
        {
            tex = Resources.Load<Texture2D>(ResourcePath + "/" + path);
            Textures[path] = tex;
        }
        return tex;
    }

    public static AudioClip LoadAudio(string path)
    {
        if (string.IsNullOrEmpty(path)) return null;
        if (!AudioClips.TryGetValue(path, out var clip))
        {
            clip = Resources.Load<AudioClip>("audio/" + path);
            AudioClips[path] = clip;
        }
        return clip;
    }

    public static void DrawTexture(Rect rect, string name)
    {
        var tex = LoadTexture(name);
        if (tex != null) GUI.DrawTexture(rect, tex, ScaleMode.StretchToFill, true);
    }

    public static void DrawTextureCentered(float x, float y, string name)
    {
        var tex = LoadTexture(name);
        if (tex == null) return;
        GUI.DrawTexture(new Rect(x - tex.width / 2f, y - tex.height / 2f, tex.width, tex.height), tex, ScaleMode.ScaleToFit, true);
    }

    public static void DrawFull(string name)
    {
        var tex = LoadTexture(name);
        if (tex != null) GUI.DrawTexture(new Rect(0, 0, GameConfig.VirtualWidth, GameConfig.VirtualHeight), tex, ScaleMode.StretchToFill, true);
    }

    public static void ClearCache()
    {
        Textures.Clear();
        AudioClips.Clear();
    }
}
