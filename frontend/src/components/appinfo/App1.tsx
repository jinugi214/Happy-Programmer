import React from "react";

const App1: React.FC = () => {
  return (
    <div className="relative">
      <div className="absolute inset-0 bg-bgone opacity-50" />
      <div className="relative flex ml-48">
        <img src="../imgs/a1.jpg" alt="img" className="mr-24 text-m mt-8 w-44"/>

        <div className="mt-16 mb-16">
          <div className="text-s text-purple mt-44">홈 화면</div>
          <div className="mt-2">현재 캐릭터의 모습과 도감, 즐겨찾기, 미션 등 여러 기능을 사용해보세요.</div>
        </div>
      </div>
    </div>
  );
};

export default App1;
