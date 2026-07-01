using UnityEngine;
using UnityEngine.Events;
using System;
using System.Collections.Generic;

namespace KyTran.Core
{
    /// <summary>
    /// EventBroker - Hệ thống Event để các Manager giao tiếp decoupled.
    /// Dùng C# Action + UnityEvent để đảm bảo type-safety và performance.
    ///
    /// Cách dùng:
    /// - EventBroker.Emit&lt;T&gt;(EventNames.GEM_MATCHED, matchData);
    /// - EventBroker.Listen&lt;T&gt;(EventNames.GEM_MATCHED, OnGemMatched);
    /// - EventBroker.Unlisten&lt;T&gt;(EventNames.GEM_MATCHED, OnGemMatched);
    /// </summary>
    public class EventBroker
    {
        // ============================================================
        // INNER CLASSES
        // ============================================================

        /// <summary>
        /// UnityEvent wrapper cho generic event.
        /// </summary>
        [Serializable]
        public class GameEvent : UnityEvent { }

        /// <summary>
        /// UnityEvent với 1 parameter.
        /// </summary>
        [Serializable]
        public class GameEvent<T> : UnityEvent<T> { }

        /// <summary>
        /// UnityEvent với 2 parameters.
        /// </summary>
        [Serializable]
        public class GameEvent<T1, T2> : UnityEvent<T1, T2> { }

        /// <summary>
        /// UnityEvent với 3 parameters.
        /// </summary>
        [Serializable]
        public class GameEvent<T1, T2, T3> : UnityEvent<T1, T2, T3> { }

        // ============================================================
        // SINGLETON
        // ============================================================

