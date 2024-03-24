package edu.famu.alertallergy.models.UserProductSearch;

import com.google.cloud.Timestamp;
import com.google.firebase.remoteconfig.User;
import edu.famu.alertallergy.models.Product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data // Lombok annotation to generate getters and setters automatically
@NoArgsConstructor
public class UserProductSearch extends AUserProductSearch {

    private User user; // Reference to the user who conducted the search
    private Product product; // Reference to the product searched
    private Timestamp createdAt; // Change the type to Timestamp

    public UserProductSearch(String searchId, Timestamp searchDate, ArrayList<String> allergenMatch, boolean canConsume, Timestamp createdAt, Timestamp updatedAt, User user, Product product) {
        super(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt);

        this.user = user;
        this.product = product;
        this.createdAt = createdAt; // Update the constructor to set the createdAt field
    }


}
