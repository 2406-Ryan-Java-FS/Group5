import { useEffect, useState } from "react"

export default function CalorieTrackTable(){

    const [calorieLogs, setCalorieLogs] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/calorietrack/user/1`)
        .then(res => res.json())
        .then(data => setCalorieLogs(data));
    },[]);


    
    return(<>
        {calorieLogs.map(calorieLog => {
            return( <p>{calorieLog.food.foodName} {calorieLog.food.calorie}</p>)

            
        })}
    
    </>)
        
}