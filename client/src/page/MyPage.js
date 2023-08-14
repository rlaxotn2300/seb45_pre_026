import { useState } from 'react';
import { connect } from 'react-redux';
import { setEmail, setNickname, setPassword } from '../redux/action';
import '../css/mypage.css';
import Nav from '../component/Nav';
import Question from '../component/Question';
import editLogo from '../images/edit.png';
import editPassword from '../images/editPassword.png';
import cross from '../images/cross.png';
import check from '../images/check.png';
import { questionData } from '../dummydata';

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

function MyPage({ email, nickname, setNickname }) {
  const [curMenu, setCurMenu] = useState('profile');
  const [nicknameEdit, setNicknameEdit] = useState(false);
  const [newNickname, setNewNickname] = useState(nickname);
  // const [passwordEdit, setPasswordEdit] = useState(false);

  let dummyData = questionData.filter((el) => el.user === 'nickname');

  function handleMenuClick(e) {
    if (e.target.innerText === 'Profile') setCurMenu('profile');
    else if (e.target.innerText === 'My Questions') setCurMenu('questions');
    else if (e.target.innerText === 'Delete Account') setCurMenu('delete');
  }

  function handleNicknameChange() {
    setNickname(newNickname);
    setNicknameEdit(false);
  }

  return (
    <div className="mypage__bg">
      <Nav />
      <div className="mypage__main">
        <div className="mypage__profile-top">
          <div className="mypage__name-container">
            <div className="mypage__profile-photo"></div>
            {nicknameEdit ? (
              <input
                type="text"
                className="mypage__nickname-edit"
                value={newNickname}
                onChange={(e) => setNewNickname(e.target.value)}
              />
            ) : (
              <div className="mypage__profile-name">{nickname}</div>
            )}
          </div>
          {nicknameEdit ? (
            <div className="mypage__btn-container">
              <button className="mypage__edit" onClick={handleNicknameChange}>
                <img
                  src={check}
                  alt="닉네임 수정"
                  className="mypage__edit-img"
                />
                <span>Edit</span>
              </button>
              <button
                className="mypage__edit"
                onClick={() => setNicknameEdit(false)}
              >
                {' '}
                <img
                  src={cross}
                  alt="닉네임 수정 취소"
                  className="mypage__edit-img"
                />
                <span>Cancel</span>
              </button>
            </div>
          ) : (
            <button
              className="mypage__edit-btn"
              onClick={() => setNicknameEdit(!nicknameEdit)}
            >
              <img src={editLogo} alt="닉네임 수정"></img>Change Nickname
            </button>
          )}
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
            <Question dummyData={dummyData} />
          </div>
        ) : (
          <div className="mypage__delete">
            <div className="mypage__delete-warning">
              Do you really want to delete your account?
            </div>
            <button className="mypage__delete-btn">Delete Account</button>
          </div>
        )}
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(MyPage);
