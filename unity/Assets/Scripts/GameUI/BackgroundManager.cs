using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BackgroundManager : MonoBehaviour
{
    public Sprite[] imageSprites;

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

        if (currentTime < 6)
        {
            imageComponent.sprite = imageSprites[2];
        }
        else if (currentTime < 16)
        {
            imageComponent.sprite = imageSprites[0];
        }
        else if (currentTime < 20)
        {
            imageComponent.sprite = imageSprites[1];
        }
        else
        {
            imageComponent.sprite = imageSprites[2];
        }
    }
}
