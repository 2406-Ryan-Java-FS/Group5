import { Link } from 'react-router-dom';
import '../styles/navbar.css';
import { useAuth } from '../AuthContext';


export default function NavBar({show}){
    const { logout } = useAuth();
    if (!show)
        return null;
    //function for log out 





    return (<>
        <div>
        <nav id="navbar">
            <Link to="/profile/form">Profile</Link>
            <Link to="/calorietrack">CalorieTrack</Link>
            <Link to="/bmi-calculator">BMI Calculator</Link>
            <Link to="/" onClick={logout}>Log Out</Link>
        </nav>
        </div>

    </>)
}
