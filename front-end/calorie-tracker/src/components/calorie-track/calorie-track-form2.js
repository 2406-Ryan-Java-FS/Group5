import { Link, useNavigate, useParams } from "react-router-dom";
import {useEffect, useState} from "react";
import ValidationSummary from "../ValidationSummary";
import {useAuth} from "../../AuthContext"
import toast, {Toaster} from 'react-hot-toast';

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
            fid: 0,
            uid: 1
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
                toast.success("Added a log Successfully! :)");
                navigate("/calorietrack");
            }else{
                return res.json();
            }
        })
        .then(err => {
            if(err){
                console.log("error!")
                toast.error("Adding a log failed :(");
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
                toast.success("Update success! :)")
                navigate('/calorietrack')
            }else if(res.status === 400){
                const body = res.json();
                console.log(body);
                return body;
            }
        })
        .then(errors => {
            toast.error("Failed to update :(")
            setErrors(errors);
        })
        .catch(error => {
            console.error(error);
            navigate("/calorietrack");
        });
    }


    return (<div className="mt-4">
        <h3 className="mb-4">Log your calories</h3>
        <Toaster position="top-center" />
        <ValidationSummary errors={errors}/>
        <form onSubmit={handleSubmit}>
            <div>
                <input type="number" name="uid" id="uid" 
                value={calorieTrack.uid} 
                onChange={handleChange} hidden/>
            </div>
            <div>
                <input type="number" name="foodUid" id="foodUid" 
                value={calorieTrack.food.uid} 
                onChange={handleChange} hidden/>
            </div>
            <div className="mb-2">
                <label className htmlFor="food">Food:
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
                {cId?<div className="originalFood mt-3"><b>{calorieTrack.food.foodName}-{calorieTrack.food.calorie}cal</b></div>:<></>}
                </label>
            </div>
            
            <div className="mb-2">
                <label className="form-label" htmlFor="serving">Servings :
                <input type="number" 
                        min="0"
                        name="serving"
                        placeholder="0.0"
                        id="serving"
                        value={calorieTrack.serving}
                        step="any"
                        onChange={handleChange}/>
                         </label>
            </div>
            <div>
                <label htmlFor="logDate">date : 
                <input type="date"
                        name="logDate"
                        id="logDate"
                        value={calorieTrack.logDate}
                        placeholder={formattedDate}
                        onChange={handleChange}/>
                </label>
            </div>
                <div className="trackForm d-flex justify-content-end pb-3">
                    <button id="addTrack"
                    calssName='btn btn-success me-2'
                    type='submit'>Submit</button>
                    <Link className='btn btn-secondary' id="addTrackCancleBtn" to='/calorietrack'>Cancel</Link>
                </div>
            
        </form>
    
    </div>)
}