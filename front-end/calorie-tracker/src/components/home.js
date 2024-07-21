import { Link } from "react-router-dom"
import '../styles/home.css';

export default function Home(){




    return (<>
        <div className="logo d-flex justify-content-center mb-4">
        <img src='/Red.png' alt='Calorie Track Pro Logo' width='100' />
        </div>
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