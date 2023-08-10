const initialState = {
  curPage: 'questions',
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_CURPAGE':
      return { ...state, curPage: action.payload };
    default:
      return state;
  }
};

export default rootReducer;
