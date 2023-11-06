using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MiniGameManager : MonoBehaviour
{
    public static MiniGameManager instance;

    public float gameTime;
    public float maxGameTime = 5 * 60f;

    public PoolManager pool;
    public CharacterInput player;

    void Awake()
    {
        instance = this;
    }

    private void Update()
    {
        gameTime += Time.deltaTime;


        if (gameTime > maxGameTime)
        {
            gameTime = maxGameTime;
        }
    }
}
