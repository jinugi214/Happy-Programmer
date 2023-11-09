using System.Collections;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public class RegisterManager : MonoBehaviour
{
    string serverUrl = "http://k9d210.p.ssafy.io:8081/api/account";

    public TMP_InputField username;
    public TMP_InputField password;
    public TMP_InputField nickname;
    public TMP_InputField language;
    public Button registerButton;

    private void Start()
    {
        username = transform.Find("InputID").GetComponent<TMP_InputField>();
        password = transform.Find("InputPW").GetComponent<TMP_InputField>();
        registerButton = transform.Find("Button").Find("Button").GetComponent<Button>();
    }

    public void OnRegisterButtonClicked()
    {
        string username = this.username.text;
        string password = this.password.text;
        string nickname = this.nickname.text;
        string language = this.language.text;
        StartCoroutine(Register(username, password, nickname, language));
    }
    IEnumerator Register(string username, string password, string nickname, string language)
    {

        if (username == "" || password == "" || nickname == "" || language == "")
        {
            Debug.LogError("입력칸을 모두 입력해주세요");
        }


        // JSON으로 변환하기
        string json = JsonUtility.ToJson(new { username = username, password = password });


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
            }
            else
            {
                // 회원가입 성공시 처리하는 로직 작성란
                Debug.Log("Register Success");
            }
        }
    }
}
