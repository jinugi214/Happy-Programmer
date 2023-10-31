using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// 캐릭터의 인벤토리를 표현하는 클래스
public class CharacterInventory : MonoBehaviour
{
    public List<Item> items = new List<Item>();
    public InventoryUI inventoryUI;

    // 아이템을 획득하는 메서드
    public void AddItem(Item item)
    {
        items.Add(item);
        Debug.Log("Adding item: " + item.itemName);
        inventoryUI.UpdateInventory(items);
    }

    // 아이템을 사용하는 메서드
    public void UseItem(Item item)
    {
        items.Remove(item);
        inventoryUI.UpdateInventory(items);
    }

private void Update()
{
    if (Input.GetKeyDown(KeyCode.U))
    {
        Item randomItem = ItemDatabase.instance.GetItem(0);
        if (randomItem == null)
        {
            Debug.Log("Failed to get item from database");  // 이 부분 추가
            return;
        }
        Debug.Log("Getting item: " + randomItem.itemName);
        AddItem(randomItem);
    }
}


}