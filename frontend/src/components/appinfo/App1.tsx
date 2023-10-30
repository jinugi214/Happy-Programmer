import React from "react";

const App1: React.FC = () => {
  return (
    <div className="relative">
      <div className="absolute inset-0 bg-bgone opacity-50" />
      <div className="relative flex ml-48">
        <div className="mr-24 text-m mt-16">(사진)</div>

        <div className="mt-16 mb-16">
          <div className="text-s text-purple">경험치 확인</div>
          <div className="mt-2">현재 경험치를 확인해보세요.</div>
        </div>
      </div>
    </div>
  );
};

export default App1;
