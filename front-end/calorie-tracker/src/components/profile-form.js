import React, { useState, useEffect } from 'react';
import { useAuth } from '../AuthContext';
import { useNavigate } from 'react-router-dom';

export default function ProfileForm() {
    const { user, login } = useAuth();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        gender: user ? user.gender : '',
        weight: user ? user.weight : '',
        height: user ? user.height : '',
        activityLevel: user ? user.activityLevel : '',
        calorieGoal: user ? user.calorieGoal : '',
    });

    useEffect(() => {
        if (!user) {
            // Redirect to login if user is not authenticated
            navigate('/login');
        }
    }, [user, navigate]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Update user profile in state and possibly backend
        login({ ...user, ...formData });
        // Optionally, send the updated user data to your backend here
        navigate('/profile');
    };

    return (
        <div className="profile-form">
            <h2>Edit Profile</h2>
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
                        <option value="">Select</option>
                        <option value="1-2 times a week">1-2 times a week</option>
                        <option value="3-4 times a week">3-4 times a week</option>
                        <option value="5-6 times a week">5-6 times a week</option>
                        <option value="7+ times a week">7+ times a week</option>
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
