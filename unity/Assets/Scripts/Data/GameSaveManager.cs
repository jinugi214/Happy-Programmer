using System.Collections;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;

public class GameSaveManager : MonoBehaviour
{
    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";
    string accessToken;
    public void OnGameSaveButtonClicked()
    {
        StartCoroutine(SaveCharacterData());
    }

    // 캐릭터 게임 서버에 저장
    public IEnumerator SaveCharacterData()
    {

        accessToken = DataManager.instance.AccountData.accessToken;

        // 캐릭터 데이터를 JSON으로 변환
        string json = JsonUtility.ToJson(DataManager.instance.CharacterData);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/save", "POST"))
        {
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
                Debug.Log("저장에 실패하였습니다.");
            }
            else
            {
                Debug.Log("캐릭터 정보 저장이 성공하였습니다.");
            }
        }
    }
}
