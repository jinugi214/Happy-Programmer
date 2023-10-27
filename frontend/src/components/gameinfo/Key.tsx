import React from 'react'

const Key: React.FC = () => {
  return (
    <div className='mb-48 flex'>

      <div className='mr-24 text-m mt-1'>조작법</div>

      <div className='mr-10 text-s'>
        <div className='mt-2'>이동</div>
        <div className='mt-2'>상호작용</div>
      </div>

      <div className='text-s'>
        <div className='mt-2'>← → ↓ ↑</div>
        <div className='mt-2'>SpaceBar</div>
      </div>

    </div>
  )
}

export default Key