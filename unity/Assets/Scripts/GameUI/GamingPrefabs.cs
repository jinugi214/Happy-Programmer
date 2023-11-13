using UnityEngine;

public class GamingPrefabs : MonoBehaviour
{
    public GameObject questWindowPrefab;
    public GameObject saveWindowPrefab;
    public GameObject settingWindowPrefab;

    public void InstantiatePrefabs()
    {
        Instantiate(questWindowPrefab);
        Instantiate(saveWindowPrefab);
        Instantiate(settingWindowPrefab);
    }

    public void EndGame()
    {
        Destroy(questWindowPrefab);
        Destroy(saveWindowPrefab);
        Destroy(settingWindowPrefab);
    }
}
