import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from "axios";

function ProductDetails() {
    const navigate = useNavigate();
    const { productId } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        try {
            setLoading(true);
            setError('');
             axios.get(`http://localhost:8080/api/user-product-search`);
            setProduct(response.data);
            setLoading(false);
        } catch (error) {
            setError('Failed to fetch product details');
            setLoading(false);
        }
    };

    useEffect(() => {
        handleSearch();
    }, [productId]);

    const handleBack = () => {
        navigate(-1);
    };

    const styles = {
        // Styles remain the same...
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
                {/* Navigation buttons */}
            </div>
        </div>
    );
}

export default ProductDetails;

