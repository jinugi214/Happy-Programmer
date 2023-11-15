using System.Collections.Generic;
using UnityEngine;

public class Quest
{
    public int Id { get; private set; }
    public string Name { get; private set; }
    public string Description { get; private set; }

    public Quest(int id, string name, string description)
    {
        this.Id = id;
        this.Name = name;
        this.Description = description;
    }
}

public class QuestManager : MonoBehaviour
{
    // 싱글톤
    public static QuestManager instance;

    private void Awake()
    {
        #region 싱글톤
        if (instance == null)
        {
            instance = this;
        }
        else if (instance != this)
        {
            Destroy(instance.gameObject);
            return;
        }

        DontDestroyOnLoad(this.gameObject);
        #endregion

    }

    // 모든 퀘스트를 저장하는 딕셔너리
    private Dictionary<int, Quest> quests = new Dictionary<int, Quest>();

    // 완료한 퀘스트의 id값을 담는 리스트
    private List<int> completedQuestIds = new List<int>();

    // 받은 퀘스트의 id값을 담는 리스트
    private List<int> receivedQuestIds = new List<int>();

    // 퀘스트 id값으로 가져오기
    public Quest GetQuest(int questId)
    {
        // 특정 ID의 퀘스트를 반환
        if (quests.TryGetValue(questId, out Quest quest))
        {
            return quest;
        }
        else
        {
            return null;
        }
    }

    // 퀘스트 완료
    public void CompleteQuest(int questId)
    {
        // 아직 완료하지 않은 퀘스트라면 리스트에 추가
        if (!completedQuestIds.Contains(questId))
        {
            completedQuestIds.Add(questId);
        }
    }

    // 퀘스트 추가
    public void AddQuest(Quest quest)
    {
        // 새 퀘스트를 딕셔너리에 추가
        if (!quests.ContainsKey(quest.Id))
        {
            quests.Add(quest.Id, quest);
        }
    }

    // 퀘스트 받았는지 확인
    public bool IsQuestReceived(int questId)
    {
        // 받은 퀘스트 리스트에 있는지 확인
        if (receivedQuestIds.Contains(questId))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<Quest> GetCurrentQuests()
    {
        List<Quest> currentQuests = new List<Quest>();
        foreach (int id in receivedQuestIds)
        {
            if (!completedQuestIds.Contains(id))
            {
                currentQuests.Add(GetQuest(id));
            }
        }
        return currentQuests;
    }
}
