// import React, { useState } from 'react';


// export default function ProfileForm({user, onUpdate }) {

//         const [gender, setGender] = useState(user.gender);
//         const [weight, setWeight] = useState(user.weight);
//         const [height, setHeight] = useState(user.height);
//         const [activityLevel, setActivityLevel] = useState(user.activityLevel);
//         const [calorieGoal, setCalorieGoal] = useState(user.calorieGoal);
    
//         const calculateBMI = () => {
//             // Convert height from cm to meters
//             const heightInMeters = height / 100;
//             // Calculate BMI
//             const bmi = weight / (heightInMeters * heightInMeters);
//             return bmi.toFixed(2); // Round to 2 decimal places
//         };
    
//         const handleSubmit = (e) => {
//             e.preventDefault();
//             // Example: Update user profile
//             onUpdate({ gender, weight, height, activityLevel, calorieGoal });
//         };
    
//         return (
//             <div className="profile-form-container">
//                 <h2>Edit Profile</h2>
//                 <form onSubmit={handleSubmit}>
//                     <label>
//                         Gender:
//                         <select
//                             value={gender}
//                             onChange={(e) => setGender(e.target.value)}
//                             required
//                         >
//                             <option value="">Select</option>
//                             <option value="male">Male</option>
//                             <option value="female">Female</option>
//                             <option value="other">Other</option>
//                         </select>
//                     </label>
//                     <label>
//                         Weight (kg):
//                         <input
//                             type="number"
//                             value={weight}
//                             onChange={(e) => setWeight(e.target.value)}
//                             required
//                         />
//                     </label>
//                     <label>
//                         Height (cm):
//                         <input
//                             type="number"
//                             value={height}
//                             onChange={(e) => setHeight(e.target.value)}
//                             required
//                         />
//                     </label>
//                     <label>
//                         Activity Level:
//                         <select
//                             value={activityLevel}
//                             onChange={(e) => setActivityLevel(e.target.value)}
//                             required
//                         >
//                             <option value="">Select</option>
//                             <option value="1-2">1-2 times a week</option>
//                             <option value="3-4">3-4 times a week</option>
//                             <option value="5-6">5-6 times a week</option>
//                             <option value="7+">Daily (7+ times a week)</option>
//                         </select>
//                     </label>
//                     <label>
//                         Calorie Goal (per day):
//                         <input
//                             type="number"
//                             value={calorieGoal}
//                             onChange={(e) => setCalorieGoal(e.target.value)}
//                             optional
//                         />
//                     </label>
//                     <button type="submit">Save Changes</button>
//                 </form>
//                 <div className="bmi-container">
//                     <h3>BMI Calculator</h3>
//                     <p>
//                         <strong>BMI:</strong> {calculateBMI()}
//                     </p>
//                 </div>
//             </div>
//         );
//     }
    

import React, { useState } from 'react';

export default function ProfileForm({ user, onUpdateProfile }) {
    // Initialize form state with user data or empty values
    const [formData, setFormData] = useState({
        gender: user ? user.gender : '',
        weight: user ? user.weight : '',
        height: user ? user.height : '',
        activityLevel: user ? user.activityLevel : '',
        calorieGoal: user ? user.calorieGoal : '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Pass updated form data to parent component for handling
        onUpdateProfile(formData);
    };

    return (
        <div className="profile-form">
            <h2>Profile Form</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Gender:
                    <input
                        type="text"
                        name="gender"
                        value={formData.gender}
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
                    Height (cm):
                    <input
                        type="number"
                        name="height"
                        value={formData.height}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Activity Level:
                    <select name="activityLevel" value={formData.activityLevel} onChange={handleChange}>
                        <option value="1-2 times a week">1-2 times a week</option>
                        <option value="3-4 times a week">3-4 times a week</option>
                        <option value="5-6 times a week">5-6 times a week</option>
                        <option value="6+ times a week">6+ times a week</option>
                    </select>
                </label>
                <label>
                    Calorie Goal:
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
