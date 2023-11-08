using UnityEngine;

public class CharacterMovement : MonoBehaviour 
{
    public float moveSpeed = 5f; // 이동 속도 설정    
    public float runMultiplier = 2f; // 달리기 배율
    public InventoryUI inventoryUI; // 인벤토리

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

    public void SetAttacking(bool isAttacking)
    {
        characterAnimation.SetAttackAnimation(isAttacking);
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
                inventoryUI.AcquireItem(itemPickup.item);
                // 아이템 오브젝트 삭제
                Destroy(collision.gameObject);
            }
        }
    }




    void OnCollisionEnter2D(Collision2D collision)
    {
        if (!MiniGameManager.instance.isLive)
            return;


        MiniGameManager.instance.health -= collision.gameObject.GetComponent<FatalController>().monsterATK;
        Debug.Log("체력 감소 " + MiniGameManager.instance.health);



        if (MiniGameManager.instance.health < 0)
        {
            for (int index = 9; index < transform.childCount; index++)
            {
                transform.GetChild(index).gameObject.SetActive(false);
            }


        }

    }
}
