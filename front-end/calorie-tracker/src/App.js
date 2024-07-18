import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";

function App() {
  return (<>
<NavBar />

  <Routes>
    <Route path='' element={<Home />} />

    <Route path='login' element={<LoginPage />} />

    <Route path='signup' element={<SignUpPage/>} />

  </Routes>
  </>);

}

export default App;
