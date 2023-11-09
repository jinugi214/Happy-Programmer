using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class FatalController : MonoBehaviour
{
    public RuntimeAnimatorController[] animCon;
    public float health;
    public float maxHealth;
    public float speed;
    public Rigidbody2D target;

    bool isLive;

    Rigidbody2D rigid;
    Animator anim;

    void Awake()
    {
        rigid = GetComponent<Rigidbody2D>();
        anim = GetComponent<Animator>();
    }

    void FixedUpdate()
    {
        if (!isLive)
            return;
        


        Vector2 dirVec = target.position - rigid.position;
        Vector2 nextVec = dirVec.normalized * speed * Time.fixedDeltaTime;
        rigid.MovePosition(rigid.position + nextVec);
        rigid.velocity = Vector2.zero;
    }

    void OnEnable()
    {
        target = MiniGameManager.instance.player.GetComponent<Rigidbody2D>();
        isLive = true;
        health = maxHealth;


    }

    public void Init(SpawnData data)
    {
        anim.runtimeAnimatorController = animCon[data.spriteType];
        speed = data.speed;
        maxHealth = data.health;
        health = data.health;
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(health <= 0)
        {
            isLive = false;

            MiniGameManager.instance.kill++;
            MiniGameManager.instance.GetExp();
        }
    }
}
