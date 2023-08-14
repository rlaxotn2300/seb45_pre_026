import { useParams } from 'react-router-dom';
import '../css/answer.css';

export default function Answer({ questionData }) {
  let { id } = useParams();

  return (
    <div className="answer__bg">
      {questionData[id].answer.length > 1 ? (
        <div>{questionData[id].answer.length} Answers</div>
      ) : questionData[id].answer.length === 1 ? (
        <div>{questionData[id].answer.length} Answer</div>
      ) : null}
      {questionData[id].answer
        ? questionData[id].answer.map((el) => (
            <li key={el.answerId}>
              <div>{el.content}</div>
              <div>
                <div>{el.date}</div>
                <div>Answered by {el.user}</div>
              </div>
            </li>
          ))
        : null}
    </div>
  );
}
