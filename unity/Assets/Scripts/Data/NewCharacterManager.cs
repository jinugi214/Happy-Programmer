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
        public string name;
        public string gender;
        public string savepoint = "LobbyMap";
        public string imgPath = "images/character/character_m.png";
    }


    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";

    public TMP_InputField characterName;
    public ToggleGroup gender;
    public bool characterNameCheck;
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;
    public CharacterLoadManager characterLoadManager;

    public void OnCharacterNameCheckButtonClicked()
    {
        string InputCharactername = characterName.text;
        if (string.IsNullOrEmpty(InputCharactername))
        {
            windowText.text = "캐릭터 이름을 입력해주세요!";
            checkWindow.SetActive(true);
            return;
        }
        StartCoroutine(CheckNewCharacterName(InputCharactername));
    }

    public void OnCreateNewCharacterButtonClicked()
    {
        if (!characterNameCheck)
        {
            windowText.text = "캐릭터 이름 중복확인을 해주세요";
            checkWindow.SetActive(true);
            return;
        }

        NewCharacter newCharacter = new NewCharacter();
        newCharacter.name = characterName.text;
        string activatedGender = gender.ActiveToggles().FirstOrDefault().GetComponentInChildren<Text>().text;
        if (activatedGender == "여자")
        {
            newCharacter.gender = "F";
        }
        else
        {
            newCharacter.gender = "M";
        }
        StartCoroutine(CreateNewCharacter(newCharacter));
    }

    IEnumerator CheckNewCharacterName(string inputCharacterName)
    {
        string accessToken = DataManager.instance.AccountData.accessToken;

        // 파라미터 값을 URL에 포함시키기
        string urlWithParameter = serverUrl + "/check/nickname/" + UnityWebRequest.EscapeURL(inputCharacterName);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(urlWithParameter, "GET"))
        {
            www.SetRequestHeader("Authorization", "Bearer " + accessToken);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 닉네임 체크 실패시
                Debug.Log(www.error);
                windowText.text = "중복된 캐릭터 이름입니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                // 닉네임 체크 성공시 처리하는 로직 작성란
                characterNameCheck = true;
                windowText.text = "만들 수 있는 캐릭터 이름입니다.";
                checkWindow.SetActive(true);
            }
        }
    }

    IEnumerator CreateNewCharacter(NewCharacter newCharacter)
    {
        string accessToken = DataManager.instance.AccountData.accessToken;

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(newCharacter);
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
                windowText.text = "캐릭터 생성에 실패하였습니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                // 캐릭터 생성 성공시
                windowText.text = "캐릭터 생성에 성공하였습니다. 곧 게임을 시작합니다.";
                checkWindow.SetActive(true);

                float delayTime = 2.0f; // 딜레이 시간 설정 (예: 2초)
                yield return new WaitForSeconds(delayTime);
                characterLoadManager.LoadCharacterData();
                
                SceneManager.LoadScene("LobbyMap"); // 어디로 이동하지
            }
        }
    }
}