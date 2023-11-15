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
          <MemberBox imgName="npc_jjw" name="정진욱" position="팀장, BackEnd" msg="정진욱" />
          <MemberBox imgName="npc_ksm" name="김수민" position="팀원, FrontEnd" msg="김수민" />
          <MemberBox imgName="npc_ksg" name="김슬기" position="팀원, FrontEnd" msg="김슬기" />
        </div>

        <div className="flex justify-center w-full space-x-32">
          <MemberBox imgName="npc_sjb" name="손준배" position="팀원, BackEnd" msg="손준배" />
          <MemberBox imgName="npc_csh" name="차선호" position="팀원, Android" msg="차선호" />
          <MemberBox imgName="npc_hhe" name="황하음" position="팀원, BackEnd" msg="하으미" />
        </div>

      </div>
    </div>
  );
};

export default TeamInfo;
