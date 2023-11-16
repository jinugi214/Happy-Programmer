using UnityEngine;
using UnityEngine.SceneManagement;

public class CharacterMovement : MonoBehaviour
{
    // 싱글톤
    public static CharacterMovement Instance { get; private set; }

    public string currentMapName;
    public float moveSpeed = 5f; // 이동 속도 설정    
    public float runMultiplier = 2f; // 달리기 배율

    private Rigidbody2D rb;
    public Vector2 movement;
    public Vector2 lastDirection; // 캐릭터가 마지막으로 움직인 방향

    private CharacterAnimation characterAnimation;

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);

        }
        else
        {
            Destroy(gameObject);
            return;
        }
        //currentMapName = DataManager.instance.CharacterData.savepoint;
    }

    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        characterAnimation = GetComponent<CharacterAnimation>();
    }

    public void ProcessInput(float moveX, float moveY)
    {
        //인벤토리가 열려있는 경우 움직임 중단
        // if (uimanager.instance.gameinventory != null && uimanager.instance.gameinventory.activeself)
        // {
        //    movement = vector2.zero;
        //    return;
        // }

        // 대화 중일 때는 움직임 중단
        // if (dialoguemanager.instance != null && dialoguemanager.instance.talking)
        // {
        //    movement = vector2.zero;
        //    return;
        // }

        if (moveX != 0 && moveY != 0)
        {
            if (Mathf.Abs(moveX) > Mathf.Abs(moveY))
                moveY = 0f;
            else
                moveX = 0f;
        }

        movement = new Vector2(moveX, moveY).normalized * moveSpeed;
        if (movement != Vector2.zero) // 캐릭터가 움직이는 경우에만 lastDirection을 업데이트
        {
            lastDirection = movement;
        }
    }

    public void SetRunning(bool isRunning)
    {
        if (isRunning)
            rb.velocity = movement * runMultiplier;
        else
            rb.velocity = movement;

        characterAnimation.SetMoveAnimation(movement, isRunning);
    }

    public void SetAttacking(bool isAttacking)
    {
        characterAnimation.SetAttacking(isAttacking);
    }

    public void OnTriggerEnter2D(Collider2D collision)
    {
        // 아이템과 충돌한 경우
        if (collision.gameObject.tag == "Item")
        {
            // 아이템을 인벤토리에 추가
            ItemPickup itemPickup = collision.gameObject.GetComponent<ItemPickup>();


            if (itemPickup != null)
            {
                GameInventoryManager gameInventoryManager = UIManager.instance.gameInventory.GetComponent<GameInventoryManager>();
                gameInventoryManager.AcquireItem(itemPickup.item);

                // 아이템 오브젝트 삭제
                Destroy(collision.gameObject);
            }
        }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        // 캐릭터의 속도를 0으로 만듭니다.
        rb.velocity = Vector2.zero;
    }

}
