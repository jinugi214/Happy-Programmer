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
    public string name;
    public string gender;
    public int exp;
    public int level;
    public int point;
    public int savepoint;
    public int storyProgress;
    // 인벤토리, 장착 아이템, 도전과제 등 추가하기

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

    public void LoadCharacterData(string name, string gender, int exp, int level,
                         int point, int savepoint, int storyProgress)
    {
        characterData.name = name;
        characterData.gender = gender;
        characterData.exp = exp;
        characterData.level = level;
        characterData.point = point;
        characterData.savepoint = savepoint;
        characterData.storyProgress = storyProgress;
    }
}
