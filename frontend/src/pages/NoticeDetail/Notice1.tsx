import React from "react";

const Notice1: React.FC = () => {
  return (
    <div className="m-20 mb-32">
      <div className="text-m mb-2">패치노트 v1.0</div>
      <div>
        <span className="text-purple">GG TEAM</span> | 2023.11.15
      </div>

      <div className="mt-16 ml-12">
        <img src="../imgs/key_i.png" alt="img" className=""/>
        <div>Happy Programmer v1.0 이 출시되었습니다.</div>

        <img src="../imgs/key_i.png" alt="img" className="mt-12"/>
        <div>게임을 다운받고 실행하면, 회원 가입을 할 수 있습니다.</div>

        <img src="../imgs/key_i.png" alt="img" className="mt-12"/>
        <div>캐릭터를 생성하고, 회사 안의 직원들을 만나며 스토리를 진행하세요.</div>
      </div>
    </div>
  );
};

export default Notice1;
