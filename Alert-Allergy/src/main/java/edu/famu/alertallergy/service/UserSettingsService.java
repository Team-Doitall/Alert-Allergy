package edu.famu.alertallergy.service;


import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.UserSettings.UserSettings;
import edu.famu.alertallergy.models.Users.Users;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserSettingsService {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final Firestore firestore = FirestoreClient.getFirestore();

    private UserSettings getUserSettings(DocumentSnapshot doc) throws ExecutionException, InterruptedException{
        UsersService usersService = new UsersService();
        Users users = usersService.getUser((DocumentReference) Objects.requireNonNull(doc.get("users")));


        return null;
    }

}
