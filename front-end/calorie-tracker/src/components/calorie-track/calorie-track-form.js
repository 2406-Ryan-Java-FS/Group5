import { useState } from "react";
import CalorieTrackForm2 from "./calorie-track-form2";

export default function CalorieTrackForm(){

    const[foodList, setFoodList] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    
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

    function handleChange(event){
        console.log("target"+ event.target.value)
        setSearchInput(event.target.value);
       }

    return(
        <div>
            <h3>Search Your Food</h3>
            {/* form for searching food */}
            <form className="doSerachFood" onSubmit={handleSearch}>
                <div className="col mb-2">
                    <input type="text" className="form-control" onChange={handleChange} value={searchInput}id="searchBox"/>
                </div>
                <div className="col">
                    <button className="btn btn-outline btn-primary" type = 'submit'>Search</button>
                </div>
            </form>

            <CalorieTrackForm2 foodList={foodList}/>

        </div>
        
    )

    


}