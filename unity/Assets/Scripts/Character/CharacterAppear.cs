using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterAppear : MonoBehaviour
{
    private int weaponType; // 1: 키보드, 2: 마우스, 3: 폰
    public int mouseNumber;
    public int keyboardNumber;
    public int phoneNumber;

    public GameObject weapon;
    public GameObject keyboard;
    public GameObject keyboard1;
    public GameObject keyboard2;
    public GameObject keyboard3;

    public GameObject mouse;
    public GameObject mouse1;
    public GameObject mouse2;
    public GameObject mouse3;

    public GameObject phone;
    public GameObject phone1;
    public GameObject phone2;
    public GameObject phone3;

    public GameObject helmet;
    public GameObject helmet1;
    public GameObject helmet2;
    public GameObject helmet3;

    public bool IsWeaponEquipped()
    {
        return weaponType != 0;
    }

    public int GetWeaponType()
    {
        return weaponType;
    }

    public int GetMouseNumber()
    {
        return mouseNumber;
    }


    // 키보드
    public void EquipKeyboard1()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(true);
        keyboard2.SetActive(false);
        keyboard3.SetActive(false);

        weaponType = 1;

        keyboardNumber = 1;
    }

    public void EquipKeyboard2()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(false);
        keyboard2.SetActive(true);
        keyboard3.SetActive(false);

        weaponType = 1;

        keyboardNumber = 2;
    }

    public void EquipKeyboard3()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(false);
        keyboard2.SetActive(false);
        keyboard3.SetActive(true);

        weaponType = 1;

        keyboardNumber = 3;
    }

    // 마우스
    public void EquipMouse1()
    {
        keyboard.SetActive(false);
        mouse.SetActive(true);
        phone.SetActive(false);

        mouse1.SetActive(true);
        mouse2.SetActive(false);
        mouse3.SetActive(false);

        weaponType = 2;

        mouseNumber = 1;
    }

    public void EquipMouse2()
    {
        keyboard.SetActive(false);
        mouse.SetActive(true);
        phone.SetActive(false);

        mouse1.SetActive(false);
        mouse2.SetActive(true);
        mouse3.SetActive(false);

        weaponType = 2;

        mouseNumber = 2;
    }

    public void EquipMouse3()
    {
        keyboard.SetActive(false);
        mouse.SetActive(true);
        phone.SetActive(false);

        mouse1.SetActive(false);
        mouse2.SetActive(false);
        mouse3.SetActive(true);

        weaponType = 2;

        mouseNumber = 3;
    }

    // 휴대폰
    public void EquipPhone1()
    {
        keyboard.SetActive(false);
        mouse.SetActive(false);
        phone.SetActive(true);

        phone1.SetActive(true);
        phone2.SetActive(false);
        phone3.SetActive(false);

        weaponType = 3;

        phoneNumber = 1;
    }

    public void EquipPhone2()
    {
        keyboard.SetActive(false);
        mouse.SetActive(false);
        phone.SetActive(true);

        phone1.SetActive(false);
        phone2.SetActive(true);
        phone3.SetActive(false);

        weaponType = 3;

        phoneNumber = 2;
    }

    public void EquipPhone3()
    {
        keyboard.SetActive(false);
        mouse.SetActive(false);
        phone.SetActive(true);

        phone1.SetActive(false);
        phone2.SetActive(false);
        phone3.SetActive(true);

        weaponType = 3;

        phoneNumber = 3;
    }


    // 헬멧
    public void EquipHelmet1()
    {
        helmet.SetActive(true);

        helmet1.SetActive(true);
        helmet2.SetActive(false);
        helmet3.SetActive(false);
    }

    public void EquipHelmet2()
    {
        helmet.SetActive(true);

        helmet1.SetActive(false);
        helmet2.SetActive(true);
        helmet3.SetActive(false);
    }

    public void EquipHelmet3()
    {
        helmet.SetActive(true);

        helmet1.SetActive(false);
        helmet2.SetActive(false);
        helmet3.SetActive(true);
    }

}
