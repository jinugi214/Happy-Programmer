using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShortcutKey : MonoBehaviour
{

    public GameObject gameSettingPanel; // 프리팹으로 만든 게임 설정 패널
    public GameObject gameSave; // 프랩으로 만든 게임 저장 패널

    // Update is called once per frame
    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (gameSettingPanel != null)
            {
                gameSettingPanel.SetActive(!gameSettingPanel.activeSelf);
            }
        }

        if (Input.GetKey(KeyCode.LeftControl) && Input.GetKeyDown(KeyCode.S))
        {
            if (gameSave != null)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        } else if (Input.GetKeyDown(KeyCode.S) && Input.GetKey(KeyCode.LeftControl))
        {
            if (gameSave != null)
            {
                gameSave.SetActive(!gameSave.activeSelf);
            }
        }
    }
}
