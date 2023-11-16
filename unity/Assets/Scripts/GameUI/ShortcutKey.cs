using UnityEngine;

public class ShortcutKey : MonoBehaviour
{
    public static ShortcutKey instance;

    #region Singleton
    private void Awake()
    {
        if (instance == null)
        {
            DontDestroyOnLoad(this.gameObject);
            instance = this;
        }
        else
        {
            Destroy(this.gameObject);
        }
    }
    #endregion Singleton

    public GameObject gameSave;
    public GameObject gameSetting;

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (gameSetting != null && !gameSave.activeSelf)
            {
                gameSetting.SetActive(!gameSetting.activeSelf);
            }
        }

        if (Input.GetKey(KeyCode.LeftControl) && Input.GetKeyDown(KeyCode.S))
        {
            if (gameSave != null && !gameSetting.activeSelf)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        }
        else if (Input.GetKeyDown(KeyCode.S) && Input.GetKey(KeyCode.LeftControl))
        {
            if (gameSave != null && !gameSetting.activeSelf)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        }
    }
}
