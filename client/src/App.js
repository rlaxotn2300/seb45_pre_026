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
import QuestionRegister from './page/QuestionRegister';
import QuestionDetail from './page/QuestionDetail';
import SearchList from './page/Search_list';
import QuestionEdit from './page/QuestionEdit';
import axios from 'axios';
import { useState, useEffect } from 'react';

function App() {
  const [isData, setIsData] = useState([]);

  const getData = () => {
    return axios
      .get('http://13.124.11.238:8080/question/?page=1&size=10', {
        headers: {
          'Content-Type': `application/json`,
        },
      })
      .then((res) => {
        setIsData(res.data);
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    getData();
  }, []);
  return (
    <div className="app__body">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/tags" element={<Tags />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/companies" element={<Companies />} />
          <Route path="/login" element={<Login />} />
          <Route path="/sign_up" element={<Signup />} />
          <Route path="/questions" element={<Questions data={isData} />} />
          <Route path="/question/:id" element={<QuestionDetail />} />
          <Route path="/question_register" element={<QuestionRegister />} />
          <Route path="/search" element={<SearchList data={isData} />} />
          <Route path="/question_register/:id" element={<QuestionEdit />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
