package edu.famu.alertallergy.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data // Lombok annotation to generate getters and setters automatically
@AllArgsConstructor
@NoArgsConstructor
public class UserProductSearch {
    @DocumentId
    private @Nullable String searchId; // Unique ID for the search instance
    private @Nullable Users user; // Reference to the user who conducted the search
    private @Nullable Product product; // Reference to the product searched
    private Date searchDate; // Date when the search was conducted
    private ArrayList<String> allergenMatch;
    private boolean canConsume;
    private Date createdAt;
    private Date updatedAt;
}
