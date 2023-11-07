import React from "react";
import MemberBox from "../components/teaminfo/MemberBox";

const TeamInfo: React.FC = () => {
  return (
    <div>
      {/* <div className="text-m text-center mb-20">Gumi Games</div> */}

      <div className="flex flex-col items-center justify-center mb-20">
        <img src="/imgs/LogoTeam2.png" alt="team" className="w-32" />
      </div>

      <div className="flex flex-col items-center space-y-12 mb-20">

        <div className="flex justify-center w-full space-x-32 mb-10">
          <MemberBox imgName="m_jw" name="정진욱" position="팀장" />
          <MemberBox imgName="m_sm" name="김수민" position="팀원" />
          <MemberBox imgName="m1" name="김슬기" position="팀원" />
        </div>

        <div className="flex justify-center w-full space-x-32">
          <MemberBox imgName="m_jb" name="손준배" position="팀원" />
          <MemberBox imgName="m_he" name="차선호" position="팀원" />
          <MemberBox imgName="m_he3" name="황하음" position="팀원" />
        </div>

      </div>
    </div>
  );
};

export default TeamInfo;
