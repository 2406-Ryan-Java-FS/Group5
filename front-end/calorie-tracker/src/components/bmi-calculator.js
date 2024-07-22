import {React, useEffect} from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import BMIChart from './BMIChart.js';

export default function BmiCalculator() {

    const { user } = useAuth();

    let userBMI = 0;

    useEffect(() => {
       
    },[user])

    if (!user) {
        return <p>Please log in to view this page.</p>;
    }
    

    const calculateBMI = () => {
        const heightInMeters = user.height / 100;
        const bmi = user.weight / (heightInMeters * heightInMeters);
        userBMI = bmi.toFixed(2);
        let definition;
        if(bmi < 18.5){
            definition = "Underweight";
        }else if(bmi>=18.5 && bmi<25.0){
            definition = "Healthy";
        }else if(bmi>=25.0 && bmi < 30.0){
            definition = "Overweight";
        }else{
            definition = "Obese";
        }
            // return bmi.toFixed(2);
            return `${bmi.toFixed(2)} (${definition})`; // Round to 2 decimal places
    };
   

  
    return (
        <div className="bmi-calculator">
            <h2>BMI Calculator</h2>
            <p className="bmi-formula text-center">
            BMI = weight (kg) / (height (m) * height (m))
            </p>
            <p><strong>BMI:</strong> {calculateBMI()}</p>
            <BMIChart userBMI = {userBMI} />
            <div className='d-flex justify-content-end'>
             <Link to="/profile/form"><button>Update Profile</button></Link>
            </div>
           
        </div>
    );
}