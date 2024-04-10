import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function LoginPage() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');


    const handleLogin = (event) => {
        event.preventDefault();
        if (username && password) {
            console.log('Logging in with:', username, password);
            // Correct usage of navigate to go to the search page
            navigate('/search'); // Navigate to search page or dashboard
        } else {
            setError('Both username and password are required');
        }
    };

    const handleSignUp = () => {
        navigate('/register'); // Navigate to the registration page
    };

    // Inline CSS
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
            cursor: 'pointer',
            marginBottom: '10px', // Added margin-bottom for spacing
        },
        signUpButton: {
            width: '100%',
            height: '40px',
            backgroundColor: '#28a745',
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
            <h1>Login</h1>
            {error && <p style={styles.error}>{error}</p>}
            <form onSubmit={handleLogin}>
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
                <button type="submit" style={styles.button}>Login</button>
            </form>
            <button onClick={handleSignUp} style={styles.signUpButton}>Sign Up</button>
        </div>
    );
}

export default LoginPage;