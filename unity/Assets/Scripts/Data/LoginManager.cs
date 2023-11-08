using System.Collections;
using System.Text;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public class LoginManager : MonoBehaviour
{
    public TMP_InputField username;
    public TMP_InputField password;
    public Button loginButton;


    private void Start()
    {
        username = transform.Find("InputID").GetComponent<TMP_InputField>();
        password = transform.Find("InputPW").GetComponent<TMP_InputField>();
        loginButton = transform.Find("Login").Find("LoginBtn").GetComponent<Button>();
    }

    public void OnLoginButtonClicked()
    {
        string username = this.username.text;
        string password = this.password.text;
        StartCoroutine(Login(username, password));
    }

    IEnumerator Login(string username, string password)
    {
        if (username == "" || password == "")
        {
            Debug.LogError("아이디와 패스워드를 모두 입력해주세요");
            yield break;
        }

        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { username = username, password = password });


        string url = "http://k9d210.p.ssafy.io:8081/api/account/login";

        // POST 요청 보내기
        using (UnityWebRequest www = new UnityWebRequest(url, "POST"))
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
                Debug.Log("로그인 실패");
            }
            else
            {
                // 로그인 성공시 처리하는 로직
                Debug.Log("로그인 성공");
                string response = www.downloadHandler.text;
                var data = JsonUtility.FromJson<Dictionary<string, string>>(response);
                string accountId = data["acocuntId"];
                StartCoroutine(ServerManager.instance.LoadCharacterData(accountId));
            }
        }
    }


   

}
