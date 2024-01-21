import { useEffect } from 'react';
// import axios from 'axios';
import '../css/question.css';

function Question({ data }) {
  let contentWithNoSpace = data.content
    .replace(/<p>/g, '')
    .replace(/<\/p>/g, '');

  // const [answer, setAnswer] = useState('');

  // const getAnswerList = async () => {
  //   try {
  //     const res = await axios.get(
  //       'http://13.124.11.238:8080/question/1/answer',
  //       {
  //         headers: {
  //           'Content-Type': `application/json`,
  //         },
  //       },
  //     );
  //     setAnswer(res.data);
  //   } catch (err) {
  //     console.log('Error >>', err);
  //   }
  // };

  // const answer_filter = answer.vote?.filter(
  //   (el) => el.questionId === data.questionId,
  // );

  useEffect(() => {
    console.log(data);
  }, []);
  return (
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
          <div className="question__content">{contentWithNoSpace}</div>
        </div>
        <div className="question__bottom">
          <div className="tag_box">
            {data.tags?.map((el) => (
              <span key={el}>{el}</span>
            ))}
          </div>
          <div className="question__author">
            <div className="question__author-photo"></div>
            <div>{data.memberId}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Question;
