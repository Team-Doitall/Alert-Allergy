import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from "axios";

function ProductDetails() {
    const navigate = useNavigate();
    const {productId} = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    const handleDetails = async () => {
        try {
            setLoading(true);
            setError('');
            const response = await axios.get("http://localhost:8080/api/product/");
            setProduct(response.data);
            setLoading(false);
        } catch (error) {
            setError('Failed to see Details');
            setLoading(false);
        }
    };

    useEffect(() => {
        handleDetails();
    }, [productId]);

    const handleBack = () => {
        navigate(-1);
    };

    /*

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
     */


    const styles = {
        container: {
            margin: '0 auto',
            width: '80%',
            textAlign: 'center',
            padding: '20px',
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




