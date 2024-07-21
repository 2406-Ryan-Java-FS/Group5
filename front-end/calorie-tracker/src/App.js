import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route} from "react-router-dom";
import { Routes } from "react-router-dom";
import CalorieTrackView from "./components/calorie-track/calorie-track-view";
import CalorieTrackForm from "./components/calorie-track/calorie-track-form";
import ProfileView from "./components/profile-view";
import ProfileForm from "./components/profile-form";
import { AuthProvider } from "./AuthContext";
import Error from "./components/Error";
import CalorieTrackDelete from "./components/calorie-track/calorie-track-delete";
import BmiCalculator from "./components/bmi-calculator";
import { useLocation } from "react-router-dom";

function App() {

  const location = useLocation();

  const showNavBar = () =>{
    const noNavbarPaths = ['/', '/login', '/signup', '/error'];
    return !noNavbarPaths.includes(location.pathname);
  };

  return (<>

<AuthProvider>
    <NavBar show={showNavBar()} />
      <Routes>
        <Route path='' element={<Home />} />

        <Route path='login' element={<LoginPage />} />

        <Route path='signup' element={<SignUpPage />} />

        <Route path="profile" element={<ProfileView />} />

        <Route path="profile/form" element={<ProfileForm />} />
        <Route path="profile/form/edit/:pId" element={<ProfileForm />}/>

    {/* Calorie-track-dashboard */}
    <Route path="/calorietrack" element={<CalorieTrackView/>}/>
    <Route path="/calorietrack/add" element={<CalorieTrackForm />}/>
    <Route path="/calorietrack/edit/:cId" element={<CalorieTrackForm/>}/>
    <Route path="/calorietrack/delete/:cId" element={<CalorieTrackDelete/>} />



    <Route path="*" element={<Error />} />
    <Route path="bmi-calculator" element={<BmiCalculator />} />

  </Routes>

</AuthProvider>
 
  </>);

}

export default App;
