import { getAuth, signInWithEmailAndPassword } from 'firebase/auth'; // Import necessary functions from firebase/auth
import { initializeApp } from 'firebase/app';
import { getFirestore } from 'firebase/firestore';

const firebaseConfig = {
    apiKey: "AIzaSyCVMvBQqM94SwTmidxM-HKczqDqr5sJ5O4",
    authDomain: "alert-allergy.firebaseapp.com",
    projectId: "alert-allergy",
    storageBucket: "alert-allergy.appspot.com",
    messagingSenderId: "684627591657",
    appId: "1:684627591657:web:6f3d7869052ce857b28b78",
    measurementId: "G-06F19R9X84"
};

const app = initializeApp(firebaseConfig);
const firestore = getFirestore(app);
const auth = getAuth(app);
export { app, firestore, auth, signInWithEmailAndPassword };

//const analytics = getAnalytics(app);