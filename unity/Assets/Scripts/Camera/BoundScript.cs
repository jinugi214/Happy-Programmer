using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoundScript : MonoBehaviour
{
    public static BoundScript instance;
    public GameObject bound;

    void Awake()
    {
        instance = this;
    }
}
