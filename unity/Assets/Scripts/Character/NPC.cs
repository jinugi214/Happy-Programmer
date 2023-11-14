using UnityEngine;
public class NPC : MonoBehaviour
{
    public Dialogue dialogue;
    private DialogueManager theDM;

    // 캐릭터가 범위에 들어왔는지 확인하는 변수
    private bool isInRange = false;

    // 대화를 시작했는지 확인하는 변수
    private bool hasStartedDialogue = false;

    void Start()
    {
        theDM = FindObjectOfType<DialogueManager>();
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        // 캐릭터와 충돌한 경우
        if (collision.gameObject.CompareTag("Player"))
        {
            isInRange = true;
            // 대화를 시작하지 않았다면 대화 시작
            if (!hasStartedDialogue)
            {
                theDM.ShowDialogue(dialogue);
                hasStartedDialogue = true;
            }
        }
    }

    private void OnTriggerExit2D(Collider2D collision)
    {
        // 캐릭터와의 충돌이 끝난 경우
        if (collision.gameObject.CompareTag("Player"))
        {
            isInRange = false;
            DialogueManager.instance.ExitDialogue();
        }
    }
}
