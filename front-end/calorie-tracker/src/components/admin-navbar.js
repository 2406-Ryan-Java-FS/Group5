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
        
                <Link to="/admin">Manage Foods</Link>
                <Link to="/profile">Manage Profiles</Link>
                <Link to="/" onClick={handleLogout}>Log Out</Link>
    
        </>
    );
};

export default AdminNavbar;
