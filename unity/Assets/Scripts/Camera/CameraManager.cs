using UnityEngine;
using static UnityEngine.GraphicsBuffer;

public class CameraManager : MonoBehaviour
{

    public GameObject target; // 카메라가 따라갈 대상
    public float moveSpeed;
    private Vector3 targetPosition; // 대상의 현재 위치 값

    public BoxCollider2D bound;

    private Vector3 minBound;
    private Vector3 maxBound;
    // 박스 컬라이더 영역의 최소 최대 xyz값

    private float halfWidth;
    private float halfHeight;

    private Camera theCamera;
    // 카메라 반높이 구할 속성 이용

    void Start()
    {
        BoundResize();
    }

    // Update is called once per frame
    void Update()
    {

        if(target.gameObject != null)
        {
            targetPosition.Set(target.transform.position.x, target.transform.position.y, this.transform.position.z);

            this.transform.position = Vector3.Lerp(this.transform.position, targetPosition, moveSpeed * Time.deltaTime);    // 1초에 moveSpeed만큼 이동

            float clampedX = Mathf.Clamp(this.transform.position.x, minBound.x + halfWidth, maxBound.x - halfWidth);
            float clampedY = Mathf.Clamp(this.transform.position.y, minBound.y + halfHeight, maxBound.y - halfHeight);

            this.transform.position = new Vector3(clampedX, clampedY, this.transform.position.z);
        }

        if(bound == null)
        {
            BoundResize();
        }
        
    }

    private void BoundResize()
    {

        target = GameObject.FindGameObjectWithTag("Player");
        bound = BoundScript.instance.bound.GetComponent<BoxCollider2D>();
        theCamera = GetComponent<Camera>();
        minBound = bound.bounds.min;
        maxBound = bound.bounds.max;

        halfHeight = theCamera.orthographicSize;
        halfWidth = halfHeight * Screen.width / Screen.height;
    }
     
}


