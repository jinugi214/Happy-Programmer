using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InventoryUI : MonoBehaviour
{
    bool activeInventory = false;

    [SerializeField]
    public GameObject inventoryBase;
    // InventoryUI 오프젝트 담을 요소

    [SerializeField]
    private GameObject SlotContent;
    // Slot들의 부모인 Grid Setting 

    private Slot[] slots;
    // 슬롯들 배열

    public bool IsInventoryOpen()
    {
        return activeInventory;
    }
    // 인벤토리 오픈 여부

    private void Start()
    {
        inventoryBase.SetActive(activeInventory);
        slots = SlotContent.GetComponentsInChildren<Slot>();
        for (int i = 0; i < slots.Length; i++)
        {
            slots[i].item = null;  // 슬롯의 item 필드를 null로 초기화
        }
    }

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.I))
        {
            activeInventory = !activeInventory;
            inventoryBase.SetActive(activeInventory);
        }
    }


    public void AcquireItem(Item _item, int _count = 1)
    {
        if (_item.itemType == Item.ItemType.Food)
        {
            for (int i = 0; i < slots.Length; i++)
            {
                if (slots[i].item != null)
                {
                    if (slots[i].item.itemName == _item.itemName)
                    {
                        slots[i].SetSlotCount(_count);
                        return;
                    }
                }
            }

            for (int i = 0; i < slots.Length; i++)
            {
                if (slots[i].item == null)
                {
                    slots[i].AddItem(_item, _count);
                    return;
                }
            }
        }
        else if (_item.itemType == Item.ItemType.Equipment)
        {
            for (int i = 0; i < slots.Length; i++)
            {
                if (slots[i].item != null)
                {
                    if (slots[i].item.itemName == _item.itemName)
                    {
                        return;  // 같은 아이템 있으면 추가 X
                    }
                }
            }

            for (int i = 0; i < slots.Length; i++)
            {
                if (slots[i].item == null)
                {
                    slots[i].AddItem(_item, _count);
                    return;
                }
            }
        }
    }

    public void FilterInventory(Item.ItemType? itemType = null)
    {
        foreach (Slot slot in slots)
        {
            // 'All' 카테고리
            if (itemType == null)
            {
                slot.gameObject.SetActive(true);
            }
            else
            {
                // 선택한 카테고리에 속하면 보임, 아니면 숨김
                if (slot.item != null)
                {
                    slot.gameObject.SetActive(slot.item.itemType == itemType);
                }
            }
        }
    }

    public void FilterAll()
    {
        FilterInventory(null);
    }

    public void FilterEquipment()
    {
        FilterInventory(Item.ItemType.Equipment);
    }

    public void FilterFood()
    {
        FilterInventory(Item.ItemType.Food);
    }


}