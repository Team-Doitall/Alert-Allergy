package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.UserProductSearch.UserProductSearch;
import edu.famu.alertallergy.models.Users.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserProductSearchService {
    private Firestore firestore;

    public UserProductSearchService() {
        this.firestore = FirestoreClient.getFirestore();
    }
}



