import { Link } from "react-router-dom"
import '../styles/home.css';

export default function Home(){




    return (<>
        <h1 >
           Calorie Tracker Pro
        </h1>
        {/* <img src={require("../styles/download-1.jpg")} alt="Food" className="food-image"/> */}
        <div className="container">
            <Link to="/"></Link>
    
            <Link to="/login"><button>Log In</button></Link>
  
            <Link to="/signup"><button>Sign Up</button></Link>
        </div>
  
     
    </>)
}