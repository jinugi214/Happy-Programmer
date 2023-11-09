using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Event1 : MonoBehaviour
{
    public Dialogue dialogue_1;
    public Dialogue dialogue_2;

    private DialogueManager theDM;
    private CharacterMovement thePlayer;

    // Start is called before the first frame update
    void Start()
    {
        theDM = FindObjectOfType<DialogueManager>();
        thePlayer = FindObjectOfType<CharacterMovement>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
