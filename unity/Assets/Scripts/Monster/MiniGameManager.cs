using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MiniGameManager : MonoBehaviour
{
    public static MiniGameManager instance;
    public PoolManager pool;
    public CharacterInput player;

    void Awake()
    {
        instance = this;
    }

}
