package edu.famu.alertallergy.models.UserProductSearch;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.expression.ParseException;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class AUserProductSearch {
    @DocumentId
    private @Nullable String searchId; // Unique ID for the search instance
    private Timestamp searchDate; // Date when the search was conducted
    private ArrayList<String> allergenMatch;
    private boolean canConsume;
    private @Nullable Timestamp createdAt;
    private @Nullable Timestamp updatedAt;

    /*
    public AUserProductSearch(String searchId, Users user, Product product, Date searchDate,ArrayList<String> allergenMatch, boolean canConsume, Date createdAt, Date updatedAt)
    {
        this.searchId = searchId;
        this.searchDate = searchDate;
        this.allergenMatch = allergenMatch;
        this.canConsume = canConsume;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    */

    public void setCreatedAt(String createdAt) throws ParseException, java.text.ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException, java.text.ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }
}
