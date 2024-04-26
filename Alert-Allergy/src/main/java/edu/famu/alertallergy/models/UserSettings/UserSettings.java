package edu.famu.alertallergy.models.UserSettings;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.alertallergy.models.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data

@NoArgsConstructor
public class UserSettings extends AUserSettings {
    @DocumentId
    private User user;

    public UserSettings(@Nullable String settingId, String userId, String language, Map<String, Object> notificationPreferences, Map<String, Object> displayPreferences, Timestamp createdAt, Timestamp updatedAt,  User user) {
        super(settingId, language, notificationPreferences, displayPreferences, createdAt, updatedAt);
        this.user = user;
    }


}

