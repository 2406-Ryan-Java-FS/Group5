import { useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import FoodResult from "./food-result";
import CalorieTrackForm from "./calorie-track-form";

export default function FoodSearch(){

   const [foodList, setFoodList] = useState([]);
   const [searchInput, setSearchInput] = useState("");
 
   const navigate = useNavigate();

   function handleChange(event){
    console.log("target"+ event.target.value)
    setSearchInput(event.target.value);
   }

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
                console.log("info: " +info)
                console.log("typeof(info): " + typeof(info))
                console.log("info.length: " + info.length)
                console.log("info.data: " + info.data)
                console.log("typeof(info.data): " + typeof(info.data))
                setFoodList(info)
            })
            .catch(error => {
                console.log(error)
            })

    }
    let index = 0;

   
    return(
        <div>
        <h3>Search Your Food</h3>
        <form className="doSerachFood" onSubmit={handleSearch}>
            <div className="col mb-2">
                <input type="text" className="form-control" onChange={handleChange} value={searchInput}id="searchBox"/>
            </div>
            <div className="col">
                <button className="btn btn-outline btn-primary" type = 'submit'>Search</button>
            </div>
        </form>
        <div className="col" id="foodSearchResult">
        {foodList && foodList.length > 0 ? (
                    <ul>
                        {foodList.map((food, index) => (
                            <li key={index}>{food.foodName} - {food.calorie} calories</li>
                        ))}
                    </ul>
                ) : (
                    <p>No results found</p>
                )}
        </div>
        <div>
            {<CalorieTrackForm />}
        </div>
        </div>
    )
}