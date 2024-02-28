package edu.famu.alertallergy.models.Admin;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import org.springframework.lang.Nullable;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public abstract class AAdmin {
    @DocumentId
    protected @Nullable String adminId;
    protected String username;
    protected String password;
    protected String email;
    protected String role;
    protected ArrayList<String> permissions;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public AAdmin(@Nullable String adminId, String username, String password, String email, String role, Timestamp createdAt, Timestamp updatedAt){
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }
}
