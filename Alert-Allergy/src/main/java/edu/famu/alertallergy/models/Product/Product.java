package edu.famu.alertallergy.models.Product;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Product {
    private String productId;
    private String productName;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergenWarnings;
    private Date createdAt;
    private Date updatedAt;

}
