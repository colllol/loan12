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
        if (Object.FindObjectOfType<Loan12Game>() != null)
        {
            return;
        }

        var cameraObject = new GameObject("Main Camera");
        var camera = cameraObject.AddComponent<Camera>();
        camera.clearFlags = CameraClearFlags.SolidColor;
        camera.backgroundColor = Color.black;
        camera.orthographic = true;
        cameraObject.tag = "MainCamera";

        var gameObject = new GameObject("Loan 12 Su Quan Port");
        Object.DontDestroyOnLoad(gameObject);
        gameObject.AddComponent<Loan12Game>();
        Debug.Log("Loan12 runtime created.");
    }
}
