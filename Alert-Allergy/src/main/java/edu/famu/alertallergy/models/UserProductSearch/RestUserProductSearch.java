package edu.famu.alertallergy.models.UserProductSearch;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.util.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Data
@NoArgsConstructor

public class RestUserProductSearch extends AUserProductSearch{

    private DocumentReference user; // Reference to the user who conducted the search
    private DocumentReference product;

    public RestUserProductSearch(String searchId, Timestamp searchDate, ArrayList<String> allergenMatch, boolean canConsume, Timestamp createdAt, Timestamp updatedAt, DocumentReference user, DocumentReference product) {
        super(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt);

        this.user = user;
        this.product = product;
    }

    public void setUser(String user) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        Firestore Utility = FirestoreClient.getFirestore();
        this.user = Utility.collection("UserProductSearch").document(user);
    }

    public void setProduct(String product) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        Firestore Utility = FirestoreClient.getFirestore();
        this.product = Utility.collection("Product").document(product);
    }

    /*
    String objectId;
    DocumentReference user, product, searchDate, canConsume, createdAt, updatedAt;
    @Nullable List<DocumentReference> allergenMatch;
    public void setUser(String user) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.user = Utility.retrieveDocumentReference("UserProductSearch", user);
    }
    public void setAllergenMatch(ArrayList<String> allergenMatch) {
        if(allergenMatch == null) return;
        this.allergenMatch = new ArrayList<>();
        for (String aller : allergenMatch) {
            this.allergenMatch.add(Utility.retrieveDocumentReference("UserProductSearch", aller));
        }
    }
    public void setProduct(String product) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.product = Utility.retrieveDocumentReference("UserProductSearch", product);
    }
    public void setSearchDate(String searchDate) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.searchDate = Utility.retrieveDocumentReference("UserProductSearch", searchDate);
    }
    public void setCanConsume(String canConsume) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.canConsume = Utility.retrieveDocumentReference("UserProductSearch", canConsume);
    }
    public void setCreatedAt(String createdAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.createdAt = Utility.retrieveDocumentReference("UserProductSearch", createdAt);
    }
    public void setUpdatedAt(String updatedAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.updatedAt = Utility.retrieveDocumentReference("UserProductSearch", updatedAt);
    }
     */

}