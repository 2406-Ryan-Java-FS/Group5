import { Link, useNavigate } from "react-router-dom";
import FoodSearch from "./food-search";
import {useState, useRef} from "react";
import ValidationSummary from "../ValidationSummary";

export default function CalorieTrackForm2({selectedFood}){
    const uid = 1;
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
    }

    //TODO: getting today's date and put it as a placeholder!!!!!
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;


    const[calorieTrack, setCalorieTrack] = useState(DEFAULT_CALORIE_TRACK);

    const [errors, setErrors] = useState([]);
    const navigate = useNavigate();

    // handles value changes of inputs
    function handleChange(event){
        setCalorieTrack({
            ...calorieTrack,
            [event.target.name]:
                 event.target.value,
        });
        console.log("food: " + calorieTrack.food.foodName)
        console.log("calorietrack: " + calorieTrack.logDate)
    }
    
    // handle submit
    const handleSubmit = (evt) => {
        evt.preventDefault();
        addCalorieTrack();
    }

    function addCalorieTrack(){
        //check if user is 'user'
        //else throw an error..?

        const config = {
            method: "POST",
            haders:{
                'Content-Type':'application/json',
            },
            body: JSON.stringify(calorieTrack)
        };

        fetch("http://localhost:8080/api/calorietrack", config)
        .then(res => {
            if(res.ok){
                console.log("res.ok")
                navigate("/calorietrack");
            }else{
                return res.json();
            }
        })
        // .then(err => {
        //     if(err){
        //         console.log("error!")
        //         return Promise.reject(err);
        //     }
        // })
        // .catch(errs => {
        //     if(errs.length){
        //         setErrors(errs);
        //     }else{
        //         setErrors([errs]);
        //     }
        // });
    }

    return (<div>
        <h3>Log your calorie</h3>
        <ValidationSummary errors={errors}/>
        <form onSubmit={handleSubmit}>
            <div>
                uId
                <input type="number" name="uid" id="uid" 
                value={calorieTrack.uid} 
                onChange={handleChange}/>
            </div>
            <div>
                fId
                <input type="number" name="fid" id="fid" 
                value={selectedFood.fid} 
                onChange={handleChange}/>
            </div>
            <div>
                <label htmlFor="foodName" >Food : </label>
                <input type="text" 
                name="foodName"
                id="foodName" 
                value={selectedFood.foodName}
                onChange={handleChange}></input>
            </div>
            <div>
                <label htmlFor="calorie" >Calories : </label>
                <input type="text" 
                name="calorie"
                id="calorie"
                value={selectedFood.calorie} 
                onChange={handleChange}/>
            </div>
            
            <div>
                <label htmlFor="serving">Servings : </label>
                <input type="number" 
                        min="0"
                        name="serving"
                        placeholder="0.0"
                        id="serving"
                        value={calorieTrack.serving}
                        onChange={handleChange}/>
            </div>
            <div>
                <label htmlFor="logDate">date : </label>
                <input type="date"
                        name="logDate"
                        id="logDate"
                        value={calorieTrack.logDate}
                        placeholder={formattedDate}
                        onChange={handleChange}/>
            </div>
                <div className="d-flex justify-content-end pb-3">
                    <button id="addTrack"
                    calssName='btn btn-success me-2'
                    type='submit'>Submit</button>
                    <Link className='btn btn-secondary' id="addTrackCancleBtn" to='/calorietrack'>Cancel</Link>
                </div>
            
        </form>
    
    </div>)
}