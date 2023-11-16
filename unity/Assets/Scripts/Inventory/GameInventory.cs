using UnityEngine;

public class GameInventory : MonoBehaviour
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
        }
    }

    public void AcquireItem(Item _item, int _count = 1)
{
    Debug.Log("Item type: " + _item.itemType);  // 아이템 타입 로그

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
                Debug.Log("Added item to slot " + i);  // 아이템 추가 로그
                return;
            }
        }
    }
    // ...
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