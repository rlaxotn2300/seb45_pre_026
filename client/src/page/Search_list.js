import Nav from '../component/Nav';
import { Link, useLocation } from 'react-router-dom';
import '../css/questions.css';
import Aside from '../component/Aside';
import Question from '../component/Question';
import data from '../dummydata';

export default function Search({ curPage, setCurPage }) {
  const location = useLocation();

  const keyWord = location.state.searchWord;

  return (
    <div>
      <div className="questions_wrap">
        <Nav curPage={curPage} setCurPage={setCurPage} />
        <div className="questions_list">
          <div className="list_header">
            <h2>Top Questions</h2>
            <Link to="/question_register">
              <button>Ask Question</button>
            </Link>
          </div>
          <Question
            dummyData={data.filter((data) => {
              return data.title.toLowerCase().includes(keyWord.toLowerCase());
            })}
          />
        </div>
        <Aside />
      </div>
    </div>
  );
}
