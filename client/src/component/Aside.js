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

const Aside = () => {
  return (
    <aside className="aside">
      <h2>Official document</h2>
      <ul>
        <li>
          <a
            href="https://developer.mozilla.org/ko/docs/Web/HTML"
            target="blank"
            className="link"
          >
            <img src={html} alt="html"></img>
            <p className="aside__focus">HTML</p>
          </a>
        </li>
        <li>
          <a
            href="https://developer.mozilla.org/ko/docs/Web/CSS/Reference"
            target="blank"
            className="link"
          >
            <img src={css} alt="css"></img>

            <p className="aside__focus">CSS</p>
          </a>
        </li>
        <li>
          <a href="https://ko.javascript.info/" target="blank" className="link">
            <img src={js} alt="js"></img>
            <p className="aside__focus">JavaScript</p>
          </a>
        </li>
        <li>
          <a
            href="https://ko.legacy.reactjs.org/docs/getting-started.html"
            target="blank"
            className="link"
          >
            <img src={react} alt="react"></img>
            <p className="aside__focus">React</p>
          </a>
        </li>
        <li>
          <a
            href="https://axios-http.com/kr/docs/intro"
            target="blank"
            className="link"
          >
            <img src={axios} alt="axios"></img>
            <p className="aside__focus">Axios</p>
          </a>
        </li>
        <li>
          <a href="https://ko.redux.js.org/" target="blank" className="link">
            <img src={redux} alt="redux"></img>
            <p className="aside__focus">Redux</p>
          </a>
        </li>
        <li>
          <a
            href="https://eslint.org/docs/latest/"
            target="blank"
            className="link"
          >
            <img src={eslint} alt="eslint"></img>
            <p className="aside__focus">ESLint</p>
          </a>
        </li>
        <li>
          <a
            href="https://prettier.io/docs/en/"
            target="blank"
            className="link"
          >
            <img src={prettier} alt="prettier"></img>
            <p className="aside__focus">Prettier</p>
          </a>
        </li>
        <li>
          <a
            href="https://docs.oracle.com/javase/8/docs/api/"
            target="blank"
            className="link"
          >
            <img src={java} alt="java"></img>
            <p className="aside__focus">JAVA</p>
          </a>
        </li>
        <li>
          <a
            href="https://docs.spring.io/spring-framework/reference/index.html"
            target="blank"
            className="link"
          >
            <img src={spring} alt="spring"></img>
            <p className="aside__focus">Spring</p>
          </a>
        </li>
        <li>
          <a
            href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/"
            target="blank"
            className="link"
          >
            <img src={springBoot} alt="springBoot"></img>
            <p className="aside__focus">SpringBoot</p>
          </a>
        </li>
        <li>
          <a
            href="https://docs.spring.io/spring-security/reference/index.html"
            target="blank"
            className="link"
          >
            <img src={springSecurity} alt="springSecurity"></img>
            <p className="aside__focus">SpringSecurity</p>
          </a>
        </li>
        <li>
          <a href="https://dev.mysql.com/doc/" target="blank" className="link">
            <img src={mysql} alt="mysql"></img>
            <p className="aside__focus">MySQL</p>
          </a>
        </li>
        <li>
          <a
            href="https://docs.aws.amazon.com/ko_kr/"
            target="blank"
            className="link"
          >
            <img src={aws} alt="aws"></img>
            <p className="aside__focus">AWS</p>
          </a>
        </li>
      </ul>
    </aside>
  );
};

export default Aside;
