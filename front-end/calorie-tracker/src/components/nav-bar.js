import { Link } from 'react-router-dom';

export default function NavBar(){




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