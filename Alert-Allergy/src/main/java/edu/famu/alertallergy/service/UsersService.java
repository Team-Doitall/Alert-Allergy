package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Users.Users;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    private final Firestore firestore;

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Users documentSnapshotToUser(DocumentSnapshot document) {
        if (document.exists()) {
            return document.toObject(Users.class);
        }
        return null;
    }

    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<Users> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Users user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public Users getUserById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

    public Users createUser(Users user) {
        DocumentReference docRef = firestore.collection("Users").document();
        String userId = docRef.getId();
        user.setUserId(userId); // Set the generated user ID
        docRef.set(user); // Save the user to Firestore
        return user;
    }
    public Users updateUsers(String userId, Users updateValues) throws ExecutionException, InterruptedException {

        DocumentReference docRef = firestore.collection("Users").document(userId);
        ApiFuture<WriteResult> future = docRef.set(updateValues);
        future.get();
        return updateValues;

    }

    public void deleteUser(String userId)
    {
        firestore.collection("Users").document(userId).delete();
    }
}
