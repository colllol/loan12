using UnityEngine;
using KyTran.Models;
using DG.Tweening;
using System.Collections;
using System.Collections.Generic;

namespace KyTran.Combat
{
    /// <summary>
    /// SkillVFX - Visual Effects cho các skill và đòn đánh.
    /// Sử dụng Object Pooling để tránh tạo/destroy objects liên tục.
    /// </summary>
    public class SkillVFX : MonoBehaviour
    {
        public static SkillVFX Instance { get; private set; }

        [Header("VFX Prefabs")]
        [SerializeField] private GameObject fireVFXPrefab;
        [SerializeField] private GameObject waterVFXPrefab;
        [SerializeField] private GameObject metalVFXPrefab;
        [SerializeField] private GameObject woodVFXPrefab;
        [SerializeField] private GameObject earthVFXPrefab;
        [SerializeField] private GameObject explosionVFXPrefab;
        [SerializeField] private GameObject slashVFXPrefab;
        [SerializeField] private GameObject impactVFXPrefab;

        [Header("Particle Settings")]
        [SerializeField] private int particlePoolSize = 20;
        [SerializeField] private float defaultVFXDuration = 2f;

        private Dictionary<ElementType, GameObject> elementVFXMap;
        private Queue<GameObject> availableVFX = new Queue<GameObject>();

        private void Awake()
        {
            if (Instance == null)
            {
                Instance = this;
                InitializePools();
            }
            else
            {
                Destroy(gameObject);
            }
        }

        private void InitializePools()
        {
            elementVFXMap = new Dictionary<ElementType, GameObject>
            {
                { ElementType.Fire, fireVFXPrefab },
                { ElementType.Water, waterVFXPrefab },
                { ElementType.Metal, metalVFXPrefab },
                { ElementType.Wood, woodVFXPrefab },
                { ElementType.Earth, earthVFXPrefab }
            };

            // Pre-instantiate particles
            for (int i = 0; i < particlePoolSize; i++)
            {
                GameObject vfxObj = CreateGenericVFX();
                vfxObj.SetActive(false);
                availableVFX.Enqueue(vfxObj);
            }
        }

        private GameObject CreateGenericVFX()
        {
            GameObject vfx = new GameObject("VFX_Particle");
            vfx.transform.SetParent(transform);

            // Add particle system
            ParticleSystem ps = vfx.AddComponent<ParticleSystem>();
            var main = ps.main;
            main.duration = 1f;
            main.startLifetime = 0.5f;
            main.startSpeed = 5f;
            main.startSize = 0.2f;
            main.simulationSpace = ParticleSystemSimulationSpace.World;
            main.maxParticles = 100;

            // Add renderer
            var renderer = vfx.GetComponent<ParticleSystemRenderer>();
            renderer.material = new Material(Shader.Find("Sprites/Default"));

            return vfx;
        }

        /// <summary>
        /// Play VFX cho đòn đánh của player.
        /// </summary>
        public void PlayAttackVFX(ElementType element, Vector3 position, Vector3 targetPosition)
        {
            StartCoroutine(PlayAttackVFXCoroutine(element, position, targetPosition));
        }

        private IEnumerator PlayAttackVFXCoroutine(ElementType element, Vector3 startPos, Vector3 targetPos)
        {
            // Tạo slash effect
            GameObject slash = Instantiate(slashVFXPrefab, startPos, Quaternion.identity);
            slash.transform.LookAt(targetPos);
            slash.transform.Rotate(90, 0, 0);

            // Di chuyển slash đến target
            slash.transform.DOMove(targetPos, 0.3f).SetEase(Ease.OutQuad);
            slash.transform.DOScale(2f, 0.3f).SetEase(Ease.OutQuad);

            // Đợi một chút rồi spawn element effect
            yield return new WaitForSeconds(0.15f);

            // Spawn element particle
            SpawnElementVFX(element, targetPos);

            // Impact effect tại target
            GameObject impact = Instantiate(impactVFXPrefab, targetPos, Quaternion.identity);
            Destroy(impact, 1f);

            Destroy(slash, 0.5f);
        }

