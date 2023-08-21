import Nav from '../component/Nav';
import { Link } from 'react-router-dom';
import '../css/questions.css';
import '../css/component.css';
import Aside from '../component/Aside';
import Question from '../component/Question';
// import axios from 'axios';
// import { useEffect, useState } from 'react';
// import { useInView } from 'react-intersection-observer';
import data from '../dummydata';

export default function Questions({ curPage, setCurPage }) {
  // const [isData, setIsData] = useState([]);
  // const [ref, inView] = useInView();

  // const getData = () => {
  //   return axios
  //     .get('http://localhost:5000/questionData', {
  //       // json-server --watch db.json --port 5000
  //       headers: {
  //         'Content-Type': `application/json`,
  //         'ngrok-skip-browser-warning': true,
  //       },
  //     })
  //     .then((res) => {
  //       console.log(res.data);
  //       setIsData(res.data);
  //     })
  //     .catch((err) => console.log(err));
  // };

  // useEffect(() => {
  //   getData();
  // }, []);

  // useEffect(() => {
  //   // inView가 true 일때만 실행한다.
  //   if (inView) {
  //     // console.log(inView, '무한 스크롤 요청 🎃');

  //     getData();
  //   }
  // }, [inView]);
  return (
    <div className="questions_bg">
      <Nav curPage={curPage} setCurPage={setCurPage} />
      <div className="questions_wrap">
        <div className="questions_list">
          <div className="list_header">
            <h2>Top Questions</h2>
            <Link to="/question_register">
              <button>Ask Question</button>
            </Link>
          </div>
          {data.map((data) => (
            <li key={data.questionId} className="list">
              <Link to={`/question/${data.questionId}`} className="link">
                <Question data={data} />
              </Link>
            </li>
          ))}
          {/* <div ref={ref}>안녕</div> */}
        </div>
        <Aside />
      </div>
    </div>
  );
}
