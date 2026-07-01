using UnityEngine;
using KyTran.Models;
using System;
using System.Collections.Generic;

namespace KyTran.Combat
{
    /// <summary>
    /// CharacterData - ScriptableObject chứa stats của Tướng/Quái.
    /// </summary>
    [CreateAssetMenu(fileName = "NewCharacter", menuName = "KyTran/Character Data")]
    public class CharacterData : ScriptableObject
    {
        [Header("Basic Info")]
        public string characterName = "Tướng Lạc";
        public ElementType element = ElementType.Fire;

        [Header("Stats")]
        public int maxHealth = 1000;
        public int attack = 100;
        public int defense = 50;

        [Header("Visual")]
        public Sprite characterSprite;
        public Color characterColor = Color.white;
        public GameObject hitVFX;
        public GameObject attackVFX;
    }

    /// <summary>
    /// Character - Runtime instance của CharacterData.
    /// </summary>
    public class Character
    {
        public CharacterData Data { get; private set; }
        public int CurrentHealth { get; private set; }
        public bool IsDead => CurrentHealth <= 0;

        public Character(CharacterData data)
        {
            Data = data;
            CurrentHealth = data.maxHealth;
        }

        public void TakeDamage(int damage)
        {
            CurrentHealth = Mathf.Max(0, CurrentHealth - damage);
        }

        public void Heal(int amount)
        {
            CurrentHealth = Mathf.Min(Data.maxHealth, CurrentHealth + amount);
        }

        public float GetHealthPercent()
        {
            return (float)CurrentHealth / Data.maxHealth;
        }
    }
}
