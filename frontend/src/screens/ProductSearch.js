import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ProductSearch() {
    const navigate = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        try {
            setLoading(true);
            setError('');

            // Simulate an API call to search for products
            setTimeout(() => {
                setProducts([
                    { id: 1, name: 'Product 1', description: 'Description of product 1' },
                    { id: 2, name: 'Product 2', description: 'Description of product 2' },
                    // Add more sample products as needed
                ]);
                setLoading(false);
            }, 1000);
        } catch (e) {
            setError('Failed to fetch products');
            setLoading(false);
        }
    };

    const handleProductClick = (productId) => {
        navigate(`/product-details/${productId}`);
    };

    // Inline CSS
    const styles = {
        container: {
            padding: '20px',
        },
        input: {
            marginRight: '10px',
            height: '30px',
        },
        productItem: {
            margin: '10px 0',
            cursor: 'pointer',
            color: 'blue',
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
        <div style={styles.container}>
            <h1>Product Search</h1>
            <input
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                style={styles.input}
            />
            <button onClick={handleSearch}>Search</button>

            {loading && <p>Loading...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            <div>
                {products.map((product) => (
                    <div
                        key={product.id}
                        style={styles.productItem}
                        onClick={() => handleProductClick(product.id)}
                    >
                        {product.name} - {product.description}
                    </div>
                ))}
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

export default ProductSearch;

