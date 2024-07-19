import React, { createContext, useState, useContext, useEffect } from 'react';

// Create context
const AuthContext = createContext();

// Custom hook to use auth context
export const useAuth = () => {
    return useContext(AuthContext);
};

// Auth provider component
export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);

    useEffect(() => {
        // Load user from local storage or API
        const loggedUser = JSON.parse(localStorage.getItem('user'));
        if (loggedUser) {
            setUser(loggedUser);
        }
    }, []);

    const login = (userData) => {
        // Save user to state and local storage
        setUser(userData);
        localStorage.setItem('user', JSON.stringify(userData));
    };
    

    const logout = () => {
        // Clear user from state and local storage
        setUser(null);
        localStorage.removeItem('user');
    };

    const value = {
        user,
        login,
        logout,
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
