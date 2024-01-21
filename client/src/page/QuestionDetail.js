import { useParams, Link } from 'react-router-dom';
import Answer from '../component/Answer';
import Nav from '../component/Nav';
import Aside from '../component/Aside';
import '../css/questionDetail.css';
import edit from '../images/edit.png';
import Vote from '../component/Vote';
import { questionData } from '../dummydata';

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
              <Link
                to={`/question_register/${questionData[id].questionId}`}
                className="link"
              >
                <button className="detail__btn">
                  <img alt="질문 수정" src={edit} />
                  <span>Edit</span>
                </button>
              </Link>
            </div>
          </div>
          <div className="detail__date">Asked</div>
        </div>
        <div className="detail__content-wrap">
          <div className="detail__content-container">
            <div className="detail__content-main">
              <Vote voteNumber={questionData[id].vote} />
              <div
                className="detail__content"
                dangerouslySetInnerHTML={{ __html: questionData[id].content }}
              ></div>
            </div>
            <div className="detail__profile-wrap">
              <div className="detail__profile-container">
                <div className="detail__profile-photo"></div>
                <div className="detail__profile-name">
                  <span className="detail__profile-name-gray">asked</span>
                  <span className="detail__profile-name-blue">
                    {questionData[id].memberId}
                  </span>
                </div>
              </div>
            </div>
            <Answer questionData={questionData[id]} />
          </div>
          <Aside />
        </div>
      </div>
    </div>
  );
}
