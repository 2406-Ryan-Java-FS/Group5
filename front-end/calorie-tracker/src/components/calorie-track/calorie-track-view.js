import {Link, useNavigate} from "react-router-dom";
import React, { useEffect, useState } from "react";
import { format, addDays, addWeeks, subWeeks, startOfWeek } from "date-fns";
import CalorieTrackCard from "./calorie-track-card";
import { useAuth } from "../../AuthContext";
import CalorieTotalCount from "./calorie-total-count";
import CalorieRemainCount from "./calorie-remain-count";

export default function CalorieTrackView(){

    const { user } = useAuth();
    const navigate = useNavigate();
    const [profile, setProfile] = useState();
    useEffect(() => {
        if(user){
            fetch(`http://localhost:8080/api/users/${user.uid}/profiles`)
            .then((res) => {
                const body = res.json();
                console.log(body);
                return body;
            })
            .then(setProfile)
            .catch(error => {
                //navigate to profile page so that the user can enter the profile.
                // navigate("/profile")
                console.error(error)
            })
        }else{
            navigate("/error")
        }
    }, [user])
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
    
    if(!user){
        return(<div>
            <Link type="login" to="/login">Log in and start to log your calories!</Link>
        </div>)
    }

    return (
        <div className="container mt-3" >
             {profile?(<h1>My Daily Calorie Goal: {profile.calorieGoal}</h1>):(<Link to={"/profile"} className="text-center"><span className="setCalorieLink">Set Calorie your daily Goal!</span></Link>)}
           
            <div className="addCalorieBtnBox d-flex justify-content-end">
                <Link type="button" className="btn btn-primary mt-3" to={'/calorietrack/add'} id='addCalorieTrackBtn'>+</Link>
            </div>
            
            <div className="d-flex justify-content-between mt-2">
                <button className="weekBtn" onClick={handlePreviousWeek}>Previous Week</button>
                <button className="weekBtn" onClick={handleNextWeek}>Next Week</button>
            </div>
            <table className="table table-border mt-2" >
                <thead>
                    <tr>
                        <th className="text-center">Date</th>
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
                        <th className="text-center">Logs</th>
                        {dates.map((date)=>(
                             <CalorieTrackCard date={date}/>
                        ))}
                      
                    </tr>
                    <tr>
                        <th className="text-center">Total 
                            <div>Calorie</div> intake</th>
                        {dates.map((date) => (
                            <CalorieTotalCount date={date}/>
                        ))}
                    </tr>
                    <tr>
                        <th className="text-center">Calorie Goal -
                            <div>Total Calorie</div>
                        </th>
                        {dates.map((date) => (
                            <CalorieRemainCount date={date} calorieGoal={profile?profile.calorieGoal:0}/>
                        ))}
                    </tr>
                </tbody>
            </table>
            
        </div>
    );

    
}