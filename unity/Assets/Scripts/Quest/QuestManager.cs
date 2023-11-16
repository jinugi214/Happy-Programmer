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

        InitializeQuest();
    }


    // 퀘스트 정보를 담는 딕셔너리
    private Dictionary<int, Quest> allQuest = new Dictionary<int, Quest>();

    // 완료한 퀘스트를 담는 리스트
    private List<int> completedQuestIds = new List<int>();

    // 받은 퀘스트를 담는 리스트
    private List<int> accpetedQuestIds = new List<int>();

    private void InitializeQuest()
    {
        AddQuest(new Quest(0, "[출근 첫날]", "차선호 개발자님이 말씀하신대로 엘레베이터를 타고 올라가보자!"));
        AddQuest(new Quest(1, "[커피 심부름]", "정진욱 과장이 비품실에 있는 커피를 가져오라고 시켰다."));
        AddQuest(new Quest(2, "[문서 가져오기]", "김수민 대리가 인쇄실에 있는 문서를 가져오라고 시켰다."));
        AddQuest(new Quest(3, "[부장의 부탁]", "손준배 부장이 회사 잡무를 부탁하였다. 택배를 주인에게 가져다주자."));
        AddQuest(new Quest(4, "[택배 주인 가져다주기]", "택배 수령 장소가 B11로 되어있다. 수령인에게 가져다주자."));

    }

    // 퀘스트 완료
    public void CompleteQuest(int questId)
    {
        // 아직 완료하지 않은 퀘스트라면 리스트에 추가
        if (!completedQuestIds.Contains(questId))
        {
            accpetedQuestIds.Remove(questId);
            completedQuestIds.Add(questId);
        }
    }

    // 퀘스트 정보에 퀘스트 추가
    public void AddQuest(Quest quest)
    {
        // 새 퀘스트를 딕셔너리에 추가
        if (!allQuest.ContainsKey(quest.Id))
        {
            allQuest.Add(quest.Id, quest);
        }
    }

    // 퀘스트를 수락하여 추가
    public void acceptQuest(int questId)
    {
        accpetedQuestIds.Add(questId);
    }
    
    public Quest GetQuest(int questId)
    {
        return allQuest[questId];
    }

    // 수락한 퀘스트 확인하기
    public List<Quest> GetCurrentQuests()
    {
        List<Quest> currentQuests = new List<Quest>();
        foreach (int id in accpetedQuestIds)
        {
            if (!completedQuestIds.Contains(id))
            {
                currentQuests.Add(GetQuest(id));
            }
        }
        return currentQuests;
    }
}
