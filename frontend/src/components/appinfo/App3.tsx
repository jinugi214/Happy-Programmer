import React from "react";

const App3: React.FC = () => {
  return (
    <div className="relative">
      <div className="absolute inset-0 bg-bgone opacity-50" />
      <div className="relative flex ml-32">
        <img src="../imgs/a3.jpg" alt="img" className="mr-4 text-m mt-8 w-44"/>
        <img src="../imgs/a4.jpg" alt="img" className="mr-4 text-m mt-8 w-44"/>
        <img src="../imgs/a5.jpg" alt="img" className="mr-32 text-m mt-8 w-44"/>

        <div className="mt-16 mb-16">
          <div className="text-s text-purple mt-44">도감 확인</div>
          <div className="mt-2">전체 아이템, 스킬, 몬스터를 도감으로 확인해보세요.</div>
          <div className="mt-2">현재 가지고 있지 않은 아이템은 회색으로 표시되고,</div>
          <div className="mt-2">즐겨찾기를 누르면 하트로 표시됩니다.</div>
        </div>
      </div>
    </div>
  );
};

export default App3;
