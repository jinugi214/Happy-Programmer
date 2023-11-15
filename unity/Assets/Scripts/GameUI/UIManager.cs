using UnityEngine;

public class UIManager : MonoBehaviour
{
    public static UIManager instance;

    public GameObject gameQuest;
    public GameObject gameSave;
    public GameObject gameSetting;
    public GameObject gameInventory;

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

    void Update()
    {
        //"I" 키를 누르면 인벤토리 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.I))
        {
            instance.gameInventory.SetActive(!instance.gameInventory.activeSelf);
        }

        // "Q" 키를 누르면 퀘스트 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.Q))
        {
            instance.gameQuest.SetActive(!instance.gameQuest.activeSelf);
        }

        // "ESC" 키를 누르면 설정 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            instance.gameSetting.SetActive(!instance.gameSetting.activeSelf);
        }

        // "F5" 키를 누르면 저장 창 활성화/비활성화
        if (Input.GetKeyDown(KeyCode.F5))
        {
            instance.gameSave.SetActive(!instance.gameSave.activeSelf);
        }
    }
}

