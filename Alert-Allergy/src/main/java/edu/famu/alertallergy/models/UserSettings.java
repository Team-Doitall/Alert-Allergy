package edu.famu.alertallergy.models;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.remoteconfig.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettings {
    @DocumentId
    private @Nullable String settingId;
    private @Nullable User user;
    private String language;
    private Map<String,Object> notificationPreferences;
    private Map<String,Object> displayPreferences;
    private Date createdAt;
    private Date updatedAt;

}
