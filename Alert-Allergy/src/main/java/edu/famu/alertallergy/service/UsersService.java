package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Users.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UsersService {
    private Firestore firestore;

    public UsersService() {this.firestore = FirestoreClient.getFirestore();}

    public Users documentSnapshotToUsers(DocumentSnapshot document) {
        Users users = null;
        if (document.exists())
        {
            String userId = document.getId();
            String username = document.getString("username");
            String password = document.getString("password");
            String email = document.getString("email");
            ArrayList<String> allergies = (ArrayList<String>) document.get("allergies");
            Date createdAt = document.getTimestamp("createdAt").toDate();
            Date updatedAt = document.getTimestamp("updatedAt").toDate();

            users = new Users(userId, username, password, email, allergies,createdAt, updatedAt);
        }
        return users;
    }

    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();
        List<Users> usersList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Users users = documentSnapshotToUsers(document);
            if (users != null) {
                usersList.add(users);
            }
        }
        return usersList;
    }

    public Users getUsersById(String usersId) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = usersCollection.document(usersId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUsers(document);
    }
}
