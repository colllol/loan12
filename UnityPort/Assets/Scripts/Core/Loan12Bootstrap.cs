using UnityEngine;

public static class Loan12Bootstrap
{
    [RuntimeInitializeOnLoadMethod(RuntimeInitializeLoadType.BeforeSceneLoad)]
    private static void Boot()
    {
        EnsureRuntime();
    }

    [RuntimeInitializeOnLoadMethod(RuntimeInitializeLoadType.AfterSceneLoad)]
    private static void BootAfterSceneLoad()
    {
        EnsureRuntime();
    }

    public static void EnsureRuntime()
    {
        if (Object.FindObjectOfType<GameManager>() != null) return;

        var cameraObject = new GameObject("Main Camera");
        var camera = cameraObject.AddComponent<Camera>();
        camera.clearFlags = CameraClearFlags.SolidColor;
        camera.backgroundColor = Color.black;
        camera.orthographic = true;
        cameraObject.tag = "MainCamera";

        var go = new GameObject("Loan12Game");
        Object.DontDestroyOnLoad(go);
        go.AddComponent<GameManager>();
        go.AddComponent<AudioManager>();
        Debug.Log("Loan12 game runtime created.");
    }
}
