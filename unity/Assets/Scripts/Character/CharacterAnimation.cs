using UnityEngine;

public class CharacterAnimation : MonoBehaviour
{
    private Animator animator;
    private SpriteRenderer spriteRenderer;
    private Vector2 lastmovement;
    private void Start()
    {
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
    }

    public void SetMoveAnimation(Vector2 movement, bool isRunning)
	{
        animator.SetFloat("DirX", movement.x);
        animator.SetFloat("DirY", movement.y);

        // 오른쪽은 왼쪽의 flipX을 true 한 것
        spriteRenderer.flipX = movement.x > 0 || lastmovement.x > 0;
        bool flipSortingLayer = movement.x > 0 || lastmovement.x > 0;
        SpriteRenderer[] otherSpriteRenderers = GetComponentsInChildren<SpriteRenderer>();

        foreach (SpriteRenderer sr in otherSpriteRenderers)
        {
            if (sr != spriteRenderer) // 현재 스프라이트 렌더러와 동일한 것은 제외합니다.
                sr.flipX = flipSortingLayer;
        }

        if (movement.magnitude > 0) 
        {
            lastmovement = new Vector2(movement.x ,movement.y);
            if (isRunning) // 뛸 때 
            {
                animator.SetBool("Run", true);
                animator.SetBool("Walk", false);
                animator.SetBool("Stand", false);
            }
            else // 걸을 때
            {   
                animator.SetBool("Stand", false);
                animator.SetBool("Run", false);
                animator.SetBool("Walk", true);
            }

        }
        else // 서있을 때
        {   
            animator.SetFloat("DirX", lastmovement.x);
            animator.SetFloat("DirY", lastmovement.y);
            animator.SetBool("Run", false);
            animator.SetBool("Walk", false);
            animator.SetBool("Stand", true);
        }
    }
}


