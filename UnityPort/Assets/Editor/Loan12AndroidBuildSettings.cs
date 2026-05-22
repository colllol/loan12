#if UNITY_EDITOR
using UnityEditor;
using UnityEditor.SceneManagement;
using UnityEngine;

public static class Loan12AndroidBuildSettings
{
    private const string BootScenePath = "Assets/Scenes/Boot.unity";
    private const string AndroidBuildPath = "Builds/Android";

    [MenuItem("Loan12/Apply Android Build Settings")]
    public static void Apply()
    {
        EnsureBootScene();
        EditorUserBuildSettings.SwitchActiveBuildTarget(BuildTargetGroup.Android, BuildTarget.Android);

        PlayerSettings.companyName = "bot-nosense";
        PlayerSettings.productName = "Loan 12 Su Quan";
        PlayerSettings.SetApplicationIdentifier(BuildTargetGroup.Android, "com.botnosense.loan12suquan");
        PlayerSettings.Android.minSdkVersion = AndroidSdkVersions.AndroidApiLevel22;
        PlayerSettings.Android.targetSdkVersion = AndroidSdkVersions.AndroidApiLevel33;
        PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
        PlayerSettings.allowedAutorotateToLandscapeLeft = false;
        PlayerSettings.allowedAutorotateToLandscapeRight = false;
        PlayerSettings.allowedAutorotateToPortrait = true;
        PlayerSettings.allowedAutorotateToPortraitUpsideDown = false;

        Debug.Log("Loan12 Android build settings applied.");
    }

    public static void BuildApk()
    {
        Apply();
        var options = new BuildPlayerOptions
        {
            scenes = new[] { BootScenePath },
            locationPathName = AndroidBuildPath + "/Loan12SuQuan.apk",
            target = BuildTarget.Android,
            options = BuildOptions.None
        };

        var report = BuildPipeline.BuildPlayer(options);
        Debug.Log("Loan12 Android build finished with result: " + report.summary.result);
    }

    private static void EnsureBootScene()
    {
        if (!AssetDatabase.IsValidFolder("Assets/Scenes"))
        {
            AssetDatabase.CreateFolder("Assets", "Scenes");
        }

        if (!System.IO.File.Exists(BootScenePath))
        {
            var scene = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
            EditorSceneManager.SaveScene(scene, BootScenePath);
        }

        EditorBuildSettings.scenes = new[]
        {
            new EditorBuildSettingsScene(BootScenePath, true)
        };
        AssetDatabase.SaveAssets();
    }
}
#endif
