import { Link, useNavigate, useParams } from "react-router-dom";
import {useEffect, useState} from "react";
import ValidationSummary from "../ValidationSummary";
import {useAuth} from "../../AuthContext"

export default function CalorieTrackForm2({foodList}){
    const {user} = useAuth();

     //TODO: getting today's date and put it as a placeholder!!!!!
     const today = new Date();
     const year = today.getFullYear();
     const month = String(today.getMonth() + 1).padStart(2, '0');
     const day = String(today.getDate()).padStart(2, '0');
     const formattedDate = `${year}-${month}-${day}`;

    const DEFAULT_CALORIE_TRACK={
        serving: "",
        logDate: formattedDate,
        food: {
            foodName: "",
            calorie: "",
            fid: 0
        },
        uid: user.uid,
        cid: 0
    }

   
    const[calorieTrack, setCalorieTrack] = useState(DEFAULT_CALORIE_TRACK);

    const [errors, setErrors] = useState([]);
    const navigate = useNavigate();
    const {cId} = useParams();


    useEffect(() => {
        if(cId){
            fetch(`http://localhost:8080/api/calorietrack/${cId}`)
            .then(res => res.json())
            .then(setCalorieTrack)
            .catch(error => {
                setErrors(error);
                navigate=("/error");
            })
        }
    },[cId])

    // handles value changes of inputs
    const handleChange = (event) => {
        const updatedCalorieTrack = {...calorieTrack};

        if(event.target.name === "food"){
            const foodId = Number.parseInt(event.target.value);
            updatedCalorieTrack.food = foodList.find(food => food.fid === foodId)
        }else{
            updatedCalorieTrack[event.target.name] = event.target.value;
        }
        setCalorieTrack(updatedCalorieTrack);
    };
    // handle submit
    const handleSubmit = (evt) => {
        evt.preventDefault();
        if(cId > 0){
            editCalorieTrackForm();
        }else{
            addCalorieTrack();    
        }
    }

    function addCalorieTrack(){
        //check if user is 'user'
        //else throw an error..?

        const config = {
            method: "POST",
            headers:{
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
        .then(err => {
            if(err){
                console.log("error!")
                return Promise.reject(err);
            }
        })
        .catch(errs => {
            if(errs.length){
                setErrors(errs);
            }else{
                setErrors([errs]);
            }
        });
    }

    function editCalorieTrackForm(){
        const config = {
            method: "PUT",
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(calorieTrack)
        };

        fetch("http://localhost:8080/api/calorietrack/" + calorieTrack.cid, config)
        .then(res => {
            if(res.ok){
                navigate('/calorietrack')
            }else if(res.status === 400){
                return res.json()
            }
        })
        .then(errors => {
            setErrors(errors);
        })
        .catch(error => {
            console.error(error);
            navigate("/calorietrack");
        });
    }


    return (<div>
        <h3>Log your calorie</h3>
        <ValidationSummary errors={errors}/>
        <form onSubmit={handleSubmit}>
            <div>
                uId
                <input type="number" name="uid" id="uid" 
                value={calorieTrack.uid} 
                onChange={handleChange} hidden/>
            </div>
            <div>
                <label htmlFor="food">Food</label>
                <select
                id="food"
                required
                value={calorieTrack.food.fid}
                name="food"
                onChange={handleChange}
                >
                <option value="0">-- Choose --</option>
                {foodList.map(food => (
                    <option key={food.fid} value={food.fid}>{food.foodName} - {food.calorie}cal</option>
                ))}
                </select>
                {cId?<div>{calorieTrack.food.foodName}-{calorieTrack.food.calorie}cal</div>:<></>}
            </div>
            
            <div>
                <label htmlFor="serving">Servings : </label>
                <input type="number" 
                        min="0"
                        name="serving"
                        placeholder="0.0"
                        id="serving"
                        value={calorieTrack.serving}
                        step="any"
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