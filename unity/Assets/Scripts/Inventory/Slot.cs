using UnityEngine;
using UnityEngine.UI;

public class Slot : MonoBehaviour
{
    public Item item; // 획득한 아이템
    public int itemCount; // 획득한 아이템의 개수
    public Image itemImage;  // 아이템의 이미지
    public CharacterAppear characterAppear;
    public AudioSource audioSource;
    public AudioClip inventorySound;

    [SerializeField]
    private Text text_Count;
    [SerializeField]

    void Start()
    {
        characterAppear = FindObjectOfType<CharacterAppear>();
    }

    void Awake()
    {
        characterAppear = FindObjectOfType<CharacterAppear>();
        audioSource = GetComponent<AudioSource>();
        if (audioSource == null)
        {
            audioSource = gameObject.AddComponent<AudioSource>();
        }
        audioSource.volume = 0.5f;
        audioSource.clip = inventorySound;
    }

    // 아이템 이미지의 투명도 조절
    private void SetColor(float _alpha)
    {
        Color color = itemImage.color;
        color.a = _alpha;
        itemImage.color = color;
    }

    // 인벤토리에 새로운 아이템 슬롯 추가
    public void AddItem(Item _item, int _count = 1)
    {
        item = _item;
        itemCount = _count;
        itemImage.sprite = item.itemImage;

        if (item.itemType != Item.ItemType.Equipment)
        {
            text_Count.text = itemCount.ToString();
        }
        else
        {
            text_Count.text = "";
        }

        SetColor(1);
    }

    // 해당 슬롯의 아이템 갯수 업데이트
    public void SetSlotCount(int _count)
    {
        itemCount += _count;
        text_Count.text = itemCount.ToString();

        if (itemCount <= 0)
            ClearSlot();
    }

    // 해당 슬롯 하나 삭제
    private void ClearSlot()
    {
        item = null;
        itemCount = 0;
        itemImage.sprite = null;
        SetColor(0);

        text_Count.text = "0";
    }

    public void UpdateSlot()
    {
        if (item != null)
        {
            // 아이템이 있을 경우, 이미지/개수 표시
            itemImage.sprite = item.itemImage;
            SetColor(1);

            if (item.itemType != Item.ItemType.Equipment)
            {
                text_Count.text = itemCount.ToString();
            }
            else
            {
                text_Count.text = "0";
            }
        }
        else
        {
            // 아이템이 없을 경우, 이미지/개수 숨기기
            itemImage.sprite = null;
            SetColor(0);
            text_Count.text = "";
        }

    }


    public void UseItem()
    {
        if (item != null)
        {
            if (item.itemType == Item.ItemType.Equipment)
            {
                // 장비 장착 로직
                switch (item.itemName)
                {
                    case "문방구 키보드":
                        characterAppear.EquipKeyboard1();
                        break;
                    case "기계식 키보드":
                        characterAppear.EquipKeyboard2();
                        break;
                    case "게이밍 키보드":
                        characterAppear.EquipKeyboard3();
                        break;

                    case "문방구 마우스":
                        characterAppear.EquipMouse1();
                        break;
                    case "게이밍 마우스":
                        characterAppear.EquipMouse2();
                        break;
                    case "한정판 마우스":
                        characterAppear.EquipMouse3();
                        break;

                    case "3G 피처폰":
                        characterAppear.EquipPhone1();
                        break;
                    case "보급형 스마트폰":
                        characterAppear.EquipPhone2();
                        break;
                    case "플래그쉽 스마트폰":
                        characterAppear.EquipPhone3();
                        break;

                    case "유선 이어폰":
                        characterAppear.EquipHelmet1();
                        break;
                    case "무선 이어폰":
                        characterAppear.EquipHelmet2();
                        break;
                    case "헤드폰":
                        characterAppear.EquipHelmet3();
                        break;

                    default:
                        Debug.Log("미등록 장비");
                        break;
                }
                audioSource.Play();
            }

            else if (item.itemType == Item.ItemType.Food)
            {
                // 음식 사용 로직
                if (itemCount > 0)
                {
                    Debug.Log("음식 냠냠");
                    audioSource.Play();
                    itemCount--;
                    if (itemCount <= 0)
                    {
                        // 아이템 개수가 0이면 아이템을 제거
                        item = null;
                    }
                    UpdateSlot();
                }
            }
        }
    }

}
