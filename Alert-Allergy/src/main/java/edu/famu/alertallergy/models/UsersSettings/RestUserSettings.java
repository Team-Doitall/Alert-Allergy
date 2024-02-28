package edu.famu.alertallergy.models.UsersSettings;

import java.util.Date;
import java.util.Map;

public class RestUserSettings extends AUserSettings{
    public RestUserSettings(String language, Map<String, Object> notificationPreferences, Map<String, Object> displayPreferences, Date createdAt, Date updatedAt) {
        super(language, notificationPreferences, displayPreferences, createdAt, updatedAt);
    }
}
