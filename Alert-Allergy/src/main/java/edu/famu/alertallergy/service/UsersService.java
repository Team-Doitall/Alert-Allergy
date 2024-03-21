package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Users.Users;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    private Firestore firestore = FirestoreClient.getFirestore();

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public ArrayList<Users> getUsers() throws ExecutionException, InterruptedException{

        Query query = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        ArrayList<Users> users = documents.size() > 0 ? new ArrayList<>() : null;
        for(QueryDocumentSnapshot doc : documents)
        {
            users.add(doc.toObject(Users.class));
        }

        return users;
    }

    public String createUsers(Users users) throws ExecutionException, InterruptedException {
        String userId = null;
        users.setCreatedAt(Timestamp.now());

        ApiFuture<DocumentReference> future = firestore.collection("User").add(users);
        DocumentReference userRef = future.get();
        userId = userRef.getId();

        return userId;
    }

    public void updateUsers(String id, Map<String, String> updateValues){

        String [] allowed = {"username", "password", "email", "allergies"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, String> entry : updateValues.entrySet()) {
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key, entry.getValue());
        }

        DocumentReference userDoc = firestore.collection("User").document(id);
        userDoc.update(formattedValues);
    }

    public Users getUser(DocumentReference userRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> userQuery = userRef.get();
        DocumentSnapshot userDoc = userQuery.get();
        return userDoc.toObject(Users.class);
    }



}




/*
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
 */
