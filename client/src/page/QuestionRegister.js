import '../css/questionRegister.css';
import pencil from '../images/pencil.png';

export default function QuestionRegister() {
  return (
    <div className="register__bg">
      <div className="register__main">
        <div className="register__title">Ask a public question</div>
        <div className="register__input-container">
          <div className="register__title-container">
            <div className="register__input-title">Title</div>
            <div className="register__input-desc">
              Be specific and imagine you&apos;re asking a question to another
              person.
            </div>
            <input
              type="text"
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              className="input register__title-input"
            ></input>
          </div>
        </div>
        <div className="register__input-container">
          <div className="register__title-container">
            <div className="register__input-title">Body</div>
            <div className="register__input-desc">
              The body of your question contains your problem details and
              results.
            </div>
            <textarea className="input register__title-input register__body-input"></textarea>
          </div>
        </div>
        <div className="register__btn-container">
          <button className="button-dark register__post-btn">
            Post your question
          </button>
          <button className="register__cancel-btn">Discard draft</button>
        </div>
      </div>
      <div className="register__side">
        <div className="register__side-wrap">
          <div className="register__side-title">Writing a good title</div>
          <div className="register__side-content-wrap">
            <img src={pencil} alt="연필 그림" className="register__side-img" />
            <div className="register__side-content">
              Now that you&apos;re ready to post your question, read through it
              from start to finish. Does it make sense? \n\n Add any details you
              missed and read through it again. Now is a good time to make sure
              that your title still describes the problem!
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
