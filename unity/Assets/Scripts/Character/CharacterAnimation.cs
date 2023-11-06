using UnityEngine;

public class CharacterAnimation : MonoBehaviour
{
    private Animator animator;
    private SpriteRenderer spriteRenderer;
    private Vector2 lastmovement;
    private CharacterAppear characterAppear;

    private void Start()
    {
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        characterAppear = GetComponent<CharacterAppear>();
    }

    public void SetMoveAnimation(Vector2 movement, bool isRunning)
    {
        animator.SetFloat("DirX", movement.x);
        animator.SetFloat("DirY", movement.y);

        if (movement.magnitude > 0)
        {
            lastmovement = new Vector2(movement.x, movement.y);
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

    public void SetAttacking(bool isAttacking)
    {
        int weaponType = characterAppear.GetWeaponType();
        SetAttackAnimation(isAttacking, weaponType);
    }


    public void SetAttackAnimation(bool isAttacking, int weaponType)
    {
        if (isAttacking)
        {
            animator.SetBool("Attack", true);
            animator.SetInteger("Weapon", weaponType);
        }
        else
        {
            animator.SetBool("Attack", false);
        }
    }

}


