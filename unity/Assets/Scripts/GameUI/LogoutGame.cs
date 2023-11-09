using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class  LogoutGame : MonoBehaviour
{
    public void OnClickExitButton()
    {
        if (DataManager.instance != null)
        {
            Destroy(DataManager.instance.gameObject);
        }

        SceneManager.LoadScene("GameLogin");
    }
}
