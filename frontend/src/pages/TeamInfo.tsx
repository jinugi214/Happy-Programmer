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

        <div className="flex justify-center w-full space-x-32 mb-10 member-one">
          <MemberBox imgName="npc_jjw" name="정진욱" position="팀장, BackEnd" msg="내일의 프로그래머는 미래의 마법사입니다. 당신은 다른 사람들이 봤을 때 마법을 가지고 있는 것처럼 보일 것입니다." />
          <MemberBox imgName="npc_ksm" name="김수민" position="팀원, FrontEnd" msg="다들 프로젝트하느라 수고많으셨습니다 :)" />
          <MemberBox imgName="npc_ksg" name="김슬기" position="팀원, FrontEnd" msg="게임을 하면 이겨야지!" />
        </div>

        <div className="flex justify-center w-full space-x-32">
          <MemberBox imgName="npc_sjb" name="손준배" position="팀원, BackEnd" msg="보이지 않는 버그가 가장 무서운 법" />
          <MemberBox imgName="npc_csh" name="차선호" position="팀원, Android" msg="버그를 두드려 때려 볼 기회! 쉽지 않습니다" />
          <MemberBox imgName="npc_hhe" name="황하음" position="팀원, BackEnd" msg="1000100 1110010 1100101 1100001 1101101 1110011 100000 1100011 1101111 1101101 1100101 100000 1110100 1110010 1110101 1100101" />
        </div>

      </div>
    </div>
  );
};

export default TeamInfo;
