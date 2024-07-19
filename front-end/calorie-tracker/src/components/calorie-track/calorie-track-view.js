import CalorieTrackTable from "./calorie-track-table";
import {Link} from "react-router-dom";
import React, { useState } from "react";
import { format, addDays, addWeeks, subWeeks, startOfWeek, endOfWeek } from "date-fns";
import CalorieTrackCard from "./calorie-track-card";

export default function CalorieTrackView(){

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
            <h1>My calorie goal: </h1>
            <div className="addCalorieBtnBox d-flex justify-content-end">
            <Link type="button" className="btn btn-primary" to={'/calorietrack/add'} id='addCalorieTrackBtn'>+</Link>
            </div>
            
            <div className="d-flex justify-content-between">
                <button onClick={handlePreviousWeek}>Previous Week</button>
                <button onClick={handleNextWeek}>Next Week</button>
            </div>
            <table border="1">
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
        </div>
    );

    
}