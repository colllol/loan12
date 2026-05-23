#if UNITY_EDITOR
using UnityEditor;
using UnityEditor.SceneManagement;
using UnityEngine;

public static class Loan12IOSBuildSettings
{
    private const string BootScenePath = "Assets/Scenes/Boot.unity";
    private const string IosBuildPath = "Builds/iOS";

    [MenuItem("Loan12/Apply iOS Build Settings")]
    public static void Apply()
    {
        EnsureBootScene();
        EditorUserBuildSettings.SwitchActiveBuildTarget(BuildTargetGroup.iOS, BuildTarget.iOS);
        PlayerSettings.companyName = "bot-nosense";
        PlayerSettings.productName = "Loan 12 Su Quan";
        PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
        PlayerSettings.allowedAutorotateToLandscapeLeft = false;
        PlayerSettings.allowedAutorotateToLandscapeRight = false;
        PlayerSettings.allowedAutorotateToPortrait = true;
        PlayerSettings.allowedAutorotateToPortraitUpsideDown = false;
        PlayerSettings.iOS.targetOSVersionString = "12.0";
        PlayerSettings.SetApplicationIdentifier(BuildTargetGroup.iOS, "com.botnosense.loan12suquan");
        Debug.Log("Loan12 iOS build settings applied.");
    }

    [MenuItem("Loan12/Build iOS Xcode Project")]
    public static void BuildIosXcodeProject()
    {
        Apply();
        var options = new BuildPlayerOptions
        {
            scenes = new[] { BootScenePath },
            locationPathName = IosBuildPath,
            target = BuildTarget.iOS,
            options = BuildOptions.None
        };
        var report = BuildPipeline.BuildPlayer(options);
        Debug.Log("Loan12 iOS build finished with result: " + report.summary.result);
    }

    private static void EnsureBootScene()
    {
        if (!AssetDatabase.IsValidFolder("Assets/Scenes"))
            AssetDatabase.CreateFolder("Assets", "Scenes");
        if (!System.IO.File.Exists(BootScenePath))
        {
            var scene = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
            EditorSceneManager.SaveScene(scene, BootScenePath);
        }
        EditorBuildSettings.scenes = new[] { new EditorBuildSettingsScene(BootScenePath, true) };
        AssetDatabase.SaveAssets();
    }
}
#endif
