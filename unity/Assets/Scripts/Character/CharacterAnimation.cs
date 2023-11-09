using UnityEngine;

public class CharacterAnimation : MonoBehaviour
{
    private Animator animator;
    private SpriteRenderer spriteRenderer;
    private Vector2 lastmovement;
    private CharacterAppear characterAppear;
    public AudioClip walkSound;  // 걷기 효과음
    public AudioClip runSound;  // 뛰기 효과음
    public AudioClip attackSound;  // 공격 효과음
    public AudioSource attackAudioSource;   // 공격 효과음
    public AudioSource walkAudioSource;  // 걷기 효과음
    public AudioSource runAudioSource;   // 뛰기 효과음

    private void Start()
    {
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        characterAppear = GetComponent<CharacterAppear>();
        // audioSource = GetComponent<AudioSource>();
        walkAudioSource = gameObject.AddComponent<AudioSource>();
        walkAudioSource.clip = walkSound;

        runAudioSource = gameObject.AddComponent<AudioSource>();
        runAudioSource.clip = runSound;

        attackAudioSource = gameObject.AddComponent<AudioSource>();
        attackAudioSource.clip = attackSound;
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
                if (!runAudioSource.isPlaying)
                {
                    runAudioSource.Play();
                }
            }
            else // 걸을 때
            {
                animator.SetBool("Stand", false);
                animator.SetBool("Run", false);
                animator.SetBool("Walk", true);
                if (!walkAudioSource.isPlaying)
                {
                    walkAudioSource.Play();
                }
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
        if (isAttacking && characterAppear.IsWeaponEquipped())
        {
            animator.SetBool("Attack", true);
            animator.SetInteger("Weapon", weaponType);
            // audioSource.PlayOneShot(attackSound); // 공격 애니메이션 시작시 효과음 재생
            attackAudioSource.volume = 0.2f;
        attackAudioSource.Play();
        }
        else
        {
            animator.SetBool("Attack", false);
        }
    }

}


