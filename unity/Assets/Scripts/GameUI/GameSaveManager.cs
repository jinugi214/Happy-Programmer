using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameSaveManager : MonoBehaviour
{
    public static GameSaveManager instance;

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

    private void Start()
    {
        if (gameSave != null)
        {
            DontDestroyOnLoad(gameSave);
        }
    }

    private void Update()
    {
        if (Input.GetKey(KeyCode.LeftControl) && Input.GetKeyDown(KeyCode.S))
        {
            if (gameSave != null)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        }
        else if (Input.GetKeyDown(KeyCode.S) && Input.GetKey(KeyCode.LeftControl))
        {
            if (gameSave != null)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        }
    }
}
