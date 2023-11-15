using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NPC_KSM : MonoBehaviour
{
    private bool isFirstInteraction = true;

    public void Interact()
    {
        // 첫 상호작용인 경우 퀘스트 추가
        if (isFirstInteraction)
        {
            QuestManager.instance.AddQuest(new Quest(2, "[문서 가져오기]", "김수민 대리가 인쇄실에 있는 문서를 가져오라고 시켰다."));
            isFirstInteraction = false;
        }
    }
}
