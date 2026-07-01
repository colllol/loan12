using UnityEngine;
using System;

namespace KyTran.Core
{
    /// <summary>
    /// GameState - Trạng thái của game.
    /// </summary>
    public enum GameState
    {
        None,           // Chưa khởi tạo
        Loading,        // Đang loading
        MainMenu,       // Menu chính
        Playing,         // Đang chơi
        Paused,         // Tạm dừng
        LevelComplete,   // Hoàn thành level
        GameOver,        // Thua
        Victory         // Thắng (boss died)
    }

    /// <summary>
    /// GameManager - Singleton quản lý state và lifecycle của game.
    /// Chịu trách nhiệm: Khởi tạo, thay đổi state, quit game.
    /// </summary>
    public class GameManager : MonoBehaviour
    {
        #region Singleton
        private static GameManager _instance;
        public static GameManager Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = FindObjectOfType<GameManager>();
                    if (_instance == null)
                    {
                        Debug.LogError("[GameManager] Instance not found!");
                    }
                }
                return _instance;
            }
            private set => _instance = value;
        }
        #endregion

        #region Events
        // Event khi state thay đổi
        public event Action<GameState, GameState> OnGameStateChanged;

        // Event lifecycle
        public event Action OnGameStarted;
        public event Action OnGamePaused;
        public event Action OnGameResumed;
        public event Action OnGameQuit;
        #endregion

        #region Properties
        [Header("Game Settings")]
        [SerializeField] private GameState initialState = GameState.MainMenu;

        private GameState _currentState;
        public GameState CurrentState
        {
            get => _currentState;
            private set
            {
                if (_currentState != value)
                {
                    GameState oldState = _currentState;
                    _currentState = value;
                    Debug.Log($"[GameManager] State: {oldState} → {value}");
                    OnGameStateChanged?.Invoke(oldState, value);
                }
            }
        }

        // Properties tiện dụng
        public bool IsPlaying => CurrentState == GameState.Playing;
        public bool IsPaused => CurrentState == GameState.Paused;
        public bool CanProcessInput => IsPlaying && !IsPaused;
        #endregion

        #region Unity Lifecycle
        private void Awake()
        {
            // Singleton setup
            if (_instance != null && _instance != this)
            {
                Destroy(gameObject);
                return;
            }

            Instance = this;
            DontDestroyOnLoad(gameObject);

            // Đăng ký ServiceLocator
            ServiceLocator.Register(this);
        }

        private void Start()
        {
            // Khởi tạo game
            InitializeGame();
        }

        private void Update()
        {
            // Xử lý Pause bằng phím Escape
            HandlePauseInput();
        }

        private void OnDestroy()
        {
            if (_instance == this)
            {
                _instance = null;
                ServiceLocator.Unregister<GameManager>();
            }
        }
        #endregion

        #region Initialization
        /// <summary>
        /// Khởi tạo game - gọi 1 lần khi bắt đầu.
        /// </summary>
        private void InitializeGame()
        {
            Debug.Log("[GameManager] Initializing...");

            // Set initial state
            CurrentState = GameState.Loading;

            // TODO: Load saved data, init services, etc.

            // Chuyển sang MainMenu
            ChangeState(initialState);

            OnGameStarted?.Invoke();
            Debug.Log("[GameManager] Initialization complete.");
        }
        #endregion

        #region State Management
        /// <summary>
        /// Thay đổi game state.
        /// </summary>
        public void ChangeState(GameState newState)
        {
            if (CurrentState == newState) return;

            // Validate transitions
            if (!IsValidTransition(CurrentState, newState))
            {
                Debug.LogWarning($"[GameManager] Invalid transition: {CurrentState} → {newState}");
                return;
            }

            // Exit current state
            ExitState(CurrentState);

            // Enter new state
            CurrentState = newState;
            EnterState(newState);
        }

        /// <summary>
        /// Kiểm tra transition có hợp lệ không.
        /// </summary>
        private bool IsValidTransition(GameState from, GameState to)
        {
            // TODO: Define valid transitions
            return true;
        }

        /// <summary>
        /// Xử lý khi exit một state.
        /// </summary>
        private void ExitState(GameState state)
        {
            switch (state)
            {
                case GameState.Playing:
                    // Dừng game logic
                    Time.timeScale = 1f;
                    break;
            }
        }

        /// <summary>
        /// Xử lý khi enter một state.
        /// </summary>
        private void EnterState(GameState state)
        {
            switch (state)
            {
                case GameState.MainMenu:
                    // Load menu scene
                    break;

                case GameState.Playing:
                    // Bắt đầu game
                    Time.timeScale = 1f;
                    break;

                case GameState.Paused:
                    // Dừng game
                    Time.timeScale = 0f;
                    break;

                case GameState.LevelComplete:
                    // Hiển thị victory UI
                    break;

                case GameState.GameOver:
                    // Hiển thị game over UI
                    break;
            }
        }
        #endregion

        #region Game Control
        /// <summary>
        /// Bắt đầu game/level mới.
        /// </summary>
        public void StartGame()
        {
            ChangeState(GameState.Playing);
        }

        /// <summary>
        /// Tạm dừng game.
        /// </summary>
        public void PauseGame()
        {
            if (CurrentState == GameState.Playing)
            {
                ChangeState(GameState.Paused);
                OnGamePaused?.Invoke();
            }
        }

        /// <summary>
        /// Tiếp tục game.
        /// </summary>
        public void ResumeGame()
        {
            if (CurrentState == GameState.Paused)
            {
                ChangeState(GameState.Playing);
                OnGameResumed?.Invoke();
            }
        }

        /// <summary>
        /// Thoát game.
        /// </summary>
        public void QuitGame()
        {
            Debug.Log("[GameManager] Quitting game...");
            OnGameQuit?.Invoke();

#if UNITY_EDITOR
            UnityEditor.EditorApplication.isPlaying = false;
#else
            Application.Quit();
#endif
        }

        /// <summary>
        /// Restart level hiện tại.
        /// </summary>
        public void RestartLevel()
        {
            // TODO: Implement restart logic
            ChangeState(GameState.Playing);
        }

        /// <summary>
        /// Next level.
        /// </summary>
        public void NextLevel()
        {
            // TODO: Load next level
            ChangeState(GameState.Playing);
        }
        #endregion

        #region Input Handling
        /// <summary>
        /// Xử lý pause bằng phím.
        /// </summary>
        private void HandlePauseInput()
        {
            if (Input.GetKeyDown(KeyCode.Escape))
            {
                if (CurrentState == GameState.Playing)
                {
                    PauseGame();
                }
                else if (CurrentState == GameState.Paused)
                {
                    ResumeGame();
                }
            }
        }
        #endregion
    }
}
