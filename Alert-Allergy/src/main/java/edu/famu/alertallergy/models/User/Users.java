package edu.famu.alertallergy.models.User;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;

 @Data//creates setters and getters automatically
 @AllArgsConstructor
 @NoArgsConstructor

    public class Users {
        @DocumentId
        protected String userId;
        protected String username;
        protected String password;
        protected String email;
        protected ArrayList<String> allergies;
        protected Timestamp createdAt;
        protected Timestamp updatedAt;






        public void setUsername(String username){
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username required.");
            }
            this.username = username;
        }
        public void setPassword(String password){
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Password Required.");
            }
            this.password = password;
        }

        public void setEmail(String email){
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email Required.");
            }
            this.email = email;
        }



    }