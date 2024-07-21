import { useEffect, useState } from "react"

export default function AdminCalorieGoal({uId}){
    const [userProfile, setUserProfile] = useState([]);

    useEffect(() => {
        if(!uId){
            console.error(`User Id is not defined.`)
        }
        fetch(`http://localhost:8080/api/users/${uId}/profiles`)
        .then(res => res.json())
        .then(setUserProfile)
        .catch(error => {
            console.log(error);
        })
    }, [uId])
    return(<>
        {userProfile? userProfile.calorieGoal : null }
    </>)
}