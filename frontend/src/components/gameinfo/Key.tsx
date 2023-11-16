import React from "react";

const Key: React.FC = () => {
  return (
    <div className="mb-32 flex">
      <div className="mr-24 text-m mt-5">조작법</div>

      <div className="mr-12 text-s">
        <div className="mt-6">이동</div>
        <div className="mt-6">상호작용</div>
        <div className="mt-6">인벤토리</div>
        <div className="mt-6">공격</div>
        <div className="mt-6">스킬</div>
        <div className="mt-6">설정</div>
        <div className="mt-6">게임 저장</div>
      </div>

      <div className="text-s">
        {/* <div className='mt-2'>WASD , ← → ↓ ↑</div> */}
        {/* <div className='mt-2'>SpaceBar</div> */}
        {/* <div className='mt-2'>I</div> */}
        {/* <div className='mt-2'>X</div> */}
        {/* <div className='mt-2'>Z</div> */}
        {/* <div className='mt-2'>Esc</div> */}
        {/* <div className='mt-2'>F5</div> */}
        <div className="mt-2 flex">
          <img src="./imgs/key_w.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_a.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_s.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_d.png" className="mt-2 w-11 mb-1" alt="key" />
          <div className="mt-5 ml-3 mr-3">|</div>
          <img src="./imgs/key_1.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_2.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_3.png" className="mt-2 w-11 mb-1" alt="key" />
          <img src="./imgs/key_4.png" className="mt-2 w-11 mb-1" alt="key" />
        </div>

        <img src="./imgs/key_sb.png" className="mt-2 w-24" alt="key" />
        <img src="./imgs/key_i.png" className="mt-2 w-11" alt="key" />
        <img src="./imgs/key_x.png" className="mt-2 w-11" alt="key" />
        <img src="./imgs/key_z.png" className="mt-2 w-11" alt="key" />
        <img src="./imgs/key_esc.png" className="mt-2 w-11" alt="key" />
        <img src="./imgs/key_f5.png" className="mt-2 w-11" alt="key" />
      </div>
    </div>
  );
};

export default Key;
