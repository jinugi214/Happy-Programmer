using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerBattle : MonoBehaviour
{


    private void Update()
    {
        if (!MiniGameManager.instance.isLive)
            return;
    }


    void OnCollisionEnter2D(Collision2D collision)
    {

        string currentSceneName = SceneManager.GetActiveScene().name;
        if (!collision.gameObject.CompareTag("Monster"))
        {
            return;
        }

        if (!MiniGameManager.instance.isLive)
            return;


        MiniGameManager.instance.health -= collision.gameObject.GetComponent<FatalController>().monsterATK;
        Debug.Log("체력 감소 " + MiniGameManager.instance.health);



        if (MiniGameManager.instance.health < 0)
        {
            for (int index = 0; index < GameObject.Find("Spawners").GetComponent<Spawner>().transform.childCount ; index++)
            {
                Debug.Log(index);
                GameObject.Find("Spawners").GetComponent<Spawner>().transform.GetChild(index).gameObject.SetActive(false);             }


            Dead();
            MiniGameManager.instance.GameOver();


        }

    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (!collision.CompareTag("Bullet"))
            return;

        MiniGameManager.instance.health -= collision.GetComponent<Bullet>().damage;

        if (MiniGameManager.instance.health > 0) { 
        // 살아있음
        }
        else
        {
            Dead();
            MiniGameManager.instance.GameOver();

        }
    }

    void Dead()
    {

        DataManager.instance.UpdateCharacterData(MiniGameManager.instance.exp, MiniGameManager.instance.level);
        
    }
}
