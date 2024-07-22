import React, { useEffect, useState } from 'react';
import { useAuth } from '../AuthContext';
import '../styles/adminfood.css';

const AdminFoodManageTable = () => {
    const { user } = useAuth();
    const [foodItems, setFoodItems] = useState([]);
    const [editFood, setEditFood] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/37/food/all`)
            .then(response => response.json())
            .then(data => setFoodItems(data))
            .catch(error => console.error('Error fetching food items:', error));
    }, [user.uId]);

    const handleUpdateFood = (foodId) => {
        fetch(`http://localhost:8080/api/users/37/food/${foodId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editFood),
        })
            .then(response => response.json())
            .then(data => {
                setFoodItems(foodItems.map(item => (item.fId === foodId ? data : item)));
                setEditFood(null);
            })
            .catch(error => console.error('Error updating food item:', error));
    };

    const handleDeleteFood = (foodId) => {
        fetch(`http://localhost:8080/api/users/37/food/${foodId}`, {
            method: 'DELETE',
        })
            .then(() => {
                setFoodItems(foodItems.filter(item => item.fId !== foodId));
            })
            .catch(error => console.error('Error deleting food item:', error));
    };

    return (
        <div className="admin-food-manage-table">
            <h2>Manage Foods</h2>
            <table>
                <thead>
                    <tr>
                        <th>Food Name</th>
                        <th>Calories</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {foodItems.map(item => (
                        <tr key={item.fId}>
                            <td>{item.foodName}</td>
                            <td>{item.calorie}</td>
                            <td>
                                <button onClick={() => setEditFood(item)}>Edit</button>
                                <button onClick={() => handleDeleteFood(item.fId)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {editFood && (
                <div>
                    <h3>Edit Food Item</h3>
                    <form onSubmit={(e) => { e.preventDefault(); handleUpdateFood(editFood.fId); }}>
                        <label>
                            Food Name:
                            <input
                                type="text"
                                value={editFood.foodName}
                                onChange={(e) => setEditFood({ ...editFood, foodName: e.target.value })}
                                required
                            />
                        </label>
                        <label>
                            Calorie:
                            <input
                                type="number"
                                value={editFood.calorie}
                                onChange={(e) => setEditFood({ ...editFood, calorie: parseInt(e.target.value) })}
                                required
                            />
                        </label>
                        <button type="submit">Update Food</button>
                        <button type="button" onClick={() => setEditFood(null)}>Cancel</button>
                    </form>
                </div>
            )}
        </div>
    );
};

export default AdminFoodManageTable;
