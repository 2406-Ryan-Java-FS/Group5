import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function CalorieTrackForm(){
    const DEFAULT_CALORIE_TRACK={
        "serving": "",
        "logDate": "",
        "food": {
            "foodName": "",
            "calorie": "",
            "fid": 0
        },
        "uid": {uid},
        "cid": 0
    };

    const[calorieTrack, setCalorieTrack] = useState(DEFAULT_CALORIE_TRACK);
    const[foodList, setFoodList] = useState([]);
    const navigate = useNavigate();

    //Populate food search results
    function handleSearch(event){
        event.preventDefault();
        console.log(`http://localhost:8080/api/food/search?foodName=${searchInput}`)
        fetch(`http://localhost:8080/api/food/search?foodName=${searchInput}`)
            .then(res =>{
                const body = res.json();
                console.log("body: " + body )
                return body;
            })
               .then(info => {
                setFoodList(info)
            })
            .catch(error => {
                console.log(error)
            })

    }

    const handleChange = (event) => {
        const updatedCalorieTrack = {...calorieTrack};

        if(event.target.name === "food"){
            const foodId = Number.parseInt(event.target.value);
            updatedCalorieTrack.food = foodList.find(food => food.fid === foodId)
        }
    }

    


}