        private static EventBroker _instance;
        public static EventBroker Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new EventBroker();
                }
                return _instance;
            }
        }

        private EventBroker() { }

        // ============================================================
        // DICTIONARIES
        // ============================================================

        // Event không có parameter
        private Dictionary<string, GameEvent> _events = new Dictionary<string, GameEvent>();

        // Events với 1 parameter
        private Dictionary<string, object> _events1 = new Dictionary<string, object>();

        // Events với 2 parameters
        private Dictionary<string, object> _events2 = new Dictionary<string, object>();

        // Events với 3 parameters
        private Dictionary<string, object> _events3 = new Dictionary<string, object>();

        // ============================================================
        // EMIT (Phát event)
        // ============================================================

        /// <summary>
        /// Emit event không có parameter.
        /// </summary>
        public void Emit(string eventName)
        {
            if (_events.TryGetValue(eventName, out GameEvent evt))
            {
                evt.Invoke();
            }
        }

        /// <summary>
        /// Emit event với 1 parameter.
        /// </summary>
        public void Emit<T>(string eventName, T param)
        {
            string key = eventName + typeof(T).Name;

            if (_events1.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T>;
                evt?.Invoke(param);
            }
        }

        /// <summary>
        /// Emit event với 2 parameters.
        /// </summary>
        public void Emit<T1, T2>(string eventName, T1 param1, T2 param2)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name;

            if (_events2.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T1, T2>;
                evt?.Invoke(param1, param2);
            }
        }

        /// <summary>
        /// Emit event với 3 parameters.
        /// </summary>
        public void Emit<T1, T2, T3>(string eventName, T1 param1, T2 param2, T3 param3)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name + typeof(T3).Name;

            if (_events3.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T1, T2, T3>;
                evt?.Invoke(param1, param2, param3);
            }
        }

        // ============================================================
        // LISTEN (Đăng ký lắng nghe)
        // ============================================================

        /// <summary>
        /// Đăng ký lắng nghe event không parameter.
        /// </summary>
        public void Listen(string eventName, Action callback)
        {
            if (!_events.ContainsKey(eventName))
            {
                _events[eventName] = new GameEvent();
            }
            _events[eventName].AddListener(callback);
        }

        /// <summary>
        /// Đăng ký lắng nghe event với 1 parameter.
        /// </summary>
        public void Listen<T>(string eventName, Action<T> callback)
        {
            string key = eventName + typeof(T).Name;

            if (!_events1.ContainsKey(key))
            {
                _events1[key] = new GameEvent<T>();
            }
            (_events1[key] as GameEvent<T>).AddListener(callback);
        }

        /// <summary>
        /// Đăng ký lắng nghe event với 2 parameters.
        /// </summary>
        public void Listen<T1, T2>(string eventName, Action<T1, T2> callback)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name;

            if (!_events2.ContainsKey(key))
            {
                _events2[key] = new GameEvent<T1, T2>();
            }
            (_events2[key] as GameEvent<T1, T2>).AddListener(callback);
        }

        /// <summary>
        /// Đăng ký lắng nghe event với 3 parameters.
        /// </summary>
        public void Listen<T1, T2, T3>(string eventName, Action<T1, T2, T3> callback)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name + typeof(T3).Name;

            if (!_events3.ContainsKey(key))
            {
                _events3[key] = new GameEvent<T1, T2, T3>();
            }
            (_events3[key] as GameEvent<T1, T2, T3>).AddListener(callback);
        }

        // ============================================================
        // UNLISTEN (Hủy đăng ký)
        // ============================================================

        /// <summary>
        /// Hủy đăng ký event không parameter.
        /// </summary>
        public void Unlisten(string eventName, Action callback)
        {
            if (_events.TryGetValue(eventName, out GameEvent evt))
            {
                evt.RemoveListener(callback);
            }
        }

        /// <summary>
        /// Hủy đăng ký event với 1 parameter.
        /// </summary>
        public void Unlisten<T>(string eventName, Action<T> callback)
        {
            string key = eventName + typeof(T).Name;

            if (_events1.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T>;
                evt?.RemoveListener(callback);
            }
        }

        /// <summary>
        /// Hủy đăng ký event với 2 parameters.
        /// </summary>
        public void Unlisten<T1, T2>(string eventName, Action<T1, T2> callback)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name;

            if (_events2.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T1, T2>;
                evt?.RemoveListener(callback);
            }
        }

        /// <summary>
        /// Hủy đăng ký event với 3 parameters.
        /// </summary>
        public void Unlisten<T1, T2, T3>(string eventName, Action<T1, T2, T3> callback)
        {
            string key = eventName + typeof(T1).Name + typeof(T2).Name + typeof(T3).Name;

            if (_events3.TryGetValue(key, out object evtObj))
            {
                var evt = evtObj as GameEvent<T1, T2, T3>;
                evt?.RemoveListener(callback);
            }
        }

        // ============================================================
        // UTILITY
        // ============================================================

        /// <summary>
        /// Xóa tất cả listeners của một event.
        /// </summary>
        public void ClearEvent(string eventName)
        {
            if (_events.ContainsKey(eventName))
            {
                _events[eventName].RemoveAllListeners();
            }
        }

        /// <summary>
        /// Xóa tất cả events.
        /// </summary>
        public void ClearAll()
        {
            _events.Clear();
            _events1.Clear();
            _events2.Clear();
            _events3.Clear();
        }

        /// <summary>
        /// Debug: In ra số listeners của một event.
        /// </summary>
        public int GetListenerCount(string eventName)
        {
            if (_events.TryGetValue(eventName, out GameEvent evt))
            {
                return evt.GetPersistentEventCount();
            }
            return 0;
        }
    }

    /// <summary>
    /// Event Names - Constants cho tất cả events trong game.
    /// Dùng constants thay vì string để tránh typo.
    /// </summary>
    public static class EventNames
    {
        // ============================================================
        // GAME EVENTS
        // ============================================================
        public const string GAME_START = "GAME_START";
        public const string GAME_PAUSE = "GAME_PAUSE";
        public const string GAME_RESUME = "GAME_RESUME";
        public const string GAME_OVER = "GAME_OVER";
        public const string GAME_VICTORY = "GAME_VICTORY";

        // ============================================================
        // BOARD EVENTS
        // ============================================================
        public const string BOARD_READY = "BOARD_READY";
        public const string GEM_SWAP = "GEM_SWAP";
        public const string GEM_SWAP_VALID = "GEM_SWAP_VALID";
        public const string GEM_SWAP_INVALID = "GEM_SWAP_INVALID";
        public const string GEM_MATCHED = "GEM_MATCHED";
        public const string GEM_DESTROYED = "GEM_DESTROYED";
        public const string CASCADE_START = "CASCADE_START";
        public const string CASCADE_COMPLETE = "CASCADE_COMPLETE";
        public const string SPECIAL_GEM_CREATED = "SPECIAL_GEM_CREATED";
        public const string SPECIAL_GEM_TRIGGERED = "SPECIAL_GEM_TRIGGERED";

        // ============================================================
        // COMBAT EVENTS
        // ============================================================
        public const string PLAYER_ATTACK = "PLAYER_ATTACK";
        public const string ENEMY_ATTACK = "ENEMY_ATTACK";
        public const string DAMAGE_DEALT = "DAMAGE_DEALT";
        public const string PLAYER_HURT = "PLAYER_HURT";
        public const string ENEMY_HURT = "ENEMY_HURT";
        public const string PLAYER_DEAD = "PLAYER_DEAD";
        public const string ENEMY_DEAD = "ENEMY_DEAD";
        public const string COMBAT_END = "COMBAT_END";
        public const string TURN_START = "TURN_START";
        public const string TURN_END = "TURN_END";

        // ============================================================
        // UI EVENTS
        // ============================================================
        public const string SCORE_CHANGED = "SCORE_CHANGED";
        public const string HEALTH_CHANGED = "HEALTH_CHANGED";
        public const string MOVES_CHANGED = "MOVES_CHANGED";
        public const string LEVEL_START = "LEVEL_START";
        public const string LEVEL_COMPLETE = "LEVEL_COMPLETE";
        public const string SHOW_POPUP = "SHOW_POPUP";
        public const string HIDE_POPUP = "HIDE_POPUP";

        // ============================================================
        // AUDIO EVENTS
        // ============================================================
        public const string SFX_MATCH = "SFX_MATCH";
        public const string SFX_SPECIAL = "SFX_SPECIAL";
        public const string SFX_ATTACK = "SFX_ATTACK";
        public const string SFX_HURT = "SFX_HURT";
        public const string MUSIC_COMBAT = "MUSIC_COMBAT";
        public const string MUSIC_MENU = "MUSIC_MENU";
    }
}
