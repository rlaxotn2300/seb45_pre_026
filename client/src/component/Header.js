import { useState } from 'react';
import '../css/header.css';

export default function Header() {
  const [loggedin, setIsLoggedin] = useState(false);

  function handleLoginClick() {
    setIsLoggedin(!loggedin);
  }

  return (
    <div className="header__container">
      <div>
        <img alt="로고" />
        <div>
          <span></span>
          <span></span>
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
