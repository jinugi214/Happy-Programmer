import React from 'react';
import { useLocation } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';

type LayoutChildrenProps = {
    children: React.ReactNode;
  };  

export const AppLayout = ({ children }: LayoutChildrenProps) => {
  const location = useLocation();
  const isHomePage = location.pathname === '/';
  const isAppInfoPage = location.pathname === '/appinfo';

  if (isHomePage) {
    return (
      <>
        <Header />
        {children}
      </>
    );
  } else {
    return (
      <>
        <Header />
        <div className={isAppInfoPage ? "" : "m-16"}>{children}</div>
        <Footer />
      </>
    );
  }
};
