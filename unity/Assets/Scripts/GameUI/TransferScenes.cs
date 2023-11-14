using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class TransferScences : MonoBehaviour
{
    public string transferMapName; // 이동할 맵의 이름

    private CharacterMovement thePlayer;

    void Start()
    {
        thePlayer = FindAnyObjectByType<CharacterMovement>(); // 모든 객체 참조
        // GetComponent 단일 객체
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.gameObject.name == "Character")
        {
            thePlayer.currentMapName = transferMapName;
            SceneManager.LoadScene(transferMapName);
        }
    }

    public void TranferScene()
    {
        SceneManager.LoadScene(transferMapName);
    }
}
