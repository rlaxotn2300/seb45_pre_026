import { useParams } from 'react-router-dom';
import '../css/answer.css';

export default function Answer({ questionData }) {
  let { id } = useParams();

  return (
    <div className="answer__bg">
      <div>{questionData[id].content}</div>
    </div>
  );
}
