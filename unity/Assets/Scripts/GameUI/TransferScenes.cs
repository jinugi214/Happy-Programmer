using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class TransferScences : MonoBehaviour
{
    public string transferMapName; // 이동할 맵의 이름

    public void TranferScene()
    {
        SceneManager.LoadScene(transferMapName);
    }
}
