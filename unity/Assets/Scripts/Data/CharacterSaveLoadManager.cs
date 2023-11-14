using System.Collections;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class CharacterSaveLoadManager : MonoBehaviour {

    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;
    public GameObject uiManagerPrefab;
    string accessToken;

    // 캐릭터 게임 서버에 저장
    public IEnumerator SaveCharacterData()
    {
        // 캐릭터 데이터를 JSON으로 변환
        string json = JsonUtility.ToJson(DataManager.instance.CharacterData);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/save", "POST"))
        {
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
                windowText.text = "저장에 실패하였습니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                windowText.text = "캐릭터 정보 저장이 성공하였습니다.";
                checkWindow.SetActive(true);
            }
        }
    }

    // 캐릭터 서버에서 불러오기
    public IEnumerator LoadCharacterData()
    {
        accessToken = DataManager.instance.AccountData.accessToken;

        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/my", "GET"))
        {
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
                windowText.text = "캐릭터를 새롭게 생성해주세요!";
                checkWindow.SetActive(true);

            }
            else
            {
                string response = www.downloadHandler.text;
                Debug.Log(response);
                CharacterData character = JsonUtility.FromJson<CharacterData>(response);
                Debug.Log(character);
                DataManager.instance.CharacterData = character;
                // UIManager 인스턴스 생성
                if (UIManager.instance == null)
                {
                    Instantiate(uiManagerPrefab);
                }
                SceneManager.LoadScene(DataManager.instance.CharacterData.savepoint);
                Debug.Log("캐릭터 불러오기 성공");

            }
        }
    }
 }