using UnityEngine;
using KyTran.Models;
using KyTran.Combat;
using DG.Tweening;
using System;

namespace KyTran.Combat
{
    /// <summary>
    /// CharacterAnimator - State Machine quản lý animation của character.
    /// Các states: Idle, Attack, Hurt, Die, Special, Victory
    /// </summary>
    public class CharacterAnimator : MonoBehaviour
    {
        [Header("Character Settings")]
        [SerializeField] private bool isPlayer = true;
        [SerializeField] private ElementType element = ElementType.Fire;

        [Header("Animation Settings")]
        [SerializeField] private float attackDuration = 0.5f;
        [SerializeField] private float hurtDuration = 0.3f;
        [SerializeField] private float dieDuration = 1.0f;
        [SerializeField] private float attackMoveDistance = 1f;
        [SerializeField] private float attackMoveDuration = 0.15f;

        [Header("VFX Settings")]
        [SerializeField] private GameObject attackVFXPrefab;
        [SerializeField] private GameObject hurtVFXPrefab;
        [SerializeField] private GameObject dieVFXPrefab;
        [SerializeField] private Transform vfxSpawnPoint;

        // State Machine
        public enum CharacterState
        {
            Idle,
            Attack,
            Hurt,
            Die,
            Special,
            Victory
        }

        private CharacterState currentState = CharacterState.Idle;
        private bool isAnimating = false;

        // References
        private Transform cachedTransform;
        private Vector3 originalPosition;
        private SpriteRenderer spriteRenderer;

        // Events
        public event Action<CharacterState> OnStateChanged;
        public event Action OnAttackComplete;
        public event Action OnHurtComplete;
        public event Action OnDieComplete;

        // Properties
        public CharacterState CurrentState => currentState;
        public bool IsAnimating => isAnimating;

        private void Awake()
        {
            cachedTransform = transform;
            originalPosition = cachedTransform.localPosition;
            spriteRenderer = GetComponent<SpriteRenderer>();
        }

        private void Start()
        {
            SetState(CharacterState.Idle);
        }

        /// <summary>
        /// Đặt state mới cho character.
        /// </summary>
        public void SetState(CharacterState newState)
        {
            if (currentState == newState) return;
            if (isAnimating && newState == CharacterState.Idle) return; // Không interrupt animation

            CharacterState previousState = currentState;
            currentState = newState;

            Debug.Log($"Character state: {previousState} → {newState}");
            OnStateChanged?.Invoke(newState);

            // Handle state entry
            switch (newState)
            {
                case CharacterState.Idle:
                    OnEnterIdle();
                    break;
                case CharacterState.Attack:
                    PlayAttackAnimation();
                    break;
                case CharacterState.Hurt:
                    PlayHurtAnimation();
                    break;
                case CharacterState.Die:
                    PlayDieAnimation();
                    break;
                case CharacterState.Special:
                    PlaySpecialAnimation();
                    break;
                case CharacterState.Victory:
                    PlayVictoryAnimation();
                    break;
            }
        }

        #region IDLE

        private void OnEnterIdle()
        {
            // Reset position
            cachedTransform.localPosition = originalPosition;

            // Idle bounce animation
            cachedTransform.DOLocalMoveY(originalPosition.y + 0.1f, 0.5f)
                .SetEase(Ease.InOutSine)
                .SetLoops(-1, LoopType.Yoyo);
        }

        #endregion

        #region ATTACK

        /// <summary>
        /// Trigger attack animation với element.
        /// </summary>
        public void TriggerAttack(ElementType element)
        {
            if (isAnimating) return;
            SetState(CharacterState.Attack);
        }

        private void PlayAttackAnimation()
        {
            isAnimating = true;

            // Kill idle tween
            DOTween.Kill(cachedTransform);

            Sequence attackSeq = DOTween.Sequence();

            // Di chuyển về phía trước (hướng enemy)
            Vector3 attackDirection = isPlayer ? Vector3.down : Vector3.up;
            Vector3 attackPosition = originalPosition + attackDirection * attackMoveDistance;

            // Phase 1: Di chuyển nhanh về phía trước
            attackSeq.Append(cachedTransform.DOLocalMove(attackPosition, attackMoveDuration)
                .SetEase(Ease.OutQuad));

            // Phase 2: Đánh (scale bump)
            attackSeq.Append(cachedTransform.DOScale(1.2f, 0.05f)
                .SetEase(Ease.OutQuad));

            // Phase 3: Spawn VFX
            attackSeq.AppendCallback(() =>
            {
                SpawnAttackVFX();
                OnAttackComplete?.Invoke();
            });

            // Phase 4: Scale về normal
            attackSeq.Append(cachedTransform.DOScale(1f, 0.05f));

            // Phase 5: Di chuyển về vị trí ban đầu
            attackSeq.Append(cachedTransform.DOLocalMove(originalPosition, attackMoveDuration)
                .SetEase(Ease.OutQuad));

            // Phase 6: Return to idle
            attackSeq.AppendCallback(() =>
            {
                isAnimating = false;
                SetState(CharacterState.Idle);
            });
        }

        private void SpawnAttackVFX()
        {
            if (attackVFXPrefab == null) return;

            Transform spawnPoint = vfxSpawnPoint != null ? vfxSpawnPoint : cachedTransform;
            GameObject vfx = Instantiate(attackVFXPrefab, spawnPoint.position, Quaternion.identity);

            // Auto destroy
            Destroy(vfx, 2f);
        }

        #endregion

        #region HURT

        /// <summary>
        /// Trigger hurt animation khi nhận damage.
        /// </summary>
        public void TriggerHurt(int damageAmount)
        {
            if (currentState == CharacterState.Die) return;
            SetState(CharacterState.Hurt);
        }

