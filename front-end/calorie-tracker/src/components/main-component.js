import { useEffect } from 'react';
import { useAuth } from '../AuthContext';
import { useNavigate } from 'react-router-dom';

export default function MainComponent() {
    const { user } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            if (user.role === 'admin') {
                navigate('/admin');
            } else {
                navigate('/profile');
            }
        } else {
            navigate('/login');
        }
    }, [user, navigate]);

    return null; 
}
