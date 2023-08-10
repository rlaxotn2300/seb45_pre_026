import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './component/Header';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
