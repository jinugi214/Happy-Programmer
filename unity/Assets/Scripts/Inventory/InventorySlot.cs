using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class InventorySlot : MonoBehaviour
{
    public Image icon;
    public Text count;
    public GameObject selected;

    public void AddItem(Item _item)
    {
        icon.sprite = _item.itemIcon;
        count.text = _item.itemCount.ToString();
    }

    public void RemoveItem()
    {
        count.text = "";
        icon.sprite = null;
    }

     public void AddRandomItem()
    {
        // 아이템 데이터베이스에서 아이템을 가져옵니다.
        // 이 예제에서는 임의로 인덱스가 0인 아이템을 가져오는 것으로 가정합니다.
        Item randomItem = ItemDatabase.instance.GetItem(0);

        // 가져온 아이템을 슬롯에 추가합니다.
        AddItem(randomItem);
    }
}
