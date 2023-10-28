import React from "react";
import NoticeBox from "../components/notice/NoticeBox";

const Notice: React.FC = () => {
  return (
    <div>
      <div className="text-m text-center mb-16">News</div>

      <div className="flex justify-center w-full space-x-12 mb-20">
        <NoticeBox
          imgName="n1"
          title="패치노트 v1.3"
          date="2023.10.27"
          link="/notice/1"
        />
        <NoticeBox
          imgName="n2"
          title="패치노트 v1.2"
          date="2023.10.26"
          link="/notice/1"
        />
        <NoticeBox
          imgName="n1"
          title="패치노트 v1.1"
          date="2023.10.25"
          link="/notice/1"
        />
      </div>
    </div>
  );
};

export default Notice;
