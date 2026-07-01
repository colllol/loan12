using UnityEngine;
using System;
using System.Collections.Generic;

namespace KyTran.Core
{
    /// <summary>
    /// ServiceLocator - Hệ thống Dependency Injection đơn giản.
    /// Quản lý các instance của Manager để các class khác có thể truy cập dễ dàng.
    ///
    /// Cách dùng:
    /// - ServiceLocator.Register(manager);
    /// - var grid = ServiceLocator.Get&lt;GridManager&gt;();
    /// - var grid = GridManager.Instance; // Hoặc dùng Singleton pattern
    /// </summary>
    public class ServiceLocator
    {
        // ============================================================
        // SINGLETON
        // ============================================================

        private static ServiceLocator _instance;
        public static ServiceLocator Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new ServiceLocator();
                }
                return _instance;
            }
        }

        private ServiceLocator()
        {
            _services = new Dictionary<Type, object>();
        }

        // ============================================================
        // STORAGE
        // ============================================================

        private readonly Dictionary<Type, object> _services;
        private readonly List<GameObject> _registeredPrefabs = new List<GameObject>();

        // ============================================================
        // REGISTER (Đăng ký)
        // ============================================================

        /// <summary>
        /// Đăng ký một service (Singleton pattern).
        /// </summary>
        public static void Register<T>(T service) where T : class
        {
            Instance.RegisterInternal(typeof(T), service);
        }

        /// <summary>
        /// Đăng ký một MonoBehaviour làm service.
        /// </summary>
        public static void RegisterMono<T>(T monoBehaviour) where T : MonoBehaviour
        {
            Instance.RegisterInternal(typeof(T), monoBehaviour);
            DontDestroyOnLoad(monoBehaviour.gameObject);
        }

        /// <summary>
        /// Đăng ký một prefab để Instantiate sau.
        /// </summary>
        public static void RegisterPrefab<T>(T prefab) where T : MonoBehaviour
        {
            if (Instance._registeredPrefabs.Contains(prefab.gameObject) == false)
            {
                Instance._registeredPrefabs.Add(prefab.gameObject);
            }
        }

        private void RegisterInternal(Type type, object service)
        {
            if (_services.ContainsKey(type))
            {
                Debug.LogWarning($"[ServiceLocator] Service {type.Name} already registered. Replacing...");
                _services[type] = service;
            }
            else
            {
                _services.Add(type, service);
                Debug.Log($"[ServiceLocator] Registered: {type.Name}");
            }
        }

        // ============================================================
        // GET (Lấy về)
        // ============================================================

        /// <summary>
        /// Lấy một service đã đăng ký.
        /// Throw exception nếu không tìm thấy.
        /// </summary>
        public static T Get<T>() where T : class
        {
            return Instance.GetInternal<T>();
        }

        /// <summary>
        /// Lấy một service đã đăng ký.
        /// Return null nếu không tìm thấy.
        /// </summary>
        public static T GetSafe<T>() where T : class
        {
            try
            {
                return Instance.GetInternal<T>();
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Kiểm tra xem service đã được đăng ký chưa.
        /// </summary>
        public static bool IsRegistered<T>() where T : class
        {
            return Instance._services.ContainsKey(typeof(T));
        }

        private T GetInternal<T>() where T : class
        {
            Type type = typeof(T);

            if (_services.TryGetValue(type, out object service))
            {
                return service as T;
            }

            Debug.LogError($"[ServiceLocator] Service {type.Name} not found!");
            return null;
        }

        // ============================================================
        // UNREGISTER (Hủy đăng ký)
        // ============================================================

        /// <summary>
        /// Hủy đăng ký một service.
        /// </summary>
        public static void Unregister<T>() where T : class
        {
            Instance._services.Remove(typeof(T));
            Debug.Log($"[ServiceLocator] Unregistered: {typeof(T).Name}");
        }

        /// <summary>
        /// Hủy đăng ký tất cả services.
        /// </summary>
        public static void UnregisterAll()
        {
            _services.Clear();
            _registeredPrefabs.Clear();
            Debug.Log("[ServiceLocator] All services unregistered.");
        }

        // ============================================================
        // INSTANTIATE PREFABS
        // ============================================================

        /// <summary>
        /// Instantiate một prefab đã đăng ký.
        /// </summary>
        public static T Instantiate<T>(T prefab) where T : MonoBehaviour
        {
            return UnityEngine.Object.Instantiate(prefab);
        }

        /// <summary>
        /// Instantiate tại vị trí.
        /// </summary>
        public static T Instantiate<T>(T prefab, Vector3 position, Quaternion rotation) where T : MonoBehaviour
        {
            return UnityEngine.Object.Instantiate(prefab, position, rotation);
        }

        // ============================================================
        // UTILITY
        // ============================================================

        /// <summary>
        /// Debug: In ra danh sách services đã đăng ký.
        /// </summary>
        public static void DebugPrintServices()
        {
            string output = "[ServiceLocator] Registered Services:\n";
            foreach (var kvp in Instance._services)
            {
                output += $"  - {kvp.Key.Name}: {kvp.Value}\n";
            }
            Debug.Log(output);
        }

        /// <summary>
        /// Số lượng services đã đăng ký.
        /// </summary>
        public static int ServiceCount => Instance._services.Count;
    }
}
