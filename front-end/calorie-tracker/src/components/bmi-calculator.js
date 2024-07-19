import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';

export default function BmiCalculator() {

    const { user } = useAuth();

    if (!user) {
        return <p>Please log in to view this page.</p>;
    }

    const calculateBMI = () => {
        const heightInMeters = user.height / 100;
        const bmi = user.weight / (heightInMeters * heightInMeters);
        return bmi.toFixed(2); // Round to 2 decimal places
    };

    return (
        <div className="bmi-calculator">
            <h2>BMI Calculator</h2>
            <p><strong>BMI:</strong> {calculateBMI()}</p>
            <Link to="/profile/form"><button>Update Profile</button></Link>
        </div>
    );
}