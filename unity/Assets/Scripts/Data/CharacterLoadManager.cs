using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class CharacterLoadManager : MonoBehaviour {

    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;
    public GameObject uiManagerPrefab;
    public GameObject questManagerPrefab;
    string accessToken;

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
                Instantiate(uiManagerPrefab);
                // QuestManager 인스턴스 생성
                Instantiate(questManagerPrefab);

                SceneManager.LoadScene(DataManager.instance.CharacterData.savepoint);
                Debug.Log("캐릭터 불러오기 성공");
            }
        }
    }
 }