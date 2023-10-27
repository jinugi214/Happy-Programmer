import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Header: React.FC = () => {
  const location = useLocation();

  return (
    <div className="flex items-center mb-2">
      <Link to="/"><img src='/imgs/LogoSmall.png' alt="Logo" className="w-32 ml-6 mr-6 hover:opacity-80" /></Link>
      <div className='flex mt-4'>
        <Link to="/gameinfo" className={`${location.pathname === '/gameinfo' ? 'text-purple' : ''} mr-6 hover:opacity-80`}>게임정보</Link>
        <Link to="/appinfo" className={`${location.pathname === '/appinfo' ? 'text-purple' : ''} mr-6 hover:opacity-80`}>어플정보</Link>
        <Link to="/notice" className={`${location.pathname === '/notice' ? 'text-purple' : ''} mr-6 hover:opacity-80`}>공지사항</Link>
        <Link to="/teaminfo" className={`${location.pathname === '/teaminfo' ? 'text-purple' : ''} hover:opacity-80`}>GG TEAM</Link>
      </div>
    </div>
  );
};

export default Header;
