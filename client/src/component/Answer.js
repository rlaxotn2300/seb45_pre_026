import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import '../css/answer.css';
import '../css/component.css';
import Vote from './Vote';

export default function Answer({ questionData }) {
  const [answerList, setAnswerList] = useState([]);
  const [answer, setAnswer] = useState('');
  const [isAnswerEmpty, setIsAnswerEmpty] = useState(false);
  let { id } = useParams();

  function handleAnswerChange(e) {
    if (e.target.value !== '') setIsAnswerEmpty(false);
    setAnswer(e.target.value);
  }

  function handleAnswerSubmit() {
    setIsAnswerEmpty(false);

    if (answer === '') setIsAnswerEmpty(true);
    console.log(questionData.answer.length);
  }

  const getDetail = () => {
    return axios
      .get(
        `https://18d6-59-8-197-35.ngrok-free.app/question/${
          Number(id) + 1
        }/answer`,
        {
          headers: {
            'Content-Type': `application/json`,
            'ngrok-skip-browser-warning': true,
          },
        },
      )
      .then((res) => {
        setAnswerList(res.data);
        console.log(res.data);
        console.log(answerList.vote.length);
      })
      .catch(() => {
        console.log('데이터 로딩에 실패하였습니다.');
      });
  };

  useEffect(() => {
    getDetail();
  }, []);

  return (
    <div className="answer__bg">
      {answerList.vote?.length > 1 ? (
        <div className="answer__title">{answerList.vote.length} Answers</div>
      ) : answerList.vote?.length === 1 ? (
        <div className="answer__title">{answerList.vote.length} Answer</div>
      ) : null}
      {answerList.vote
        ? answerList.vote.map((el) => (
            <div key={el.answerId} className="list answer__content-container">
              <div className="answer__content-wrap">
                <Vote voteNumber={el.vote} />
                <div className="answer__content">{el.content}</div>
              </div>
              <div className="answer__bottom">
                <div>
                  {el.createdAt
                    .slice(0, 19)
                    .replace(/-/g, '/')
                    .replace('T', ' ')}
                </div>
                <div>Answered by {el.memberId}</div>
              </div>
            </div>
          ))
        : null}
      <div className="answer__input-container">
        <div className="answer__input-title">Your Answer</div>
        <div className="answer__input-wrap">
          <textarea
            className={
              isAnswerEmpty
                ? 'input input-red answer__input'
                : 'input answer__input answer__input-gray'
            }
            onChange={handleAnswerChange}
          ></textarea>
          {isAnswerEmpty ? (
            <div className="answer__warning">Body is missing</div>
          ) : null}
        </div>
        <button
          className="button-dark answer__post-btn"
          onClick={handleAnswerSubmit}
        >
          Post Your Answer
        </button>
      </div>
    </div>
  );
}
