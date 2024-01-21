import Logo from '../images/Logo.png';
import html from '../images/html.png';
import css from '../images/css.png';
import ESLint from '../images/ESLint.png';
import Prettier from '../images/prettier.png';
import javascript from '../images/javascript.png';
import react from '../images/react.png';
import redux from '../images/redux.png';
import axios from '../images/axios.png';
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
          </ul>
        </div>
      </section>
    </div>
  );
}

export default Main;
