using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

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
    // mpublic SpriteRenderer rendererSprite; 
    public SpriteRenderer rendererDialogueWindow;

    private List<string> listSentences;
    // private List<Sprite> listSprites;
    private List<Sprite> listDialogueWindows;

    private int count; // 대화 진행 상황 카운트

    // public Animator animSprite;
    public Animator animDialogueWindow;

    // Start is called before the first frame update
    void Start()
    {
        count = 0;
        text.text = "";
        listSentences = new List<string>();
        // listSprites = new List<Sprite>();
        listDialogueWindows = new List<Sprite>();
    }

    public void ShowDialogue(Dialogue dialogue)
    {
        for (int i = 0; i < dialogue.sentences.Length; i++)
        {
            listSentences.Add(dialogue.sentences[i]);
            // listSprites.Add(dialogue.sprites[i]);
            listDialogueWindows.Add(dialogue.dialogueWindos[i]);
        }

        // animSprite.SetBool("Appear", true);
        animDialogueWindow.SetBool("Appear", true);
        StartCoroutine(StartDialogueCoroutine());
    }

    public void ExitDialogue()
    {
        count = 0;
        text.text = "";
        listSentences.Clear();
        // listSprites.Clear();
        listDialogueWindows.Clear();
        // animSprite.SetBool("Appear", false);
        animDialogueWindow.SetBool("Appear", false);
    }

    IEnumerator StartDialogueCoroutine()
    {
        if(count > 0)
        {
            if (listDialogueWindows[count] != listDialogueWindows[count - 1])
            {
                // animSprite.SetBool("Change", true);
                animDialogueWindow.SetBool("Appear", false);
                yield return new WaitForSeconds(0.2f);
                rendererDialogueWindow.GetComponent<SpriteRenderer>().sprite = listDialogueWindows[count];
                // rendererSprite.GetComponent<SpriteRenderer>().sprite = listSprites[count];
                animDialogueWindow.SetBool("Appear", true);
                // animSprite.SetBool("Change", false);
            }
            else
            {
                // if (listSprites[count] != listSprites[count - 1])
                // {
                    // animSprite.SetBool("Change", true);
                    // yield return new WaitForSeconds(0.1f);
                    // rendererSprite.GetComponent<SpriteRenderer>().sprite = listSprites[count];
                    // animSprite.SetBool("Change", false);
                // }
                // else
                // {
                    yield return new WaitForSeconds(0.05f);
                // }
            }
        } 
        else
        {
            rendererDialogueWindow.GetComponent<SpriteRenderer>().sprite = listDialogueWindows[count];
            // rendererSprite.GetComponent<SpriteRenderer>().sprite = listSprites[count];
        }

        for (int i = 0; i < listSentences[count].Length; i++)
        {
            text.text += listSentences[count][i]; // 한 문장에 한 글자씩 출력
            yield return new WaitForSeconds(0.01f);
        }

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Space))
        {
            count++;
            text.text = "";

            if (count == listSentences.Count - 1)
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
