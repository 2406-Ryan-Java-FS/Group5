import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import '../styles/navbar.css';

const AdminNavbar = () => {
    const { logout } = useAuth();
    // if (!show)
    //     return null;
    //function for log out 

    const handleLogout = () => {
        logout();
    };

    return (
        <>
            <nav id='navbar'>
                <Link to="/admin">Manage Foods</Link>
                <Link to="/admin/users">Manage Profiles</Link>
                <Link to="/" onClick={handleLogout}>Log Out</Link>
            </nav>
        </>
    );
};

export default AdminNavbar;
