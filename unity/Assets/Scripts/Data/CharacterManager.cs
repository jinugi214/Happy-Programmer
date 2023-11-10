using System.Collections;
using System.Linq;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;


public class CharacterManager : MonoBehaviour
{
    [System.Serializable]
    private class NewCharacter
    {
        public string characterName;
        public char gender;
    }


    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/character";

    public TMP_InputField characterName;
    public ToggleGroup gender;
    
    void Start()
    {
        characterName = transform.Find("Name").Find("CharacterName").GetComponent<TMP_InputField>();
        gender = transform.Find("Gender").Find("RadioBtnGroup").GetComponent<ToggleGroup>();
    }
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
        NewCharacter newCharacter = new NewCharacter();
        string activatedGender = gender.ActiveToggles().FirstOrDefault().GetComponentInChildren<Text>().text;
        if (activatedGender == "여자")
        {
            newCharacter.gender = char.Parse("F");
        }
        else
        {
            newCharacter.gender = char.Parse("M");
        }
        Debug.Log(newCharacter.characterName);
        Debug.Log(newCharacter.gender);
        StartCoroutine(CreateNewCharacter(newCharacter));
    }

    IEnumerator CheckNewCharacterName(string InputCharactername)
    {

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { name = InputCharactername });


        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/name/check", "POST"))
        {
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
                Debug.Log("캐릭터 이름 체크 성공");
            }
        }
    }

    IEnumerator CreateNewCharacter(NewCharacter newCharacter)
    {
        string accessToken = DataManager.instance.AccountData.accessToken;
        Debug.Log(accessToken);

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
                Debug.Log("캐릭터 생성 실패");
            }
            else
            {
                // 캐릭터 생성 성공시
                Debug.Log("캐릭터 생성 성공");
                SceneManager.LoadScene("GameLogin"); // 어디로 이동하지
            }
        }
    }
}
