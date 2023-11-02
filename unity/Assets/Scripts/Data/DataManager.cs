using UnityEngine;
using System.IO;

public class PlayerData
{
    public string name;
    public int exp;
    public int level;
    public int point;
}


public class DatabaseManager : MonoBehaviour
{   
    // 싱글톤
    public static DatabaseManager instance;

    PlayerData nowPlayer = new PlayerData();

    string path;
    string filename = "save";


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

        path = Application.persistentDataPath + "/";
    }

    public string[] var_name;
    public float[] var; // float 값을 기억시키는 배열

    public string[] switch_name;
    public bool[] switches; // true/false값을 기억시키는 배열
    // 이미 완료했던 것을 다시 실행되도록 하지 않기 위해서 true/false 값을 저장하는 곳에 사용

    void Start()
    {
        
    }

    public void SaveData()
    {
        string data = JsonUtility.ToJson(nowPlayer);
        File.WriteAllText(path + filename, data);
    }

    public void LoadData()
    {
        string data = File.ReadAllText(path + filename);
        JsonUtility.FromJson<PlayerData>(data);
    }
}
