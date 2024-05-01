package edu.famu.alertallergy.models.rests;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.alertallergy.models.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestUserSettings {
    String objectId;
    DocumentReference user,language,notificationPreferences,displayPreferences,createdAt,updatedAt;
    public void setUser(String user) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.user = Utility.retrieveDocumentReference("UserSettings", user);
    }
    public void setLanguage(String language) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.language = Utility.retrieveDocumentReference("UserSettings", language);
    }
    public void setNotificationPreferences(String notificationPreferences) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.notificationPreferences = Utility.retrieveDocumentReference("UserSettings", notificationPreferences);
    }
    public void setDisplayPreferences(String displayPreferences) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.displayPreferences = Utility.retrieveDocumentReference("UserSettings", displayPreferences);
    }
    public void setCreatedAt(String createdAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.createdAt = Utility.retrieveDocumentReference("UserSettings", createdAt);
    }
    public void setUpdatedAt(String updatedAt) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.updatedAt = Utility.retrieveDocumentReference("UserSettings", updatedAt);
    }
}
