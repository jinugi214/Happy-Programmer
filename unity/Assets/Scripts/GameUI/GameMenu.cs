using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameMenu : MonoBehaviour
{
    public Button newStart;
    public Button loadStart;
    public Button exitGame;

    public void OnNewStartClicked()
    {
        SceneManager.LoadScene("CharacterSetting");
    }

    public void OnLoadStartClicked()
    {
        SaveLoadManager saveLoadManager = new SaveLoadManager();
        StartCoroutine(saveLoadManager.LoadCharacterData());
    }

    public void OnExitGameClicked()
    {
        if (DataManager.instance != null)
        {
            Destroy(DataManager.instance.gameObject);
        }

        SceneManager.LoadScene("GameLogin");
    }

}
