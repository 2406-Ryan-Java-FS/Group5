import { Link, NavLink } from 'react-router-dom';
import '../styles/nav.css';
import { useAuth } from '../AuthContext';
import AdminNavbar from './admin-navbar';



export default function NavBar({show}){
  const { logout, user } = useAuth();

    if (!show)
        return null;
    //function for log out 
    const handleLogout = () => {
      logout();
  };



    return (<>
    <header>
      <nav className="navbar navbar-expand-lg navbar-light mt-2 mb-2 ps-4 pe-4">
        {user && user.role === 'ADMIN' ? (
          <AdminNavbar /> // Show AdminNavbar if user is an admin
        ) : (
          <>
            <NavLink className="navbar-brand" to="/calorietrack">
          <img src='/Group 1 (1).png' alt='Calorie Track Pro Logo' width='80' />
        </NavLink>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <NavLink className="nav-link" to="/profile">
                Profile
              </NavLink>
            </li>
            {user && (
              <li className="nav-item">
                <NavLink className="nav-link" to="/calorieTrack">
                  Calorie Track
                </NavLink>
              </li>
            )}
            {user && (
              <li className="nav-item">
                <NavLink className="nav-link" to="/bmi-calculator">
                  BMI Calculator
                </NavLink>
              </li>
            )}
          </ul>

          {user && (
            <div className="d-flex justify-content-between">
              <div className="usernameBadge  mt-2 me-3">
               <span>{user.username}</span>
              
              </div>
              <div className='ms-3'>
              <Link 
                to="/"
                onClick={logout}
                className="btn buttonFill" id="logoutBtn"
              >
                Log out
              </Link>
              </div>
              
            </div>
          )}
        </div>
          </>
        )

        }
        
      </nav>
    </header>

        {/* <div>
        <nav id="navbar navbar-expand-lg navbar-light mt-2 mb-2 ps-4 pe-4">
    
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
        </div> */}

   </>)
}
