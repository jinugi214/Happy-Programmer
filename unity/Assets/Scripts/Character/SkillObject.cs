using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkillObject : MonoBehaviour
{
    public float speed; // 스킬의 이동 속도
    public Vector2 direction; // 스킬의 움직일 방향
    public GameObject effectPrefab; // 효과를 나타내는 에셋
    public int weaponType; // 1: 키보드, 2: 마우스, 3: 폰

    public void Create(int weaponType)
    {
        this.weaponType = weaponType;

        if (weaponType == 1) // 키보드인 경우
        {
            Destroy(gameObject, 1f);
        }
    }

    void Update()
    {
        transform.Translate(direction * speed * Time.deltaTime);
    }

    private void MoveForward()
    {
        transform.Translate(transform.right * speed * Time.deltaTime);
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Monster")
        {
            // 충돌 위치에서 랜덤한 위치를 계산
            Vector3 position = other.transform.position;
            position.x += Random.Range(-1f, 1f);
            position.y += Random.Range(-1f, 1f);
            position.z = 0;

            // 계산한 위치에서 효과를 생성
            GameObject effect = Instantiate(effectPrefab, position, Quaternion.identity);

            // 2초 후에 효과를 제거
            Destroy(effect, 1f);

            // 스킬 오브젝트를 제거
            Destroy(gameObject);
        }
    }



}
