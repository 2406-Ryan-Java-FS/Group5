import React, { useEffect, useState } from 'react';
import { useAuth } from '../AuthContext';

const AdminFoodPage = () => {
    const { user } = useAuth();
    const [foodItems, setFoodItems] = useState([]);
    const [newFood, setNewFood] = useState({ foodName: '', calorie: 0 });
    const [editFood, setEditFood] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/${user.uId}/food/all`)
            .then(response => response.json())
            .then(data => setFoodItems(data))
            .catch(error => console.error('Error fetching food items:', error));
    }, [user.uId]);

    const handleAddFood = (e) => {
        e.preventDefault();
        fetch(`http://localhost:8080/api/users/${user.uId}/food`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newFood),
        })
            .then(response => response.json())
            .then(data => {
                setFoodItems([...foodItems, data]);
                setNewFood({ foodName: '', calorie: 0 });
            })
            .catch(error => console.error('Error adding food item:', error));
    };

    const handleUpdateFood = (foodId) => {
        fetch(`http://localhost:8080/api/users/${user.uId}/food/${foodId}`, {
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
        fetch(`http://localhost:8080/api/users/${user.uId}/food/${foodId}`, {
            method: 'DELETE',
        })
            .then(() => {
                setFoodItems(foodItems.filter(item => item.fId !== foodId));
            })
            .catch(error => console.error('Error deleting food item:', error));
    };

    return (
        <div className="admin-food-page">
            <h2>Manage Foods</h2>

            <form onSubmit={handleAddFood}>
                <h3>Add New Food Item</h3>
                <label>
                    Food Name:
                    <input
                        type="text"
                        value={newFood.foodName}
                        onChange={(e) => setNewFood({ ...newFood, foodName: e.target.value })}
                        required
                    />
                </label>
                <label>
                    Calorie:
                    <input
                        type="number"
                        value={newFood.calorie}
                        onChange={(e) => setNewFood({ ...newFood, calorie: parseInt(e.target.value) })}
                        required
                    />
                </label>
                <button type="submit">Add Food</button>
            </form>

            <h3>Food Items</h3>
            <ul>
                {foodItems.map(item => (
                    <li key={item.fId}>
                        {item.foodName} - {item.calorie} calories
                        <button onClick={() => handleDeleteFood(item.fId)}>Delete</button>
                        <button onClick={() => setEditFood(item)}>Edit</button>
                    </li>
                ))}
            </ul>

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

export default AdminFoodPage;
