import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/questionRegister.css';
import pencil from '../images/pencil.png';
import { questionData } from '../dummydata';

export default function QuestionEdit() {
  let { id } = useParams();

  let bodyWithNoPTag = questionData[id].content
    .replace(/ {4}/g, '')
    .replace(/<p>/g, '')
    .replace(/<\/p>/g, '');

  const [title, setTitle] = useState(questionData[id].title);
  const [isTitleEmpty, setIsTitleEmpty] = useState(false);
  const [body, setBody] = useState(bodyWithNoPTag);
  const [bodyData, setBodyData] = useState('');
  const [isBodyEmpty, setIsBodyEmpty] = useState(false);
  const navigate = useNavigate();

  function handleTitleChange(e) {
    setTitle(e.target.value);

    if (title !== '') setIsTitleEmpty(false);
  }

  function handleBodyChange(e) {
    setBody(e.target.value);

    if (body !== '') setIsBodyEmpty(false);
  }

  function handleQuestionSubmit() {
    setIsTitleEmpty(false);
    setIsBodyEmpty(false);

    let bodyContent = '<p>' + body.replace(/\n/g, '</p>\n<p>') + '</p>';
    setBodyData(bodyContent);
    console.log(bodyData);

    if (title === '') setIsTitleEmpty(true);
    if (body === '') setIsBodyEmpty(true);
    if (!isTitleEmpty && !isBodyEmpty) {
      navigate(`/question/${questionData[id].questionId}`);
    }
  }

  function handleDiscardClick() {
    if (
      window.confirm(
        'Are you sure you want to discard this question? This cannot be undone.',
      )
    ) {
      navigate(`/question/${questionData[id].questionId}`);
    }
  }
  return (
    <div className="register__bg">
      <div className="register__wrap">
        <div className="register__title">Edit question</div>
        <div className="register__main-wrap">
          <div className="register__main">
            <div className="register__input-container">
              <div className="register__title-container">
                <div className="register__input-title">Title</div>
                <div className="register__input-desc">
                  Be specific and imagine you&apos;re asking a question to
                  another person.
                </div>
                <input
                  type="text"
                  placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                  className={
                    isTitleEmpty
                      ? 'input input-red register__title-input'
                      : 'input input-gray register__title-input'
                  }
                  onChange={(e) => handleTitleChange(e)}
                  value={title}
                ></input>
                {isTitleEmpty ? (
                  <div className="register__warning">
                    Title cannot be empty.
                  </div>
                ) : null}
              </div>
            </div>
            <div className="register__input-container">
              <div className="register__title-container">
                <div className="register__input-title">Body</div>
                <div className="register__input-desc">
                  The body of your question contains your problem details and
                  results.
                </div>
                <textarea
                  className={
                    isBodyEmpty
                      ? 'input input-red register__title-input register__body-input'
                      : 'input input-gray register__title-input register__body-input'
                  }
                  onChange={(e) => handleBodyChange(e)}
                  value={body}
                ></textarea>
                {isBodyEmpty ? (
                  <div className="register__warning">Body cannot be empty.</div>
                ) : null}
              </div>
            </div>
          </div>
          <div className="register__side">
            <div className="register__side-wrap">
              <div className="register__side-title">Writing a good title</div>
              <div className="register__side-content-wrap">
                <img
                  src={pencil}
                  alt="연필 그림"
                  className="register__side-img"
                />
                <div className="register__side-content">
                  Your title should summarize the problem. <br />
                  <br /> You might find that you have a better idea of your
                  title after writing out the rest of the question.
                </div>
              </div>
            </div>
            <div className="register__side-wrap">
              <div className="register__side-title">
                Proof-read before posting
              </div>
              <div className="register__side-content-wrap">
                <img
                  src={pencil}
                  alt="연필 그림"
                  className="register__side-img"
                />
                <div className="register__side-content">
                  Now that you&apos;re ready to post your question, read through
                  it from start to finish. Does it make sense? <br />
                  <br /> Add any details you missed and read through it again.
                  Now is a good time to make sure that your title still
                  describes the problem!
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="register__btn-container">
          <button
            className="button-dark register__post-btn"
            onClick={handleQuestionSubmit}
          >
            Edit your question
          </button>
          <button className="register__cancel-btn" onClick={handleDiscardClick}>
            Discard draft
          </button>
        </div>
      </div>
    </div>
  );
}
