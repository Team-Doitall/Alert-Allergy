package edu.famu.alertallergy.models.UserSettings;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
public class RestUserSettings extends AUserSettings {
    private DocumentReference user;

    public RestUserSettings(@Nullable String settingId, String language, Map<String,Object> notificationPreferences, Map<String, Object> displayPreferences, Timestamp createdAt, Timestamp updatedAt, DocumentReference user) {
        super(settingId, language, notificationPreferences, displayPreferences, createdAt, updatedAt);
        this.user = user;
    }

    public void setUser(String userRef) {

      Firestore Utility = FirestoreClient.getFirestore();
      this.user = Utility.collection("User").document(userRef);
    }









    /*
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

     */
}