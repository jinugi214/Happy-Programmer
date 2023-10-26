using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class BackButton : MonoBehaviour
{
    public static string previousScene;

    public void SavePreviousSceneName()
    {
        // 시작 시 현재 씬을 저장합니다.
        previousScene = SceneManager.GetActiveScene().name;
    }

    public void OnBackButtonClick()
    {
        // 이전 씬으로 돌아갑니다.
        SceneManager.LoadScene(previousScene);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
