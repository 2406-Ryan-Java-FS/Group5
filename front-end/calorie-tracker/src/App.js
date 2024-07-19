import Home from "./components/home";
import LoginPage from "./components/login-page";
import SignUpPage from "./components/sign-up-page";
import NavBar from "./components/nav-bar";
import { Route } from "react-router-dom";
import { Routes } from "react-router-dom";
import ProfileView from "./components/profile-view";
import ProfileForm from "./components/profile-form";

function App() {
  return (<>
<NavBar />

  <Routes>
    <Route path='' element={<Home />} />

    <Route path='login' element={<LoginPage />} />

    <Route path='signup' element={<SignUpPage/>} />

    <Route path="profile" element={<ProfileView/>} />

    <Route path="profile/form" element={<ProfileForm/>} />

  </Routes>
  </>);

}

export default App;
