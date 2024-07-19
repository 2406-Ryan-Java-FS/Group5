import { useEffect } from "react";
import {useAuth} from "../../AuthContext";

export default function CalorieGoalTitle(){
    const { user } = useAuth();
    const uId = 1;
    useEffect(() => {

    },[])

    return(
        <>
            <h1>My Daily Calorie Goal: </h1>
        </>
    )
}