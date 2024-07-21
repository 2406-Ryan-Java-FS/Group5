import React, { useState } from 'react';
import '../styles/BMIChart.css';

export default function BMIChart({ userBMI }) {
    // Scale 0 to 60
    const maxBMI = 60;

    // Calculate the position of the user's BMI on the scale
    const bmiPosition = (userBMI / maxBMI) * 100;

    return (
      <div className="bmi-chart">
        <div className="bmi-scale">
          <div
            className="bmi-indicator"
            style={{ left: `${bmiPosition}%` }}
          >
            {userBMI}
          </div>
        </div>
        <div className="bmi-labels">
          <span>0</span>
          <span>30</span>
          <span>60</span>
        </div>
      </div>
    );
}
