import '../css/question.css';

function Question({ data, answer }) {
  let contentWithNoSpace = data.content
    .replace(/<p>/g, '')
    .replace(/<\/p>/g, '');
  const answer_filter = answer.vote?.filter((el) => {
    return el.questionId === data.questionId;
  });
  // console.log(answer_filter);

  return (
    <div>
      <div className="question__container" key={data.questionId}>
        <div className="question__side">
          <div>{data.vote} votes</div>
          <div
            className={
              answer_filter?.length === 0
                ? 'quesiton__no-answer'
                : 'question__answer'
            }
          >
            {answer_filter?.length} answers
          </div>
        </div>
        <div className="question__main">
          <div className="question__content-container">
            <div className="question__title">{data.title}</div>
            <div className="question__content">{contentWithNoSpace}</div>
          </div>
          <div className="question__bottom">
            <div className="tag_box">
              {data.tags.map((el) => (
                <span key={el}>{el}</span>
              ))}
            </div>
            <div className="question__author">
              <div className="question__author-photo"></div>
              {/* <div>{data.user}</div> */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Question;
