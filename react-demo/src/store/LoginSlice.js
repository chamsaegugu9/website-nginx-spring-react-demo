import { createSlice } from '@reduxjs/toolkit'

export const loginSlice = createSlice({
  name: 'login',
  initialState: {
    login: false,
    username: "",
  },
  reducers: {
    setlogin: (state, action) => {
      state.login = true;
      state.username = action.payload;
    },
    setlogout: (state) => {
      state.login = false;
      state.username = "";
    },
  },
})

// Action creators are generated for each case reducer function
export const { setlogin, setlogout } = loginSlice.actions

export default loginSlice.reducer