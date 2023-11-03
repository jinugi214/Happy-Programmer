using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShortcutKey : MonoBehaviour
{

    public GameObject gameSettingPanel; // 프리팹으로 만든 게임 설정 패널

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
    }
}
