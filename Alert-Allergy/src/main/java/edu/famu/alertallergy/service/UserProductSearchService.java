package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.User.User;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.UserProductSearch.UserProductSearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserProductSearchService {

    private final Firestore firestore;

    public UserProductSearchService() {
        this.firestore = FirestoreClient.getFirestore();
    }


    public UserProductSearch documentSnapshotToUserProductSearch(DocumentSnapshot document) {
        if (document.exists()) {
            String searchId = document.getId();
            Timestamp searchDate = document.contains("searchDate") ? document.getTimestamp("searchDate") : null;
            ArrayList<String> allergenMatch = document.contains("allergenMatch") ? (ArrayList<String>) document.get("allergenMatch") : new ArrayList<>();
            boolean canConsume = document.contains("canConsume") && document.getBoolean("canConsume");
            Timestamp createdAt = document.contains("createdAt") ? document.getTimestamp("createdAt") : null;
            Timestamp updatedAt = document.contains("updatedAt") ? document.getTimestamp("updatedAt") : null;
            User user = null;
            Product product = null;

            if(document.contains("user"))
            {
                user = document.toObject(User.class);
            }

            if (document.contains("product")) {
                product = document.toObject(Product.class);
            }
            return new UserProductSearch(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt, user, product);
        }
        return null;
    }



    public List<UserProductSearch> getAllUserProductSearches() throws ExecutionException, InterruptedException {
        CollectionReference userProductSearchCollection = firestore.collection("UserProductSearch");
        ApiFuture<QuerySnapshot> future = userProductSearchCollection.get();
        List<UserProductSearch> userProductSearchList = new ArrayList<>();
        for (QueryDocumentSnapshot document : future.get().getDocuments()) {
            UserProductSearch userProductSearch = documentSnapshotToUserProductSearch(document);
            if (userProductSearch != null) {
                userProductSearchList.add(userProductSearch);
            }
        }
        return userProductSearchList;
    }

    public UserProductSearch getUserProductSearchById(String searchId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("UserProductSearch").document(searchId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUserProductSearch(document);
    }

    public void addUserProductSearch(String searchId, Timestamp searchDate, ArrayList<String> allergenMatch, boolean canConsume, Timestamp createdAt, Timestamp updatedAt, User user, Product product) {
        UserProductSearch userProductSearch = new UserProductSearch(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt, user, product);
        CollectionReference userProductSearchCollection = firestore.collection("UserProductSearch");
        userProductSearchCollection.document(searchId).set(userProductSearch); // Update to set the document with searchId
    }

    public void updateUserProductSearch(String searchId, UserProductSearch updatedUserProductSearch) {
        DocumentReference docRef = firestore.collection("UserProductSearch").document(searchId);
        docRef.set(updatedUserProductSearch);
    }

    public void deleteUserProductSearch(String searchId) {
        firestore.collection("UserProductSearch").document(searchId).delete();
    }
}





