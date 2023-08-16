import { useParams } from 'react-router-dom';
import Answer from '../component/Answer';
import Nav from '../component/Nav';
import Aside from '../component/Aside';
import { questionData } from '../dummydata';
import '../css/questionDetail.css';
import edit from '../images/edit.png';

export default function QuestionDetail() {
  let { id } = useParams();

  return (
    <div className="detail__bg">
      <Nav />
      <div className="detail__main">
        <div className="detail__top">
          <div className="detail__title-container">
            <div className="detail__title">{questionData[id].title}</div>
            <div className="detail__btn-container">
              <button className="detail__btn">Delete</button>
              <button className="detail__btn">
                <img alt="질문 수정" src={edit} />
                <span>Edit</span>
              </button>
            </div>
          </div>
          <div>Asked {questionData[id].date}</div>
        </div>
        <div className="detail__content-wrap">
          <div className="detail__content-container">
            <div>{questionData[id].content}</div>
            <div>
              <div></div>
              <div>{questionData[id].user}</div>
            </div>
            <Answer questionData={questionData} />
          </div>
          <Aside />
        </div>
      </div>
    </div>
  );
}
