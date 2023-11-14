using System.Collections;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class RegisterData
{
    public string username;
    public string password;
    public string nickname;
    public string language;
}

public class RegisterManager : MonoBehaviour
{
    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/account";

    public TMP_InputField nickname;
    public TMP_InputField username;
    public TMP_InputField password;
    public TMP_InputField passwordCheck;
    public TMP_Dropdown language;
    public Button registerButton;
    public Button nicknameCheckButton;
    public Button usernameCheckButton;
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;

    public void OnNicknameCheckButtonClicked()
    {
        string InputNickname = nickname.text;
        if (string.IsNullOrEmpty(InputNickname))
        {
            windowText.text = "닉네임을 입력해주세요";
            checkWindow.SetActive(true);
            return;
        }
        StartCoroutine(CheckNickname(InputNickname));
    }

    public void OnUsernameCheckButtonClicked()
    {
        string InputUsername = username.text;
        if (string.IsNullOrEmpty(InputUsername))
        {
            windowText.text = "아이디를 입력해주세요";
            checkWindow.SetActive(true);
            return;
        }

        StartCoroutine(CheckUsername(InputUsername));
    }

    public void OnRegisterButtonClicked()
    {
          
        RegisterData registerData = new RegisterData();
        registerData.username = username.text;
        registerData.password = password.text;
        registerData.nickname = nickname.text;
        registerData.language = language.options[language.value].text;

        if (string.IsNullOrEmpty(registerData.username) ||
            string.IsNullOrEmpty(registerData.password) ||
            string.IsNullOrEmpty(registerData.nickname) ||
            string.IsNullOrEmpty(registerData.language))
        {
            windowText.text = "입력칸을 모두 채워주세요";
            checkWindow.SetActive(true);
            return;
        }


        if (registerData.password != passwordCheck.text)
        {
            windowText.text = "비밀번호를 다시 확인해주세요!";
            checkWindow.SetActive(true);    
            return;
        }
        StartCoroutine(Register(registerData));
    }


    IEnumerator CheckNickname(string InputNickname)
    {
        string urlWithParameter = serverUrl + "/check/nickname/" + UnityWebRequest.EscapeURL(InputNickname);

        using (UnityWebRequest www = new UnityWebRequest(urlWithParameter, "GET"))

        {
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
                windowText.text = "중복된 닉네임입니다.";
                checkWindow.SetActive(true);

            }
            else
            {
                windowText.text = "만들 수 있는 닉네임입니다.";
                checkWindow.SetActive(true);
            }
        }
    }

    IEnumerator CheckUsername(string InputUsername)
    {
        string urlWithParameter = serverUrl + "/check/username/" + UnityWebRequest.EscapeURL(InputUsername);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(urlWithParameter, "GET"))
        {
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                Debug.Log(www.error);
                windowText.text = "중복된 아이디입니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                windowText.text = "만들 수 있는 아이디입니다.";
                checkWindow.SetActive(true);
            }
        }
    }


    IEnumerator Register(RegisterData registerData)
    {

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(registerData);
        Debug.Log(json);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/sign-up", "POST"))
        {
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 회원가입 실패시
                Debug.Log(www.error);
                windowText.text = "회원가입에 실패하였습니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                // 회원가입 성공시 처리하는 로직 작성란
                windowText.text = "성공적으로 회원가입이 되었습니다.";
                checkWindow.SetActive(true);
                SceneManager.LoadScene("GameLogin");
            }
        }
    }
}
