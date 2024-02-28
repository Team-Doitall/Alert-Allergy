package edu.famu.alertallergy.models.Admin;

import com.google.cloud.Timestamp;

import java.util.ArrayList;

public class RestAdmin extends AAdmin{

    public RestAdmin(String username, String password, String email, String role, ArrayList<String> permissions) {
        super(null, username, password, email, role, Timestamp.now(), Timestamp.now());
        this.permissions = permissions;
    }

    // Constructor without permissions (assuming empty permissions by default)
    public RestAdmin(String username, String password, String email, String role) {
        this(username, password, email, role, new ArrayList<>());
    }
}
