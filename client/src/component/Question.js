import '../css/question.css';
import axios from 'axios';
import { useEffect, useState } from 'react';

function Question({ data, questionId }) {
  let contentWithNoSpace = data.content
    .replace(/<p>/g, '')
    .replace(/<\/p>/g, '');

  const [answer, setAnswer] = useState([]);
  const TestApiCall = async () => {
    try {
      const res = await axios.get(
        `https://18d6-59-8-197-35.ngrok-free.app/question/${questionId}/answer`,
        {
          headers: {
            'Content-Type': `application/json`,
            'ngrok-skip-browser-warning': true,
          },
        },
      );
      setAnswer(res.data);
    } catch (err) {
      console.log('Error >>', err);
    }
  };
  useEffect(() => {
    TestApiCall();
    console.log(answer);
  }, []);

  // const answer_filter = answer.vote?.filter((el) => {
  //   return el.questionId === data.questionId;
  // });

  return (
    <div className="question__container" key={data.questionId}>
      <div className="question__side">
        <div>{data.vote} votes</div>
        <div
          className={
            answer?.length === 0 ? 'quesiton__no-answer' : 'question__answer'
          }
        >
          {answer.vote?.length} answers
        </div>
      </div>
      <div className="question__main">
        <div className="question__content-container">
          <div className="question__title">{data.title}</div>
          <div className="question__content">{contentWithNoSpace}</div>
        </div>
        <div className="question__bottom">
          <div className="tag_box">
            {data.tags.map((el) => (
              <span key={el}>{el}</span>
            ))}
          </div>
          <div className="question__author">
            <div className="question__author-photo"></div>
            <div>{data.memberId}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Question;
