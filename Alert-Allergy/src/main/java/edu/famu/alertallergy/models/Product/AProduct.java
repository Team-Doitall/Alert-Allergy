package edu.famu.alertallergy.models.Product;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import org.springframework.lang.Nullable;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public abstract class AProduct {
    @DocumentId
    private @Nullable String objectId;
    private String productName;
    private String ingredients;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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
