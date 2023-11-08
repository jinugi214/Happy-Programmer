using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MiniGameManager : MonoBehaviour
{
    public static MiniGameManager instance;

    [Header("# Game Control")]
    public float gameTime;
    public float maxGameTime = 5 * 60f;
    public bool isLive;

    [Header("# Player Info")]
    public int level;
    public int kill;
    public int exp;
    public int[] nextExp = { 10, 30, 60, 100, 150 };
    public float health;
    public float maxHealth = 100;

    

    [Header("# Game Object")]
    public PoolManager pool;
    public CharacterInput player;

    void Awake()
    {
        instance = this;
        isLive = true;
        health = maxHealth;
    }

    private void Update()
    {
        gameTime += Time.deltaTime;


        if (gameTime > maxGameTime)
        {
            gameTime = maxGameTime;
        }
    }

    public void GetExp()
    {
        exp++;

        if (exp == nextExp[level])
        {
            level++;
            exp = 0;
        }
    }

    public void MonstersSkill(int monsterType)
    {
        switch (monsterType)
        {
            case 0:
                return;
            case 1:
                // 여기에 원거리 공격이나 근접공격이 되도록 넣어야... 하는데~
                return;
        }
    }
}
