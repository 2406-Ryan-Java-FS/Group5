import { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom";
import {format} from 'date-fns';
import { useAuth } from "../../AuthContext";

export default function CalorieTrackCard({date}){
    //Add authcontext instead!!
    const { user } = useAuth();
    const formattedDate = format(date, 'yyyy-MM-dd');

    const [ctListforADay, setCtListForADay] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/api/calorietrack/users/${user.uid}/date?logDate=${formattedDate}`)
        .then(res => {
            const body = res.json();
            // console.log(body);
            return body})
        .then(setCtListForADay)
        .catch(error => {
            navigate("/calorietrack")
        })
    },[formattedDate, navigate, user.uid]);

    return (
        <>
            <td>
                {ctListforADay && ctListforADay.map((ct, index) => (
                    <div className="d-flex justify-content-between" key={index}>
                        
                        <div className="log-detail">
                            <div> <b>{ct.food.foodName}</b></div>
                            <div>{ct.serving} servs</div> 
                            <div>{ct.food.calorie} cal</div>
                            
                        </div>
                        <div className="d-flex">
                            <div>
                                    <Link to={`/calorietrack/edit/${ct.cid}`}>
                                        <i className="bi bi-arrow-up-circle-fill"></i>
                                    </Link>
                                </div>
                            
                            <div>
                                <Link to={`/calorietrack/delete/${ct.cid}`}>
                                <i className="bi bi-x-circle-fill"></i>
                                </Link>
                            </div>
                        </div>
                       
                    </div>
                ))}
            </td>
        
        </>
    );

}