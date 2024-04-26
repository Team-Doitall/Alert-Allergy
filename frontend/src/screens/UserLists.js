import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function UserLists() {
    const navigate = useNavigate();
    const [lists, setLists] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchUserLists = async () => {
            try {
                setLoading(true);
                setError('');
                const result = await axios.get("http://localhost:8080/api/product/");
                setLists(result.data);
                setLoading(false);
            } catch (error) {
                setError('Failed to fetch products');
                setLoading(false);
            }
        };

        fetchUserLists();
    }, []); // Empty dependency array ensures this effect runs only once on component mount

    const handleCreateNewList = () => {
        navigate('/create-list');
    };

    const handleEditList = (listId) => {
        navigate(`/edit-list/${listId}`);
    };

    // Inline CSS remains the same...

    return (
        <div>
            <div style={styles.container}>
                <h1>User Lists</h1>
                {loading ? (
                    <p>Loading...</p>
                ) : (
                    <ul style={styles.list}>
                        {lists.map((list) => (
                            <li
                                key={list.id}
                                style={styles.listItem}
                                onClick={() => handleEditList(list.id)}
                            >
                                {list.name}
                            </li>
                        ))}
                    </ul>
                )}
                <button style={styles.button} onClick={handleCreateNewList}>
                    Create New List
                </button>
            </div>
            <div style={styles.navBar}>
                <button style={styles.navButton} onClick={() => navigate('/search')}>Search</button>
                <button style={styles.navButton} onClick={() => navigate('/settings')}>Settings</button>
                <button style={styles.navButton} onClick={() => navigate('/profile')}>Profile</button>
                <button style={styles.navButton} onClick={() => navigate('/lists')}>Saved</button>
            </div>
        </div>
    );
}

export default UserLists;
