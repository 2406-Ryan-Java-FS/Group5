// import React from 'react';
// import { Link } from 'react-router-dom';
// import { useAuth } from '../AuthContext'; // Import useAuth to access user context

// export default function ProfileView() {
//     const { user } = useAuth(); // Get the user from the context

//     if (!user) {
//         return (
//             <div className="profile-container-empty">
//                 <h2>Profile</h2>
//                 <p><strong>Gender:</strong> N/A</p>
//                 <p><strong>Weight:</strong> N/A</p>
//                 <p><strong>Height:</strong> N/A</p>
//                 <p><strong>Activity Level:</strong> N/A</p>
//                 <Link to="/profile/form">
//                     <button>Fill out Profile</button>
//                 </Link>
//             </div>
//         );
//     }

//     return (
//         <div className="profile-container">
//             <h2>Profile</h2>
//             <p><strong>Gender:</strong> {user.gender || 'N/A'}</p>
//             <p><strong>Weight:</strong> {user.weight || 'N/A'} kg</p>
//             <p><strong>Height:</strong> {user.height || 'N/A'} cm</p>
//             <p><strong>Activity Level:</strong> {user.activityLevel || 'N/A'}</p>
//             <p><strong>Calorie Goal (per day):</strong> {user.calorieGoal || 'N/A'}</p>
//             {/* Add more fields as needed */}
//             <Link to="/profile/form">
//                 <button>Edit Profile</button>
//             </Link>
//         </div>
//     );
// }


import React, { useEffect, useState, useCallback } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import '../styles/profile.css';

export default function ProfileView() {
    const { user } = useAuth();
    const [profile, setProfile] = useState(null);

    const fetchProfile = useCallback(async () => {
        if (!user || !user.uid) {
            console.error('User or user.uid is not defined');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/users/${user.uid}/profiles`);
        
            if (response.ok) {
                const profileData = await response.json();
                console.log(profileData);
                setProfile(profileData);
            } else {
                console.error('Failed to fetch profile', response.status);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }, [user]);

    useEffect(() => {
        if (user && user.uid) {
            fetchProfile();
        } else {
            console.error('User or user.uid is not defined');
        }
    }, [user, fetchProfile]);

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
            <p><strong>Gender:</strong> {profile?.gender || 'N/A'}</p>
            <p><strong>Weight:</strong> {profile?.weight || 'N/A'} kg</p>
            <p><strong>Height:</strong> {profile?.height || 'N/A'} cm</p>
            <p><strong>Activity Level:</strong> {profile?.activity || 'N/A'}</p>
            <p><strong>Calorie Goal (per day):</strong> {profile?.calorieGoal || 'N/A'}</p>
            {profile && profile.pid?(<Link to={`/profile/form/edit/${profile.pid}`}>
                <button>Edit Profile</button>
            </Link>) : (<Link to="/profile/form">
                <button>Make Profile</button>
            </Link>)}
        </div>
    );
}
