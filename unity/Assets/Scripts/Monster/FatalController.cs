using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class FatalController : MonoBehaviour
{
    public static FatalController instance;

    public RuntimeAnimatorController[] animCon;
    public float health;
    public float maxHealth;
    public float speed;
    public int monsterType;
    public int monsterATK;
    public Rigidbody2D target;

    private float timeAfterAttack;

    bool isLive;

    Rigidbody2D rigid;
    Animator anim;
    WaitForFixedUpdate wait;

    void Awake()
    {
        instance = this;
        timeAfterAttack = 0;
        rigid = GetComponent<Rigidbody2D>();
        anim = GetComponent<Animator>();
        wait = new WaitForFixedUpdate();
    }

    void FixedUpdate()
    {
        if (!isLive || anim.GetCurrentAnimatorStateInfo(0).IsName("Hit"))
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
        monsterType = data.monsterType;
        monsterATK = data.monsterATK;
    }



    /*private void OnTriggerEnter2D(Collider2D collision)
    {
        StartCoroutine(KnockBack());
         
        if (health > 0)
        {
            anim.SetTrigger("Hit");
            Debug.Log("맞았다!");
        }
        else
        {
            isLive = false;

            MiniGameManager.instance.kill++;
            MiniGameManager.instance.GetExp();
        }
        
    }*/

        IEnumerator KnockBack()
        {
            yield return null; // 1 프레임 쉬기
            Vector3 playerPos = MiniGameManager.instance.player.transform.position;
            Vector3 dirVector = transform.position - playerPos;
            rigid.AddForce(dirVector.normalized * 3, ForceMode2D.Impulse);

        }

    void Dead()
    {
        gameObject.SetActive(false);
    }



    // 몬스터의 원거리 공격 

    public GameObject bulletPrefab;
    public float attackRate = 2f;
    



    void LongAttack()
    {
        timeAfterAttack  = Time.deltaTime;

        if (timeAfterAttack >= attackRate)
        {
            timeAfterAttack = 0f;
            GameObject bullet = Instantiate(bulletPrefab, transform.position, transform.rotation);
        }
    }

}
