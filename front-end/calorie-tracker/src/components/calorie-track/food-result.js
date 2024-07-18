import { useState } from "react";
import { Link } from "react-router-dom";

export default function FoodResult(food, index){
    
    const [selectedFood, setSelectedFood] = useState([]);

    return(
        <>
            <tr>
                <td>{index}</td>
                <td>{food.foodName}</td>
                <td>{food.calorie}</td>
                <td><button className="btn btn-outline-primary" onClick>Select</button></td>
            </tr>
        </>
    )
}