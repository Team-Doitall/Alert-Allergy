package edu.famu.alertallergy.models.UsersProductSearch;

import com.google.firebase.internal.Nullable;
import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.models.Users.Users;

import java.util.ArrayList;
import java.util.Date;

public class RestUserProductSearch extends AUserProductSearch{
    private ArrayList<String> permissions;

    public RestUserProductSearch(@Nullable String searchId, @Nullable Users user, @Nullable Product product, Date searchDate, ArrayList<String> allergenMatch, boolean canConsume, Date createdAt, Date updatedAt, ArrayList<String> permissions) {
        super(searchId, user, product, searchDate, allergenMatch, canConsume, createdAt, updatedAt);
        this.permissions = permissions;
    }
}
