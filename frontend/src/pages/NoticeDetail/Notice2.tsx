import React from "react";

const Notice2: React.FC = () => {
  return (
    <div className="m-20 mb-32">
      <div className="text-m mb-1">UCC 업로드</div>
      <div>
        <span className="text-purple">GG TEAM</span> | 2023.11.16
      </div>

      <div className="mt-16 ml-12">
        <div>Happy Programmer 의 UCC가 업로드 되었습니다.</div>
        <div>이미지를 클릭하면 유튜브 링크로 이동합니다.</div>

        <a
          href="https://www.youtube.com/watch?v=dMIWamE9cqw"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img
            src="../imgs/n_2.gif"
            alt="img"
            className="mt-12 mb-12"
          />
        </a>

      </div>
    </div>
  );
};

export default Notice2;
