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
    if (keyWord.includes('[')) {
      const tagWord = keyWord.replace(/\[/g, '').replace(/\]/g, '');
      return el.tag.includes(tagWord.toLowerCase());
    }
    return (
      el.title.toLowerCase().includes(keyWord.toLowerCase()) ||
      el.content.toLowerCase().includes(keyWord.toLowerCase())
    );
  });

  return (
    <div>
      <div className="search_wrap">
        <Nav curPage={curPage} setCurPage={setCurPage} />
        <div className="questions_list search_list">
          <div className="list_header">
            <h2>Search Top Questions</h2>
            <Link to="/question_register">
              <button>Ask Question</button>
            </Link>
          </div>
          {search_data.length >= 1 ? (
            search_data.map((data) => (
              <li key={data.questionId} className="list">
                <Link to={`/question/${data.questionId}`} className="link">
                  <Question data={data} />
                </Link>
              </li>
            ))
          ) : (
            <p className="none_Search">No Search found!</p>
          )}
        </div>
        <Aside />
      </div>
    </div>
  );
}
