import React from "react";

const Notice1: React.FC = () => {
  return (
    <div className="m-20 mb-32">
      <div className="text-m mb-2">패치노트 v1.0</div>
      <div>
        <span className="text-purple">GG TEAM</span> | 2023.11.15
      </div>

      <div className="mt-16 ml-12">
        <div className="mt-20">Happy Programmer v1.0 이 출시되었습니다.</div>
        <img src="../imgs/n1_1.gif" alt="img" className="mt-24 mb-12"/>
        <div>게임을 다운받고 실행하면, 회원 가입을 할 수 있습니다.</div>

        <img src="../imgs/n1_2.gif" alt="img" className="mt-24 mb-12"/>
        <div>처음부터 한다면 새로시작, 이어서 하고싶다면 불러오기를 할 수 있습니다.</div>

        <img src="../imgs/n1_3.gif" alt="img" className="mt-24 mb-12"/>
        <div>캐릭터를 생성하고, 회사 안의 직원들을 만나며 스토리를 진행해보세요!</div>
      </div>
    </div>
  );
};

export default Notice1;
