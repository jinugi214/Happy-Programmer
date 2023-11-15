import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AppLayout } from "./layouts/AppLayout";
import Home from "./pages/Home";
import GameInfo from "./pages/GameInfo";
import AppInfo from "./pages/AppInfo";
import TeamInfo from "./pages/TeamInfo";

import Notice from "./pages/Notice";
import Notice1 from "./pages/NoticeDetail/Notice1";
import Notice2 from "./pages/NoticeDetail/Notice2";
import Notice3 from "./pages/NoticeDetail/Notice3";

function App() {
  return (
    <BrowserRouter>
      <AppLayout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/gameinfo" element={<GameInfo />} />
          <Route path="/appinfo" element={<AppInfo />} />
          <Route path="/teaminfo" element={<TeamInfo />} />

          <Route path="/notice" element={<Notice />} />
          <Route path="/notice/1" element={<Notice1 />} />
          <Route path="/notice/2" element={<Notice2 />} />
          <Route path="/notice/3" element={<Notice3 />} />
        </Routes>
      </AppLayout>
    </BrowserRouter>
  );
}

export default App;
