using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NPC_SJB : MonoBehaviour
{
    private bool isFirstInteraction = true;

    public void Interact()
    {
        // 첫 상호작용인 경우 퀘스트 추가
        if (isFirstInteraction)
        {
            QuestManager.instance.AddQuest(new Quest(3, "[부장의 부탁]", "손준배 부장이 회사 잡무를 부탁하였다. 택배를 주인에게 가져다주자."));
            isFirstInteraction = false;
        }
    }
}
