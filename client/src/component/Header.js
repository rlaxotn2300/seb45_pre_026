import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/header.css';
import '../css/component.css';
import Logo from '../images/Logo.png';
import QuestionsLogo from '../images/questions-logo.png';
import Mypage from '../images/mypage.png';
import { connect } from 'react-redux';
import { setCurPage } from '../redux/action';

const mapDispatchToProps = (dispatch) => {
  return {
    setCurPage: (curPage) => dispatch(setCurPage(curPage)),
  };
};

function Header({ setCurPage }) {
  const [loggedin, setIsLoggedin] = useState(false);

  function handleLoginClick() {
    setIsLoggedin(!loggedin);
  }

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
        <div className="header__right">
          {loggedin ? (
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
              <button
                className="button-light header__login-btn"
                onClick={handleLoginClick}
              >
                Log in
              </button>
            </Link>
          )}
          {loggedin ? (
            <button className="button-dark" onClick={handleLoginClick}>
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

export default connect(null, mapDispatchToProps)(Header);
