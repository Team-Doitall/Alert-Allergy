package edu.famu.alertallergy.models.Admin;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @DocumentId
    protected @Nullable String adminId;
    protected String username;
    protected String password;
    protected String email;
    protected String role;
    protected ArrayList<String> permissions;
    protected Date createdAt;
    protected Date updatedAt;
}
