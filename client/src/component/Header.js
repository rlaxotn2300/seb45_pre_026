import { useState } from 'react';
import '../css/header.css';
import Logo from '../images/Logo.png';
import QuestionsLogo from '../images/questions-logo.png';

export default function Header() {
  const [loggedin, setIsLoggedin] = useState(false);

  function handleLoginClick() {
    setIsLoggedin(!loggedin);
  }

  return (
    <div className="header__container">
      <div className="header__left">
        <img className="header__logo" src={Logo} alt="로고" />
        <div>
          <span>Code</span>
          <span>Knitters</span>
        </div>
        <div>
          <img
            className="header__questions-logo"
            src={QuestionsLogo}
            alt="질문 로고"
          />
          <span>Questions</span>
        </div>
      </div>
      <div>
        {loggedin ? (
          <img alt="마이페이지" />
        ) : (
          <button onClick={handleLoginClick}></button>
        )}
        {loggedin ? (
          <button onClick={handleLoginClick}></button>
        ) : (
          <button></button>
        )}
      </div>
    </div>
  );
}
