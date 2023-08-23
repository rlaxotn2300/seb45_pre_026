const initialState = {
  curPage: '',
  stateEmail: '',
  nickname: '',
  statePassword: '',
  isLogin: false,
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_CURPAGE':
      return { ...state, curPage: action.payload };
    case 'SET_STATEEMAIL':
      return { ...state, stateEmail: action.payload };
    case 'SET_NICKNAME':
      return { ...state, nickname: action.payload };
    case 'SET_STATEPASSWORD':
      return { ...state, statePassword: action.payload };
    case 'SET_ISLOGIN':
      return { ...state, isLogin: action.payload };
    default:
      return state;
  }
};

export default rootReducer;
