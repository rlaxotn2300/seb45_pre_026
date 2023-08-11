import { useState } from 'react';
import Nav from '../component/Nav';
import '../css/mypage.css';
import editLogo from '../images/edit.png';

export default function MyPage() {
  const [curMenu, setCurMenu] = useState('profile');

  function handleMenuClick(e) {
    if (e.target.innerText === 'Profile') setCurMenu('profile');
    else if (e.target.innerText === 'My Questions') setCurMenu('questions');
    else if (e.target.innerText === 'Delete Account') setCurMenu('delete');
  }

  return (
    <div className="mypage__bg">
      <Nav />
      <div className="mypage__main">
        <div className="mypage__profile-top">
          <div className="mypage__name-container">
            <div className="mypage__profile-photo"></div>
            <div className="mypage__profile-name">닉네임</div>
          </div>
          <button className="mypage__edit-btn">
            <img src={editLogo} alt="닉네임 수정"></img>Change Nickname
          </button>
        </div>
        <div className="mypage__menu">
          <div
            className={
              curMenu === 'profile'
                ? 'mypage__menu-btn mypage__menu-focus'
                : 'mypage__menu-btn'
            }
            role="presentation"
            onClick={(e) => handleMenuClick(e)}
          >
            Profile
          </div>
          <div
            className={
              curMenu === 'questions'
                ? 'mypage__menu-btn mypage__menu-focus'
                : 'mypage__menu-btn'
            }
            role="presentation"
            onClick={(e) => handleMenuClick(e)}
          >
            My Questions
          </div>
          <div
            className={
              curMenu === 'delete'
                ? 'mypage__menu-btn mypage__menu-focus'
                : 'mypage__menu-btn'
            }
            role="presentation"
            onClick={(e) => handleMenuClick(e)}
          >
            Delete Account
          </div>
        </div>
      </div>
    </div>
  );
}
