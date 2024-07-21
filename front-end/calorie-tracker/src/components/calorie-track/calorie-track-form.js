import {Link} from 'react-router-dom';
import { useState } from "react";
import {useAuth} from "../../AuthContext"
import CalorieTrackForm2 from "./calorie-track-form2";
import '../../styles/calorie-track-form.css';

export default function CalorieTrackForm(){

    const {user} = useAuth();
    const[foodList, setFoodList] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    
    //Populate food search results
    function handleSearch(event){
        event.preventDefault();
        fetch(`http://localhost:8080/api/users/${user.uid}/food/search?foodName=${searchInput}`)
            .then(res =>{
                const body = res.json();
                console.log("body: " + body )
                return body;
            })
               .then(info => {
                setFoodList(info)
                setSearchInput("");
            })
            .catch(error => {
                console.log(error)
            })

    }

    function handleChange(event){
        console.log("target"+ event.target.value)
        setSearchInput(event.target.value);

        
    }

    if(!user){
        return(<div>
            <Link type="login" to="/login">Log in and start to log your calories!</Link>
        </div>)
    }


    return(
        <div className=" calorie-track-form">
            <div className="foodSearchBox">
            <h3 className="mt-3 mb-3">Search Your Food</h3>
            {/* form for searching food */}
            <form className="doSerachFood" onSubmit={handleSearch}>
                <div className=" mb-2">
                    <div className="d-flex justify-content-start">
                    <input type="text" className="form-control" onChange={handleChange} value={searchInput} id="searchBox"/>
                    </div>
                </div>
                <div className="d-flex justify-content-end">
                    <button className="btn btn-outline-success" type = 'submit'>Search</button>
                </div>
            </form>
            </div>
            <hr/>
            
            <CalorieTrackForm2 foodList={foodList}/>

        </div>
        
    )

    


}