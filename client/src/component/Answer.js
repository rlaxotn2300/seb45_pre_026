import { useParams } from 'react-router-dom';
import '../css/answer.css';
import '../css/component.css';

export default function Answer({ questionData }) {
  let { id } = useParams();

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
              <div className="answer__content">{el.content}</div>
              <div className="answer__bottom">
                <div>{el.date}</div>
                <div>Answered by {el.user}</div>
              </div>
            </div>
          ))
        : null}
      <div className="answer__input-container">
        <div className="answer__input-title">Your Answer</div>
        <textarea className="input answer__input"></textarea>
        <button className="button-dark answer__post-btn">
          Post Your Answer
        </button>
      </div>
    </div>
  );
}
