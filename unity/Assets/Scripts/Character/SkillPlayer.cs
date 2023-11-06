using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkillPlayer : MonoBehaviour
{
    // 스킬 프리팹을 할당하기 위한 변수
    public SkillObject mouse1Prefab;
public SkillObject mouse2Prefab;
public SkillObject mouse3Prefab;

    public Vector2 direction; // 스킬의 움직일 방향

    void Update()
{
    if (Input.GetKeyDown(KeyCode.X))
    {
        SkillObject skillToUse = null; // 사용할 스킬 프리팹을 저장하는 변수

        // 현재 장착된 마우스 유형에 따라 사용할 스킬 프리팹을 선택
        switch(GetComponent<CharacterAppear>().GetMouseNumber())
        {
            case 1:
                skillToUse = mouse1Prefab;
                break;
            case 2:
                skillToUse = mouse2Prefab;
                break;
            case 3:
                skillToUse = mouse3Prefab;
                break;
        }

        // 선택된 스킬 프리팹을 인스턴스화하여 생성
        SkillObject newSkill = Instantiate(skillToUse, transform.position, Quaternion.identity);
        newSkill.direction = GetComponent<CharacterMovement>().lastDirection;
    }
}

}
