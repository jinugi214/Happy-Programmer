using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class TransferScences : MonoBehaviour
{
    public string transferMapName; // 이동할 맵의 이름

    private CharacterMovement thePlayer;
    private FadeManager theFade;

    void Start()
    {
        thePlayer = FindObjectOfType<CharacterMovement>(); // 모든 객체 참조
        // GetComponent 단일 객체
        theFade = FindObjectOfType<FadeManager>();
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.gameObject.name == "Character")
        {
            StartCoroutine(TransferCoroutine());
        }
    }

    IEnumerator TransferCoroutine()
    {
        theFade.FadeOut();

        yield return new WaitForSeconds(1f);
        thePlayer.currentMapName = transferMapName;
        SceneManager.LoadScene(transferMapName);
        theFade.FadeIn();
    }

    public void TranferScene()
    {
        SceneManager.LoadScene(transferMapName);
    }
}
