using System.Collections;
using System.Collections.Generic;
using System.Security.Cryptography;
using UnityEngine;

public class Spawner : MonoBehaviour
{
    public Transform[] spawnPoint;

    float timer;

    void Awake()
    {
        spawnPoint = GetComponentsInChildren<Transform>();
    }

    void Update()
    {
        timer += Time.deltaTime;

        if(timer > 2.5f)
        {
            timer = 0f;
            Spawn();
        }

    }

    void Spawn()
    {
        GameObject monster = MiniGameManager.instance.pool.Get(0);
        monster.transform.position = spawnPoint[UnityEngine.Random.Range(1, spawnPoint.Length)].position;
    
    }
}
