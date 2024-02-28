package edu.famu.alertallergy.models.Users;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

public abstract class AUsers {
    @DocumentId
    protected @Nullable String userId;
    protected String username;
    protected String password;
    protected String email;
    protected ArrayList<String> allergies;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public AUsers(@Nullable String userId, String username, String password, String email, ArrayList<String> allergies, Timestamp updatedAt, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.allergies = allergies;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
