package edu.famu.alertallergy.models.UserProductSearch;
import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class UserProductSearch extends AUserProductSearch {

    private User user;
    private Product product;

    public UserProductSearch(@Nullable String searchId, Timestamp searchDate, ArrayList<String> allergenMatch, boolean canConsume, Timestamp createdAt, Timestamp updatedAt, User user, Product product) {
        super(searchId, searchDate, allergenMatch, canConsume, createdAt, updatedAt);

        this.user = user;
        this.product = product;
    }

}