        /// <summary>
        /// Spawn element-specific VFX.
        /// </summary>
        public void SpawnElementVFX(ElementType element, Vector3 position)
        {
            if (elementVFXMap.ContainsKey(element) && elementVFXMap[element] != null)
            {
                GameObject vfx = Instantiate(elementVFXMap[element], position, Quaternion.identity);
                Destroy(vfx, defaultVFXDuration);
            }
            else
            {
                // Fallback to generic VFX
                SpawnGenericVFX(position, GetElementColor(element));
            }
        }

        /// <summary>
        /// Spawn generic particle với màu.
        /// </summary>
        public void SpawnGenericVFX(Vector3 position, Color color)
        {
            GameObject vfx = GetFromPool();
            if (vfx != null)
            {
                vfx.transform.position = position;
                vfx.SetActive(true);

                ParticleSystem ps = vfx.GetComponent<ParticleSystem>();
                var main = ps.main;
                main.startColor = color;

                StartCoroutine(ReturnToPoolDelayed(vfx, defaultVFXDuration));
            }
        }

        /// <summary>
        /// Play explosion effect.
        /// </summary>
        public void PlayExplosionVFX(Vector3 position, float radius = 1f)
        {
            if (explosionVFXPrefab != null)
            {
                GameObject explosion = Instantiate(explosionVFXPrefab, position, Quaternion.identity);
                explosion.transform.localScale = Vector3.one * radius;
                Destroy(explosion, 2f);
            }
            else
            {
                // Fallback: tạo ring effect
                StartCoroutine(PlayRingExplosion(position, radius));
            }
        }

        private IEnumerator PlayRingExplosion(Vector3 position, float radius)
        {
            GameObject ring = GameObject.CreatePrimitive(PrimitiveType.Ring);
            ring.transform.position = position;
            ring.transform.localScale = Vector3.zero;

            SpriteRenderer sr = ring.GetComponent<SpriteRenderer>();
            if (sr != null)
            {
                sr.color = new Color(1f, 0.5f, 0f, 1f);
            }

            // Expand ring
            ring.transform.DOScale(radius * 3f, 0.3f).SetEase(Ease.OutQuad);
            sr.DOFade(0f, 0.3f).SetEase(Ease.InQuad);

            yield return new WaitForSeconds(0.3f);
            Destroy(ring);
        }

        /// <summary>
        /// Play line clear effect (Hỏa Tiễn).
        /// </summary>
        public void PlayLineClearVFX(Vector3 startPos, Vector3 endPos, ElementType element)
        {
            StartCoroutine(PlayLineClearCoroutine(startPos, endPos, element));
        }

        private IEnumerator PlayLineClearCoroutine(Vector3 start, Vector3 end, ElementType element)
        {
            // Tạo nhiều particles dọc theo đường line
            float duration = 0.5f;
            int particleCount = 10;

            for (int i = 0; i < particleCount; i++)
            {
                float t = (float)i / particleCount;
                Vector3 pos = Vector3.Lerp(start, end, t);

                SpawnElementVFX(element, pos);

                yield return new WaitForSeconds(duration / particleCount);
            }

            // Impact at end
            SpawnElementVFX(element, end);
        }

        /// <summary>
        /// Play cross clear effect (Bẫy Chông).
        /// </summary>
        public void PlayCrossClearVFX(Vector3 center, float size, ElementType element)
        {
            Vector3[] directions = {
                new Vector3(1, 1, 0).normalized,
                new Vector3(1, -1, 0).normalized,
                new Vector3(-1, -1, 0).normalized,
                new Vector3(-1, 1, 0).normalized
            };

            foreach (Vector3 dir in directions)
            {
                for (int i = 1; i <= 4; i++)
                {
                    Vector3 pos = center + dir * i * size * 0.25f;
                    SpawnElementVFX(element, pos);
                }
            }

            // Center explosion
            PlayExplosionVFX(center, size * 0.5f);
        }

        /// <summary>
        /// Play color bomb effect (Ngũ Hành Trận).
        /// </summary>
        public void PlayColorBombVFX(Vector3 center, float radius)
        {
            StartCoroutine(PlayColorBombCoroutine(center, radius));
        }

