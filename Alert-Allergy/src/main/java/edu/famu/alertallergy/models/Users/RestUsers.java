package edu.famu.alertallergy.models.Users;

import com.google.cloud.Timestamp;

import java.util.ArrayList;

public class RestUsers extends AUsers{

    public RestUsers(String userId, String username, String password, String email, ArrayList<String> allergies, Timestamp createdAt, Timestamp updatedAt)
    {
        super(userId, username, password, email, allergies, updatedAt, createdAt);
        this.allergies = allergies;
    }
}
