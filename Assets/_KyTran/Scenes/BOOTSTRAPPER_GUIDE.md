# Hướng dẫn Bootstrapper Scene

## Mục đích

`Bootstrapper` là Scene đầu tiên được load khi game khởi động. Nó có nhiệm vụ:
1. Khởi tạo các Manager cốt lõi (GameManager, ServiceLocator)
2. Setup Audio System
3. Load Scene chính (MainMenu hoặc Gameplay)

## Cấu trúc Bootstrapper

```
Assets/_KyTran/Scenes/
├── Bootstrapper.unity          ← Scene khởi động
├── Menu/MainMenu.unity         ← Scene Menu
└── Game/Gameplay.unity         ← Scene Chơi
```

## Cách tạo Bootstrapper Scene

### Bước 1: Tạo Scene mới

```
1. Unity → File → New Scene
2. Save as: Assets/_KyTran/Scenes/Bootstrapper.unity
3. Delete Main Camera (sẽ được setup lại)
```

### Bước 2: Tạo GameObject cấu trúc

```
Hierarchy:
├── [DO NOT DESTROY]
│   ├── GameManager
│   │   └── GameManager.cs
│   ├── EventBroker
│   │   └── EventBroker.cs (MonoBehaviour wrapper - optional)
│   └── ServiceLocator
│       └── (Khong can MonoBehaviour)
│
├── [PERSISTENT]
│   ├── AudioManager
│   │   └── AudioManager.cs
│   └── UIManager
│       └── UIManager.cs
│
└── Bootstrapper
    └── Bootstrapper.cs
```

### Bước 3: Tạo GameManager Prefab

```csharp
// Assets/_KyTran/Scripts/Core/GameManager.cs
using UnityEngine;
using KyTran.Core;

public class GameManagerBootstrap : MonoBehaviour
{
    [SerializeField] private string firstScene = "MainMenu";

    private void Awake()
    {
        // Đảm bảo chỉ có 1 instance
        if (GameManager.Instance != this)
        {
            Destroy(gameObject);
            return;
        }

        // DontDestroyOnLoad để tồn tại qua các scene
        DontDestroyOnLoad(gameObject);

        Debug.Log("[Bootstrap] GameManager initialized");
    }

    private void Start()
    {
        // Load scene đầu tiên
        LoadFirstScene();
    }

    private void LoadFirstScene()
    {
        if (!string.IsNullOrEmpty(firstScene))
        {
            UnityEngine.SceneManagement.SceneManager.LoadScene(firstScene);
        }
    }
}
```

### Bước 4: Setup Scene trong Unity

```
1. Tạo Empty GameObject "Bootstrapper"
2. Add Component: Bootstrapper.cs
3. Kéo thả GameManager prefab vào slot
```

### Bước 5: Cấu hình Build Settings

```
1. File → Build Settings
2. Add Bootstrapper scene (đảm bảo index 0)
3. Add MainMenu scene (index 1)
4. Add Gameplay scene (index 2)
```

## Bootstrapper.cs Script

