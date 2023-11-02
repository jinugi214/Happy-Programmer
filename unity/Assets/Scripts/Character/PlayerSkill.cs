using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerSkill : MonoBehaviour
{
    public Skill skillPrefab; // 스킬 프리팹을 할당하기 위한 변수

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Z))
        {
            // 스킬 프리팹을 인스턴스화하여 생성
            Skill newSkill = Instantiate(skillPrefab, transform.position, Quaternion.identity);
        newSkill.direction = GetComponent<NewBehaviourScript>().vector;
        }
    }
}
