package edu.famu.alertallergy.models.Product;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Product {
    @DocumentId
    private @Nullable String objectId;
    private String productName;
    private String ingredients;
    private ArrayList<String> allergenWarnings;
    private Date createdAt;
    private Date updatedAt;

}
