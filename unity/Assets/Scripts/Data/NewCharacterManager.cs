using System.Collections;
using System.Linq;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;


public class NewCharacterManager : MonoBehaviour
{
    [System.Serializable]
    private class NewCharacter
    {
        public string characterName;
        public string gender;
    }


    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";

    public TMP_InputField characterName;
    public ToggleGroup gender;
    public bool characterNameCheck;

    public void OnCharacterNameCheckButtonClicked()
    {
        string InputCharactername = characterName.text;
        if (string.IsNullOrEmpty(InputCharactername))
        {
            Debug.LogError("캐릭터 이름을 입력해주세요");
            return;
        }
        StartCoroutine(CheckNewCharacterName(InputCharactername));
    }

    public void OnCreateNewCharacterButtonClicked()
    {
        if (!characterNameCheck)
        {
            Debug.Log("캐릭터 이름 중복확인을 해주세요");
            return;
        }

        NewCharacter newCharacter = new NewCharacter();
        newCharacter.characterName = characterName.text;
        string activatedGender = gender.ActiveToggles().FirstOrDefault().GetComponentInChildren<Text>().text;
        if (activatedGender == "여자")
        {
            newCharacter.gender = "F";
        }
        else
        {
            newCharacter.gender = "M";
        }
        Debug.Log(newCharacter.characterName);
        Debug.Log(newCharacter.gender);
        StartCoroutine(CreateNewCharacter(newCharacter));
    }

    IEnumerator CheckNewCharacterName(string inputCharacterName)
    {
        string accessToken = DataManager.instance.AccountData.accessToken;

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { name = inputCharacterName });

        // 파라미터 값을 URL에 포함시키기
        string urlWithParameter = serverUrl + "/check/nickname/" + UnityWebRequest.EscapeURL(inputCharacterName);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(urlWithParameter, "POST"))
        {
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 닉네임 체크 실패시
                Debug.Log(www.error);
                Debug.Log("캐릭터 이름 체크 실패");
            }
            else
            {
                // 닉네임 체크 성공시 처리하는 로직 작성란
                characterNameCheck = true;
                Debug.Log("캐릭터 이름 체크 성공");
            }
        }
    }

    IEnumerator CreateNewCharacter(NewCharacter newCharacter)
    {
        string accessToken = DataManager.instance.AccountData.accessToken;

        CharacterData characterData = new CharacterData();

        characterData.name = newCharacter.characterName;
        characterData.gender = newCharacter.gender;
        characterData.exp = 0;
        characterData.level = 1;
        characterData.savepoint = "LobbyMap";
        characterData.point = 0;
        characterData.storyProgress = 0;

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(characterData);
        Debug.Log(json);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/save", "POST"))
        {
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 캐릭터 생성 실패시
                Debug.Log(www.error);
                Debug.Log("캐릭터 생성 실패");
            }
            else
            {
                // 캐릭터 생성 성공시
                float delayTime = 2.0f; // 딜레이 시간 설정 (예: 2초)
                yield return new WaitForSeconds(delayTime);

                CharacterSaveLoadManager characterSaveLoadManager = new CharacterSaveLoadManager();
                characterSaveLoadManager.LoadCharacterData();
                Debug.Log("캐릭터 생성 성공");
                SceneManager.LoadScene("LobbyMap"); // 어디로 이동하지
            }
        }
    }
}