using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class MiniGameHud : MonoBehaviour
{
    public enum InfoType{EXP, kill, Level, Health, Time, Mp }

    public InfoType type;

    TextMeshProUGUI myText;
    Slider mySlider;

    private void Awake()
    {
        myText = GetComponent<TextMeshProUGUI>();
        mySlider = GetComponent<Slider>();
    }

    void LateUpdate()
    {
        switch (type)
        {
            case InfoType.EXP:
                float curExp = MiniGameManager.instance.exp;
                float maxExp = MiniGameManager.instance.nextExp[MiniGameManager.instance.level];
                mySlider.value = curExp / maxExp;
                break;
            case InfoType.Level:
                break;
            case InfoType.kill:
                myText.text = string.Format("{0} 개의 버그를 처리했습니다.", MiniGameManager.instance.kill);
                break;
            case InfoType.Time:
                myText.text = string.Format(" 처리 시간 : {0}", MiniGameManager.instance.gameTime);
                break;
            case InfoType.Health:
                float curHealth = MiniGameManager.instance.health;
                float maxHealth = MiniGameManager.instance.maxHealth;
                mySlider.value = curHealth / maxHealth;
                break;
            case InfoType.Mp:
                float curMP = MiniGameManager.instance.mp;
                float maxMP = MiniGameManager.instance.maxMp;
                mySlider.value = curMP / maxMP;
                break;


        }
    }
}
