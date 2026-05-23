using System.Collections.Generic;
using System.IO;

public static class SaveManager
{
    private static readonly Dictionary<string, string> Values = new Dictionary<string, string>();
    private static bool _loaded;
    private static string _saveDir;
    private static string FilePath => Path.Combine(SaveDir, "loan12-save.txt");

    private static string SaveDir
    {
        get
        {
            if (string.IsNullOrEmpty(_saveDir))
            {
                _saveDir = Path.Combine(UnityEngine.Application.persistentDataPath, "Loan12");
                Directory.CreateDirectory(_saveDir);
            }
            return _saveDir;
        }
    }

    private static void EnsureLoaded()
    {
        if (_loaded) return;
        _loaded = true;
        if (!File.Exists(FilePath)) return;
        var lines = File.ReadAllLines(FilePath);
        for (int i = 0; i < lines.Length; i++)
        {
            var line = lines[i];
            int sep = line.IndexOf('=');
            if (sep <= 0) continue;
            Values[line.Substring(0, sep)] = line.Substring(sep + 1);
        }
    }

    public static void SetInt(string key, int value) { EnsureLoaded(); Values[key] = value.ToString(); }
    public static int GetInt(string key, int defaultValue) { EnsureLoaded(); return Values.TryGetValue(key, out var v) && int.TryParse(v, out var p) ? p : defaultValue; }
    public static void SetString(string key, string value) { EnsureLoaded(); Values[key] = value ?? ""; }
    public static string GetString(string key, string defaultValue) { EnsureLoaded(); return Values.TryGetValue(key, out var v) ? v : defaultValue; }
    public static void DeleteKey(string key) { EnsureLoaded(); Values.Remove(key); }
    public static bool HasKey(string key) { EnsureLoaded(); return Values.ContainsKey(key); }

    public static void Save()
    {
        EnsureLoaded();
        var lines = new List<string>();
        foreach (var kv in Values) lines.Add(kv.Key + "=" + kv.Value);
        File.WriteAllLines(FilePath, lines);
    }

    public static void Clear()
    {
        Values.Clear();
        if (File.Exists(FilePath)) File.Delete(FilePath);
    }
}
