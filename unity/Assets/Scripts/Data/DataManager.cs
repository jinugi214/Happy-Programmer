using System.Collections.Generic;
using UnityEngine;



[System.Serializable]
public class AccountData
{
    public string accessToken;
    public string refreshToken;
    public string accountId;
    public string username;
    public string nickname;
    public string language;
}


[System.Serializable]
public class CharacterData
{
    public long id;
    public string name;
    public string gender;
    public int exp;
    public int level;
    public int point;
    public string savepoint;
    public int storyProgress;
    public List<Item> itemList;
}

public class DataManager : MonoBehaviour
{

    // 싱글톤
    public static DataManager instance;


    private void Awake()
    {
        #region 싱글톤
        if (instance == null)
        {
            instance = this;
        }
        else if (instance != this)
        {
            Destroy(instance.gameObject);
            return;
        }

        DontDestroyOnLoad(this.gameObject);
        #endregion

        if (accountData == null)
        {
            accountData = new AccountData();
        }

        if (characterData == null)
        {
            characterData = new CharacterData();
        }
    }

    private AccountData accountData;
    private CharacterData characterData;

    public AccountData AccountData
    {
        get { return accountData; }
        set { accountData = value; }
    }

    public CharacterData CharacterData
    {
        get { return characterData; }
        set { characterData = value; }
    }


    public void LoadCharacterData(CharacterData characterLoadData)
    {
        characterData.name = characterLoadData.name;
        characterData.gender = characterLoadData.gender;
        characterData.exp = characterLoadData.exp;
        characterData.level = characterLoadData.level;
        characterData.point = characterLoadData.point;
        characterData.savepoint = characterLoadData.savepoint;
        characterData.storyProgress = characterLoadData.storyProgress;
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }

    public void UpdateCharacterData(int exp, int level)
    {
        characterData.exp = exp;
        characterData.level = level;
        Debug.Log("호출됨"+characterData.exp);
        Debug.Log("호출됐음" + characterData.level);
    }
}
