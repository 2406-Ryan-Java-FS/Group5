import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/login.css';
import { useAuth } from '../AuthContext';
import toast, {Toaster} from 'react-hot-toast';


export default function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const { login } = useAuth();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const userData = await response.json();
                console.log('Login successful', userData);
                toast.success("Login Successful! :)")
                login(userData);

                if (userData.role === 'ADMIN') {
                    navigate('/admin');
                } else {
                    navigate('/profile');
                }
               
            } else {
                const errorData = await response.json();
                console.error('Login failed:', errorData);
                toast.error("Login Failed")
            }
        } catch (error) {
            console.error('Login error:', error);
            navigate('/')
        }
    };

    return (
        <div className="login-container">
            <Toaster
                position = "top-center"/>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <label>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <button type="submit">Login</button>
            </form>
            <p className='mt-2'>
                Don't have an account? <Link to="/signup">Sign Up</Link>
            </p>
        </div>
    );
}
