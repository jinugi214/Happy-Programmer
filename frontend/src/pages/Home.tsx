import React from "react";
import Center from "../components/home/Center";
import "./Home.css";

const Home: React.FC = () => {
  return (
    <div
      className="relative flex items-center justify-center h-screen bg-transparent home-bg"
      style={{ height: "calc(100vh - 86px)" }}
    >
      <div className="absolute flex items-center justify-center w-full h-full mt-6">
        <Center />
      </div>
    </div>
  );
};

export default Home;
