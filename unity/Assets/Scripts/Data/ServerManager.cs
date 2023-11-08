using System.Collections;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public class ServerManager : MonoBehaviour
{
    string serverUrl = "http://k9d210.p.ssafy.io:8081/save-file";

    public Button saveButton;
    public Button loadButton;

    
    // 싱글톤 패턴 적용
    public static ServerManager instance;


    private void Awake()
    {
        if (instance == null)
        {
            instance = this;
            DontDestroyOnLoad(this.gameObject);
        }
        else if (instance != this)
        {
            Destroy(instance.gameObject);
        }
    }

    private void Start()
    {
        // 버튼의 onClick 이벤트에 메소드를 할당
        saveButton.onClick.AddListener(() => StartCoroutine(SaveCharacterData("accountId")));
        loadButton.onClick.AddListener(() => StartCoroutine(LoadCharacterData("accountId")));
    }

    public IEnumerator LoadCharacterData(string accountId)
    {
        using (UnityWebRequest www = UnityWebRequest.Get(serverUrl + "/" + accountId))
        {
            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
            }
            else
            {
                string json = www.downloadHandler.text;
                DataManager.instance.characterData = JsonUtility.FromJson<CharacterData>(json);

                Debug.Log("캐릭터 정보 불러오기를 성공하였습니다.");
            }
        }
    }

    public IEnumerator SaveCharacterData(string accountId)
    {
        // 캐릭터 데이터를 JSON으로 변환
        string json = JsonUtility.ToJson(DataManager.instance.characterData);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl, "POST"))
        {
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
            }
            else
            {
                Debug.Log("캐릭터 정보 저장이 성공하였습니다.");
            }
        }
    }
}
