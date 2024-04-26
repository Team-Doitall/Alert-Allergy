package edu.famu.alertallergy.models.User;


import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data//creates setters and getters automatically
@AllArgsConstructor
@NoArgsConstructor

    public class User {
        protected @Nullable String userId;
        protected String username;
        protected String password;
        protected String email;
        protected ArrayList<String> allergies;
        protected Date createdAt;
        protected Date updatedAt;
    }