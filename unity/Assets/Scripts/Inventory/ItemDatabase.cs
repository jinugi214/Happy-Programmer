using System.Collections.Generic;
using UnityEngine;

public class ItemDatabase : MonoBehaviour
{
    public static ItemDatabase instance;
    public List<Item> itemDB = new List<Item>();

    private void Awake()
    {
        instance = this;
    }

    public Item GetItem(int index)
    {
        // 인덱스 유효성 검사
        if (index < 0 || index >= itemDB.Count)
        {
            Debug.LogError("Invalid item index!");
            return null;
        }

        return itemDB[index];
    }
}
