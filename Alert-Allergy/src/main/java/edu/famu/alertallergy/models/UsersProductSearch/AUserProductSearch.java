package edu.famu.alertallergy.models.UsersProductSearch;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AUserProductSearch {
    @DocumentId
    private @Nullable String searchId; // Unique ID for the search instance
    private @Nullable Users user; // Reference to the user who conducted the search
    private @Nullable Product product; // Reference to the product searched
    private Date searchDate; // Date when the search was conducted
    private ArrayList<String> allergenMatch;
    private boolean canConsume;
    private Date createdAt;
    private Date updatedAt;

    public AUserProductSearch(String searchId, Users user, Product product, Date searchDate,ArrayList<String> allergenMatch, boolean canConsume, Date createdAt, Date updatedAt)
    {
        this.searchId = searchId;
        this.user = user;
        this.product = product;
        this.searchDate = searchDate;
        this.allergenMatch = allergenMatch;
        this.canConsume = canConsume;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
