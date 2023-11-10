using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class LoadCharacter : MonoBehaviour
{
    public void OnLoadStartClicked()
    {
        StartCoroutine(LoadCharacterData());
    }
    
    IEnumerator LoadCharacterData()
    {

        string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";


        string accessToken = DataManager.instance.AccountData.accessToken;
        Debug.Log(accessToken);
        
        

        using (UnityWebRequest www = new UnityWebRequest(serverUrl, "GET"))
        {
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 캐릭터 생성 실패시
                Debug.Log(www.error);
                Debug.Log("캐릭터 불러오기 실패");
            }
            else
            {
                // 캐릭터 생성 성공시
                string response = www.downloadHandler.text;
                CharacterData character = JsonUtility.FromJson<CharacterData>(response);
                DataManager.instance.CharacterData = character;
                Debug.Log("캐릭터 불러오기 성공");
                SceneManager.LoadScene("LobbyMap");
            }
        }
    }
}
