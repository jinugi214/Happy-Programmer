import React from 'react';
import Key from '../components/gameinfo/Key';
import Story from '../components/gameinfo/Story';
// import Screen from '../components/gameinfo/Screen';

const GameInfo: React.FC = () => {
  return (
    <div>
      <div className='text-center text-s mt-24 mb-32'>
        <div><span className='text-purple'>HAPPY PROGRAMMER</span> 에 오신 것을 환영합니다.</div>
        <div>주인공이 행복 코딩을 할 수 있도록 도와주세요.</div>
      </div>

      <Story/>
      <Key/>
      {/* <Screen/> */}
    </div>
  )
}

export default GameInfo