        private void PlayHurtAnimation()
        {
            isAnimating = true;

            // Kill idle tween
            DOTween.Kill(cachedTransform);

            // Shake effect
            cachedTransform.DOShakePosition(hurtDuration, 0.2f, 10, 90f, false, true)
                .OnComplete(() =>
                {
                    // Flash red
                    if (spriteRenderer != null)
                    {
                        spriteRenderer.DOColor(Color.red, 0.1f)
                            .SetLoops(3, LoopType.Yoyo)
                            .OnComplete(() =>
                            {
                                isAnimating = false;
                                SetState(CharacterState.Idle);
                                OnHurtComplete?.Invoke();
                            });
                    }
                    else
                    {
                        isAnimating = false;
                        SetState(CharacterState.Idle);
                        OnHurtComplete?.Invoke();
                    }
                });

            // Spawn hurt VFX
            SpawnHurtVFX();
        }

        private void SpawnHurtVFX()
        {
            if (hurtVFXPrefab == null) return;

            GameObject vfx = Instantiate(hurtVFXPrefab, cachedTransform.position, Quaternion.identity);
            Destroy(vfx, 2f);
        }

        #endregion

        #region DIE

        /// <summary>
        /// Trigger die animation.
        /// </summary>
        public void TriggerDie()
        {
            if (currentState == CharacterState.Die) return;
            SetState(CharacterState.Die);
        }

        private void PlayDieAnimation()
        {
            isAnimating = true;

            // Kill all tweens
            DOTween.Kill(cachedTransform);

            Sequence dieSeq = DOTween.Sequence();

            // Fade out
            if (spriteRenderer != null)
            {
                dieSeq.Append(spriteRenderer.DOFade(0f, dieDuration)
                    .SetEase(Ease.InQuad));
            }

            // Fall down
            dieSeq.Join(cachedTransform.DOLocalMoveY(originalPosition.y - 1f, dieDuration)
                .SetEase(Ease.InQuad));

            // Scale down
            dieSeq.Join(cachedTransform.DOScale(0.5f, dieDuration)
                .SetEase(Ease.InQuad));

            dieSeq.OnComplete(() =>
            {
                isAnimating = false;
                OnDieComplete?.Invoke();
            });

            // Spawn die VFX
            SpawnDieVFX();
        }

        private void SpawnDieVFX()
        {
            if (dieVFXPrefab == null) return;

            GameObject vfx = Instantiate(dieVFXPrefab, cachedTransform.position, Quaternion.identity);
            Destroy(vfx, 3f);
        }

        #endregion

        #region SPECIAL

        /// <summary>
        /// Trigger special skill animation.
        /// </summary>
        public void TriggerSpecial(SpecialType specialType)
        {
            if (isAnimating) return;
            SetState(CharacterState.Special);
        }

        private void PlaySpecialAnimation()
        {
            isAnimating = true;

            // Kill idle tween
            DOTween.Kill(cachedTransform);

            Sequence specialSeq = DOTween.Sequence();

            // Glow effect
            if (spriteRenderer != null)
            {
                Color specialColor = GetSpecialColor();
                specialSeq.Append(spriteRenderer.DOColor(specialColor, 0.2f));

                // Pulse
                specialSeq.Append(cachedTransform.DOScale(1.5f, 0.2f)
                    .SetEase(Ease.OutQuad));

                specialSeq.Append(cachedTransform.DOScale(1f, 0.2f)
                    .SetEase(Ease.InQuad));

                // Return to normal
                specialSeq.Append(spriteRenderer.DOColor(Color.white, 0.2f));
            }
            else
            {
                specialSeq.Append(cachedTransform.DOScale(1.5f, 0.2f));
                specialSeq.Append(cachedTransform.DOScale(1f, 0.2f));
            }

            specialSeq.OnComplete(() =>
            {
                isAnimating = false;
                SetState(CharacterState.Idle);
            });

            // Spawn special VFX
            SpawnSpecialVFX();
        }

        private Color GetSpecialColor()
        {
            switch (currentState)
            {
                case CharacterState.Special:
                    return new Color(1f, 0.8f, 0f); // Gold
                default:
                    return Color.yellow;
            }
        }

        private void SpawnSpecialVFX()
        {
            if (attackVFXPrefab == null) return;

            Transform spawnPoint = vfxSpawnPoint != null ? vfxSpawnPoint : cachedTransform;
            GameObject vfx = Instantiate(attackVFXPrefab, spawnPoint.position, Quaternion.identity);
            Destroy(vfx, 3f);
        }

        #endregion

        #region VICTORY

        /// <summary>
        /// Trigger victory animation.
        /// </summary>
        public void TriggerVictory()
        {
            if (currentState == CharacterState.Die) return;
            SetState(CharacterState.Victory);
        }

        private void PlayVictoryAnimation()
        {
            // Kill all tweens
            DOTween.Kill(cachedTransform);

            // Jump celebration
            cachedTransform.DOLocalMoveY(originalPosition.y + 0.5f, 0.3f)
                .SetEase(Ease.OutQuad)
                .SetLoops(-1, LoopType.Yoyo);
        }

        #endregion

        /// <summary>
        /// Reset về idle state.
        /// </summary>
        public void ResetToIdle()
        {
            DOTween.Kill(cachedTransform);
            isAnimating = false;
            currentState = CharacterState.Idle;
            cachedTransform.localPosition = originalPosition;
            cachedTransform.localScale = Vector3.one;

            if (spriteRenderer != null)
            {
                spriteRenderer.color = Color.white;
                spriteRenderer.DOFade(1f, 0.1f);
            }

            SetState(CharacterState.Idle);
        }
    }
}
