import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Settings() {
    const navigate = useNavigate();
    const [accountEmail, setAccountEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [dateOfBirth, setDateOfBirth] = useState('');
    const [gender, setGender] = useState('');
    const [notificationsEnabled, setNotificationsEnabled] = useState(true);
    const [visibility, setVisibility] = useState('private');
    const [language, setLanguage] = useState('English');

    const handleSaveSettings = (event) => {
        event.preventDefault();
        console.log('Settings saved with:', { accountEmail, firstName, lastName, email, dateOfBirth, gender, notificationsEnabled, visibility, language });
        alert('Settings saved successfully!');
        navigate('/search');  // Navigate to dashboard or relevant page
    };

    const styles = {
        container: {
            margin: '0 auto',
            width: '400px',
            textAlign: 'center',
            border: '1px solid #ddd',
            padding: '20px',
            borderRadius: '5px',
            marginTop: '100px'
        },
        input: {
            marginBottom: '15px',
            width: '100%',
            height: '40px',
            padding: '0 10px',
            borderRadius: '5px',
            border: '1px solid #ccc'
        },
        select: {
            marginBottom: '15px',
            width: '100%',
            height: '40px',
            borderRadius: '5px',
            border: '1px solid #ccc'
        },
        button: {
            width: '100%',
            height: '40px',
            backgroundColor: '#28a745',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
        }
    };

    return (
        <div style={styles.container}>
            <h1>Settings</h1>
            <form onSubmit={handleSaveSettings}>
                <input
                    type="text"
                    placeholder="First Name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="text"
                    placeholder="Last Name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="date"
                    placeholder="Date of Birth"
                    value={dateOfBirth}
                    onChange={(e) => setDateOfBirth(e.target.value)}
                    style={styles.input}
                />
                <input
                    type="text"
                    placeholder="Gender"
                    value={gender}
                    onChange={(e) => setGender(e.target.value)}
                    style={styles.input}
                />
                <div>
                    <label>
                        Notifications:
                        <input
                            type="checkbox"
                            checked={notificationsEnabled}
                            onChange={(e) => setNotificationsEnabled(e.target.checked)}
                        />
                    </label>
                </div>
                <select
                    value={visibility}
                    onChange={(e) => setVisibility(e.target.value)}
                    style={styles.select}
                >
                    <option value="private">Private</option>
                    <option value="public">Public</option>
                </select>
                <select
                    value={language}
                    onChange={(e) => setLanguage(e.target.value)}
                    style={styles.select}
                >
                    <option value="English">English</option>
                    <option value="Spanish">Spanish</option>
                    <option value="French">French</option>
                </select>
                <button type="submit" style={styles.button}>Save Settings</button>
            </form>
        </div>
    );
}

export default Settings;

