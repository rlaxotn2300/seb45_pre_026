import Nav from '../component/Nav';
import '../css/questions.css';

export default function Questions({ curPage, setCurPage }) {
  return (
    <div>
      <div className="questions_wrap">
        <Nav curPage={curPage} setCurPage={setCurPage} />
        <div className="questions_list">770px</div>
        <aside className="aside">305px 뭘 넣지?....</aside>
      </div>
    </div>
  );
}
