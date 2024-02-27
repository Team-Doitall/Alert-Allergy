package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.UserSettings;
import edu.famu.alertallergy.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class UserSettingsService {
    private Firestore firestore;

    public UserSettingsService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public UserSettingsService documentSnapshotToUserSettingsService(DocumentSnapshot document) {
        UserSettingsService usersettings = null;
        if (document.exists())
        {
            String settingId = document.getId();
            String user = document.getString("user"); //Reference
            String language = document.getString("english");
            // Retrieving notificationPreferences
            Map<String, Object> notificationPreferences = document.get("notificationPreferences", Map.class);
            // Retrieving displayPreferences
            Map<String, Object> displayPreferences = document.get("displayPreferences", Map.class);
            Date createdAt = document.getTimestamp("createdAt").toDate();
            Date updatedAt = document.getTimestamp("updatedAt").toDate();

            usersettings = new UserSettingsService(settingId, user, language, notificationPreferences, displayPreferences,createdAt, updatedAt);

        }
        return usersettings;
    }

    public List<UserSettingsService> getAllUserSettings() throws ExecutionException, InterruptedException {
        CollectionReference UserSettingsCollection = firestore.collection("UserSettings");
        ApiFuture<QuerySnapshot> future = UserSettingsCollection.get();
        List<UserSettings> UserSettingsServiceList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            UserSettingsService usersettings = documentSnapshotToUserSettingsService(document);
            if (usersettings != null) {
                UserSettingsServiceList.add(usersettings);
            }
        }
        return UserSettingsServiceList;
    }

    public UserSettings getUserSettingsById(String settingId) throws ExecutionException, InterruptedException {
        CollectionReference UserSettingsCollection = firestore.collection("UserSettings");
        ApiFuture<DocumentSnapshot> future = UserSettingsCollection.document(settingId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUserSettingsService(document);
    }
}
