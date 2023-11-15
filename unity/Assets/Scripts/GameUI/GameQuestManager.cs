using TMPro;
using UnityEngine;

public class GameQuestManager : MonoBehaviour
{
    public TextMeshProUGUI title;
    public TextMeshProUGUI content;

    void Update()
    {
        DisplayCurrentQuests();
    }

    public void DisplayCurrentQuests()
    {
        var currentQuests = QuestManager.instance.GetCurrentQuests();

        if (currentQuests.Count > 0)
        {
            title.text = currentQuests[0].Name;
            content.text = currentQuests[0].Description;
        }
        else
        {
            // 현재 진행 중인 퀘스트가 없을 때에는 해당 UI를 비워줍니다.
            title.text = "현재 진행중인 퀘스트가 없습니다.";
            content.text = "새로운 퀘스트를 NPC와 상호작용하여 받아주세요";
        }
    }
}
