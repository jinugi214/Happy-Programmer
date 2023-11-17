import React from "react";
import NoticeBox from "../components/notice/NoticeBox";

const Notice: React.FC = () => {
  return (
    <div>
      <div className="text-m text-center mb-16">News</div>

      <div className="flex justify-center w-full space-x-20 mb-20">
        <NoticeBox
          imgName="n1_1(morning)_2"
          title="패치노트 v1.0"
          date="2023.11.15"
          link="/notice/1"
        />

        <NoticeBox
          imgName="n_2"
          title="UCC 업로드"
          date="2023.11.16"
          link="/notice/2"
        />

        <NoticeBox
          imgName="n_3"
          title="패치 예정 사항"
          date="2023.11.17"
          link="/notice/3"
        />
      </div>
    </div>
  );
};

export default Notice;
