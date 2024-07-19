import { useState } from "react";
import { Link } from "react-router-dom";

export default function FoodResult({food, index, onDataChange}){
    
    const sendDataToFoodSearch = () => {
        onDataChange(food);
    };


    return(
        <>
            <tr>
                <td scope="row">{food.fid}</td>
                <td>{food.foodName}</td>
                <td>{food.calorie}</td>
                <td><button className="btn btn-outline-primary" onClick={sendDataToFoodSearch}>Select</button></td>
            </tr>
        </>
    )
}