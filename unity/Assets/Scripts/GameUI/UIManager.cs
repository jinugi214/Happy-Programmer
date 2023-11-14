using UnityEngine;

public class UIManager : MonoBehaviour
{
    public static UIManager instance;

    public GameObject gameQuest;
    public GameObject gameSave;
    public GameObject gameSetting;

    private GameObject gameQuestInstance;
    private GameObject gameSaveInstance;
    private GameObject gameSettingInstance;

    void Awake()
    {
        if (instance == null)
        {
            instance = this;
            DontDestroyOnLoad(gameObject);
        }
        else
        {
            Destroy(gameObject);
            return;
        }

        gameQuestInstance = Instantiate(gameQuest);
        gameSaveInstance = Instantiate(gameSave);
        gameSettingInstance = Instantiate(gameSetting);

        // 처음에는 모두 비활성화합니다.
        gameQuestInstance.SetActive(false);
        gameSaveInstance.SetActive(false);
        gameSettingInstance.SetActive(false);
    }

    void Update()
    {
        // "I" 키를 누르면 인벤토리 창 활성화/비활성화
        //if (Input.GetKeyDown(KeyCode.I))
        //{

        //}

        // "Q" 키를 누르면 퀘스트 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.Q))
        {
            gameQuestInstance.SetActive(!gameQuestInstance.activeSelf);
        }

        // "ESC" 키를 누르면 설정 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            gameSettingInstance.SetActive(!gameSettingInstance.activeSelf);
        }

        // "CTRL + S" 키를 누르면 저장 창 활성화/비활성화
        if ((Input.GetKey(KeyCode.LeftControl) || Input.GetKey(KeyCode.RightControl)) && Input.GetKeyDown(KeyCode.S))
        {
            gameSaveInstance.SetActive(!gameSaveInstance.activeSelf);
        }
    }
}

