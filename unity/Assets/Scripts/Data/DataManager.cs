using UnityEngine;

[System.Serializable]


public class AccountData
{
    public string username;
    public string nickname;
    public string language;
}


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
        }
        DontDestroyOnLoad(this.gameObject);
        #endregion
    }

    public AccountData accountData;
    public CharacterData characterData;



    public string[] var_name;
    public float[] var; // float 값을 기억시키는 배열

    public string[] switch_name;
    public bool[] switches; // true/false값을 기억시키는 배열

    // 이미 완료했던 것을 다시 실행되도록 하지 않기 위해서 true/false 값을 저장하는 곳에 사용

}
