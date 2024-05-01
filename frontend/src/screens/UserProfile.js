import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function UserProfile() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [allergies, setAllergies] = useState(['']);
    const [aboutMe, setAboutMe] = useState('');
    const [showModal, setShowModal] = useState(false);

    // Function to open modal
    const openModal = () => {
        setShowModal(true);
    };

    // Function to close modal
    const closeModal = () => {
        setShowModal(false);
    };

    const updateUserProfile = async (updatedUserData) => {
        try {
            const response = await axios.put("http://localhost:8080/api/user/", updatedUserData);
            console.log(response.data); // Log the response data (optional)
            // Optionally update state or perform other actions based on the response
        } catch (error) {
            console.error("Error updating user profile:", error);
            // Optionally handle error (e.g., display an error message)
        }
    };

    // Function to handle saving changes
    const saveChanges = async (event) => {
        event.preventDefault();
        const updatedUserData = {
            username,
            email,
            allergies,
            aboutMe
        };
        await updateUserProfile(updatedUserData);
        closeModal();
    };


    // Placeholder profile picture URL
    const profilePictureUrl =
        "https://t3.ftcdn.net/jpg/02/83/12/98/360_F_283129831_MTTjxBv6zrcSREEsmLgvLQJFU0HyW2ui.jpg";

    return (
        <div className="profile-container">
            {/* Profile information */}
            <div className="user-info">
                {/* Profile picture */}
                <img
                    className="profile-picture"
                    src={profilePictureUrl}
                    alt="Profile Picture"
                />
                {/* Display user information */}
                <h1>{username}</h1>
                <b>Email:</b>
                <p>{email}</p>
                <b>Allergies:</b>
                <ul>
                    {allergies.map((allergy, index) => (
                        <li key={index}>{allergy}</li>
                    ))}
                </ul>
                <b>About Me:</b>
                <p>{aboutMe}</p>
                {/* Button to open modal */}
                <button className="button" onClick={openModal}>
                    Edit Profile
                </button>
            </div>

            {/* Modal */}
            {showModal && (
                <div className="modal">
                    {/* Modal content */}
                    <div className="modal-content">
            <span className="close" onClick={closeModal}>
              &times;
            </span>
                        <h2>Edit Profile</h2>
                        {/* Form for editing profile attributes */}
                        <form onSubmit={saveChanges}>
                            <label htmlFor="username">Username:</label>
                            <input
                                type="text"
                                id="username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />

                            <label htmlFor="email">Email:</label>
                            <input
                                type="email"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />

                            <label htmlFor="allergies">Allergies:</label>
                            <input
                                type="text"
                                id="allergies"
                                value={allergies.join(',')}
                                onChange={(e) => setAllergies(e.target.value.split(','))}
                            />

                            <label htmlFor="aboutMe">About Me:</label>
                            <textarea
                                id="aboutMe"
                                value={aboutMe}
                                onChange={(e) => setAboutMe(e.target.value)}
                            />

                            <button type="submit">Save Changes</button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}

export default UserProfile;
