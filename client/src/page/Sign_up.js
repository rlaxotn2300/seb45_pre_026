import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import questions from '../images/quesion.png';
import commenting from '../images/commention.png';
import tags from '../images/tags.png';
import earn from '../images/earn.png';
import robot from '../images/robot_check.png';
import '../css/sign_up.css';

export default function Sign_up() {
  const [displayname, setDisplayname] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [checked, setChecked] = useState(false);
  const navigate = useNavigate();

  const handledisplaynameChange = (event) => {
    setDisplayname(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleRobotChecked = () => {
    setChecked(!checked);
  };

  const isdisplaynameValid = (displayname) => {
    return displayname.length >= 3;
  };

  const isEmailValid = (email) => {
    // 간단한 이메일 유효성 검사
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  };

  const isPasswordValid = (password) => {
    return password.length >= 6;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      isdisplaynameValid(displayname) &&
      isEmailValid(email) &&
      isPasswordValid(password) &&
      checked
    ) {
      axios
        .post(`http://13.124.11.238:8080/member/join`, {
          email: email,
          name: displayname,
          password: password,
        })
        .then(() => {
          navigate('/login');
          window.alert('Your signup process has been successfully done!');
        })
        .catch(() => {
          window.alert('Failed to signup. Please try again.');
        });
    } else if (
      isdisplaynameValid(displayname) &&
      isEmailValid(email) &&
      isPasswordValid(password) &&
      !checked
    ) {
      alert('당신은 로봇입니까?');
    } else if (
      !isdisplaynameValid(displayname) ||
      !isEmailValid(email) ||
      !isPasswordValid(password)
    ) {
      alert('입력이 유효하지 않습니다.');
    }
  };

  return (
    <div>
      <div className="sign_box">
        <div className="characteristic">
          <h2>Join the Code Knitters community</h2>
          <ul>
            <li>
              <img src={questions} alt="questions" />
              <p>Get unstuck - ask a question</p>
            </li>
            <li>
              <img src={commenting} alt="questions" />
              <p>Unlock new privileges like voting and commenting</p>
            </li>
            <li>
              <img src={tags} alt="questions" />
              <p>Save your favorite questions, answers, watch tags, and more</p>
            </li>
            <li>
              <img src={earn} alt="questions" />
              <p>Earn reputation and badges</p>
            </li>
            <li>
              <p className="last_font">
                <strong>
                  Collaborate and share knowledge with a private group for FREE
                </strong>
              </p>
            </li>
          </ul>
        </div>
        <div className="sign_form">
          <form>
            <p>Display name</p>
            <input
              type="text"
              name="name"
              value={displayname}
              onChange={handledisplaynameChange}
            />
            {!isdisplaynameValid(displayname) && (
              <span>닉네임은 최소 3글자 이상이어야 합니다.</span>
            )}
            <p>Email</p>
            <input type="text" name="Email" onChange={handleEmailChange} />
            {!isEmailValid(email) && (
              <span>유효한 이메일 주소를 입력하세요.</span>
            )}
            <p>Password</p>
            <input
              type="password"
              name="pasword"
              onChange={handlePasswordChange}
            />
            {!isPasswordValid(password) && (
              <span>비밀번호는 최소 6글자 이상이어야 합니다.</span>
            )}
            <p className="password_text">
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number
            </p>
            <div className="robot_checked">
              <div>
                <input
                  type="checkbox"
                  name="robot"
                  onClick={handleRobotChecked}
                />
                <p>{`I'm not a robot`}</p>
                <img src={robot} alt="robot_checked" />
              </div>
            </div>
            <button
              type="submit"
              value="Submit"
              onClick={(e) => handleSubmit(e)}
              className="submit_button"
            >
              Sign up
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
