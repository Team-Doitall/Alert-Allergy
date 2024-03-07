package edu.famu.alertallergy.models.UserProductSearch;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data // Lombok annotation to generate getters and setters automatically
@NoArgsConstructor
public class UserProductSearch extends AUserProductSearch {
    @DocumentId

    private  Users user; // Reference to the user who conducted the search
    private Product product; // Reference to the product searched

    public UserProductSearch(String searchId,Timestamp searchDate, ArrayList<String> allergenMatch,boolean canConsume,Timestamp createdAt,Timestamp updatedAt, Users user, Product product){
        super(searchId,searchDate,allergenMatch,canConsume,createdAt,updatedAt);

        this.user = user;
        this.product = product;
    }


}
