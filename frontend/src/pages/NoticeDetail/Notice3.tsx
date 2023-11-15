import React from "react";

const Notice3: React.FC = () => {
  return (
    <div>
      <div className="text-m mb-1">패치 예정 사항</div>
      <div>
        <span className="text-purple">GG TEAM</span> | 2023.11.17
      </div>

      <div className="mt-10 ml-6">
        <div className="mb-4">추후 패치 예정인 사항들입니다.</div>
        <div>* 커뮤니티</div>
        <div>* 레벨 시스템</div>
        <div>* 궁극기 스킬 추가</div>
        <div>* 스토리 중 선택 기능</div>

        <div className="mt-12 mb-4">추가적으로 아래 버그사항들을 수정할 예정입니다.</div>
        <div>* </div>
        <div>* </div>
        <div>* </div>
      </div>
    </div>
  );
};

export default Notice3;
