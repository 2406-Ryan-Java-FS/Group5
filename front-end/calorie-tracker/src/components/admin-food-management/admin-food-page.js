import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../AuthContext';

const AdminFoodPage = () => {
    const { user } = useAuth();
    const [newFood, setNewFood] = useState({ foodName: '', calorie: 0 });
    const navigate = useNavigate();

    const handleAddFood = (e) => {
        e.preventDefault();
        fetch(`http://localhost:8080/api/users/${user.uid}/food`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newFood),
        })
            .then(response => response.json())
            .then(() => {
                setNewFood({ foodName: '', calorie: 0 });
                alert('Food added successfully!');
            })
            .catch(error => console.error('Error adding food item:', error));
    };

    const handleManageFood = () => {
        navigate('/admin/manage-food');
    };

    return (
        <div className="admin-food-page">
            <h2>Admin Dashboard</h2>
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
            <button onClick={handleManageFood}>View All Foods</button>
        </div>
    );
};

export default AdminFoodPage;
