#if UNITY_EDITOR
using UnityEngine;
using UnityEditor;
using KyTran.Data;
using KyTran.Models;

namespace KyTran.Editor
{
    /// <summary>
    /// LevelEditorWindow - Editor window để tạo và chỉnh sửa levels.
    /// </summary>
    public class LevelEditorWindow : EditorWindow
    {
        private LevelData currentLevel;
        private Vector2 scrollPosition;

        // Grid visualization
        private const int CELL_SIZE = 40;
        private Vector2 gridOffset = new Vector2(20, 100);

        // Tool state
        private bool showObstacleEditor = true;
        private bool showEnemyEditor = true;
        private bool showSettings = true;

        // Obstacle editing
        private ObstacleType selectedObstacleType = ObstacleType.Ice;
        private bool isPlacingObstacle = false;

        // Colors
        private readonly Color iceColor = new Color(0.7f, 0.9f, 1f);
        private readonly Color chainColor = new Color(0.5f, 0.5f, 0.5f);
        private readonly Color blockColor = new Color(0.4f, 0.4f, 0.4f);
        private readonly Color cageColor = new Color(0.8f, 0.6f, 0.2f);

        [MenuItem("Window/KyTran/Level Editor")]
        public static void ShowWindow()
        {
            var window = GetWindow<LevelEditorWindow>("Level Editor");
            window.minSize = new Vector2(600, 500);
        }

        private void OnGUI()
        {
            DrawToolbar();
            DrawMainContent();
        }

        /// <summary>
        /// Vẽ toolbar chính.
        /// </summary>
        private void DrawToolbar()
        {
            EditorGUILayout.BeginHorizontal(EditorStyles.toolbar);

            // New Level button
            if (GUILayout.Button("New Level", EditorStyles.toolbarButton))
            {
                CreateNewLevel();
            }

            // Load Level button
            EditorGUILayout.BeginVertical();
            currentLevel = (LevelData)EditorGUILayout.ObjectField(currentLevel, typeof(LevelData), false, GUILayout.Width(200));
            EditorGUILayout.EndVertical();

            // Save button
            GUI.enabled = currentLevel != null;
            if (GUILayout.Button("Save", EditorStyles.toolbarButton))
            {
                SaveLevel();
            }
            GUI.enabled = true;

            EditorGUILayout.EndHorizontal();
        }

        /// <summary>
        /// Vẽ nội dung chính.
        /// </summary>
        private void DrawMainContent()
        {
            if (currentLevel == null)
            {
                DrawNoLevelSelected();
                return;
            }

            scrollPosition = EditorGUILayout.BeginScrollView(scrollPosition);

            // Level Settings
            showSettings = EditorGUILayout.Foldout(showSettings, "Level Settings", true);
            if (showSettings)
            {
                EditorGUI.indentLevel++;
                DrawLevelSettings();
                EditorGUI.indentLevel--;
            }

            EditorGUILayout.Space();

            // Grid Editor
            DrawGridEditor();

            EditorGUILayout.Space();

            // Obstacle Editor
            showObstacleEditor = EditorGUILayout.Foldout(showObstacleEditor, "Obstacle Editor", true);
            if (showObstacleEditor)
            {
                EditorGUI.indentLevel++;
                DrawObstacleEditor();
                EditorGUI.indentLevel--;
            }

            EditorGUILayout.Space();

            // Enemy Editor
            showEnemyEditor = EditorGUILayout.Foldout(showEnemyEditor, "Enemy Editor", true);
            if (showEnemyEditor)
            {
                EditorGUI.indentLevel++;
                DrawEnemyEditor();
                EditorGUI.indentLevel--;
            }

            EditorGUILayout.EndScrollView();
        }

        /// <summary>
        /// Vẽ khi không có level nào được chọn.
        /// </summary>
        private void DrawNoLevelSelected()
        {
            EditorGUILayout.Space(50);
            EditorGUILayout.BeginVertical();
            EditorGUILayout.CenterLabel(new GUIContent("No Level Selected"));
            EditorGUILayout.Space(20);

            if (GUILayout.Button("Create New Level", GUILayout.Height(40)))
            {
                CreateNewLevel();
            }

            EditorGUILayout.EndVertical();
        }

