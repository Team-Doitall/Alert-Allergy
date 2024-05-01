package edu.famu.alertallergy.util;

import com.google.firebase.internal.Nullable;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
    private @Nullable String displayName;

}
