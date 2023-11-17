import React from 'react'

const App4: React.FC = () => {
  return (
    <div className="relative">
    <div className="absolute inset-0 bg-bgtwo opacity-60" />
    <div className="relative flex mr-32 justify-end">

      <div className="mt-16 mb-16 mr-32">
        <div className="text-s text-purple mt-40">검색 및 상세화면</div>
        <div className="mt-2">도감에서 원하는 것을 검색해보세요.</div>
        <div className="mt-2">클릭하면 상세정보도 확인이 가능합니다.</div>
      </div>

      <img src="../imgs/a6.jpg" alt="img" className="mr-12 text-m mt-8 w-44"/>
      <img src="../imgs/a7.jpg" alt="img" className="mr-12 text-m mt-8 w-44"/>

    </div>
  </div>
  )
}

export default App4