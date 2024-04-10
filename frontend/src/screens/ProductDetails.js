import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

function ProductDetails() {
    const navigate = useNavigate();
    const { productId } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchProductDetails(productId);
    }, [productId]);

    const fetchProductDetails = async (id) => {
        try {
            setLoading(true);
            setError('');

            setTimeout(() => {
                setProduct({
                    id: id,
                    name: 'Sample Product',
                    description: 'This is a sample description for the product.',
                    ingredients: 'Sample ingredients list',
                    allergens: 'Possible allergens here'
                });
                setLoading(false);
            }, 1000);
        } catch (error) {
            setError('Failed to fetch product details');
            setLoading(false);
        }
    };

    const handleBack = () => {
        navigate(-1);
    };

    const styles = {
        container: {
            margin: '20px',
            padding: '20px',
            border: '1px solid #ddd',
            borderRadius: '5px'
        },
        button: {
            margin: '10px 0',
            padding: '10px 20px',
            backgroundColor: '#007bff',
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

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <div style={styles.container}>
                <h1>{product?.name}</h1>
                <p><strong>Description:</strong> {product?.description}</p>
                <p><strong>Ingredients:</strong> {product?.ingredients}</p>
                <p><strong>Allergens:</strong> {product?.allergens}</p>
                <button style={styles.button} onClick={handleBack}>Go Back</button>
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

export default ProductDetails;

