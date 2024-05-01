import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { auth } from './firebaseConfig'; // Import your Firebase auth instance
import { signInWithEmailAndPassword } from 'firebase/auth';

function LoginPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const userCredential = await signInWithEmailAndPassword(auth, email, password);
            const user = userCredential.user;
            // Navigate to the search page upon successful login
            navigate('/search');
            console.log('Logged in user:', user);
        } catch (error) {
            setError('Failed to login. Please check your credentials.');
            console.error('Login error:', error.message); // Log the Firebase error message
        }
    };



    const handleSignUp = () => {
        navigate('/register');
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
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
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