```csharp
using UnityEngine;
using UnityEngine.SceneManagement;
using KyTran.Core;

namespace KyTran.Bootstrap
{
    /// <summary>
    /// Bootstrapper - Khởi tạo game systems trước khi load scene chính.
    /// </summary>
    public class Bootstrapper : MonoBehaviour
    {
        [Header("Scene Configuration")]
        [SerializeField] private string initialScene = "MainMenu";
        [SerializeField] private bool loadSplashScreen = true;
        [SerializeField] private float splashDuration = 2f;

        [Header("Managers")]
        [SerializeField] private bool initializeGameManager = true;
        [SerializeField] private bool initializeAudioManager = true;
        [SerializeField] private bool initializeUIManager = true;

        [Header("Debug")]
        [SerializeField] private bool showDebugLogs = true;

        private void Awake()
        {
            // Setup execution order - chạy đầu tiên
            Application.targetFrameRate = 60;
            QualitySettings.vSyncCount = 1;

            if (showDebugLogs)
            {
                Debug.Log("[Bootstrap] Initializing...");
            }
        }

        private IEnumerator Start()
        {
            // Splash screen delay (optional)
            if (loadSplashScreen && splashDuration > 0)
            {
                yield return new WaitForSeconds(splashDuration);
            }

            // Initialize core systems
            yield return StartCoroutine(InitializeSystems());

            // Load initial scene
            LoadScene(initialScene);
        }

        /// <summary>
        /// Khởi tạo các hệ thống cốt lõi.
        /// </summary>
        private System.Collections.IEnumerator InitializeSystems()
        {
            // GameManager
            if (initializeGameManager)
            {
                if (GameManager.Instance == null)
                {
                    Debug.Log("[Bootstrap] Creating GameManager...");
                    GameObject gmObj = new GameObject("GameManager");
                    gmObj.AddComponent<GameManager>();
                    DontDestroyOnLoad(gmObj);
                }
                yield return null;
            }

            // AudioManager (tạo sau)
            if (initializeAudioManager)
            {
                Debug.Log("[Bootstrap] Initializing Audio...");
                // AudioManager sẽ được init bởi GameManager
                yield return null;
            }

            // UIManager (tạo sau)
            if (initializeUIManager)
            {
                Debug.Log("[Bootstrap] Initializing UI...");
                yield return null;
            }

            // Subscribe to events
            SetupEventListeners();

            if (showDebugLogs)
            {
                Debug.Log($"[Bootstrap] Systems initialized. Services: {ServiceLocator.ServiceCount}");
            }
        }

        /// <summary>
        /// Đăng ký event listeners.
        /// </summary>
        private void SetupEventListeners()
        {
            // Có thể đăng ký global listeners ở đây
            // EventBroker.Instance.Listen(...)
        }

        /// <summary>
        /// Load một scene.
        /// </summary>
        private void LoadScene(string sceneName)
        {
            if (string.IsNullOrEmpty(sceneName))
            {
                Debug.LogError("[Bootstrap] Scene name is empty!");
                return;
            }

            Debug.Log($"[Bootstrap] Loading scene: {sceneName}");
            SceneManager.LoadScene(sceneName);
        }

        /// <summary>
        /// Load scene async (nếu muốn loading screen).
        /// </summary>
        public void LoadSceneAsync(string sceneName)
        {
            StartCoroutine(LoadSceneAsyncCoroutine(sceneName));
        }

        private IEnumerator LoadSceneAsyncCoroutine(string sceneName)
        {
            AsyncOperation op = SceneManager.LoadSceneAsync(sceneName);
            op.allowSceneActivation = false;

            while (!op.isDone)
            {
                float progress = Mathf.Clamp01(op.progress / 0.9f);
                Debug.Log($"[Bootstrap] Loading progress: {progress * 100}%");

                if (progress >= 1f)
                {
                    op.allowSceneActivation = true;
                }

                yield return null;
            }
        }

        private void OnDestroy()
        {
            // Cleanup
            EventBroker.Instance.ClearAll();
        }
    }
}
```

## Scene Loading Flow

```
┌─────────────────────────────────────────────────┐
│                  Bootstrapper                    │
│  ┌─────────────────────────────────────────┐   │
│  │ 1. Awake()                              │   │
│  │    - Set target FPS                     │   │
│  │    - Create GameManager                 │   │
│  └─────────────────────────────────────────┘   │
│                      │                          │
│                      ▼                          │
│  ┌─────────────────────────────────────────┐   │
│  │ 2. Start()                              │   │
│  │    - Initialize Systems                 │   │
│  │    - Load Initial Scene                 │   │
│  └─────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│                   MainMenu                      │
│  ┌─────────────────────────────────────────┐   │
│  │ GameManager.ChangeState(MainMenu)        │   │
│  │ UIManager.ShowMainMenu()                │   │
│  └─────────────────────────────────────────┘   │
│                      │                          │
│              [User clicks Play]                 │
│                      ▼                          │
│  ┌─────────────────────────────────────────┐   │
│  │ SceneManager.LoadScene("Gameplay")      │   │
│  └─────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│                  Gameplay                       │
│  ┌─────────────────────────────────────────┐   │
│  │ GameManager.ChangeState(Playing)         │   │
│  │ GridManager.SpawnGrid()                 │   │
│  │ CombatManager.StartCombat()              │   │
│  └─────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
```

