#if UNITY_EDITOR
using UnityEditor;
using UnityEngine;

[InitializeOnLoad]
public static class Loan12PlayBootstrap
{
    static Loan12PlayBootstrap()
    {
        EditorApplication.playModeStateChanged -= OnPlayModeStateChanged;
        EditorApplication.playModeStateChanged += OnPlayModeStateChanged;
    }

    private static void OnPlayModeStateChanged(PlayModeStateChange state)
    {
        if (state != PlayModeStateChange.EnteredPlayMode)
        {
            return;
        }

        Loan12Bootstrap.EnsureRuntime();
        if (Object.FindObjectOfType<Loan12Game>() == null)
        {
            Debug.LogError("Loan12 runtime was not created.");
        }
    }
}
#endif
