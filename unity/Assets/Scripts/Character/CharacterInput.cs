// CharacterInput.cs
using UnityEngine;

public class CharacterInput : MonoBehaviour
{
    private CharacterMovement characterMovement;
    private bool isAttacking;
    private bool isRunning;

    private void Start()
    {
        characterMovement = GetComponent<CharacterMovement>();
    }

    private void Update()
    {
        float moveX = Input.GetAxisRaw("Horizontal");
        float moveY = Input.GetAxisRaw("Vertical");

        characterMovement.ProcessInput(moveX, moveY);

        // Shift로 달리기
        isRunning = Input.GetKey(KeyCode.LeftShift) || Input.GetKey(KeyCode.RightShift);
        characterMovement.SetRunning(isRunning);

        if (Input.GetKeyDown(KeyCode.Z))
        {
            isAttacking = true; // Z 키를 누르면 공격 상태로 변경
        }
        else if (Input.GetKeyUp(KeyCode.Z))
        {
            isAttacking = false; // Z 키를 뗐을 때 공격 상태 해제
        }

        characterMovement.SetAttacking(isAttacking);
    }
}

