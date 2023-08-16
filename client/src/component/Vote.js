import { useParams } from 'react-router-dom';
import { connect } from 'react-redux';
import { setQuestionData } from '../redux/action';
import '../css/vote.css';
import DownArrow from '../images/downArrow.png';
import UpArrow from '../images/upArrow.png';
import { useState } from 'react';

const mapStateToProps = (state) => {
  return {
    questionData: state.questionData,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setQuestionData: (questionData) => dispatch(setQuestionData(questionData)),
  };
};

function Vote({ questionData }) {
  let { id } = useParams();
  const [voteNumber, setVoteNumber] = useState(questionData[id].vote);

  function handleVoteUp() {
    setVoteNumber(voteNumber + 1);
    setQuestionData({ ...questionData[id], vote: voteNumber });
    console.log(questionData[id]);
  }

  function handleVoteDown() {
    if (voteNumber > 0) {
      setVoteNumber(voteNumber - 1);
      setQuestionData({ ...questionData[id], vote: voteNumber });
    }
    console.log(questionData[id]);
  }

  return (
    <div className="vote__bg">
      <div className="vote__btn" role="presentation" onClick={handleVoteUp}>
        <img src={UpArrow} alt="투표 올리기 버튼" className="vote__arrow" />
      </div>
      <div className="vote__number">{voteNumber}</div>
      <div className="vote__btn" role="presentation" onClick={handleVoteDown}>
        <img src={DownArrow} alt="투표 내리기 버튼" className="vote__arrow" />
      </div>
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(Vote);
