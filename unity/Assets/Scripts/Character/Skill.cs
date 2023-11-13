using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Skill : MonoBehaviour
{
    // 스킬의 이동 속도
    public float speed; 
    
    public Vector2 direction; // 스킬의 움직일 방향

     void Update()
     {
         transform.Translate(direction * speed * Time.deltaTime);
     }

private void MoveForward()
{
    transform.Translate(transform.right * speed * Time.deltaTime);
}

}
