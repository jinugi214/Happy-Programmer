using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerBattle : MonoBehaviour
{
    void OnCollisionEnter2D(Collision2D collision)
    {
        if (MiniGameManager.instance.isLive)
            return;

        MiniGameManager.instance.health -= Time.deltaTime * 10;
        Debug.Log("체력 감소 " + MiniGameManager.instance.health);



        if (MiniGameManager.instance.health < 0)
        {
            for (int index = 0; index < transform.childCount; index++)
            {
                transform.GetChild(index).gameObject.SetActive(false);
            }


        }

    }
}
