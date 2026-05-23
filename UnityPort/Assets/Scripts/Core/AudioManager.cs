using UnityEngine;

public class AudioManager : MonoBehaviour
{
    public static AudioManager Instance { get; private set; }

    private AudioSource _musicSource;
    private AudioSource _sfxSource;
    private float _musicVolume = 0.5f;
    private float _sfxVolume = 0.8f;
    private bool _musicEnabled = true;
    private bool _sfxEnabled = true;

    private void Awake()
    {
        if (Instance != null) { Destroy(gameObject); return; }
        Instance = this;
        DontDestroyOnLoad(gameObject);
        _musicSource = gameObject.AddComponent<AudioSource>();
        _musicSource.loop = true;
        _sfxSource = gameObject.AddComponent<AudioSource>();
        _sfxSource.loop = false;
    }

    public void PlayMusic(string name)
    {
        if (!_musicEnabled) return;
        var clip = AssetManager.LoadAudio(name);
        if (clip == null) return;
        if (_musicSource.clip == clip && _musicSource.isPlaying) return;
        _musicSource.clip = clip;
        _musicSource.volume = _musicVolume;
        _musicSource.Play();
    }

    public void StopMusic()
    {
        if (_musicSource.isPlaying) _musicSource.Stop();
    }

    public void PlaySFX(string name)
    {
        if (!_sfxEnabled) return;
        var clip = AssetManager.LoadAudio(name);
        if (clip == null) return;
        _sfxSource.PlayOneShot(clip, _sfxVolume);
    }

    public void SetMusicVolume(float vol) { _musicVolume = Mathf.Clamp01(vol); if (_musicSource.isPlaying) _musicSource.volume = _musicVolume; }
    public void SetSFXVolume(float vol) { _sfxVolume = Mathf.Clamp01(vol); }
    public void SetMusicEnabled(bool en) { _musicEnabled = en; if (!en) StopMusic(); }
    public void SetSFXEnabled(bool en) { _sfxEnabled = en; }
    public float GetMusicVolume() => _musicVolume;
    public float GetSFXVolume() => _sfxVolume;
    public bool IsMusicEnabled() => _musicEnabled;
    public bool IsSFXEnabled() => _sfxEnabled;
}
