using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ShortcutKey : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            BackButton backButton = new BackButton();
            backButton.SavePreviousSceneName();
            SceneManager.LoadScene("GameSetting");
        }

        if (Input.GetKeyDown(KeyCode.I)) 
        { 
            BackButton backbutton = new BackButton();
            backbutton.SavePreviousSceneName();
            SceneManager.LoadScene("GameMarket");
        }
    }
}
