const initialState = {
  curPage: '',
  email: '',
  nickname: '',
  password: '',
  isLogin: false,
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
    case 'SET_ISLOGIN':
      return { ...state, isLogin: action.payload };
    default:
      return state;
  }
};

export default rootReducer;
