using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    public GameObject talkPanel;
    public Text talkText;
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
