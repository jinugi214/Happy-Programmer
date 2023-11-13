using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    public GameObject talkPanel;
    public TextMeshProUGUI talkText;
    public bool isAction;

    // Update is called once per frame
    public void Action()
    {
        if (isAction)
        {
            isAction = false;
        }
        else
        {
            isAction = true;
        }

        talkPanel.SetActive(isAction);
    }
}
