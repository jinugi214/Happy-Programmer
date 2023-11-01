import React from 'react'

const Center: React.FC = () => {
  return (
    <div className="flex flex-col items-center justify-center">
        <img src='/imgs/LogoBig.png' alt="Logo" className="w-64 mb-4" />
        <button className='bg-blue hover:bg-opacity-60 p-3 pl-10 pr-10 rounded'>지금 플레이</button>
    </div>
  )
}

export default Center
