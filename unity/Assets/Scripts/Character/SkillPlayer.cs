using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkillPlayer : MonoBehaviour
{
    public Vector2 direction; // 스킬의 움직일 방향

    // 키보드
    public SkillObject keyboard1Prefab;
    public SkillObject keyboard2Prefab;
    public SkillObject keyboard3Prefab;

    // 마우스
    public SkillObject mouse1Prefab;
    public SkillObject mouse2Prefab;
    public SkillObject mouse3Prefab;

    // 폰
    public SkillObject phone1Prefab;
    public SkillObject phone2Prefab;
    public SkillObject phone3Prefab;

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.X))
        {
            SkillObject skillToUse = null; // 사용할 스킬 프리팹을 저장하는 변수

            var weaponType = GetComponent<CharacterAppear>().GetWeaponType();
            var keyboardNumber = GetComponent<CharacterAppear>().keyboardNumber;
            var mouseNumber = GetComponent<CharacterAppear>().mouseNumber;
            var phoneNumber = GetComponent<CharacterAppear>().phoneNumber;

            // 현재 장착된 무기 유형에 따라 사용할 스킬 프리팹을 선택
            switch (weaponType)
            {
                case 1: // 키보드
                    switch (keyboardNumber)
                    {
                        case 1:
                            skillToUse = keyboard1Prefab;
                            break;
                        case 2:
                            skillToUse = keyboard2Prefab;
                            break;
                        case 3:
                            skillToUse = keyboard3Prefab;
                            break;
                    }
                    break;

                case 2: // 마우스
                    switch (mouseNumber)
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
                    break;
                case 3: // 폰
                    switch (phoneNumber)
                    {
                        case 1:
                            skillToUse = phone1Prefab;
                            break;
                        case 2:
                            skillToUse = phone2Prefab;
                            break;
                        case 3:
                            skillToUse = phone3Prefab;
                            break;
                    }
                    break;
            }

            // 선택된 스킬 프리팹을 인스턴스화하여 생성
Vector3 position = transform.position;
if (weaponType == 1) // 키보드인 경우
{
    position.y += 1;
}
SkillObject newSkill = Instantiate(skillToUse, position, Quaternion.identity);
newSkill.gameObject.SetActive(true);
newSkill.skillAudioSource = GetComponent<AudioSource>();
newSkill.Create(weaponType); // 무기 유형을 설정하면서, 스킬 오브젝트 생성
newSkill.direction = GetComponent<CharacterMovement>().lastDirection;


        }
    }
}
