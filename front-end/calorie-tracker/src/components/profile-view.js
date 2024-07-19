import React from 'react';
import { Link } from 'react-router-dom';

export default function ProfileView({user}){
    if (!user) {
        return <>
        <div className="profile-container-empty">
            <h2>Profile</h2>
            <p>
                <strong>Gender:</strong>
            </p>
            <p>
                <strong>Weight:</strong> 
            </p>
            <p>
                <strong>Height:</strong>
            </p>
            <p>
                <strong>Activity Level:</strong>
            </p>
            <Link to={"/profile/form"}><button>Fill out Profile</button></Link>
            {/* Add more fields as needed */}
        </div>

    
    
    </>; // Handle case where user data is still loading
    }
    return (<>
        <div className="profile-container">
            <h2>Profile</h2>
            <p>
                <strong>Gender:</strong> {user.gender}
            </p>
            <p>
                <strong>Weight:</strong> {user.weight} kg
            </p>
            <p>
                <strong>Height:</strong> {user.height} cm
            </p>
            <p>
                <strong>Activity Level:</strong> {user.activityLevel}
            </p>
            {/* Add more fields as needed */}
        </div>

    
    
    </>)
}