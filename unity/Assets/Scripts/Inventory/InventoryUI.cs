using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InventoryUI : MonoBehaviour
{
    public GameObject inventoryPanel;
    // InventoryUI 오프젝트 담을 요소

    bool activeInventory = false;

    public List<InventorySlot> slots;
    // slots : slot을 담을 요소
    public Transform slotHolder;
    // slotHolder : content 넣을 것
    public GameObject slotPrefab;


    private void Start()
    {
        slots = new List<InventorySlot>(slotHolder.GetComponentsInChildren<InventorySlot>());
        // GetComponentsInChildren : 자식 오브젝트 가져옴
        inventoryPanel.SetActive(activeInventory);
    }

    private void Update()
    {
        if(Input.GetKeyDown(KeyCode.I))
        {
            activeInventory = !activeInventory;
            inventoryPanel.SetActive(activeInventory);
        }
    }

    public void CreateSlot(Item item)
{
    GameObject slot = Instantiate(slotPrefab, slotHolder);
    Debug.Log(slot);
    InventorySlot inventorySlot = slot.GetComponent<InventorySlot>();
    inventorySlot.AddItem(item);
    slots.Add(inventorySlot);
}

        // 인벤토리를 업데이트하는 메서드
    public void UpdateInventory(List<Item> items)
{
    for (int i = 0; i < items.Count; i++)
    {
        if (i < slots.Count)
        {
            slots[i].AddItem(items[i]);
        }
        else
        {
            Debug.Log("Creating new slot for item: " + items[i].itemName);  // 이 부분 추가
            CreateSlot(items[i]);
        }
    }
    while (slots.Count > items.Count)
    {
        InventorySlot slotToRemove = slots[slots.Count - 1];
        slots.Remove(slotToRemove);
        Destroy(slotToRemove.gameObject);
    }
}

}
