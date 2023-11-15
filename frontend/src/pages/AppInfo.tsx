import React from "react";
import App1 from "../components/appinfo/App1";
import App2 from "../components/appinfo/App2";

const AppInfo: React.FC = () => {
  return (
    <div>
      <div className="text-s m-24 mb-32">
        <div>
          HAPPY PROGRAMMER 어플과 함께
          <br />
          게임을 더 풍성하게 즐겨요!
        </div>
      </div>

      <App1 />
      <App2 />

      <div className="mb-20"/>
    </div>
  );
};

export default AppInfo;