        /// <summary>
        /// Vẽ cài đặt level.
        /// </summary>
        private void DrawLevelSettings()
        {
            currentLevel.levelNumber = EditorGUILayout.IntField("Level Number", currentLevel.levelNumber);
            currentLevel.levelName = EditorGUILayout.TextField("Level Name", currentLevel.levelName);
            currentLevel.levelDescription = EditorGUILayout.TextField("Description", currentLevel.levelDescription);

            EditorGUILayout.Space();

            currentLevel.objective = (ObjectiveType)EditorGUILayout.EnumPopup("Objective", currentLevel.objective);
            currentLevel.targetScore = EditorGUILayout.IntField("Target Score", currentLevel.targetScore);
            currentLevel.targetMoves = EditorGUILayout.IntField("Target Moves", currentLevel.targetMoves);

            EditorGUILayout.Space();

            currentLevel.difficulty = (Difficulty)EditorGUILayout.EnumPopup("Difficulty", currentLevel.difficulty);
            currentLevel.scoreMultiplier = EditorGUILayout.Slider("Score Multiplier", currentLevel.scoreMultiplier, 0.5f, 3f);
            currentLevel.enemyDamageMultiplier = EditorGUILayout.Slider("Enemy Damage", currentLevel.enemyDamageMultiplier, 0.5f, 3f);

            EditorGUILayout.Space();

            currentLevel.goldReward = EditorGUILayout.IntField("Gold Reward", currentLevel.goldReward);
            currentLevel.experienceReward = EditorGUILayout.IntField("EXP Reward", currentLevel.experienceReward);
        }

        /// <summary>
        /// Vẽ grid editor với preview obstacles.
        /// </summary>
        private void DrawGridEditor()
        {
            EditorGUILayout.BeginVertical("box");
            EditorGUILayout.LabelField("Grid Preview", EditorStyles.boldLabel);

            int width = currentLevel.gridWidth;
            int height = currentLevel.gridHeight;

            // Tạo texture cho grid
            EditorGUILayout.BeginVertical();

            for (int y = height - 1; y >= 0; y--)
            {
                EditorGUILayout.BeginHorizontal();
                for (int x = 0; x < width; x++)
                {
                    // Tìm obstacle tại vị trí này
                    ObstacleData obstacle = GetObstacleAt(x, y);
                    bool hasObstacle = obstacle != null && obstacle.Type != ObstacleType.None;

                    Color bgColor = Color.gray;
                    string label = "";

                    if (hasObstacle)
                    {
                        switch (obstacle.Type)
                        {
                            case ObstacleType.Ice:
                                bgColor = iceColor;
                                label = "I";
                                break;
                            case ObstacleType.Chain:
                                bgColor = chainColor;
                                label = "C";
                                break;
                            case ObstacleType.Block:
                                bgColor = blockColor;
                                label = "B";
                                break;
                            case ObstacleType.Cage:
                                bgColor = cageColor;
                                label = "K";
                                break;
                        }
                    }

                    // Vẽ ô grid
                    GUI.backgroundColor = bgColor;
                    if (GUILayout.Button(label, GUILayout.Width(30), GUILayout.Height(30)))
                    {
                        HandleGridCellClick(x, y);
                    }
                    GUI.backgroundColor = Color.white;
                }
                EditorGUILayout.EndHorizontal();
            }

            EditorGUILayout.EndVertical();

            // Legend
            EditorGUILayout.BeginHorizontal();
            DrawLegendItem(iceColor, "Ice (1 hit)");
            DrawLegendItem(chainColor, "Chain (Special)");
            DrawLegendItem(blockColor, "Block");
            DrawLegendItem(cageColor, "Cage");
            EditorGUILayout.EndHorizontal();

            EditorGUILayout.EndVertical();
        }

        /// <summary>
        /// Vẽ một item trong legend.
        /// </summary>
        private void DrawLegendItem(Color color, string label)
        {
            EditorGUILayout.BeginHorizontal();
            GUI.backgroundColor = color;
            GUILayout.Button("", GUILayout.Width(20), GUILayout.Height(20));
            GUI.backgroundColor = Color.white;
            EditorGUILayout.LabelField(label, GUILayout.Width(80));
            EditorGUILayout.EndHorizontal();
        }

