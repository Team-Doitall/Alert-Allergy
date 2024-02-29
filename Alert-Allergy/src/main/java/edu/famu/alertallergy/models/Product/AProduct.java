/*
package edu.famu.alertallergy.models.Product;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AProduct {
    @DocumentId
    private @Nullable String productId;
    private String productName;
    private String ingredients;
    private Timestamp createdAt;
    private @Nullable Timestamp updatedAt;


    public AProduct(@Nullable String objectId, String productName, String ingredients, Timestamp createdAt, Timestamp updatedAt)
    {
        this.objectId = objectId;
        this.productName = productName;
        this. ingredients = ingredients;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt  = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }
}
*/
