package edu.famu.alertallergy.models.UserProductSearch;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AUserProductSearch {
    @DocumentId
    protected @Nullable String searchId;  // Assuming searchId is of type String and annotated with @DocumentId
    protected Timestamp searchDate;
    protected ArrayList<String> allergenMatch;
    protected boolean canConsume;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }

}

