import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/nav.css';
import QuestionsLogoGray from '../images/questions-logo.png';
import QuestionsLogoBlack from '../images/questions-logo-black.png';
import { connect } from 'react-redux';
import { setCurPage } from '../redux/action';

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
    else if (e.target.innerText === 'Users') setCurPage('users');
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
            onKeyUp={() => 'Hello'}
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
            onKeyUp={() => 'Hello'}
          >
            Tags
          </div>
        </Link>
        <Link to="/users" className="link">
          <div
            className={
              curPage === 'users' ? 'nav__menu nav__focused' : 'nav__menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
            onKeyUp={() => 'Hello'}
          >
            Users
          </div>
        </Link>
        <Link to="/companies" className="link">
          <div
            className={
              curPage === 'companies' ? 'nav__menu nav__focused' : 'nav__menu'
            }
            role="presentation"
            onClick={(e) => handleMenuChange(e)}
            onKeyUp={() => 'Hello'}
          >
            Companies
          </div>
        </Link>
      </div>
      <div>
        <div className="nav__tab-name">TEAM</div>
        <div className="nav__team">
          <div className="nav__profile-photo"></div>
          <div className="nave__github-ID">Github ID</div>
        </div>
        <div className="nav__team">
          <div className="nav__profile-photo"></div>
          <div className="nave__github-ID">Github ID</div>
        </div>
        <div className="nav__team">
          <div className="nav__profile-photo"></div>
          <div className="nave__github-ID">Github ID</div>
        </div>
        <div className="nav__team">
          <div className="nav__profile-photo"></div>
          <div className="nave__github-ID">Github ID</div>
        </div>
        <div className="nav__team">
          <div className="nav__profile-photo"></div>
          <div className="nave__github-ID">Github ID</div>
        </div>
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Nav);
