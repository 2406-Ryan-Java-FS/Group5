import { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom";
import {format} from 'date-fns';
import { useAuth } from "../../AuthContext";

export default function CalorieTotalCount({date}){
    const { user } = useAuth();
    const uId = 1;
    const formattedDate = format(date, 'yyyy-MM-dd');

    const [ctListforADay, setCtListForADay] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/api/calorietrack/user/${uId}/date?logDate=${formattedDate}`)
        .then(res => {
            const body = res.json();
            console.log(body);
            return body})
        .then(setCtListForADay)
        .catch(error => {
            navigate("/calorietrack")
        })
    },[formattedDate, navigate, uId]);

    let totalCalorieCount = 0;
    if(ctListforADay && ctListforADay.length >0){
        for(let i=0; i<ctListforADay.length; i++){
            console.log("ct[i]: " + ctListforADay[i])
            console.log("ct[i].food.foodName" + ctListforADay[i].food.foodName)
            console.log(typeof(ctListforADay[i].food.calorie))
            console.log(typeof(ctListforADay[i].serving))
            totalCalorieCount += (ctListforADay[i].food.calorie * ctListforADay[i].serving);
        }
    }
    

   

    return(
        <>
            <td>
                {totalCalorieCount} calories
            </td>
        </>
    )

}