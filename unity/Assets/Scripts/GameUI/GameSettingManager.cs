using UnityEngine;

public class GameSettingManager : MonoBehaviour
{
    public void GameExit()
    {
        if (UnityEditor.EditorApplication.isPlaying)
        {
            UnityEditor.EditorApplication.isPlaying = false;
        }
        else
        {
            Application.Quit();
        }
        
    }
}
