import React from 'react'

const Footer: React.FC = () => {
  return (
    <div className="flex items-center justify-center flex-col mb-10">
      <img src='/imgs/LogoBig.png' alt="Logo" className="w-32 ml-6 mr-6" />
      <div>ⓒ2023 Gumi Games, Inc.</div>
    </div>
  )
}

export default Footer