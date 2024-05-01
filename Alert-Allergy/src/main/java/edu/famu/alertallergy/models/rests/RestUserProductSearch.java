package edu.famu.alertallergy.models.rests;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.alertallergy.models.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestUserProductSearch {
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
}
