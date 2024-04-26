package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.User.User;
import edu.famu.alertallergy.models.UserSettings.UserSettings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserSettingsService {

    private Firestore firestore;

    public UserSettingsService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public UserSettings documentSnapshotToUserSettings(DocumentSnapshot document) {
        if (document.exists()) {
            String settingId = document.getId();
            String userId = document.getString("userId");
            String language = document.getString("language");
            Map<String, Object> notificationPreferences = document.contains("notificationPreferences") ? (Map<String, Object>) document.getData().get("notificationPreferences") : null;
            Map<String, Object> displayPreferences = document.contains("displayPreferences") ? (Map<String, Object>) document.getData().get("displayPreferences") : null;
            Timestamp createdAt = document.contains("createdAt") ? document.getTimestamp("createdAt") : null;
            Timestamp updatedAt = document.contains("updatedAt") ? document.getTimestamp("updatedAt") : null;
            User user = document.contains("user") ? document.toObject(User.class) : null;
            return new UserSettings(settingId, userId, language, notificationPreferences, displayPreferences, createdAt, updatedAt, user);
        }
        return null;
    }

    public List<UserSettings> getAllUserSettings() throws ExecutionException, InterruptedException {
        CollectionReference userSettingsCollection = firestore.collection("UserSettings");
        ApiFuture<QuerySnapshot> future = userSettingsCollection.get();
        List<UserSettings> userSettingsList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            UserSettings userSettings = documentSnapshotToUserSettings(document);
            if (userSettings != null) {
                userSettingsList.add(userSettings);
            }
        }
        return userSettingsList;
    }

    public UserSettings getUserSettingsById(String settingId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("UserSettings").document(settingId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUserSettings(document);
    }

    public UserSettings updateUserSettings(String settingId, UserSettings updatedSettings) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("UserSettings").document(settingId);
        ApiFuture<WriteResult> future = docRef.set(updatedSettings);
        future.get();
        return updatedSettings;
    }

    public void deleteUserSettings(String settingId) {
        firestore.collection("UserSettings").document(settingId).delete();
    }
    public UserSettings createUserSettings(UserSettings userSettings) {
        DocumentReference docRef = firestore.collection("UserSettings").document();
        userSettings.setSettingId(docRef.getId());
        docRef.set(userSettings);
        return userSettings;
    }
}



