import { useNavigate, Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Answer from '../component/Answer';
import Nav from '../component/Nav';
import Aside from '../component/Aside';
import '../css/questionDetail.css';
import edit from '../images/edit.png';
import Vote from '../component/Vote';

export default function QuestionDetail() {
  let { id } = useParams();
  const navigate = useNavigate();
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:5000/questionData?questionId=${id}`)
      .then((res) => {
        setData(res.data);
        console.log(res.data);
      })
      .catch(() => {
        console.log('데이터 로딩에 실패하였습니다.');
      });
  }, []);

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
            <div className="detail__title">{data?.title}</div>
            <div className="detail__btn-container">
              <button className="detail__btn" onClick={handleDeleteClick}>
                Delete
              </button>
              <Link
                to={`/question_register/${data.questionId}`}
                className="link"
              >
                <button className="detail__btn">
                  <img alt="질문 수정" src={edit} />
                  <span>Edit</span>
                </button>
              </Link>
            </div>
          </div>
          <div className="detail__date">Asked {data.date}</div>
        </div>
        <div className="detail__content-wrap">
          <div className="detail__content-container">
            <div className="detail__content-main">
              <Vote voteNumber={data.vote} />
              <div
                className="detail__content"
                dangerouslySetInnerHTML={{ __html: data.content }}
              ></div>
            </div>
            <div className="detail__profile-wrap">
              <div className="detail__profile-container">
                <div className="detail__profile-photo"></div>
                <div className="detail__profile-name">
                  <span className="detail__profile-name-gray">asked</span>
                  <span className="detail__profile-name-blue">{data.user}</span>
                </div>
              </div>
            </div>
            <Answer questionData={data} />
          </div>
          <Aside />
        </div>
      </div>
    </div>
  );
}
