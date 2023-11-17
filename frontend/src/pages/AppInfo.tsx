import React from "react";
import App1 from "../components/appinfo/App1";
import App2 from "../components/appinfo/App2";
import App3 from "../components/appinfo/App3";
import App4 from "../components/appinfo/App4";

const AppInfo: React.FC = () => {
  const handleDownload = () => {
    const link = document.createElement("a");
    link.href = "HappyProgrammer.apk";
    link.download = "HappyProgrammer.apk";
    link.click();
  };

  return (
    <div>
      <div className="text-s ml-52 mr-24 mt-32 mb-8 flex justify-between">

        <div>
          HAPPY PROGRAMMER 어플과 함께
          <br />
          게임을 더 풍성하게 즐겨요!
          <img
            src="../imgs/dd.png"
            alt="img"
            className="mt-8 w-48"
            onClick={handleDownload}
            style={{ cursor: "pointer" }}
          />
        </div>

        <div className="">
          <img src="../imgs/app.png" alt="img" className="h-56" />
        </div>
      </div>

      <App1 />
      <App2 />
      <App3 />
      <App4 />

      <div className="mb-20" />
    </div>
  );
};

export default AppInfo;
