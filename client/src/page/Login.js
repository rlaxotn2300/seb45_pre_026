import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Cookies } from 'react-cookie';
import axios from 'axios';
import '../css/login.css';
import { connect } from 'react-redux';
import { setIsLogin } from '../redux/action';
import base64 from 'base-64';

const mapDispatchToProps = (dispatch) => {
  return {
    setIsLogin: (isLogin) => dispatch(setIsLogin(isLogin)),
  };
};

function Login({ setIsLogin }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [emailEmpty, setEmailEmpty] = useState(false);
  const [passwordEmpty, setPasswordEmpty] = useState(false);
  const [emailValidity, setEmailValidity] = useState(true);
  const cookies = new Cookies();
  const navigate = useNavigate();

  function handleEmailInputChange(e) {
    setEmail(e.target.value);
  }

  function handlePasswordInputChange(e) {
    setPassword(e.target.value);
  }

  function handleLoginSubmit() {
    let validEmail = /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9-]+/;
    setEmailEmpty(false);
    setPasswordEmpty(false);
    setEmailValidity(true);

    if (email === '') setEmailEmpty(true);
    else if (!validEmail.test(email)) setEmailValidity(false);

    if (password === '') setPasswordEmpty(true);

    if (!emailEmpty && !passwordEmpty && emailValidity) {
      loginDb();
    }
  }

  const loginDb = () => {
    return axios
      .post(
        `https://18d6-59-8-197-35.ngrok-free.app/member/login`,
        {
          username: email,
          password: password,
        },
        { headers: { 'Content-Type': 'application/json' } },
      )
      .then((res) => {
        console.log(res);
        setIsLogin(true);

        const accessToken = res.headers.authorization;
        cookies.set('is_login', `${accessToken}`);
        let payload = accessToken.substring(
          accessToken.indexOf('.') + 1,
          accessToken.lastIndexOf('.'),
        );
        let dec = JSON.parse(base64.decode(payload));
        window.localStorage.setItem('memberId', dec.memberId);

        navigate('/');
      })
      .catch(() =>
        alert('There are no matching user information. Please try again.'),
      );
  };

  return (
    <div className="login__bg">
      <div className="login__container">
        <div className="login__input-container">
          <div className="login__title">Email</div>
          <input
            type="email"
            className={
              emailEmpty || emailValidity === false
                ? 'login__input login__input-red'
                : 'login__input'
            }
            onChange={(e) => handleEmailInputChange(e)}
          ></input>
          {emailEmpty ? (
            <span className="login__warning">Email cannot be empty.</span>
          ) : emailValidity === false ? (
            <span className="login__warning">
              The email is not a valid email address.
            </span>
          ) : null}
        </div>
        <div className="login__input-container">
          <div className="login__title-container">
            <div className="login__title">Password</div>
            <a href="/" className="login__link">
              Forgot password?
            </a>
          </div>
          <input
            type="password"
            className={
              passwordEmpty ? 'login__input login__input-red' : 'login__input'
            }
            onChange={(e) => handlePasswordInputChange(e)}
          ></input>
          {passwordEmpty ? (
            <span className="login__warning">Password cannot be empty.</span>
          ) : null}
        </div>
        <button
          className="button-dark login__btn-long"
          onClick={handleLoginSubmit}
        >
          Log in
        </button>
      </div>
      <div className="login__account">
        <span className="login__account-msg">Don&apos;t have an account?</span>
        <Link to="/sign_up" className="login__link">
          Sign up
        </Link>
      </div>
    </div>
  );
}

export default connect(null, mapDispatchToProps)(Login);
