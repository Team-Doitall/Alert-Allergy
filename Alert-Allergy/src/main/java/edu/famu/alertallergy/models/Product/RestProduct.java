/*
package edu.famu.alertallergy.models.Product;

import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.alertallergy.util.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestProduct {
    String objectId;
    DocumentReference productName;
    @Nullable List<DocumentReference> allergenWarnings, ingredients;
    DocumentReference createdAt, updatedAt;
    public void setAllergenWarnings(ArrayList<String> allergenWarnings) {
        if(allergenWarnings == null) return;
        this.allergenWarnings = new ArrayList<>();
        for (String aller : allergenWarnings) {
            this.allergenWarnings.add(Utility.retrieveDocumentReference("Product", aller));
        }
    }
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = new ArrayList<>();
        for (String ingre : ingredients) {
            this.ingredients.add(Utility.retrieveDocumentReference("Product", ingre));
        }
    }
    public void setProductName(String product) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.productName = Utility.retrieveDocumentReference("Product", product);
    }
    public void setCreatedAt(String createdAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.createdAt = Utility.retrieveDocumentReference("Users", createdAt);
    }
    public void setUpdatedAt(String updatedAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.updatedAt = Utility.retrieveDocumentReference("Users", updatedAt);
    }
}
 */
