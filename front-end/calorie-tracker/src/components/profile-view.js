import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext'; // Import useAuth to access user context

export default function ProfileView() {
    const { user } = useAuth(); // Get the user from the context

    if (!user) {
        return (
            <div className="profile-container-empty">
                <h2>Profile</h2>
                <p><strong>Gender:</strong> N/A</p>
                <p><strong>Weight:</strong> N/A</p>
                <p><strong>Height:</strong> N/A</p>
                <p><strong>Activity Level:</strong> N/A</p>
                <Link to="/profile/form">
                    <button>Fill out Profile</button>
                </Link>
            </div>
        );
    }

    return (
        <div className="profile-container">
            <h2>Profile</h2>
            <p><strong>Gender:</strong> {user.gender || 'N/A'}</p>
            <p><strong>Weight:</strong> {user.weight || 'N/A'} kg</p>
            <p><strong>Height:</strong> {user.height || 'N/A'} cm</p>
            <p><strong>Activity Level:</strong> {user.activityLevel || 'N/A'}</p>
            {/* Add more fields as needed */}
            <Link to="/profile/form">
                <button>Edit Profile</button>
            </Link>
        </div>
    );
}
