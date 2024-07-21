// import React, { useState, useEffect } from 'react';
// import { useAuth } from '../AuthContext';
// import { useNavigate } from 'react-router-dom';

// export default function ProfileForm() {
//     const { user, login } = useAuth();
//     const navigate = useNavigate();

//     const [formData, setFormData] = useState({
//         gender: user ? user.gender : '',
//         weight: user ? user.weight : '',
//         height: user ? user.height : '',
//         activityLevel: user ? user.activityLevel : '',
//         calorieGoal: user ? user.calorieGoal : '',
//     });

//     useEffect(() => {
//         if (!user) {
//             // Redirect to login if user is not authenticated
//             navigate('/login');
//         }
//     }, [user, navigate]);

//     const handleChange = (e) => {
//         const { name, value } = e.target;
//         setFormData({
//             ...formData,
//             [name]: value,
//         });
//     };

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         // Update user profile in state and possibly backend
//         login({ ...user, ...formData });
//         // Optionally, send the updated user data to your backend here
//         navigate('/profile');
//     };

//     return (
//         <div className="profile-form">
//             <h2>Edit Profile</h2>
//             <form onSubmit={handleSubmit}>
//                 <label>
//                     Gender:
//                     <select
//                         name="gender"
//                         value={formData.gender}
//                         onChange={handleChange}
//                     >
//                         <option value="">Select</option>
//                         <option value="male">Male</option>
//                         <option value="female">Female</option>
//                         <option value="other">Other</option>
//                     </select>
//                 </label>
//                 <label>
//                     Weight (kg):
//                     <input
//                         type="number"
//                         name="weight"
//                         value={formData.weight}
//                         onChange={handleChange}
//                     />
//                 </label>
//                 <label>
//                     Height (cm):
//                     <input
//                         type="number"
//                         name="height"
//                         value={formData.height}
//                         onChange={handleChange}
//                     />
//                 </label>
//                 <label>
//                     Activity Level:
//                     <select name="activityLevel" value={formData.activityLevel} onChange={handleChange}>
//                         <option value="">Select</option>
//                         <option value="1-2 times a week">1-2 times a week</option>
//                         <option value="3-4 times a week">3-4 times a week</option>
//                         <option value="5-6 times a week">5-6 times a week</option>
//                         <option value="7+ times a week">7+ times a week</option>
//                     </select>
//                 </label>
//                 <label>
//                     Calorie Goal (per day):
//                     <input
//                         type="number"
//                         name="calorieGoal"
//                         value={formData.calorieGoal}
//                         onChange={handleChange}
//                     />
//                 </label>
//                 <button type="submit">Save Profile</button>
//             </form>
//         </div>
//     );
// }


import React, { useState, useEffect } from 'react';
import { useAuth } from '../AuthContext';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/profile.css';
import toast, {Toaster} from 'react-hot-toast';

export default function ProfileForm() {
    const { user, login } = useAuth();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        gender: '',
        weight: '',
        height: '',
        activity: '',
        calorieGoal: '',
    });
    const [isLoading, setIsLoading] = useState(true);
    const [profileExists, setProfileExists] = useState(false); // Track if a profile exists

    useEffect(() => {
        if (!user) {
            navigate('/login');
        } else {
            // Fetch existing profile data
            fetchProfileData();
        }
    }, [user, navigate]);

    const fetchProfileData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/users/${user.uid}/profiles`);
            if (response.ok) {
                const profileData = await response.json();
                setFormData({
                    pid: profileData.pid,
                    gender: profileData.gender,
                    weight: profileData.weight,
                    height: profileData.height,
                    activity: profileData.activity,
                    calorieGoal: profileData.calorieGoal,
                });
                console.log("fetched profileData: " +profileData)
                setProfileExists(true); // Set profile exists to true if data is fetched successfully
            } else {
                console.error('Failed to fetch profile:', response.status);
                setProfileExists(false); // Set profile exists to false if fetching failed
            }
        } catch (error) {
            console.error('Error fetching profile:', error);
            setProfileExists(false); // Set profile exists to false if an error occurs
        } finally {
            setIsLoading(false);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const profileData = {
            ...formData,
            weight: parseFloat(formData.weight),
            height: parseFloat(formData.height),
            activity: parseInt(formData.activity, 10),
            calorieGoal: parseInt(formData.calorieGoal, 10),
        };

        console.log('Submitting profile data:', profileData);

        try {
            const response = await fetch(profileExists?(`http://localhost:8080/api/users/${user.uid}/profiles/${profileData.pid}`):(`http://localhost:8080/api/users/${user.uid}/profiles`), {
                method: profileExists ? 'PUT' : 'POST', // Use PUT if profile exists, otherwise POST
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(profileData),
            });

            if (response.ok) {
                const updatedUser = { ...user, ...profileData };
                login(updatedUser);
                toast.success("Success!")
                navigate('/profile');
            } else {
                const errorText = await response.text();
                console.error('Failed to update profile:', errorText);
                toast.error(errorText)
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="profile-form">
            <h2>{profileExists? "Edit" : "Add a "} Profile</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Gender:
                    <select
                        name="gender"
                        value={formData.gender}
                        onChange={handleChange}
                    >
                        <option value="">Select</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </label>
                <label>
                    Height (cm):
                    <input
                        type="number"
                        name="height"
                        value={formData.height}
                        onChange={handleChange}
                    
                    />
                </label>
                <label>
                    Weight (kg):
                    <input
                        type="number"
                        name="weight"
                        value={formData.weight}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Activity Level:
                    <select name="activity" value={formData.activity} onChange={handleChange}>
                        <option value="">Select</option>
                        <option value="1">1-2 times a week</option>
                        <option value="2">3-4 times a week</option>
                        <option value="3">5-6 times a week</option>
                        <option value="4">7+ times a week</option>
                    </select>
                </label>
                <label>
                    Calorie Goal (per day):
                    <input
                        type="number"
                        name="calorieGoal"
                        value={formData.calorieGoal}
                        onChange={handleChange}
                    />
                </label>
                <button type="submit">Save Profile</button>
            </form>
        </div>
    );
}

