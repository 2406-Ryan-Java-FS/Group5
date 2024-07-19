import React, { useState } from 'react';
import Signup from './Signup'; // Adjust path as needed

export default function App() {
    const [user, setUser] = useState({
        username: '',
        email: '',
        // Add more fields as needed
    });

    return (
        <div className="app-container">
            <Signup user={user} setUser={setUser} />
        </div>
    );

}