        private IEnumerator PlayColorBombCoroutine(Vector3 center, float radius)
        {
            Color[] rainbowColors = {
                Color.red, Color.yellow, Color.green, Color.cyan, Color.blue, Color.magenta
            };

            // Expand ring
            GameObject ring = GameObject.CreatePrimitive(PrimitiveType.Ring);
            ring.transform.position = center;
            ring.transform.localScale = Vector3.zero;

            SpriteRenderer sr = ring.GetComponent<SpriteRenderer>();

            foreach (Color color in rainbowColors)
            {
                if (sr != null) sr.color = color;
                ring.transform.DOScale(radius * 3f, 0.15f).SetEase(Ease.OutQuad);
                SpawnGenericVFX(center, color);

                yield return new WaitForSeconds(0.15f);
            }

            // Final explosion
            PlayExplosionVFX(center, radius);

            Destroy(ring, 0.5f);
        }

        /// <summary>
        /// Play critical hit effect.
        /// </summary>
        public void PlayCriticalHitVFX(Vector3 position)
        {
            // Tạo "CRITICAL!" text effect
            GameObject text = new GameObject("CriticalText");
            text.transform.position = position + Vector3.up;

            TMPro.TextMeshPro tmp = text.AddComponent<TMPro.TextMeshPro>();
            tmp.text = "CRITICAL!";
            tmp.fontSize = 5;
            tmp.color = Color.red;
            tmp.alignment = TMPro.TextAlignmentOptions.Center;

            // Animate
            text.transform.DOMoveY(position.y + 2f, 1f).SetEase(Ease.OutQuad);
            tmp.DOFade(0f, 1f).SetEase(Ease.InQuad);
            tmp.transform.DOScale(1.5f, 0.3f).SetEase(Ease.OutQuad);

            Destroy(text, 1.5f);

            // Spawn extra particles
            for (int i = 0; i < 5; i++)
            {
                Vector3 offset = Random.insideUnitSphere * 0.5f;
                SpawnGenericVFX(position + offset, Color.red);
            }
        }

        private Color GetElementColor(ElementType element)
        {
            switch (element)
            {
                case ElementType.Fire: return new Color(1f, 0.3f, 0f);
                case ElementType.Water: return new Color(0.2f, 0.5f, 1f);
                case ElementType.Metal: return new Color(1f, 0.9f, 0.2f);
                case ElementType.Wood: return new Color(0.2f, 0.8f, 0.2f);
                case ElementType.Earth: return new Color(0.6f, 0.4f, 0.2f);
                default: return Color.white;
            }
        }

        #region OBJECT_POOLING

        private GameObject GetFromPool()
        {
            if (availableVFX.Count > 0)
            {
                return availableVFX.Dequeue();
            }

            // Create new if pool empty
            return CreateGenericVFX();
        }

        private IEnumerator ReturnToPoolDelayed(GameObject obj, float delay)
        {
            yield return new WaitForSeconds(delay);
            obj.SetActive(false);
            availableVFX.Enqueue(obj);
        }

        #endregion
    }

    /// <summary>
    /// Simple VFX cho line clear (dùng LineRenderer).
    /// </summary>
    public class LineClearVFX : MonoBehaviour
    {
        [SerializeField] private LineRenderer lineRenderer;
        [SerializeField] private float animationDuration = 0.5f;
        [SerializeField] private Color lineColor = Color.red;
        [SerializeField] private float lineWidth = 0.3f;

        public void Play(Vector3 start, Vector3 end, Action onComplete)
        {
            if (lineRenderer == null)
            {
                lineRenderer = GetComponent<LineRenderer>();
            }

            lineRenderer.startWidth = lineWidth;
            lineRenderer.endWidth = lineWidth;
            lineRenderer.startColor = lineColor;
            lineRenderer.endColor = lineColor;
            lineRenderer.SetPosition(0, start);
            lineRenderer.SetPosition(1, start);

            gameObject.SetActive(true);

            // Animate line extension
            lineRenderer.DOMove(end, animationDuration, true)
                .OnComplete(() =>
                {
                    // Fade out
                    lineRenderer.DOColor(new Color(lineColor.r, lineColor.g, lineColor.b, 0f), 0.2f)
                        .OnComplete(() =>
                        {
                            gameObject.SetActive(false);
                            onComplete?.Invoke();
                        });
                });
        }
    }
}
