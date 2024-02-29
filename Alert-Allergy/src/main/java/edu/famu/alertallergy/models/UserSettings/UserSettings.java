package edu.famu.alertallergy.models.UserSettings;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import edu.famu.alertallergy.models.Users.Users;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data

@NoArgsConstructor
public class UserSettings extends AUserSettings {
    @DocumentId
    private Users user;

    public UserSettings(String settingId, String language, Map<String,Object> notificationPreferences, Map<String, Object> displayPreferences, Timestamp createdAt, Timestamp updatedAt, Users user) {
        super(settingId, language, notificationPreferences, displayPreferences, createdAt, updatedAt);
        this.user = user;
    }
}

