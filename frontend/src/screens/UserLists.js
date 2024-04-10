import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function UserLists() {
    const navigate = useNavigate();
    const [lists, setLists] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let isMounted = true;

        const fetchUserLists = async () => {
            setLoading(true);
            setTimeout(() => {
                if (isMounted) {
                    setLists([
                        { id: 1, name: 'Grocery List' },
                        { id: 2, name: 'Allergy Safe Foods' }
                    ]);
                    setLoading(false);
                }
            }, 1000);
        };

        fetchUserLists();

        return () => {
            isMounted = false;
        };
    }, []);

    const handleCreateNewList = () => {
        navigate('/create-list');
    };

    const handleEditList = (listId) => {
        navigate(`/edit-list/${listId}`);
    };

    // Inline CSS
    const styles = {
        container: {
            padding: '20px',
            maxWidth: '600px',
            margin: 'auto',
        },
        list: {
            listStyle: 'none',
            padding: 0,
        },
        listItem: {
            backgroundColor: '#f0f0f0',
            border: '1px solid #ddd',
            marginTop: '10px',
            padding: '10px',
            cursor: 'pointer',
        },
        button: {
            marginTop: '20px',
            padding: '10px 20px',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
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
