package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Admin;
import edu.famu.alertallergy.models.User.User;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    private final Firestore firestore;

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public User documentSnapshotToUser(DocumentSnapshot document) {
        User user = null;
        if (document.exists())
        {
            String id = document.getId();
            String username = document.getString("username");
            String password = document.getString("password");
            String email = document.getString("email");
            ArrayList<String> allergies = (ArrayList<String>) document.get("allergies");


            Timestamp createdAt = document.getTimestamp("createdAt");
            Timestamp updatedAt = document.getTimestamp("updatedAt");

            return new User(id, username, password, email, allergies, createdAt, updatedAt);
        }
        return user;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

    public User createUser(User user) {
        DocumentReference docRef = firestore.collection("Users").document();
        String userId = docRef.getId();
        user.setUserId(userId);
        docRef.set(user);
        return user;
    }

    public User updateUser(String userId, User updatedUser) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        DocumentReference docRef = userCollection.document(userId);
        ApiFuture<WriteResult> future = docRef.set(updatedUser);
        future.get();
        return updatedUser;
    }

    public void deleteUser(String userId) {
        firestore.collection("Users").document(userId).delete();
    }
}
