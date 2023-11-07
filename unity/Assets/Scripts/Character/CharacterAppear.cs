using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterAppear : MonoBehaviour
{
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


    // 키보드
    public void EquipKeyboard1()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(true);
        keyboard2.SetActive(false);
        keyboard3.SetActive(false);
    }

        public void EquipKeyboard2()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(false);
        keyboard2.SetActive(true);
        keyboard3.SetActive(false);
    }

        public void EquipKeyboard3()
    {
        keyboard.SetActive(true);
        mouse.SetActive(false);
        phone.SetActive(false);

        keyboard1.SetActive(false);
        keyboard2.SetActive(false);
        keyboard3.SetActive(true);
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
    }

        public void EquipMouse2()
    {
        keyboard.SetActive(false);
        mouse.SetActive(true);
        phone.SetActive(false);

        mouse1.SetActive(false);
        mouse2.SetActive(true);
        mouse3.SetActive(false);
    }

        public void EquipMouse3()
    {
        keyboard.SetActive(false);
        mouse.SetActive(true);
        phone.SetActive(false);

        mouse1.SetActive(false);
        mouse2.SetActive(false);
        mouse3.SetActive(true);
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
    }

        public void EquipPhone2()
    {
        keyboard.SetActive(false);
        mouse.SetActive(false);
        phone.SetActive(true);

        phone1.SetActive(false);
        phone2.SetActive(true);
        phone3.SetActive(false);
    }

        public void EquipPhone3()
    {
        keyboard.SetActive(false);
        mouse.SetActive(false);
        phone.SetActive(true);

        phone1.SetActive(false);
        phone2.SetActive(false);
        phone3.SetActive(true);
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
