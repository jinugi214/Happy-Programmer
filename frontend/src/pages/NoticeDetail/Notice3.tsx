import React from "react";

const Notice3: React.FC = () => {
  return (
    <div className="m-20 mb-32">
      <div className="text-m mb-1">패치 예정 사항</div>
      <div>
        <span className="text-purple">GG TEAM</span> | 2023.11.17
      </div>

      <div className="mt-16 ml-12">
        <div className="mb-4">추후 패치 예정인 사항들입니다.</div>
        <div>* 2회차 제작 (여러가지 엔딩 ver)</div>
        <div>* 유니티-모바일 연동 미션 (AI인식)</div>
        <div>* 커뮤니티</div>
        <div>* 레벨 시스템</div>
        <div>* 궁극기 스킬 추가</div>
      </div>
    </div>
  );
};

export default Notice3;
