import Nav from '../component/Nav';
import '../css/questions.css';
import Question from '../component/Question';
import html from '../images/html.png';
import css from '../images/css.png';
import js from '../images/javascript.png';
import react from '../images/react.png';
import axios from '../images/axios.png';
import redux from '../images/redux.png';
import eslint from '../images/ESLint.png';
import prettier from '../images/prettier.png';
import java from '../images/java.png';
import spring from '../images/spring.png';
import springBoot from '../images/springboot.png';
import springSecurity from '../images/springsecurity.png';
import mysql from '../images/mysql.png';
import aws from '../images/aws.png';

export default function Questions({ curPage, setCurPage }) {
  return (
    <div>
      <div className="questions_wrap">
        <Nav curPage={curPage} setCurPage={setCurPage} />
        <div className="questions_list">
          <Question />
        </div>
        <aside className="aside">
          <h2>Official document</h2>
          <ul>
            <li>
              <a
                href="https://developer.mozilla.org/ko/docs/Web/HTML"
                target="blank"
              >
                <img src={html} alt="html"></img>
                <p>HTML</p>
              </a>
            </li>
            <li>
              <a
                href="https://developer.mozilla.org/ko/docs/Web/CSS/Reference"
                target="blank"
              >
                <img src={css} alt="css"></img>

                <p>CSS</p>
              </a>
            </li>
            <li>
              <a href="https://ko.javascript.info/" target="blank">
                <img src={js} alt="js"></img>
                <p>JavaScript</p>
              </a>
            </li>
            <li>
              <a
                href="https://ko.legacy.reactjs.org/docs/getting-started.html"
                target="blank"
              >
                <img src={react} alt="react"></img>
                <p>React</p>
              </a>
            </li>
            <li>
              <a href="https://axios-http.com/kr/docs/intro" target="blank">
                <img src={axios} alt="axios"></img>
                <p>Axios</p>
              </a>
            </li>
            <li>
              <a href="https://ko.redux.js.org/" target="blank">
                <img src={redux} alt="redux"></img>
                <p>Redux</p>
              </a>
            </li>
            <li>
              <a href="https://eslint.org/docs/latest/" target="blank">
                <img src={eslint} alt="eslint"></img>
                <p>ESLint</p>
              </a>
            </li>
            <li>
              <a href="https://prettier.io/docs/en/" target="blank">
                <img src={prettier} alt="prettier"></img>
                <p>Prettier</p>
              </a>
            </li>
            <li>
              <a
                href="https://docs.oracle.com/javase/8/docs/api/"
                target="blank"
              >
                <img src={java} alt="java"></img>
                <p>JAVA</p>
              </a>
            </li>
            <li>
              <a
                href="https://docs.spring.io/spring-framework/reference/index.html"
                target="blank"
              >
                <img src={spring} alt="spring"></img>
                <p>Spring</p>
              </a>
            </li>
            <li>
              <a
                href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/"
                target="blank"
              >
                <img src={springBoot} alt="springBoot"></img>
                <p>SpringBoot</p>
              </a>
            </li>
            <li>
              <a
                href="https://docs.spring.io/spring-security/reference/index.html"
                target="blank"
              >
                <img src={springSecurity} alt="springSecurity"></img>
                <p>SpringSecurity</p>
              </a>
            </li>
            <li>
              <a href="https://dev.mysql.com/doc/" target="blank">
                <img src={mysql} alt="mysql"></img>
                <p>MySQL</p>
              </a>
            </li>
            <li>
              <a href="https://docs.aws.amazon.com/ko_kr/" target="blank">
                <img src={aws} alt="aws"></img>
                <p>AWS</p>
              </a>
            </li>
          </ul>
        </aside>
      </div>
    </div>
  );
}
