package edu.famu.alertallergy.util;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
