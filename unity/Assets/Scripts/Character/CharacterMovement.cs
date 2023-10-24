using UnityEngine;

public class CharacterMovement : MonoBehaviour 
{
    public float moveSpeed = 5f; // 이동 속도 설정	
    public float runMultiplier = 2f; // 달리기 배율

    private Rigidbody2D rb;
    private Vector2 movement;
    
    private CharacterAnimation characterAnimation;

    private void Start() 
    {	
        rb = GetComponent<Rigidbody2D>();
        characterAnimation=GetComponent<CharacterAnimation>();
    }

    public void ProcessInput(float moveX, float moveY)
    {
        if (moveX != 0 && moveY != 0) 
        {
            if (Mathf.Abs(moveX) > Mathf.Abs(moveY))
                moveY=0f;
            else 
                moveX=0f;
        }

        movement = new Vector2(moveX,moveY).normalized * moveSpeed;
    }	

    public void SetRunning(bool isRunning)
    {   
        if(isRunning)
            rb.velocity=movement * runMultiplier;       
        else  
            rb.velocity=movement;
        
        characterAnimation.SetMoveAnimation(movement, isRunning);   
    }  
}

