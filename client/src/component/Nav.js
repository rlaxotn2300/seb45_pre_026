import { useState } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { setCurPage } from '../redux/action';
import '../css/nav.css';
import '../css/component.css';
import QuestionsLogoGray from '../images/questions-logo.png';
import QuestionsLogoBlack from '../images/questions-logo-black.png';

const mapStateToProps = (state) => {
  return {
    curPage: state.curPage,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setCurPage: (curPage) => dispatch(setCurPage(curPage)),
  };
};

function Nav({ curPage, setCurPage }) {
  const [isHovering, setIsHovering] = useState(false);

  function handleMenuChange(e) {
    if (e.target.innerText === 'Tags') setCurPage('tags');
    else if (e.target.innerText === 'User') setCurPage('user');
    else if (e.target.innerText === 'Companies') setCurPage('companies');
    else setCurPage('questions');
  }

  return (
    <div className="nav__container">
      <Link to="/" className="link nav__second-container">
        <div className="nav__home">Home</div>
      </Link>
      <div>
        <div className="nav__tab-name">PUBLIC</div>
        <Link to="/questions" className="link">
          <div
            className={
              curPage === 'questions'
                ? 'nav__menu nav__questions-menu nav__focused'
                : 'nav__menu nav__questions-menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
            onMouseOver={() => setIsHovering(true)}
            onMouseOut={() => setIsHovering(false)}
            onFocus={() => 'Hello'}
            onBlur={() => 'Hello'}
          >
            <img
              src={
                isHovering || curPage === 'questions'
                  ? QuestionsLogoBlack
                  : QuestionsLogoGray
              }
              alt="질문 로고"
              className="nav__questions-logo"
            ></img>
            <span>Questions</span>
          </div>
        </Link>
        <Link to="/tags" className="link">
          <div
            className={
              curPage === 'tags' ? 'nav__menu nav__focused' : 'nav__menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
          >
            Tags
          </div>
        </Link>
        <Link to="/mypage" className="link">
          <div
            className={
              curPage === 'user' ? 'nav__menu nav__focused' : 'nav__menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
          >
            User
          </div>
        </Link>
        <Link to="/companies" className="link">
          <div
            className={
              curPage === 'companies' ? 'nav__menu nav__focused' : 'nav__menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
          >
            Companies
          </div>
        </Link>
      </div>
      <div>
        <div className="nav__tab-name">TEAM</div>
        <a href="https://github.com/Beomda" target="blank" className="link">
          <div className="nav__team">
            <div className="nav__profile-photo"></div>
            <div className="nav__github-ID">Beomda</div>
          </div>
        </a>
        <a href="https://github.com/Jess-Apr" target="blank" className="link">
          <div className="nav__team">
            <div className="nav__profile-photo"></div>
            <div className="nav__github-ID">Jess-Apr</div>
          </div>
        </a>
        <a href="https://github.com/oksu01" target="blank" className="link">
          <div className="nav__team">
            <div className="nav__profile-photo"></div>
            <div className="nav__github-ID">oksu01</div>
          </div>
        </a>
        <a
          href="https://github.com/rlaxotn2300"
          target="blank"
          className="link"
        >
          <div className="nav__team">
            <div className="nav__profile-photo"></div>
            <div className="nav__github-ID">rlaxotn2300</div>
          </div>
        </a>
        <a href="https://github.com/sahel4" target="blank" className="link">
          <div className="nav__team">
            <div className="nav__profile-photo"></div>
            <div className="nav__github-ID">sahel4</div>
          </div>
        </a>
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Nav);
