using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameMenu : MonoBehaviour
{
    public Button newStart;
    public Button loadStart;
    public Button exitGame;
    public CharacterLoadManager characterLoadManager;

    public void OnNewStartClicked()
    {
        SceneManager.LoadScene("CharacterSetting");
    }

    public void OnLoadStartClicked()
    {
        StartCoroutine(characterLoadManager.LoadCharacterData());
    }
    
    public void OnExitGameClicked()
    {
        if (DataManager.instance != null)
        {
            Destroy(DataManager.instance.gameObject);
            Destroy(UIManager.instance.gameObject);
        }

        SceneManager.LoadScene("GameLogin");
    }

}
