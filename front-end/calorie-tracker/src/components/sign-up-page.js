import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/signup.css';
import toast, {Toaster} from 'react-hot-toast';

export default function SignUpPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role] = useState('USER');  // Automatically set role to 'USER'
    const navigate = useNavigate();

    const handleSignup = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password, role }), // Include role here
            });

            if (response.ok) {
                console.log('Signup successful');
                console.log(username, password, role)
                toast.success("Signup Successful! :)")
                navigate('/login'); // Redirect after successful signup
            } else {
                const errorData = await response.json();
                console.error('Signup failed:', errorData);
                toast.error(errorData.message)
            }
        } catch (error) {
            console.error('Signup error:', error);
            navigate("/")
        }
    };

    return (
        <div className="signup-container">
             <Toaster
                position = "top-center"/>
            <h2>Sign Up</h2>
            <form onSubmit={handleSignup}>
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
