using UnityEngine;

public class NPC : MonoBehaviour
{
    public Dialogue dialogue;
    private DialogueManager theDM;
    private int cnt = 0;

    // 캐릭터가 범위에 들어왔는지 확인하는 변수
    public bool isInRange = false;

    void Start()
    {
        theDM = FindObjectOfType<DialogueManager>();
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            isInRange = true;
            if (!dialogue.IsDone())
            {
                theDM.ShowDialogue(dialogue);
                Debug.Log(cnt + "번 퀘스트");
                QuestManager.instance.acceptQuest(cnt);
                if (cnt > 0)
                {
                    QuestManager.instance.CompleteQuest(cnt - 1);

                }
                cnt += 1;

            }
        }
    }

    private void OnTriggerExit2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            isInRange = false;
            DialogueManager.instance.ExitDialogue();
        }
    }
}
