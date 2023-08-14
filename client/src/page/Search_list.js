import Nav from '../component/Nav';
import { Link, useLocation } from 'react-router-dom';
import '../css/questions.css';
import Aside from '../component/Aside';
import Question from '../component/Question';
import data from '../dummydata';

export default function Search({ curPage, setCurPage }) {
  const location = useLocation();

  const keyWord = location.state.searchWord;

  const search_data = data.filter((el) => {
    return (
      el.title.toLowerCase().includes(keyWord.toLowerCase()) ||
      el.content.toLowerCase().includes(keyWord.toLowerCase())
    );
  });

  return (
    <div>
      <div className="questions_wrap">
        <Nav curPage={curPage} setCurPage={setCurPage} />
        <div className="questions_list">
          <div className="list_header">
            <h2>Search Top Questions</h2>
            <Link to="/question_register">
              <button>Ask Question</button>
            </Link>
          </div>
          {search_data.map((data) => (
            <li key={data.questionId} className="list">
              <Link to={`/question/${data.questionId}`} className="link">
                <Question data={data} />
              </Link>
            </li>
          ))}
        </div>
        <Aside />
      </div>
    </div>
  );
}
