using System.Collections;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public class SaveManager : MonoBehaviour
{
    string serverUrl = "http://k9d210.p.ssafy.io:8081/save-file";

    public Button saveButton;

    private void Start()
    {
        saveButton = transform.Find("Save").Find("SaveBtn").GetComponent<Button>();
    }

    public IEnumerator SaveCharacterData(string accountId)
    {
        // 캐릭터 데이터를 JSON으로 변환
        string json = JsonUtility.ToJson(DataManager.instance.CharacterData);

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
