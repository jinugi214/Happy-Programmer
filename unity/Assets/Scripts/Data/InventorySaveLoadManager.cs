using System.Collections;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class InventorySaveLoadManager
{

    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/inventory";
    string accessToken;

    // 캐릭터의 인벤토리 게임 서버에 저장
    public IEnumerator SaveInventoryData()
    {
        // 인벤토리 데이터를 JSON으로 변환
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
            }
            else
            {
                Debug.Log("캐릭터의 인벤토리 저장이 성공하였습니다.");
            }
        }
    }

    // 캐릭터의 인벤토리 서버에서 불러오기
    public IEnumerator LoadInventoryData()
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
                Debug.Log("캐릭터 불러오기 실패");
            }
            else
            {
                string response = www.downloadHandler.text;
                Debug.Log(response);
                CharacterData character = JsonUtility.FromJson<CharacterData>(response);
                DataManager.instance.CharacterData = character;
                SceneManager.LoadScene(DataManager.instance.CharacterData.savepoint);

                Debug.Log(DataManager.instance.CharacterData.name + "가 접속하였습니다.");
                Debug.Log("캐릭터 불러오기 성공");
            }
        }
    }
}