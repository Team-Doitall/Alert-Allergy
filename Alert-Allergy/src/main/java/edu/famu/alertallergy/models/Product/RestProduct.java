package edu.famu.alertallergy.models.Product;

import org.springframework.lang.Nullable;

import java.sql.Time;
import com.google.cloud.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class RestProduct extends AProduct{


    public RestProduct(String objectId, String productName, String ingredients, Timestamp createdAt, Timestamp updatedAt)
    {
        super(objectId, productName, ingredients, createdAt, updatedAt);


    }
}
