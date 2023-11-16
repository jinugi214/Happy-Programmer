using UnityEngine;

public class NPC_Senior : MonoBehaviour
{
    private bool hasCollided = false; // 충돌을 한번만 처리하기 위한 변수

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Player") && !hasCollided)
        {
            hasCollided = true;
            QuestManager.instance.acceptQuest(0);
        }
    }
}
