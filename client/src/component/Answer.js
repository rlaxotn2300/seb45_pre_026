import { useParams } from 'react-router-dom';
import '../css/answer.css';
import '../css/component.css';
import Vote from './Vote';
import { questionData } from '../dummydata';

export default function Answer() {
  // const [answerList, setAnswerList] = useState(questionData[id].questionData);
  // const [answer, setAnswer] = useState('');
  // const [isAnswerEmpty, setIsAnswerEmpty] = useState(false);
  // const getCookie = cookies.get('is_login');
  // const memberId = window.localStorage.getItem('memberId');
  const { id } = useParams();
  // function handleAnswerChange(e) {
  //   if (e.target.value !== '') setIsAnswerEmpty(false);
  //   setAnswer(e.target.value);
  // }

  // function handleAnswerSubmit() {
  //   setIsAnswerEmpty(false);

  //   if (answer === '') setIsAnswerEmpty(true);

  //   if (!isAnswerEmpty) {
  //     answerRegister();
  //     window.location.reload(true);
  //   }
  // }

  // const getDetail = () => {
  //   return axios
  //     .get(`http://13.124.11.238:8080/question/${Number(id)}/answer`, {
  //       headers: {
  //         'Content-Type': `application/json`,
  //       },
  //     })
  //     .then((res) => {
  //       setAnswerList(res.data);
  //     })
  //     .catch(() => {
  //       console.log('데이터 로딩에 실패하였습니다.');
  //     });
  // };

  // useEffect(() => {
  //   getDetail();
  // }, []);

  // const answerRegister = () => {
  //   return axios
  //     .post(
  //       `http://13.124.11.238:8080/question/${id}?memberId=${memberId}`,
  //       {
  //         content: answer,
  //       },
  //       {
  //         headers: {
  //           'Content-Type': `application/json`,
  //           Authorization: getCookie,
  //         },
  //       },
  //     )
  //     .then((res) => {
  //       console.log(res.data);
  //     })
  //     .catch(() => {
  //       alert('Something went wrong. Please try again.');
  //     });
  // };

  return (
    <div className="answer__bg">
      {questionData[id].answer.length > 1 ? (
        <div className="answer__title">
          {questionData[id].answer.length} Answers
        </div>
      ) : questionData[id].answer.length === 1 ? (
        <div className="answer__title">
          {questionData[id].answer.length} Answer
        </div>
      ) : null}
      {questionData[id].answer
        ? questionData[id].answer.map((el) => (
            <div key={el.answerId} className="list answer__content-container">
              <div className="answer__content-wrap">
                <div className="answer__vote-content-wrap">
                  <Vote voteNumber={el.vote} />
                  <div className="answer__content">{el.content}</div>
                </div>
                <button className="detail__btn">Delete</button>
              </div>
              <div className="answer__bottom">
                <div>
                  {el.date.slice(0, 19).replace(/-/g, '/').replace('T', ' ')}
                </div>
                <div>Answered by {el.memberId}</div>
              </div>
            </div>
          ))
        : null}
      <div className="answer__input-container">
        <div className="answer__input-title">Your Answer</div>
        <div className="answer__input-wrap">
          {/* <textarea
          className={
            isAnswerEmpty
              ? 'input input-red answer__input'
              : 'input answer__input answer__input-gray'
          }
          ></textarea>
          {isAnswerEmpty ? (
            <div className="answer__warning">Body is missing</div>
          ) : null} */}
        </div>
        <button className="button-dark answer__post-btn">
          Post Your Answer
        </button>
      </div>
    </div>
  );
}
