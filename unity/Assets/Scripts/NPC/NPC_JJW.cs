using UnityEngine;

public class NPC_JJW : MonoBehaviour
{
    private bool isFirstInteraction = true;

    public void Interact()
    {
        // 첫 상호작용인 경우 퀘스트 추가
        if (isFirstInteraction)
        {
            QuestManager.instance.AddQuest(new Quest(1, "[커피 심부름]", "정진욱 과장이 비품실에 있는 커피를 가져오라고 시켰다."));
            isFirstInteraction = false;
        }
    }
}
