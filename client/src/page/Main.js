import Logo from '../images/Logo.png';
import '../css/main.css';

function Main() {
  return (
    <div className="Main_container">
      <div className="head">
        <img src={Logo} alt="Logo" className="Logo" />
        <p>CodeStates Pre-Project TEAM 26</p>
        <h1>Code Knitters</h1>
      </div>
      <section>
        <div className="members">
          <h2>Members</h2>
          <ul>
            <li>
              <a href="https://github.com/sahel4">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김지원</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://www.naver.com/">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김진아</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://www.naver.com/">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>손승범</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://github.com/Jess-Apr">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>한재연</strong>
              </p>
              <p>FE</p>
            </li>
            <li>
              <a href="https://github.com/rlaxotn2300">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김태수</strong>
              </p>
              <p>FE</p>
            </li>
          </ul>
        </div>
        <content className="Tools">
          <h2>Tools</h2>
        </content>
      </section>
    </div>
  );
}

export default Main;
