import { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Cookies } from 'react-cookie';
import '../css/header.css';
import '../css/component.css';
import Logo from '../images/Logo.png';
import QuestionsLogo from '../images/questions-logo.png';
import Mypage from '../images/mypage.png';
import { connect } from 'react-redux';
import {
  setCurPage,
  setIsLogin,
  setStateEmail,
  setNickname,
} from '../redux/action';

const mapStateToProps = (state) => {
  return {
    isLogin: state.isLogin,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setCurPage: (curPage) => dispatch(setCurPage(curPage)),
    setIsLogin: (isLogin) => dispatch(setIsLogin(isLogin)),
    setStateEmail: (stateEmail) => dispatch(setStateEmail(stateEmail)),
    setNickname: (nickname) => dispatch(setNickname(nickname)),
  };
};

function Header({
  isLogin,
  setCurPage,
  setIsLogin,
  setStateEmail,
  setNickname,
}) {
  const [searchKeyword, setSearchKeyword] = useState(''); // 검색창
  const navigate = useNavigate();
  const cookies = new Cookies();
  const getCookie = cookies.get('is_login');
  const token = localStorage.getItem('token');

  const handleKeyPress = (e) => {
    if (e.key === 'Enter' && searchKeyword !== '') {
      navigate(`/search`, { state: { searchWord: searchKeyword } });
    }
  };

  const handleSearchButton = () => {
    if (searchKeyword !== '') {
      navigate(`/search`, { state: { searchWord: searchKeyword } });
    }
  };

  const handleLogoutClick = () => {
    cookies.remove('is_login');
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('memberId');
    alert('You have successfully signed out.');
    setIsLogin(false);
    setStateEmail('');
    setNickname('');
    navigate('/');
  };

  useEffect(() => {
    if (getCookie && token) {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
  }, []);

  return (
    <div className="header__container">
      <div className="header_wrap">
        <div className="header__left">
          <Link to="/" className="link header__left">
            <img className="header__logo" src={Logo} alt="로고" />
            <div className="header__name">
              <span>Code</span>
              <span className="header__name-strong">Knitters</span>
            </div>
          </Link>
          <Link to="/questions" className="link">
            <div
              className="header__questions"
              role="presentation"
              onClick={() => setCurPage('questions')}
            >
              <img
                className="header__questions-logo"
                src={QuestionsLogo}
                alt="질문 로고"
              />
              <span className="header__questions-name">Questions</span>
            </div>
          </Link>
        </div>
        {/* 검색창 */}
        <span className="search_box">
          <input
            className="search"
            type="text"
            placeholder="Search..."
            value={searchKeyword}
            onChange={(e) => setSearchKeyword(e.target.value)}
            onKeyUp={handleKeyPress}
          />
          <button onClick={handleSearchButton}>Search</button>
        </span>
        {/* 검색창 */}
        <div className="header__right">
          {isLogin ? (
            <Link to="/mypage" className="link">
              <img
                className="header__mypage"
                src={Mypage}
                alt="마이페이지"
                role="presentation"
                onClick={() => setCurPage('user')}
              />
            </Link>
          ) : (
            <Link to="/login" className="link">
              <button className="button-light header__login-btn">Log in</button>
            </Link>
          )}
          {isLogin ? (
            <button className="button-dark" onClick={handleLogoutClick}>
              Log out
            </button>
          ) : (
            <Link to="/sign_up">
              <button className="button-dark">Sign up</button>
            </Link>
          )}
        </div>
      </div>
    </div>
  );
}
export default connect(mapStateToProps, mapDispatchToProps)(Header);
