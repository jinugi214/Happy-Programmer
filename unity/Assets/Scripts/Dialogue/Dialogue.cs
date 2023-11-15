using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Dialogue
{
    [System.Serializable]
    public class Situation
    {
        [TextArea(1, 2)]
        public string[] sentences;
        public Sprite[] dialogueWindows;
    }

    public Situation[] situations;

    // Situation 인덱스
    private int currentSituationIndex = 0;

    public bool HasNextSituation()
    {
        return currentSituationIndex < situations.Length;
    }

    public Situation GetNextSituation()
    {
        if (HasNextSituation())
        {
            return situations[currentSituationIndex++];
        }
        return null;
    }

    public bool IsDone()
    {
        return currentSituationIndex >= situations.Length;
    }
}

