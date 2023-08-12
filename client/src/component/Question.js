import { connect } from 'react-redux';
import '../css/question.css';

const mapStateToProps = (state) => {
  return {
    questionData: state.questionData,
  };
};

function Question({ questionData }) {
  let dummyData = questionData[0];

  return (
    <div className="question__container">
      <div className="question__side">
        <div>{dummyData.vote} votes</div>
        <div
          className={
            dummyData.answer.length === 0
              ? 'quesiton__no-answer'
              : 'question__answer'
          }
        >
          {dummyData.answer.length} answers
        </div>
      </div>
      <div className="question__main">
        <div className="question__content-container">
          <div className="question__title">{dummyData.title}</div>
          <div className="question__content">{dummyData.content}</div>
        </div>
        <div className="question__bottom">
          <div>{dummyData.date}</div>
          <div className="question__author">
            <div className="question__author-photo"></div>
            <div>{dummyData.user}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default connect(mapStateToProps)(Question);
