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

    private void Start()
    {
        nickname = transform.Find("Nickname").GetComponent<TMP_InputField>();
        username = transform.Find("ID").GetComponent<TMP_InputField>();
        password = transform.Find("Password").GetComponent<TMP_InputField>();
        passwordCheck = transform.Find("PasswordCheck").GetComponent<TMP_InputField>();
        language = transform.Find("Dropdown").GetComponent<TMP_Dropdown>();
        nicknameCheckButton = transform.Find("NicknameCheck").Find("NicknameCheckBtn").GetComponent<Button>();
        usernameCheckButton = transform.Find("IdCheck").Find("IdCheckBtn").GetComponent<Button>();
        registerButton = transform.Find("Confirm").Find("ConfirmBtn").GetComponent<Button>();
    }

    public void OnNicknameCheckButtonClicked()
    {
        string InputNickname = nickname.text;
        if (string.IsNullOrEmpty(InputNickname))
        {
            Debug.LogError("닉네임을 입력해주세요");
            return;
        }
        StartCoroutine(CheckNickname(InputNickname));
    }

    public void OnUsernameCheckButtonClicked()
    {
        string InputUsername = username.text;
        if (string.IsNullOrEmpty(InputUsername))
        {
            Debug.LogError("아이디를 입력해주세요");
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
            Debug.LogError("입력칸을 모두 채워주세요");
            return;
        }


        if (registerData.password != passwordCheck.text)
        {
            Debug.LogError("비밀번호를 다시 확인해주세요!");
            return;
        }
        StartCoroutine(Register(registerData));
    }


    IEnumerator CheckNickname(string InputNickname)
    {

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { nickname = InputNickname });


        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/nickname-check", "POST"))
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
                Debug.Log("닉네임 체크 실패"); 
            }
            else
            {
                // 닉네임 체크 성공시 처리하는 로직 작성란
                Debug.Log("닉네임 체크 성공");
            }
        }
    }

    IEnumerator CheckUsername(string InputUsername)
    {
        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { username = InputUsername });


        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(serverUrl + "/username-check", "POST"))
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
                Debug.Log("아이디 체크 실패");
            }
            else
            {
                // 닉네임 체크 성공시 처리하는 로직 작성란
                Debug.Log("아이디 체크 성공");
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
                Debug.Log("회원가입 실패");
            }
            else
            {
                // 회원가입 성공시 처리하는 로직 작성란
                Debug.Log("회원가입 성공");
                SceneManager.LoadScene("GameLogin");
            }
        }
    }
}