        /// <summary>
        /// Xử lý click vào một ô grid.
        /// </summary>
        private void HandleGridCellClick(int x, int y)
        {
            if (!isPlacingObstacle)
            {
                // Toggle obstacle tại vị trí
                ObstacleData obstacle = GetObstacleAt(x, y);
                if (obstacle != null && obstacle.Type != ObstacleType.None)
                {
                    // Xóa obstacle
                    RemoveObstacleAt(x, y);
                }
                else
                {
                    // Thêm obstacle với type đang chọn
                    AddObstacleAt(x, y, selectedObstacleType);
                }
            }
            else
            {
                // Chế độ đặt: đặt obstacle với type đang chọn
                AddObstacleAt(x, y, selectedObstacleType);
            }
        }

        /// <summary>
        /// Lấy obstacle data tại vị trí.
        /// </summary>
        private ObstacleData GetObstacleAt(int x, int y)
        {
            if (currentLevel.obstacles == null) return null;

            foreach (var obs in currentLevel.obstacles)
            {
                if (obs.X == x && obs.Y == y)
                    return obs;
            }
            return null;
        }

        /// <summary>
        /// Thêm obstacle tại vị trí.
        /// </summary>
        private void AddObstacleAt(int x, int y, ObstacleType type)
        {
            if (type == ObstacleType.None) return;

            // Kiểm tra đã tồn tại chưa
            ObstacleData existing = GetObstacleAt(x, y);
            if (existing != null)
            {
                existing.Type = type;
                EditorUtility.SetDirty(currentLevel);
                return;
            }

            // Tạo mảng mới nếu cần
            if (currentLevel.obstacles == null || currentLevel.obstacles.Length == 0)
            {
                currentLevel.obstacles = new ObstacleData[1];
                currentLevel.obstacles[0] = new ObstacleData(x, y, type);
            }
            else
            {
                // Thêm vào mảng
                var list = new System.Collections.Generic.List<ObstacleData>(currentLevel.obstacles);
                list.Add(new ObstacleData(x, y, type));
                currentLevel.obstacles = list.ToArray();
            }

            EditorUtility.SetDirty(currentLevel);
        }

        /// <summary>
        /// Xóa obstacle tại vị trí.
        /// </summary>
        private void RemoveObstacleAt(int x, int y)
        {
            if (currentLevel.obstacles == null) return;

            var list = new System.Collections.Generic.List<ObstacleData>(currentLevel.obstacles);
            list.RemoveAll(obs => obs.X == x && obs.Y == y);
            currentLevel.obstacles = list.ToArray();

            EditorUtility.SetDirty(currentLevel);
        }

        /// <summary>
        /// Vẽ obstacle editor panel.
        /// </summary>
        private void DrawObstacleEditor()
        {
            EditorGUILayout.BeginVertical("box");

            EditorGUILayout.LabelField("Obstacle Type to Place");
            EditorGUILayout.BeginHorizontal();
            selectedObstacleType = (ObstacleType)EditorGUILayout.EnumPopup(selectedObstacleType);
            EditorGUILayout.EndHorizontal();

            EditorGUILayout.Space();

            isPlacingObstacle = EditorGUILayout.Toggle("Place Mode (click to add)", isPlacingObstacle);

            EditorGUILayout.Space();

            if (GUILayout.Button("Clear All Obstacles"))
            {
                if (EditorUtility.DisplayDialog("Clear Obstacles", "Clear all obstacles from this level?", "Yes", "Cancel"))
                {
                    currentLevel.obstacles = new ObstacleData[0];
                    EditorUtility.SetDirty(currentLevel);
                }
            }

            EditorGUILayout.Space();

            // Hiển thị số lượng obstacles
            int iceCount = 0, chainCount = 0, blockCount = 0, cageCount = 0;
            if (currentLevel.obstacles != null)
            {
                foreach (var obs in currentLevel.obstacles)
                {
                    switch (obs.Type)
                    {
                        case ObstacleType.Ice: iceCount++; break;
                        case ObstacleType.Chain: chainCount++; break;
                        case ObstacleType.Block: blockCount++; break;
                        case ObstacleType.Cage: cageCount++; break;
                    }
                }
            }

            EditorGUILayout.LabelField($"Total: {currentLevel.obstacles?.Length ?? 0} obstacles");
            EditorGUILayout.LabelField($"  - Ice: {iceCount}");
            EditorGUILayout.LabelField($"  - Chain: {chainCount}");
            EditorGUILayout.LabelField($"  - Block: {blockCount}");
            EditorGUILayout.LabelField($"  - Cage: {cageCount}");

            EditorGUILayout.EndVertical();
        }

