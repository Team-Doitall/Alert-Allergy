import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './screens/LoginPage';
import RegistrationPage from './screens/RegistrationPage';
import ProductSearch from './screens/ProductSearch';
import ProductDetails from './screens/ProductDetails';
import UserLists from './screens/UserLists';
import CreateList from './screens/CreateList';
import Settings from './screens/Settings';
import UserProfile from "./screens/UserProfile";

function App() {

    return (
       //<AuthProvider>
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/register" element={<RegistrationPage />} />
                <Route path="/search" element={<ProductSearch />} />
                <Route path="/product-details" element={<ProductDetails />} />
                <Route path="/lists" element={<UserLists />} />
                <Route path="/create-list" element={<CreateList />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="/profile" element={<UserProfile/>} />
                {/* Add other routes as needed */}
            </Routes>
        </Router>
       // </AuthProvider>
    );
}

export default App;
