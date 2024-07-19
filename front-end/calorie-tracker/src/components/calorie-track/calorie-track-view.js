import {Link} from "react-router-dom";
import React, { useEffect, useState } from "react";
import { format, addDays, addWeeks, subWeeks, startOfWeek, endOfWeek } from "date-fns";
import CalorieTrackCard from "./calorie-track-card";
import { useAuth } from "../../AuthContext";
import CalorieTrackDelete from "./calorie-track-delete";
import CalorieTotalCount from "./calorie-total-count";
import CalorieGoalTitle from "./calorie-goal-title";

export default function CalorieTrackView(){

    const { user } = useAuth();
    const uId = 1;
    if(user){

    }

    // useEffect(() => {
    //     if(user){
    //         fetch(`http://localhost:8080/users/${uId}/profiles`)
    //     }
    // }, [])
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
            {/* <CalorieGoalTitle /> */}
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
                        <th>Date</th>
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
                        <th>Logs</th>
                        {dates.map((date)=>(
                             <CalorieTrackCard date={date}/>
                        ))}
                      
                    </tr>
                    <tr>
                        <th>Total 
                            <div>Calorie</div> intake</th>
                        {dates.map((date) => (
                            <CalorieTotalCount date={date}/>
                        ))}
                    </tr>
                </tbody>
            </table>
            
        </div>
    );

    
}