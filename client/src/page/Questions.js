import Nav from '../component/Nav';
import { Link } from 'react-router-dom';
import '../css/questions.css';
import '../css/component.css';
import Aside from '../component/Aside';
import Question from '../component/Question';

export default function Questions({ curPage, setCurPage, data }) {
  // const [answer, setAnswer] = useState('');
  // const TestApiCall = async () => {
  //   try {
  //     const res = await axios.get(
  //       'https://18d6-59-8-197-35.ngrok-free.app/question/1/answer',
  //       {
  //         headers: {
  //           'Content-Type': `application/json`,
  //           'ngrok-skip-browser-warning': true,
  //         },
  //       },
  //     );
  //     setAnswer(res.data);
  //   } catch (err) {
  //     console.log('Error >>', err);
  //   }
  // };

  // useEffect(() => {
  //   TestApiCall();
  // }, []);

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
                <Question data={data} questionId={data.questionId} />
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
