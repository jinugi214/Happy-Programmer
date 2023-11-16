using UnityEngine;

public class NPC_JJW : MonoBehaviour
{
    private bool isFirstInteraction = true;

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            if (isFirstInteraction)
            {
                QuestManager.instance.acceptQuest(1);
                isFirstInteraction = false;
            }
        }
    }
}

