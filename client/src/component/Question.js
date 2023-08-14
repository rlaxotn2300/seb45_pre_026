import '../css/question.css';
import { questionData } from '../dummydata';

function Question() {
  const dummyData = questionData;

  return (
    <div>
      {dummyData.map((data) => (
        <div className="question__container" key={data.questionId}>
          <div className="question__side">
            <div>{data.vote} votes</div>
            <div
              className={
                data.answer.length === 0
                  ? 'quesiton__no-answer'
                  : 'question__answer'
              }
            >
              {data.answer.length} answers
            </div>
          </div>
          <div className="question__main">
            <div className="question__content-container">
              <div className="question__title">{data.title}</div>
              <div className="question__content">{data.content}</div>
            </div>
            <div className="question__bottom">
              <div>{data.date}</div>
              <div className="question__author">
                <div className="question__author-photo"></div>
                <div>{data.user}</div>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default Question;
