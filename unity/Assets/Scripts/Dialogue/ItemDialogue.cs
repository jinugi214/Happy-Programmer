using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ItemDialogue : MonoBehaviour
{
    public Dialogue dialogue;
    private DialogueManager theDM;

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
