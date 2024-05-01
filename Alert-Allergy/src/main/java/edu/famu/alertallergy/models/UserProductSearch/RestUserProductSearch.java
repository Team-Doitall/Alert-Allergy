package edu.famu.alertallergy.models.UserProductSearch;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Data
@NoArgsConstructor
public class RestUserProductSearch extends AUserProductSearch {

    private DocumentReference user;
    private DocumentReference product;

    public RestUserProductSearch(String searchId, Timestamp searchDate, ArrayList<String> allergenMatch, boolean canConsume, Timestamp createdAt, Timestamp updatedAt, DocumentReference user, DocumentReference product) {
        super(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt);

        this.user = user;
        this.product = product;
    }

    public void setUser(String userRef) {

        Firestore Utility = FirestoreClient.getFirestore();
        this.user = Utility.collection("User").document(userRef);
    }

    public void setProduct(String productRef) {

        Firestore Utility = FirestoreClient.getFirestore();
        this.product = Utility.collection("Product").document(productRef);
    }


}