import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/signup.css';

export default function SignUpPage(){
    
        const [username, setUsername] = useState('');
        const [password, setPassword] = useState('');
    
        const handleSignup = async (e) => {
            e.preventDefault();
    
            // Example: Send signup data to backend
            const response = await fetch('/api/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });
    
            if (response.ok) {
                // Redirect or handle successful signup
                console.log('Signup successful');
            } else {
                // Handle signup error
                console.error('Signup failed');
            }
        };
    
        return (
            <div className="signup-container">
                <h2>Sign Up</h2>
                <form onSubmit={handleSignup}>
                    <label>
                        Email:
                        <input
                            type="email"
                            required
                        />
                    </label>
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
                    <button type="submit">Sign Up</button>
                </form>
                <p>
                    Already have an account? <Link to="/login">Log In</Link>
                </p>
            </div>
        );
    }
    