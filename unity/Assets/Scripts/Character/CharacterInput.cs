using UnityEngine;
using System.Collections;
public class CharacterInput : MonoBehaviour
{
    private CharacterMovement characterMovement;
    private bool isRunning;
    private bool isAttacking;
    public Camera characterCamera;

    private void Start()
    {
        characterMovement = CharacterMovement.Instance;
    }

    private void Update()
    {
        float moveX = Input.GetAxisRaw("Horizontal");
        float moveY = Input.GetAxisRaw("Vertical");

        characterMovement.ProcessInput(moveX, moveY);

        // Shift로 달리기
        isRunning = Input.GetKey(KeyCode.LeftShift) || Input.GetKey(KeyCode.RightShift);
        characterMovement.SetRunning(isRunning);

        if (Input.GetKeyDown(KeyCode.Z))
        {
            isAttacking = true; // Z 키를 누르면 공격 상태로 변경
        }
        else if (Input.GetKeyUp(KeyCode.Z))
        {
            isAttacking = false; // Z 키를 뗐을 때 공격 상태 해제
        }

        characterMovement.SetAttacking(isAttacking);


        // 임시 : C 키를 누르면 스크린샷 캡처
        if (Input.GetKeyDown(KeyCode.C))
        {
            Debug.Log("치즈~");
            StartCoroutine(CaptureCharacter());
        }
    }

    public IEnumerator CaptureCharacter()
    {
        yield return new WaitForEndOfFrame();

        // 정사각형의 크기를 설정합니다. 원하는 크기로 수정할 수 있습니다.
        int squareResolution = 1024;

        // 별도의 RenderTexture를 생성합니다.
        RenderTexture renderTexture = new RenderTexture(squareResolution, squareResolution, 24);

        // 캡처할 카메라의 원래 targetTexture를 저장합니다.
        RenderTexture originalTargetTexture = characterCamera.targetTexture;

        // 캡처할 카메라의 targetTexture를 새 RenderTexture로 설정합니다.
        characterCamera.targetTexture = renderTexture;
        characterCamera.Render();

        // 캡처할 텍스처를 생성합니다.
        Texture2D texture = new Texture2D(squareResolution, squareResolution, TextureFormat.RGB24, false);
        RenderTexture.active = renderTexture;
        texture.ReadPixels(new Rect(0, 0, squareResolution, squareResolution), 0, 0);
        texture.Apply();

        // 캡처할 카메라의 targetTexture를 원래대로 복원합니다.
        characterCamera.targetTexture = originalTargetTexture;

        // RenderTexture를 해제합니다.
        RenderTexture.active = null;
        Destroy(renderTexture);

        // 텍스처를 JPEG 이미지로 변환합니다.
        byte[] bytes = texture.EncodeToJPG();

        // 이미지를 사용자의 컴퓨터에 저장합니다.
        string fileName = "SavedScreen_" + System.DateTime.Now.ToString("yyyyMMdd_HHmmss") + ".jpg";
        System.IO.File.WriteAllBytes(Application.dataPath + "/" + fileName, bytes);

        // 캡처한 텍스처를 해제합니다.
        Destroy(texture);

        // 백엔드에 전송 구현하기
    }

}
