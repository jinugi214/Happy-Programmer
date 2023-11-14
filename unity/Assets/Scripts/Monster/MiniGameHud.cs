using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class MiniGameHud : MonoBehaviour
{
    public enum InfoType{EXP, kill, Level, Health, Time, Mp, EXPText }

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
            case InfoType.EXPText:
                myText.text = string.Format("È¹µæÇÑ °æÇèÄ¡ : {0} ", MiniGameManager.instance.exp);
                break;
            case InfoType.Level:
                myText.text = string.Format("{0} ·¹º§ÀÌ »ó½ÂÇß½À´Ï´Ù.", MiniGameManager.instance.level);
                break;
            case InfoType.kill:
                break;
            case InfoType.Time:
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
