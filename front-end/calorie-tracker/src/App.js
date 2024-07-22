import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route} from "react-router-dom";
import { Routes } from "react-router-dom";
import CalorieTrackView from "./components/calorie-track/calorie-track-view";
import CalorieTrackForm from "./components/calorie-track/calorie-track-form";
import AdminFoodPage from "./components/admin-food-management/admin-food-page";
import ProfileView from "./components/profile-view";
import ProfileForm from "./components/profile-form";
import MainComponent from "./components/main-component";
import { AuthProvider } from "./AuthContext";
import Error from "./components/Error";
import CalorieTrackDelete from "./components/calorie-track/calorie-track-delete";
import BmiCalculator from "./components/bmi-calculator";
import { useLocation } from "react-router-dom";
import AdminUserManageView from "./components/admin-user-management/admin-user-manage-view";
import AdminFoodManageTable from "./components/admin-food-management/admin-food-manage-table";

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

        <Route path="/admin" element={<AdminFoodPage />} />

        <Route path="/admin/manage-food" element={<AdminFoodManageTable />} />

        <Route path="/admin/profiles" element={<AdminUserManageView />} />
  

        <Route path="profile/form/edit/:pId" element={<ProfileForm />}/>

        <Route path="/" element={<MainComponent />} />

    {/* Calorie-track-dashboard */}
    <Route path="/calorietrack" element={<CalorieTrackView/>}/>
    <Route path="/calorietrack/add" element={<CalorieTrackForm />}/>
    <Route path="/calorietrack/edit/:cId" element={<CalorieTrackForm/>}/>
    <Route path="/calorietrack/delete/:cId" element={<CalorieTrackDelete/>} />



    <Route path="*" element={<Error />} />
    <Route path="bmi-calculator" element={<BmiCalculator />} />


    <Route path="/admin/users" element={<AdminUserManageView/>} />

  </Routes>

</AuthProvider>
 
  </>);

}

export default App;

