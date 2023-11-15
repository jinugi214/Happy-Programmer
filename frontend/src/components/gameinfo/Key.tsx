import React from 'react'

const Key: React.FC = () => {
  return (
    <div className='mb-48 flex'>

      <div className='mr-24 text-m mt-1'>조작법</div>

      <div className='mr-10 text-s'>
        <div className='mt-2'>이동</div>
        <div className='mt-2'>상호작용</div>
        <div className='mt-2'>인벤토리</div>
        <div className='mt-2'>공격</div>
        <div className='mt-2'>스킬</div>
        <div className='mt-2'>설정</div>
        <div className='mt-2'>게임 저장</div>
      </div>

      <div className='text-s'>
        <div className='mt-2'>WASD , ← → ↓ ↑</div>
        <div className='mt-2'>SpaceBar</div>
        <div className='mt-2'>I</div>
        <div className='mt-2'>X</div>
        <div className='mt-2'>Z</div>
        <div className='mt-2'>Esc</div>
        <div className='mt-2'>F5</div>
      </div>

    </div>
  )
}

export default Key