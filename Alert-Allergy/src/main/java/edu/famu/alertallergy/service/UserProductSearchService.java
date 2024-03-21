package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
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
        if(document.exists())
        {
            return document.toObject(UserProductSearch.class);
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

    public void addUserProductSearch(UserProductSearch userProductSearch) {
        CollectionReference userProductSearchCollection = firestore.collection("UserProductSearch");
        userProductSearchCollection.add(userProductSearch);
    }

    public void updateUserProductSearch(String searchId, UserProductSearch updatedUserProductSearch) {
        DocumentReference docRef = firestore.collection("UserProductSearch").document(searchId);
        docRef.set(updatedUserProductSearch);
    }

    public void deleteUserProductSearch(String searchId) {
        firestore.collection("UserProductSearch").document(searchId).delete();
    }

}




