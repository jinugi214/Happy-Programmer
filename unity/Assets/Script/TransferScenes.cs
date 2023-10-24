using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TransferScences : MonoBehaviour
{
    public void LoginToSignup()
    {
        SceneManager.LoadScene("GameSignup");
    }

    public void SignupCancle()
    {
        SceneManager.LoadScene("GameLogin");
    }
}
