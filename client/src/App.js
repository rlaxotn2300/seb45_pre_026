import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/Header';
import Footer from './component/Footer';
import Main from './page/Main';
import Questions from './page/Questions';
import Tags from './page/Tags';
import Companies from './page/Companies';
import Login from './page/Login';
import MyPage from './page/MyPage';

import Signup from './page/Sign_up';

function App() {
  return (
    <div className="app__body">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/questions" element={<Questions />} />
          <Route path="/tags" element={<Tags />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/companies" element={<Companies />} />
          <Route path="/login" element={<Login />} />
          <Route path="/sign_up" element={<Signup />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
