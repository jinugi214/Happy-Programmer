using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Tilemaps;

public class UpStairs : MonoBehaviour
{
    public Collider2D thisStair;
    public Collider2D otherLock;
    public Collider2D thisLock;
    public Collider2D otherStair;
    //public SpriteRenderer backStair;

    public Color blockedColor;

    private void Update()
    {
        
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        //backStair.color = blockedColor;
        otherLock.isTrigger = true;
        thisLock.isTrigger = false;
        thisStair.isTrigger = true;
        otherStair.isTrigger = false;
        
        
    }
}
