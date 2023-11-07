using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MiniGameHud : MonoBehaviour
{
    public enum InfoType{EXP, kill, Level, Health, Time}

    public InfoType type;

    Text myText;
    Slider mySlider;

    private void Awake()
    {
        myText = GetComponent<Text>();
        mySlider = GetComponent<Slider>();
    }

    void LateUpdate()
    {
        switch (type)
        {
            case InfoType.EXP:
                float curExp = MiniGameManager.instance.exp;
                break;
            case InfoType.Level:
                break;
            case InfoType.kill:
                break;
            case InfoType.Time:
                break;
            case InfoType.Health:
                break;


        }
    }
}
