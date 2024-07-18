import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";
import CalorieTrackView from "./components/calorie-track/calorie-track-view";

function App() {
  return (<>
<NavBar />

  <Routes>
    <Route path='' element={<Home />} />

    <Route path='login' element={<LoginPage />} />

    <Route path='signup' element={<SignUpPage/>} />

    {/* Calorie-track-dashboard */}
    <Route path="/calorietrack" element={<CalorieTrackView/>}/>

  </Routes>
  </>);

}

export default App;
