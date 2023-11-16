import React from 'react';

const Center: React.FC = () => {
  const handleDownload = () => {
    const link = document.createElement('a');
    link.href = '/HappyProgrammer.zip'; // 다운로드할 zip 파일의 경로
    link.download = 'HappyProgrammer.zip'; // 다운로드되는 파일의 이름
    link.click();
  };

  return (
    <div className="flex flex-col items-center justify-center">
      <img src='/imgs/LogoBig.png' alt="Logo" className="w-64 mb-4" />
      <button className='bg-blue hover:bg-opacity-60 p-3 pl-10 pr-10 rounded' onClick={handleDownload}>
        지금 플레이
      </button>
    </div>
  );
};

export default Center;
