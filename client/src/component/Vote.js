import { useState } from 'react';
import '../css/vote.css';
import DownArrow from '../images/downArrow.png';
import UpArrow from '../images/upArrow.png';

export default function Vote({ voteNumber }) {
  const [vote, setVote] = useState(voteNumber);

  function handleVoteUp() {
    setVote(vote + 1);
  }

  function handleVoteDown() {
    if (vote > 0) {
      setVote(vote - 1);
    }
  }

  return (
    <div className="vote__bg">
      <div className="vote__btn" role="presentation" onClick={handleVoteUp}>
        <img src={UpArrow} alt="투표 올리기 버튼" className="vote__arrow" />
      </div>
      <div className="vote__number">{vote}</div>
      <div className="vote__btn" role="presentation" onClick={handleVoteDown}>
        <img src={DownArrow} alt="투표 내리기 버튼" className="vote__arrow" />
      </div>
    </div>
  );
}
