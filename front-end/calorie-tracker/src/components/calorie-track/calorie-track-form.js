import FoodSearch from "./food-search";

export default function CalorieTrackForm(){
    const DEFAULT_CALORIE_TRACK={
        fid: "",
        servings: "",
        date: "",
    }



    return (<div>
        <form>
            <div>
                <input type="number" name="uId" id="uId" value="1" onChange={handleChange}></input>
            </div>
            <div>
                <label htmlFor >Food : </label>
                <input type="text"></input>
            </div>
            <div>
                <label >servings : </label>
                <input type="number" min="0"></input>
            </div>
            <div>
                <label >date : </label>
                <input type="date"></input>
            </div>
            
        </form>
    
    </div>)
}