        /// <summary>
        /// Vẽ enemy editor panel.
        /// </summary>
        private void DrawEnemyEditor()
        {
            EditorGUILayout.BeginVertical("box");

            // Thêm enemy mới
            if (GUILayout.Button("Add Enemy Wave"))
            {
                AddEnemyWave();
            }

            EditorGUILayout.Space();

            // Hiển thị danh sách enemies
            if (currentLevel.enemyWaves == null || currentLevel.enemyWaves.Length == 0)
            {
                EditorGUILayout.HelpBox("No enemies configured.", MessageType.Info);
            }
            else
            {
                for (int i = 0; i < currentLevel.enemyWaves.Length; i++)
                {
                    EditorGUILayout.BeginVertical("box");
                    DrawEnemyWave(currentLevel.enemyWaves[i], i);
                    EditorGUILayout.EndVertical();
                }
            }

            EditorGUILayout.EndVertical();
        }

        /// <summary>
        /// Vẽ một enemy wave.
        /// </summary>
        private void DrawEnemyWave(EnemyWave wave, int index)
        {
            EditorGUILayout.BeginHorizontal();
            EditorGUILayout.LabelField($"Wave {index + 1}", EditorStyles.boldLabel, GUILayout.Width(60));

            if (GUILayout.Button("X", GUILayout.Width(20)))
            {
                RemoveEnemyWave(index);
                return;
            }
            EditorGUILayout.EndHorizontal();

            EditorGUI.indentLevel++;
            wave.enemyId = EditorGUILayout.TextField("Enemy ID", wave.enemyId);
            wave.tier = (EnemyTier)EditorGUILayout.EnumPopup("Tier", wave.tier);
            wave.isBoss = EditorGUILayout.Toggle("Is Boss", wave.isBoss);
            wave.healthMultiplier = EditorGUILayout.IntSlider("Health Mult", wave.healthMultiplier, 1, 10);
            wave.attackMultiplier = EditorGUILayout.IntSlider("Attack Mult", wave.attackMultiplier, 1, 5);
            wave.spawnDelay = EditorGUILayout.FloatField("Spawn Delay", wave.spawnDelay);
            EditorGUI.indentLevel--;
        }

        /// <summary>
        /// Thêm enemy wave mới.
        /// </summary>
        private void AddEnemyWave()
        {
            var list = currentLevel.enemyWaves != null
                ? new System.Collections.Generic.List<EnemyWave>(currentLevel.enemyWaves)
                : new System.Collections.Generic.List<EnemyWave>();

            list.Add(new EnemyWave());
            currentLevel.enemyWaves = list.ToArray();

            EditorUtility.SetDirty(currentLevel);
        }

        /// <summary>
        /// Xóa enemy wave.
        /// </summary>
        private void RemoveEnemyWave(int index)
        {
            var list = new System.Collections.Generic.List<EnemyWave>(currentLevel.enemyWaves);
            list.RemoveAt(index);
            currentLevel.enemyWaves = list.ToArray();

            EditorUtility.SetDirty(currentLevel);
        }

        /// <summary>
        /// Tạo level mới.
        /// </summary>
        private void CreateNewLevel()
        {
            string path = EditorUtility.SaveFilePanelInProject(
                "Create New Level",
                "NewLevel.asset",
                "asset",
                "Choose a location for the new level"
            );

            if (!string.IsNullOrEmpty(path))
            {
                currentLevel = LevelData.CreateDefault(EditorPrefs.GetInt("LastLevelNumber", 1));
                AssetDatabase.CreateAsset(currentLevel, path);
                EditorPrefs.SetInt("LastLevelNumber", currentLevel.levelNumber + 1);
                Selection.activeObject = currentLevel;
            }
        }

        /// <summary>
        /// Lưu level.
        /// </summary>
        private void SaveLevel()
        {
            if (currentLevel == null) return;
            EditorUtility.SetDirty(currentLevel);
            AssetDatabase.SaveAssets();
            Debug.Log($"Level '{currentLevel.levelName}' saved.");
        }
    }
}
#endif
