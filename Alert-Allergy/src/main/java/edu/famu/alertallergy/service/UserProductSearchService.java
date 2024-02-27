package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.UserProductSearch;
import edu.famu.alertallergy.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserProductSearchService {
    private Firestore firestore;

    public UserProductSearchService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public UserProductSearchService documentSnapshotToUserProductSearch(DocumentSnapshot document) {
        UserProductSearch usps = null;
        if (document.exists())
        {
            String searchId = document.getId();
            String user = new document.getString("user"); //Reference to User
            String product = document.getString("product"); //Reference to product searched
            ArrayList<String> allergenMatch = (ArrayList<String>) document.get("allergenMatch");
            boolean canConsume = Boolean.TRUE.equals(document.getBoolean("false"));
            Date searchDate = document.getTimestamp("searchDate").toDate();
            Date createdAt = document.getTimestamp("createdAt").toDate();
            Date updatedAt = document.getTimestamp("updatedAt").toDate();

            usps = new UserProductSearch(searchId, user, product, allergenMatch, canConsume,searchDate,createdAt, updatedAt);
        }
        return usps;
    }

    public List<UserProductSearch> getAllUserProductSearch() throws ExecutionException, InterruptedException {
        CollectionReference uspsCollection = firestore.collection("UserProductSearch");
        ApiFuture<QuerySnapshot> future = uspsCollection.get();
        List<UserProductSearch> uspsList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            UserProductSearch usps = documentSnapshotToUserProductSearch(document);
            if (usps != null) {
                uspsList.add(usps);
            }
        }
        return uspsList;
    }

    public UserProductSearch getUserProductSearchById(String searchId) throws ExecutionException, InterruptedException {
        CollectionReference uspsCollection = firestore.collection("UserProductSearch");
        ApiFuture<DocumentSnapshot> future = uspsCollection.document(searchId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUserProductSearch(document);
    }
}

