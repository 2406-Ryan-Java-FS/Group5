import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";
import CalorieTrackView from "./components/calorie-track/calorie-track-view";
import CalorieTrackForm from "./components/calorie-track/calorie-track-form";
import FoodSearch from "./components/calorie-track/food-search";

function App() {
  return (<>
<NavBar />

  <Routes>
    <Route path='' element={<Home />} />

    <Route path='login' element={<LoginPage />} />

    <Route path='signup' element={<SignUpPage/>} />

    {/* Calorie-track-dashboard */}
    <Route path="/calorietrack" element={<CalorieTrackView/>}/>
    <Route path="/calorietrack/add" element={<FoodSearch />}/>

  </Routes>
  </>);

}

export default App;
