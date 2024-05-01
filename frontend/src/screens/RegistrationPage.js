import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import {getAuth, createUserWithEmailAndPassword} from 'firebase/auth';


function RegistrationPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);


    const handleRegistration = async (e) => {
        e.preventDefault();
        const auth = getAuth()
        createUserWithEmailAndPassword(auth, email, password)
            .then(async (userCredential) => {
                // Signed in
                const user = userCredential.user;

                try {
                    if (!email || !username || !password || !confirmPassword) {
                        throw new Error('All fields are required');
                    }
                    if (password !== confirmPassword) {
                        throw new Error('Passwords do not match');
                    }
                    setLoading(true);
                    setError('');


                    console.log('Registering:', email, username, password);

                    let data = {

                    }
                    const response = await axios.post(`http://localhost:8080/api/user/`,data );

                    setError('');
                    alert('Registration successful! Redirecting to login page.');
                    navigate('/');
                } catch (error) {
                    setLoading(false);
                    setError(error.message);
                    console.error('Registration error:', error.message);
                }
            })
    };

    const styles = {
        container: {
            margin: '0 auto',
            width: '300px',
            textAlign: 'center',
            border: '1px solid #ddd',
            padding: '20px',
            borderRadius: '5px',
            marginTop: '100px'
        },
        input: {
            marginBottom: '10px',
            width: '100%',
            height: '40px',
            padding: '0 10px',
            borderRadius: '5px',
            border: '1px solid #ccc'
        },
        button: {
            width: '100%',
            height: '40px',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
        },
        error: {
            color: 'red',
            marginBottom: '10px'
        }
    };

    return (
        <div style={styles.container}>
            <h1>Register</h1>
            {error && <p style={styles.error}>{error}</p>}
            <form onSubmit={handleRegistration}>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    style={styles.input}
                />
                <button type="submit" style={styles.button}>Register</button>
            </form>
        </div>
    );
}

export default RegistrationPage;


