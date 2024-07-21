import { Link } from 'react-router-dom';
import '../styles/navbar.css';
import { useAuth } from '../AuthContext';
import AdminNavbar from './admin-navbar';


export default function NavBar({show}){
    const { user, logout } = useAuth();
    if (!show)
        return null;
    //function for log out 

    const handleLogout = () => {
        logout();
    };



    return (<>
        <div>
        <nav id="navbar">
        {user && user.role === 'ADMIN' ? (
                    <AdminNavbar /> // Show AdminNavbar if user is an admin
                ) : (<>
            <Link to="/profile">Profile</Link>
            <Link to="/calorietrack">CalorieTrack</Link>
            <Link to="/bmi-calculator">BMI Calculator</Link>
            <Link to="/" onClick={handleLogout}>Log Out</Link>
            </>
        )}
        </nav>
        </div>

    </>)
}