## Tạo Manager Prefabs

### 1. GameManager Prefab

```
Hierarchy:
└── GameManager (Empty GameObject)
    └── Component: GameManager.cs

Inspector:
    - Initial State: MainMenu
    - (Tự động được DontDestroyOnLoad)
```

### 2. AudioManager Script

```csharp
using UnityEngine;
using KyTran.Core;

namespace KyTran.Audio
{
    public class AudioManager : MonoBehaviour
    {
        private static AudioManager _instance;
        public static AudioManager Instance => _instance;

        [Header("Audio Sources")]
        [SerializeField] private AudioSource sfxSource;
        [SerializeField] private AudioSource musicSource;

        [Header("Audio Clips")]
        public AudioClip matchSound;
        public AudioClip specialSound;
        public AudioClip attackSound;
        public AudioClip hurtSound;

        private void Awake()
        {
            if (_instance != null && _instance != this)
            {
                Destroy(gameObject);
                return;
            }

            _instance = this;
            DontDestroyOnLoad(gameObject);

            // Setup default audio sources
            SetupAudioSources();

            // Register
            ServiceLocator.Register(this);

            // Subscribe to events
            EventBroker.Instance.Listen(EventNames.SFX_MATCH, OnGemMatched);
        }

        private void SetupAudioSources()
        {
            if (sfxSource == null)
            {
                GameObject sfxObj = new GameObject("SFXSource");
                sfxObj.transform.SetParent(transform);
                sfxSource = sfxObj.AddComponent<AudioSource>();
                sfxSource.playOnAwake = false;
            }

            if (musicSource == null)
            {
                GameObject musicObj = new GameObject("MusicSource");
                musicObj.transform.SetParent(transform);
                musicSource = musicObj.AddComponent<AudioSource>();
                musicSource.loop = true;
                musicSource.volume = 0.5f;
            }
        }

        public void PlaySFX(AudioClip clip)
        {
            if (clip != null && sfxSource != null)
            {
                sfxSource.PlayOneShot(clip);
            }
        }

        public void PlayMusic(AudioClip clip, float volume = 0.5f)
        {
            if (clip != null && musicSource != null)
            {
                musicSource.clip = clip;
                musicSource.volume = volume;
                musicSource.Play();
            }
        }

        private void OnGemMatched()
        {
            PlaySFX(matchSound);
        }
    }
}
```

## Checklist

```
☐ Tạo Bootstrapper scene
☐ Tạo GameManager prefab và add vào Bootstrapper
☐ Tạo AudioManager script và prefab
☐ Cấu hình Build Settings (Bootstrapper = Scene 0)
☐ Test: Game có load được không?
☐ Test: GameManager tồn tại qua các scene?
☐ Test: EventBroker hoạt động?
```

## Troubleshooting

### "GameManager Instance not found!"
```
→ Kiểm tra GameManager có được AddComponent vào GameObject
→ Kiểm tra Bootstrapper có chạy trước
```

### "Scene not found!"
```
→ Kiểm tra Scene đã được Add vào Build Settings
→ Kiểm tra tên scene chính xác (case-sensitive)
```

### "Multiple GameManagers!"
```
→ Đảm bảo Bootstrapper chạy trước
→ Kiểm tra Awake() có check instance != null
```
