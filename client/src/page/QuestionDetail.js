// import { useParams } from 'react-router-dom';
import Answer from '../component/Answer';
import { questionData } from '../dummydata';
import '../css/questionDetail.css';

export default function QuestionDetail() {
  //   let { id } = useParams();

  return (
    <div className="detail__bg">
      <Answer questionData={questionData} />
    </div>
  );
}
