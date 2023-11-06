using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{

    // Raycast : 변수 선언
    private BoxCollider2D boxCollider;
    public LayerMask layerMask;
    // 충돌할 때 어떤 레이어와 충돌했는지 판단. 통과가 불가능한 레이어 설정

    // 캐릭터 움직임을 담당할 변수 선언
    public float speed;
    public Vector3 vector;

    public float runSpeed;
    private float applyRunSpeed; 
    private bool applyRunFlag = false;

    public int walkCount;
    private int currentWalkCount;

    private bool canMove = true;
    private bool notMove = false;

    private Animator animator;

    private bool attacking = false;
    public float attackDelay;
    private float currentAttackDelay;

    // Start is called before the first frame update
    void Start()
    {
        // Raycast
        boxCollider = GetComponent<BoxCollider2D>();

        animator = GetComponent<Animator>();
    }

    IEnumerator MoveCoroutine()
    {
        while ((Input.GetAxisRaw("Vertical") != 0 || Input.GetAxisRaw("Horizontal") != 0) && !notMove && !attacking)
        {
            if (Input.GetKey(KeyCode.LeftShift)) 
            {
                            applyRunSpeed = runSpeed;
                            applyRunFlag = true;
            }
            else
            {
                applyRunSpeed = 0;
                applyRunFlag = false;
            }

            // vector 에 x, y, z 값 저장
            vector.Set(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical"), 0);

            if (vector.x != 0)
                vector.y = 0;

            animator.SetFloat("DirX", vector.x);
            animator.SetFloat("DirY", vector.y);

            // Raycast : SetFloat - SetBool 사이에 이동 불가 지역을 설정하는 코드 작성
            RaycastHit2D hit;
            // ex) 시작지점, 끝지점이 있는데
            // 무사히 도착하면 hit = Null, 도착하지 못하고 방해물에 충돌했을 경우 hit = 방해물이 return됨.

            // 시작점. 캐릭터의 현재 위치값
            Vector2 start = transform.position;
            // 도착점. 캐릭터가 이동하고자 하는 위치값
            Vector2 end = start + new Vector2(vector.x * speed * walkCount, vector.y * speed * walkCount);

            // 잠깐 player의 boxCollider를 꺼준 후에, 다시 켜준다.
            boxCollider.enabled = false;
            hit = Physics2D.Linecast(start, end, layerMask);
            boxCollider.enabled = true;

            // hit에 반환되는 방해물이 있을 경우, 명령어 실행 X
            if (hit.transform != null)
                break;

            animator.SetBool("Walking", true);
            // standing tree -> walking tree 상태 전이 일어남

            while(currentWalkCount < walkCount)
            {
                if (vector.x != 0)
                {
                    transform.Translate(vector.x * (speed + applyRunSpeed), 0, 0);
                }
                else if (vector.y != 0)
                {
                    transform.Translate(0, vector.y * (speed + applyRunSpeed), 0);
                }
                if (applyRunFlag)
                {
                    currentWalkCount++;
                }
                currentWalkCount++;
                yield return new WaitForSeconds(0.01f);
                // 0.01초동안 대기
            }
            currentWalkCount = 0;
        }
        animator.SetBool("Walking", false);
        canMove = true;
    }

        

    // Update is called once per frame
    void Update()
    {    
        if (canMove && !notMove && !attacking) {
            // Input.GetAxisRaw("Horizontal") : 우 방향키가 눌리면 1 리턴, 좌 방향키가 눌리면 -1 리턴
            // Input.GetAxisRaw("Vertical") : 상 방향키가 눌리면 1 리턴, 하 방향키가 눌리면 -1 리턴
            if(Input.GetAxisRaw("Horizontal") != 0 || Input.GetAxisRaw("Vertical") != 0)
            // 상하좌우 키가 눌러질 경우
            {
                canMove = false;
                StartCoroutine(MoveCoroutine());
            }
        }

        if (!notMove && !attacking) {
            if(Input.GetKeyDown(KeyCode.Space)) {
                currentAttackDelay = attackDelay;
                attacking = true;
                animator.SetBool("Attacking", true);
            }
        }

        if (attacking)
        {
            currentAttackDelay -= Time.deltaTime;
            if(currentAttackDelay <= 0)
            {
                animator.SetBool("Attacking", false);
                attacking = false;
            }
        }

        }
    }
