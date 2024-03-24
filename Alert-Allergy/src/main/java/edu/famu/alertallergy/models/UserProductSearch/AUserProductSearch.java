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
    protected @Nullable String searchId; // Unique ID for the search instance
    protected Timestamp searchDate; // Date when the search was conducted
    protected ArrayList<String> allergenMatch;
    protected boolean canConsume;
    protected @Nullable Timestamp createdAt;
    protected @Nullable Timestamp updatedAt;




}
