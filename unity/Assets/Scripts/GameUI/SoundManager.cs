using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class SoundManager : MonoBehaviour
{
    static public SoundManager instance;

    private void Awake()
    {
        if (instance != null)
        {
            Destroy(this.gameObject);
        }
        else
        {
            DontDestroyOnLoad(this.gameObject);
            instance = this;
        }
    }

    public List<SceneBGM> sceneBGMs;
    public AudioSource musicSource;
    public Slider volumeSlider;

    [System.Serializable]
    public class SceneBGM
    {
        public string sceneName; // 씬의 이름
        public AudioClip bgm; // 씬에 해당하는 배경음악
    }

    private void Start()
    {
       if (volumeSlider != null && musicSource != null)
        {
            // 저장된 볼륨이 있으면 불러와서 설정, 없으면 기본 값으로 설정
            float savedVolume = PlayerPrefs.GetFloat("MusicVolume", 0.5f);
            volumeSlider.value = savedVolume;
            SetMusicVolume(savedVolume);
        }

        PlayBGM();
    }

    private void SetMusicVolume(float volume)
    {
        if (musicSource != null)
        {
            musicSource.volume = volume;
        }
    }

    public void OnVolumeSliderChanged()
    {
        if (volumeSlider != null)
        {
            SetMusicVolume(volumeSlider.value);
            // 설정한 볼륨을 저장
            PlayerPrefs.SetFloat("MusicVolume", volumeSlider.value);
        }
    }

    private void PlayBGM()
    {
        string currentSceneName = SceneManager.GetActiveScene().name;

        // 현재 씬에 해당하는 배경음악을 찾아 재생
        SceneBGM currentSceneBGM = sceneBGMs.Find(sceneBGM => sceneBGM.sceneName == currentSceneName);

        if (currentSceneBGM != null && musicSource != null)
        {
            musicSource.clip = currentSceneBGM.bgm;
            musicSource.Play();
        }
    }

    private void OnEnable()
    {
        SceneManager.sceneLoaded += OnSceneLoaded;
    }

    private void OnSceneLoaded(Scene scene, LoadSceneMode mode)
    {
        PlayBGM();
    }

    private void OnDisable()
    {
        SceneManager.sceneLoaded -= OnSceneLoaded;
    }
}
