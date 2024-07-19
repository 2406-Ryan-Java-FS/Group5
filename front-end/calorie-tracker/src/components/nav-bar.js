import { Link } from 'react-router-dom';
import '../styles/navbar.css';

export default function NavBar({show}){
    if (!show)
        return null;

    return (<>
        <div>
            <nav id="navbar">
                <Link to="">Home</Link>
                <Link to="/profile/form">Profile</Link>
                <Link to="/calorietrack">CalorieTrack</Link>
                <Link to="/bmi-calculator">BMI Calculator</Link>
            </nav>
        </div>

    </>)
}