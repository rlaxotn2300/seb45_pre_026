import Nav from '../component/Nav';

export default function Questions({ curPage, setCurPage }) {
  return (
    <div>
      <Nav curPage={curPage} setCurPage={setCurPage} />
    </div>
  );
}
