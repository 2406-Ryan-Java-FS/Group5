import {Link} from "react-router-dom";
import React, { useState } from "react";
import { format, addDays, addWeeks, subWeeks, startOfWeek, endOfWeek } from "date-fns";
import CalorieTrackCard from "./calorie-track-card";
import { useAuth } from "../../AuthContext";
import CalorieTrackDelete from "./calorie-track-delete";

export default function CalorieTrackView(){

    const { user } = useAuth();

    const [currentDate, setCurrentDate] = useState(new Date());

    const startOfWeekDate = startOfWeek(currentDate, { weekStartsOn: 0 }); // Week starts on Sunday

    // Generate an array of dates for the current week
    const dates = [];
    for (let i = 0; i < 7; i++) {
        dates.push(addDays(startOfWeekDate, i));
    }

    const handlePreviousWeek = () => {
        setCurrentDate((prevDate) => subWeeks(prevDate, 1));
    };

    const handleNextWeek = () => {
        setCurrentDate((prevDate) => addWeeks(prevDate, 1));
    };

    return (
        <div>
            <h1>My Daily Calorie Goal: </h1>
            <div className="addCalorieBtnBox d-flex justify-content-end">
            <Link type="button" className="btn btn-primary" to={'/calorietrack/add'} id='addCalorieTrackBtn'>+</Link>
            </div>
            
            <div className="d-flex justify-content-between">
                <button onClick={handlePreviousWeek}>Previous Week</button>
                <button onClick={handleNextWeek}>Next Week</button>
            </div>
            <table className="table table-border" >
                <thead>
                    <tr>
                        {dates.map((date, index) => (
                            <th key={index}>
                                 <div className="text-center">{format(date, "yyyy-MM-dd")}</div>
                                 <div className="text-center"> {format(date, "EE")}</div>
                                
                               </th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        {dates.map((date)=>(
                             <CalorieTrackCard date={date}/>
                        ))}
                      
                    </tr>
                </tbody>
            </table>
            <CalorieTrackDelete />
        </div>
    );

    
}