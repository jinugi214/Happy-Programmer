using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class DialogueManager : MonoBehaviour
{
    public static DialogueManager instance;

    #region Singleton
    private void Awake()
    {
        if (instance == null)
        {
            DontDestroyOnLoad(this.gameObject);
            instance = this;
        }
        else
        {
            Destroy(this.gameObject);
        }
    }
    #endregion Singleton

    public TextMeshProUGUI text;
    public SpriteRenderer rendererDialogueWindow;

    private List<string> listSentences;
    private List<Sprite> listDialogueWindows;

    private int count;

    public Animator animDialogueWindow;

    public bool talking = false;

    // Start is called before the first frame update
    void Start()
    {
        count = 0;
        text.text = "";
        listSentences = new List<string>();
        listDialogueWindows = new List<Sprite>();
    }

    public void ShowDialogue(Dialogue dialogue)
    {
        talking = true;

        listSentences.Clear(); // 대화 시작 시 리스트 초기화
        listDialogueWindows.Clear();

        for (int i = 0; i < dialogue.sentences.Length; i++)
        {
            listSentences.Add(dialogue.sentences[i]);
            listDialogueWindows.Add(dialogue.dialogueWindos[i]);
        }

        animDialogueWindow.SetBool("Appear", true);
        StartCoroutine(StartDialogueCoroutine());
    }

    public void ExitDialogue()
    {
        count = 0;
        text.text = "";
        listSentences.Clear();
        listDialogueWindows.Clear();
        animDialogueWindow.SetBool("Appear", false);
        talking = false;
    }

    IEnumerator StartDialogueCoroutine()
    {
        if (count > 0)
        {
            if (listDialogueWindows[count] != listDialogueWindows[count - 1])
            {
                animDialogueWindow.SetBool("Appear", false);
                yield return new WaitForSeconds(0.2f);
                rendererDialogueWindow.sprite = listDialogueWindows[count];
                animDialogueWindow.SetBool("Appear", true);
            }
            else
            {
                yield return new WaitForSeconds(0.05f);
            }
        }
        else
        {
            rendererDialogueWindow.sprite = listDialogueWindows[count];
        }

        for (int i = 0; i < listSentences[count].Length; i++)
        {
            text.text += listSentences[count][i];
            yield return new WaitForSeconds(0.01f);
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (talking)
        {
            if (Input.GetKeyDown(KeyCode.Space))
            {
                count++;
                text.text = "";

                if (count == listSentences.Count)
                {
                    StopAllCoroutines();
                    ExitDialogue();
                }
                else
                {
                    StopAllCoroutines();
                    StartCoroutine(StartDialogueCoroutine());
                }
            }
        }
    }
}