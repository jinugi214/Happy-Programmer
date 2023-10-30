using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BackgroundManager : MonoBehaviour
{
    public Sprite[] imageSprites;
    public float[] timeOfDay;

    private Image imageComponent;

    // Start is called before the first frame update
    void Start()
    {
        imageComponent = GetComponent<Image>();
    }

    // Update is called once per frame
    void Update()
    {
        float currentTime = System.DateTime.Now.Hour;

        for (int i = 0; i < timeOfDay.Length; i++)
        {
            if (currentTime < timeOfDay[i])
            {
                imageComponent.sprite = imageSprites[i - 1];
                break;
            }
        }
    }
}
