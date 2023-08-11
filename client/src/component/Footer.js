import { useLocation } from 'react-router-dom';
import '../css/footer.css';
import WhiteLogo from '../images/Logo-white.png';

export default function Footer() {
  const locationNow = useLocation();
  if (locationNow.pathname === '/login') return null;

  return (
    <div className="footer__container">
      <div className="footer__left">
        <div className="footer__logo-container">
          <img className="footer__logo" src={WhiteLogo} alt="로고" />
          <div className="footer__name">Code Knitters</div>
        </div>
        <span className="footer__date">
          프로젝트 기간: <br /> 2023.08.04 ~ 2023.08.23
        </span>
      </div>
      <div className="footer__position">
        <span className="footer__position-title">Frontend</span>
        <div className="footer__profile-container">
          <div className="footer__profile-photo"></div>
          <span className="footer__profile-name">
            한재연
            <br />
            (팀장)
          </span>
        </div>
        <div className="footer__profile-container">
          <div className="footer__profile-photo"></div>
          <span className="footer__profile-name">김태수</span>
        </div>
      </div>
      <div className="footer__position">
        <span className="footer__position-title">Backend</span>
        <div className="footer__profile-container">
          <div className="footer__profile-photo"></div>
          <span className="footer__profile-name">
            김진아
            <br />
            (부팀장)
          </span>
        </div>
        <div className="footer__profile-container">
          <div className="footer__profile-photo"></div>
          <span className="footer__profile-name">김지원</span>
        </div>
        <div className="footer__profile-container">
          <div className="footer__profile-photo"></div>
          <span className="footer__profile-name">손승범</span>
        </div>
      </div>
    </div>
  );
}
