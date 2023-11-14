using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Volume : MonoBehaviour
{
    public AudioSource musicSource;
    public Slider volumeSlider;

    private int currentBGMIndex = 0;

    private void Start()
    {
        if (volumeSlider != null && musicSource != null)
        {
            volumeSlider.value = musicSource.volume;
        }
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
        }
    }
}
