import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Settings() {
    const navigate = useNavigate();
    const [accountEmail, setAccountEmail] = useState('');
    const [notificationsEnabled, setNotificationsEnabled] = useState(true);
    const [visibility, setVisibility] = useState('private');
    const [language, setLanguage] = useState('English');

    const handleSaveSettings = (event) => {
        event.preventDefault();
        console.log('Settings saved with:', { accountEmail, notificationsEnabled, visibility, language });
        alert('Settings saved successfully!');
        // navigate('/dashboard'); // Assuming you have a dashboard route to navigate after saving
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
        },
        navBar: {
            display: 'flex',
            justifyContent: 'space-around',
            position: 'fixed',
            bottom: 0,
            left: 0,
            right: 0,
            backgroundColor: '#f0f0f0',
            borderTop: '1px solid #ccc',
            padding: '10px 0'
        },
        navButton: {
            flex: 1,
            padding: '10px 20px',
            backgroundColor: '#e7e7e7',
            border: 'none',
            outline: 'none',
            cursor: 'pointer'
        }
    };

    return (
        <>
            <div style={styles.container}>
                <h1>Settings</h1>
                <form onSubmit={handleSaveSettings}>
                    <input
                        type="email"
                        placeholder="Account Email"
                        value={accountEmail}
                        onChange={(e) => setAccountEmail(e.target.value)}
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
                        {/* Add more languages as needed */}
                    </select>
                    <button type="submit" style={styles.button}>Save Settings</button>
                </form>
            </div>
            <div style={styles.navBar}>
                <button style={styles.navButton} onClick={() => navigate('/search')}>Search</button>
                <button style={styles.navButton} onClick={() => navigate('/settings')}>Settings</button>
                <button style={styles.navButton} onClick={() => navigate('/profile')}>Profile</button>
                <button style={styles.navButton} onClick={() => navigate('/lists')}>Saved</button>
            </div>
        </>
    );
}

export default Settings;


