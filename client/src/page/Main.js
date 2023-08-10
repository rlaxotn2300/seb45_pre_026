import Logo from '../images/Logo.png';
import html from '../images/html.png';
import css from '../images/css.png';
import ESLint from '../images/ESLint.png';
import Prettier from '../images/prettier.png';
import javascript from '../images/javascript.png';
import react from '../images/react.png';
import redux from '../images/redux.png';
import axios from '../images/axios.png';
import java from '../images/java.png';
import spring from '../images/spring.png';
import s_boot from '../images/springboot.png';
import s_security from '../images/springsecurity.png';
import mysql from '../images/mysql.png';
import aws from '../images/aws.png';
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
              <a href="https://github.com/sahel4" target="blank">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김지원</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://github.com/oksu01" target="blank">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김진아</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://github.com/Beomda" target="blank">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>손승범</strong>
              </p>
              <p>BE</p>
            </li>
            <li>
              <a href="https://github.com/Jess-Apr" target="blank">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>한재연</strong>
              </p>
              <p>FE</p>
            </li>
            <li>
              <a href="https://github.com/rlaxotn2300" target="blank">
                <div className="img_ex"></div>
              </a>
              <p>
                <strong>김태수</strong>
              </p>
              <p>FE</p>
            </li>
          </ul>
        </div>
        <div className="Tools">
          <h2>Tools</h2>
          <ul>
            <li className="Front_end-Tools">
              <h3>Front-end</h3>
              <ul>
                <li>
                  <h4>HTML</h4>
                  <img src={html} alt="html" />
                </li>
                <li>
                  <h4>CSS</h4>
                  <img src={css} alt="css" />
                </li>
                <li>
                  <h4>Javascript</h4>
                  <img src={javascript} alt="javascript" />
                </li>
                <li>
                  <h4>React</h4>
                  <img src={react} alt="react" />
                </li>
                <li>
                  <h4>Axios</h4>
                  <img src={axios} alt="axios" />
                </li>
                <li>
                  <h4>Redux</h4>
                  <img src={redux} alt="redux" />
                </li>
                <li>
                  <h4>ESLint</h4>
                  <img src={ESLint} alt="ESLint" />
                </li>
                <li>
                  <h4>Prettier</h4>
                  <img src={Prettier} alt="Prettier" />
                </li>
              </ul>
            </li>
            <li className="Back_end-Tools">
              <h3>Back_end</h3>
              <ul>
                <li>
                  <h4>Java</h4>
                  <img src={java} alt="java" />
                </li>
                <li>
                  <h4>Spring</h4>
                  <img src={spring} alt="spring" />
                </li>
                <li>
                  <h4>Spring Boot</h4>
                  <img src={s_boot} alt="springboot" />
                </li>
                <li>
                  <h4>Spring Security</h4>
                  <img src={s_security} alt="spring security" />
                </li>
                <li>
                  <h4>MySQL</h4>
                  <img src={mysql} alt="My SQL" />
                </li>
                <li>
                  <h4>AWS</h4>
                  <img src={aws} alt="AWS" />
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </section>
    </div>
  );
}

export default Main;
