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
        CharacterSaveLoadManager characterSaveLoadManager = new CharacterSaveLoadManager();
        StartCoroutine(characterSaveLoadManager.LoadCharacterData());
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
