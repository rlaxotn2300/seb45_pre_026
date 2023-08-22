import { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { setNickname, setStateEmail, setIsLogin } from '../redux/action';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Cookies } from 'react-cookie';
import '../css/mypage.css';
import Nav from '../component/Nav';
import Question from '../component/Question';
import editLogo from '../images/edit.png';
import editPassword from '../images/editPassword.png';
import cross from '../images/cross.png';
import check from '../images/check.png';
import data from '../dummydata';

const mapStateToProps = (state) => {
  return {
    stateEmail: state.stateEmail,
    nickname: state.nickname,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setStateEmail: (stateEmail) => dispatch(setStateEmail(stateEmail)),
    setNickname: (nickname) => dispatch(setNickname(nickname)),
    setIsLogin: (isLogin) => dispatch(setIsLogin(isLogin)),
  };
};

function MyPage({
  stateEmail,
  setStateEmail,
  nickname,
  setNickname,
  setIsLogin,
}) {
  const [curMenu, setCurMenu] = useState('profile');
  const [nicknameEdit, setNicknameEdit] = useState(false);
  const [newNickname, setNewNickname] = useState(nickname);
  const [passwordEdit, setPasswordEdit] = useState(false);
  const [newPassword, setNewPassword] = useState('');
  const cookies = new Cookies();
  const getCookie = cookies.get('is_login');
  const memberId = window.localStorage.getItem('memberId');
  const navigate = useNavigate();

  let filteredData = data.filter((el) => el.user === 'nickname');

  function handleMenuClick(e) {
    if (e.target.innerText === 'Profile') setCurMenu('profile');
    else if (e.target.innerText === 'My Questions') setCurMenu('questions');
    else if (e.target.innerText === 'Delete Account') setCurMenu('delete');
  }

  function handleNicknameChange() {
    axios
      .patch(
        `http://13.124.11.238:8080/member/update/${memberId}`,
        {
          name: newNickname,
        },
        {
          headers: {
            'Content-Type': `application/json`,
            'ngrok-skip-browser-warning': true,
            Authorization: getCookie,
          },
        },
      )
      .then(() => {
        setNickname(newNickname);
        setNicknameEdit(false);
      })
      .catch(() => {
        console.log('데이터 수정에 실패하였습니다.');
      });
  }

  function handlePasswordChange() {
    axios
      .patch(
        `http://13.124.11.238:8080/member/update/${memberId}`,
        {
          password: newPassword,
        },
        {
          headers: {
            'Content-Type': `application/json`,
            'ngrok-skip-browser-warning': true,
            Authorization: getCookie,
          },
        },
      )
      .then(() => {
        setPasswordEdit(false);
      })
      .catch(() => {
        console.log('데이터 수정에 실패하였습니다.');
      });
  }

  function handleDeleteClick() {
    axios
      .delete(`http://13.124.11.238:8080/member/delete/${memberId}`, {
        headers: {
          'Content-Type': `application/json`,
          Authorization: getCookie,
        },
      })
      .then(() => {
        alert('Your account has been successfully deleted.');
        navigate('/');
        setIsLogin(false);
      })
      .catch(() => alert('Error occurred. Please try again later.'));
  }

  const getUserDetail = () => {
    return axios
      .get(`http://13.124.11.238:8080/member/detail/${memberId}`, {
        headers: {
          'Content-Type': `application/json`,
        },
      })
      .then((res) => {
        setStateEmail(res.data.email);
        setNickname(res.data.name);
      })
      .catch(() => {
        console.log('데이터 로딩에 실패하였습니다.');
      });
  };

  useEffect(() => {
    getUserDetail();
  }, []);

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
                onClick={() => {
                  setNewNickname(nickname);
                  setNicknameEdit(false);
                }}
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
              onClick={() => setNicknameEdit(true)}
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
              <div className="mypage__profile-content">{stateEmail}</div>
            </div>
            <div className="mypage__profile-section">
              <div className="mypage__profile-title">Nickname</div>
              <div className="mypage__profile-content">{nickname}</div>
            </div>
            <div className="mypage__profile-section">
              <div className="mypage__profile-title">Password</div>
              <div className="mypage__password-container">
                {passwordEdit ? (
                  <input
                    type="password"
                    className="mypage__nickname-edit"
                    onChange={(e) => setNewPassword(e.target.value)}
                  />
                ) : (
                  <button
                    className="mypage__edit-btn"
                    onClick={() => setPasswordEdit(true)}
                  >
                    <img src={editPassword} alt="비밀번호 수정"></img>Change
                    Password
                  </button>
                )}
                {passwordEdit ? (
                  <div className="mypage__btn-container">
                    <button
                      className="mypage__edit"
                      onClick={handlePasswordChange}
                    >
                      <img
                        src={check}
                        alt="비밀번호 수정"
                        className="mypage__edit-img"
                      />
                      <span>Edit</span>
                    </button>
                    <button
                      className="mypage__edit"
                      onClick={() => {
                        setNewPassword('');
                        setPasswordEdit(false);
                      }}
                    >
                      {' '}
                      <img
                        src={cross}
                        alt="비밀번호 수정 취소"
                        className="mypage__edit-img"
                      />
                      <span>Cancel</span>
                    </button>
                  </div>
                ) : null}
              </div>
            </div>
          </div>
        ) : curMenu === 'questions' ? (
          <div className="mypage__questions">
            {filteredData.map((data) => (
              <li key={data.questionId} className="list">
                <Question data={data} />
              </li>
            ))}
          </div>
        ) : (
          <div className="mypage__delete">
            <div className="mypage__delete-warning">
              Do you really want to delete your account?
            </div>
            <button className="mypage__delete-btn" onClick={handleDeleteClick}>
              Delete Account
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(MyPage);
