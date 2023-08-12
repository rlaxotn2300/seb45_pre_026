import { useState } from 'react';
import { connect } from 'react-redux';
import { setEmail, setNickname, setPassword } from '../redux/action';
import '../css/mypage.css';
import Nav from '../component/Nav';
import Question from '../component/Question';
import editLogo from '../images/edit.png';
import editPassword from '../images/editPassword.png';

const mapStateToProps = (state) => {
  return {
    email: state.email,
    nickname: state.nickname,
    password: state.password,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setEmail: (email) => dispatch(setEmail(email)),
    setNickname: (nickname) => dispatch(setNickname(nickname)),
    setPassword: (password) => dispatch(setPassword(password)),
  };
};

function MyPage({ email, nickname }) {
  const [curMenu, setCurMenu] = useState('profile');
  const [nicknameEdit, setNicknameEdit] = useState(false);
  // const [passwordEdit, setPasswordEdit] = useState(false);

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
            {nicknameEdit ? (
              <input type="text" className="mypage__nickname-edit" />
            ) : (
              <div className="mypage__profile-name">{nickname}</div>
            )}
          </div>
          <button
            className="mypage__edit-btn"
            onClick={() => setNicknameEdit(!nicknameEdit)}
          >
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
        {curMenu === 'profile' ? (
          <div className="mypage__profile">
            <div className="mypage__profile-section">
              <div className="mypage__profile-title">Email</div>
              <div className="mypage__profile-content">{email}</div>
            </div>
            <div className="mypage__profile-section">
              <div className="mypage__profile-title">Nickname</div>
              <div className="mypage__profile-content">{nickname}</div>
            </div>
            <div className="mypage__profile-section">
              <div className="mypage__profile-title">Password</div>
              <button className="mypage__edit-btn">
                <img src={editPassword} alt="비밀번호 수정"></img>Change
                Password
              </button>
            </div>
          </div>
        ) : curMenu === 'questions' ? (
          <div className="mypage__questions">
            <Question />
          </div>
        ) : (
          <div>Delete Account</div>
        )}
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(MyPage);
