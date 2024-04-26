import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function CreateList() {
    const navigate = useNavigate();
    const [listName, setListName] = useState('');
    const [error, setError] = useState('');

    const handleCreate = (event) => {
        event.preventDefault();
        if (!listName) {
            setError('List name is required');
            return;
        }

        console.log('Creating list:', listName);
        // Simulate creating a list
        // You would typically send a request to your backend here

        // After list creation, redirect to the lists overview or dashboard
        navigate('/lists');  // Corrected this line
    };

    // Inline CSS
    const styles = {
        container: {
            margin: '0 auto',
            width: '300px',
            textAlign: 'center', // ensure textAlign matches the expected type
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
            <h1>Create a New List</h1>
            {error && <p style={styles.error}>{error}</p>}
            <form onSubmit={handleCreate}>
                <input
                    type="text"
                    placeholder="List name"
                    value={listName}
                    onChange={(e) => setListName(e.target.value)}
                    style={styles.input}
                />
                <button type="submit" style={styles.button}>Create</button>
            </form>
        </div>
    );
}

export default CreateList;
