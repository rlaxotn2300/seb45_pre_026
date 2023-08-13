import { questionData } from '../dummydata';

const initialState = {
  curPage: '',
  email: 'qwe123@gmail.com',
  nickname: 'nickname',
  password: '123qwe',
  questionData: questionData,
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_CURPAGE':
      return { ...state, curPage: action.payload };
    case 'SET_EMAIL':
      return { ...state, email: action.payload };
    case 'SET_NICKNAME':
      return { ...state, nickname: action.payload };
    case 'SET_PASSWORD':
      return { ...state, password: action.payload };
    case 'SET_questionData':
      return { ...state, questionData: action.payload };
    default:
      return state;
  }
};

export default rootReducer;
