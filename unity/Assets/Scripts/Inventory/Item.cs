using UnityEngine;

[System.Serializable]
public class Item
{
    public enum ItemType { Equip , Food }
    public int id;
    public string itemName;
    public Sprite itemIcon;
    public ItemType itemType;
    public int itemCount;
}
