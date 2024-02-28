package edu.famu.alertallergy.models.UsersSettings;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.remoteconfig.User;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Map;

public abstract class AUserSettings {
    @DocumentId
    private @Nullable String settingId;
    private @Nullable User user;
    private String language;
    private Map<String,Object> notificationPreferences;
    private Map<String,Object> displayPreferences;
    private Date createdAt;
    private Date updatedAt;

    // Constructor with required fields
    public AUserSettings(String language, Map<String,Object> notificationPreferences, Map<String,Object> displayPreferences, Date createdAt, Date updatedAt) {
        this.language = language;
        this.notificationPreferences = notificationPreferences;
        this.displayPreferences = displayPreferences;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
