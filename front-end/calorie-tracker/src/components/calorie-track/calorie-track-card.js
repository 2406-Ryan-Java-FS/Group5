import { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom";
import {format} from 'date-fns';

export default function CalorieTrackCard({date}){
    //Add authcontext instead!!
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

    return(
        <td>
        {ctListforADay&&(ctListforADay.map((ct, index) => (
            <>
            <div className="d-flex" key={index}>
                <div>
                    {ct.food.foodName} {ct.serving} {ct.food.calorie * ct.serving}cal
                </div>
                <div>
                    <Link to={`/calorietrack/edit/${ct.cid}`}><i class="bi bi-arrow-up-circle-fill"></i></Link>
                </div>
                <div>
                    <Link to={`/calorietrack/delete/${ct.cid}`}><i class="bi bi-x-circle-fill"></i></Link>
                </div>
            </div>
            <hr/>
            </>
            
        ))
        )}
        </td>
    );
}