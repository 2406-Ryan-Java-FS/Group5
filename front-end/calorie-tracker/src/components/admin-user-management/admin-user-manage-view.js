import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import { useAuth } from "../../AuthContext"
import AmdinUserManageTable from "./admin-user-manage-table";

export default function AdminUserManageView(){
    const {user} = useAuth();
    const [userList, setUserList] = useState([]);
    const navigate = useNavigate();

    //fetch all the user's lists
    useEffect(() => {
        fetch(`http://localhost:8080/api/users/1/all`) //This will be an issue for my routing because the id for me is 37...
        .then(res => res.json())
        .then(body => {
            console.log("userList: ", body);
            setUserList(body);
        })
        .catch(error => {
            console.log(error);
            navigate("/");
        });
    },[user, navigate])

    return(<>

        <h2> User Management</h2>
        <table className="table">
            <thead>
                <tr>
                    <th>Index</th>
                    <th>Username</th>
                    <th>Calorie Goal</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody> 
                <AmdinUserManageTable userList={userList}/>
            </tbody>
        </table>
    </>
        
    )
}