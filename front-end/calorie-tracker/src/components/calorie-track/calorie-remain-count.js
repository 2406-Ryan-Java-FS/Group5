import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import {format} from 'date-fns';
import { useAuth } from "../../AuthContext";

export default function CalorieRemainCount({date, calorieGoal}){
    const { user } = useAuth();
    const formattedDate = format(date, 'yyyy-MM-dd');

    const [ctListforADay, setCtListForADay] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        if(user){
            fetch(`http://localhost:8080/api/calorietrack/users/${user.uid}/date?logDate=${formattedDate}`)
        .then(res => {
            const body = res.json();
            // console.log(body);
            return body})
        .then(setCtListForADay)
        .catch(error => {
            navigate("/calorietrack")
        })
        }
        
    },[formattedDate, navigate, user.uid]);

    let totalCalorieCount = 0;
    if(ctListforADay && ctListforADay.length >0){
        for(let i=0; i<ctListforADay.length; i++){
            totalCalorieCount += (ctListforADay[i].food.calorie * ctListforADay[i].serving);
        }
    }
    

   

    return(
        <>
            <td className="text-center">
               <b>{calorieGoal==0? 0: (calorieGoal - totalCalorieCount)} </b> cals
            </td>
        </>
    )

}