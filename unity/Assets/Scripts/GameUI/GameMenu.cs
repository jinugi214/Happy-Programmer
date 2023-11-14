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
    public GameObject checkWindow;
    public TextMeshProUGUI windowText;
    public CharacterSaveLoadManager characterSaveLoadManager;

    public void OnNewStartClicked()
    {
        SceneManager.LoadScene("CharacterSetting");
    }

    public void OnLoadStartClicked()
    {
        StartCoroutine(characterSaveLoadManager.LoadCharacterData());
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
