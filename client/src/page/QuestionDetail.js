import { useParams, useNavigate } from 'react-router-dom';
import Answer from '../component/Answer';
import Nav from '../component/Nav';
import Aside from '../component/Aside';
import { questionData } from '../dummydata';
import '../css/questionDetail.css';
import edit from '../images/edit.png';

export default function QuestionDetail() {
  let { id } = useParams();
  const navigate = useNavigate();

  function handleDeleteClick() {
    if (
      window.confirm(
        'Do you really want to delete this question? This cannot be undone.',
      )
    ) {
      navigate('/questions');
    }
  }

  return (
    <div className="detail__bg">
      <Nav />
      <div className="detail__main">
        <div className="detail__top">
          <div className="detail__title-container">
            <div className="detail__title">{questionData[id].title}</div>
            <div className="detail__btn-container">
              <button className="detail__btn" onClick={handleDeleteClick}>
                Delete
              </button>
              <button className="detail__btn">
                <img alt="질문 수정" src={edit} />
                <span>Edit</span>
              </button>
            </div>
          </div>
          <div className="detail__date">Asked {questionData[id].date}</div>
        </div>
        <div className="detail__content-wrap">
          <div className="detail__content-container">
            <div
              dangerouslySetInnerHTML={{ __html: questionData[id].content }}
            ></div>
            <div className="detail__profile-wrap">
              <div className="detail__profile-container">
                <div className="detail__profile-photo"></div>
                <div className="detail__profile-name">
                  <span className="detail__profile-name-gray">asked</span>
                  <span className="detail__profile-name-blue">
                    {questionData[id].user}
                  </span>
                </div>
              </div>
            </div>
            <Answer questionData={questionData} />
          </div>
          <Aside />
        </div>
      </div>
    </div>
  );
}
