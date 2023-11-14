using System.Collections;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LoginManager : MonoBehaviour
{

    [System.Serializable]
    private class LoginData
    {
        public string username;
        public string password;
    }

    [System.Serializable]
    private class AccountFirstResponse
    {
        public long accountId;
        public string accessToken;
        public string refreshToken;
    }

    [System.Serializable]
    private class AccountSecondResponse
    {
        public string username;
        public string nickname;
        public string language;
    }


    private string url = "http://k9d210.p.ssafy.io:8081/api/account";

    public TMP_InputField username;
    public TMP_InputField password;
    public Button loginButton;
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;
    public GameObject dataManagerPrefab;


    public void OnLoginButtonClicked()
    {
        string InputUsername = username.text;
        string InputPassword = password.text;
        if (string.IsNullOrEmpty(InputUsername) || string.IsNullOrEmpty(InputPassword))
        {
            windowText.text = "아이디와 비밀번호를 모두 입력해주세요";
            checkWindow.SetActive(true);
            return;
        }
        LoginData loginData = new LoginData();
        loginData.username = InputUsername;
        loginData.password = InputPassword;
        StartCoroutine(Login(loginData));
    }

    IEnumerator Login(LoginData loginData)
    {
        
        // JSON으로 변환하기
        string json = JsonUtility.ToJson(loginData);

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(url + "/login", "POST"))
        {
            // JSON 올바른지 확인해보기
            byte[] bodyRaw = Encoding.UTF8.GetBytes(json);
            www.uploadHandler = new UploadHandlerRaw(bodyRaw);
            www.downloadHandler = new DownloadHandlerBuffer();
            www.SetRequestHeader("Content-Type", "application/json");

            yield return www.SendWebRequest();

            if (www.result != UnityWebRequest.Result.Success)
            {
                // 로그인 실패시
                Debug.Log(www.error);
                windowText.text = "아이디 또는 비밀번호가 틀렸습니다.";
                checkWindow.SetActive(true);
            }
            else
            {
                // 로그인 성공시 처리하는 로직
                string response = www.downloadHandler.text;
                AccountFirstResponse account = JsonUtility.FromJson<AccountFirstResponse>(response);
                Instantiate(dataManagerPrefab);
                DataManager.instance.AccountData.accessToken = account.accessToken;
                DataManager.instance.AccountData.refreshToken = account.refreshToken;
                DataManager.instance.AccountData.accountId = account.accountId.ToString();
                StartCoroutine(GetAccountInfo(account.accessToken));
                SceneManager.LoadScene("GameStart");
            }
        }
    }

    IEnumerator GetAccountInfo(string accessToken)
    {
        UnityWebRequest www = UnityWebRequest.Get(url + "/my/account");
        www.SetRequestHeader("Authorization", "Bearer " + accessToken);

        yield return www.SendWebRequest();

        if (www.result != UnityWebRequest.Result.Success)
        {
            Debug.LogError(www.error);
            Debug.Log("유저 정보를 가져오는 것에 실패했습니다.");
        }
        else
        {
            string response = www.downloadHandler.text;
            AccountSecondResponse account = JsonUtility.FromJson<AccountSecondResponse>(response);
            DataManager.instance.AccountData.username = account.username;
            DataManager.instance.AccountData.nickname = account.nickname;
            DataManager.instance.AccountData.language = account.language;
            Debug.Log("유저 정보를 가져오는 것에 성공했습니다.");
        }
    }
}
