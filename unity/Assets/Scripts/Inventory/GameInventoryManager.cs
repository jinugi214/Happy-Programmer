using UnityEngine;

public class GameInventoryManager : MonoBehaviour
{

    [SerializeField]
    public GameObject inventoryBase;
    // InventoryUI ������Ʈ ���� ���

    [SerializeField]
    private GameObject SlotContent;
    // Slot���� �θ��� Grid Setting 

    private Slot[] slots;
    // ���Ե� �迭


     private void Awake()
    {
        slots = SlotContent.GetComponentsInChildren<Slot>();
        for (int i = 0; i < slots.Length; i++)
        {
            slots[i].item = null;  // ������ item �ʵ带 null�� �ʱ�ȭ
            slots[i].slotId = i;  // slotId 설정
        }
    }


    public void AcquireItem(Item _item, int _count = 1)
{
    Debug.Log("_item: " + (_item == null ? "null" : "not null"));
    
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
                        return;  // ���� ������ ������ �߰� X
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
            // 'All' ī�װ���
            if (itemType == null)
            {
                slot.gameObject.SetActive(true);
            }
            else
            {
                // ������ ī�װ����� ���ϸ� ����, �ƴϸ� ����
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
