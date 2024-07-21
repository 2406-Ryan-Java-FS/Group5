import AdminCalorieGoal from "./admin-calorie-goal";
import { Link } from 'react-router-dom';

export default function AmdinUserManageTable({userList}){
    
    return(<>
     {userList.map((u, index) => (
                <tr key={u.uid}>
                    <td>{index + 1}</td>
                    <td>{u.username}</td>
                    <td><AdminCalorieGoal uId ={u.uid}/></td>
                </tr>
            ))}
    </>)
}