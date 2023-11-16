import React from 'react'

const App2: React.FC = () => {
  return (
    <div className="relative">
    <div className="absolute inset-0 bg-bgtwo opacity-60" />
    <div className="relative flex mr-48 justify-end">

      <div className="mt-16 mb-16 mr-24">
        <div className="text-s text-purple mt-40">프로필 확인</div>
        <div className="mt-2">현재 나의 정보를 확인해보세요.</div>
        <div className="mt-2">포인트, 마지막 저장 장소, 아이템 등을 볼 수 있어요.</div>
      </div>

      <img src="../imgs/a2.jpg" alt="img" className="mr-24 text-m mt-8 w-44"/>

    </div>
  </div>
  )
}

export default App2