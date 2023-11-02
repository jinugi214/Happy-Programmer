using System.Collections;
using UnityEngine;
using UnityEngine.Networking;

public class NetworkManager : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(UnityWebRequestGet());
        StartCoroutine(UnityWebRequestPost());
    }

    IEnumerator UnityWebRequestGet()
    {
        string id = "wlsdnr214";
        string name = "정진욱";
        
        string url = "http://k9d210.p.ssafy.io:8081";
        string urlWithParams = $"http://k9d210.p.ssafy.io:8081/{id}?name={name}";


        // url에 Get 요청 보내기
        UnityWebRequest www = UnityWebRequest.Get(url);

        // 응답이 올 때까지 기다리기
        yield return www.SendWebRequest();

        if (www.error == null)
        {
            // 응답에 에러가 없다면 응답내용 텍스트 출력해보기
            Debug.Log(www.downloadHandler.text);
        }
        else
        {
            Debug.Log("ERROR");
        }
    }

    IEnumerator UnityWebRequestPost()
    {
        string url = "http://k9d210.p.ssafy.io:8081";
        WWWForm form = new WWWForm();
        string id = "아이디";
        string pw = "비밀번호";
        form.AddField("Username", id);
        form.AddField("Password", pw);
        // 보낼 주소와 데이터 입력
        UnityWebRequest www = UnityWebRequest.Post(url, form);

        yield return www.SendWebRequest();

        if(www.error == null)
        {
            // 데이터 출력
            Debug.Log(www.downloadHandler.text);
        }
        else
        {
            Debug.Log("error");
        }
    }
}
