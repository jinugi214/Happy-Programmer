// CharacterInput.cs
using UnityEngine;

public class CharacterInput : MonoBehaviour
{
    private CharacterMovement characterMovement;

    private void Start()
    {
        characterMovement = GetComponent<CharacterMovement>();
    }

    private void Update()
    {
        // 입력 받기
        float moveX = Input.GetAxisRaw("Horizontal");
        float moveY = Input.GetAxisRaw("Vertical");

        characterMovement.ProcessInput(moveX, moveY);

        bool isRunning = Input.GetKey(KeyCode.LeftShift) || Input.GetKey(KeyCode.RightShift);
        characterMovement.SetRunning(isRunning);
    }
}

