import './App.css';
import { BrowserRouter, Routes } from 'react-router-dom';
import Header from './component/Header';
// import Main from './page/Main';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Header />
        <Routes>{/* <Route path="/" element={<Main />} /> */}</Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
