import Logo from '../images/Logo.png';

function Main() {
  return (
    <div className="Main_container">
      <div className="head">
        <img src={Logo} alt="Logo" />
        <p>CodeStates Pre-Project TEAM 26</p>
        <h1>Code Knitters</h1>
      </div>
      <section>
        <content className="members">
          <h2>Members</h2>
          <div className="people">
            <ul>
              <li></li>
            </ul>
          </div>
        </content>
        <content className="Tools">
          <h2>Tools</h2>
        </content>
      </section>
    </div>
  );
}

export default Main;
