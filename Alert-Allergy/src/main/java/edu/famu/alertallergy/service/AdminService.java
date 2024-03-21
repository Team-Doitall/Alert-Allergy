package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Admin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminService {
    private Firestore firestore;

    public AdminService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Admin documentSnapshotToAdmin(DocumentSnapshot document) {
        Admin admin = null;
        if (document.exists())
        {
            String id = document.getId();
            String username = document.getString("username");
            String password = document.getString("password");
            String email = document.getString("email");
            String role = document.getString("role");

            ArrayList<String> permissions = (ArrayList<String>) document.get("permissions");

            Date createdAt = document.getTimestamp("createdAt").toDate();
            Date updatedAt = document.getTimestamp("updatedAt").toDate();

            admin = new Admin(id, username, password, email, role, permissions, createdAt, updatedAt);
        }
        return admin;
    }

    public List<Admin> getAllAdmins() throws ExecutionException, InterruptedException {
        CollectionReference adminCollection = firestore.collection("Admin");
        ApiFuture<QuerySnapshot> future = adminCollection.get();
        List<Admin> adminList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Admin admin = documentSnapshotToAdmin(document);
            if (admin != null) {
                adminList.add(admin);
            }
        }
        return adminList;
    }

    public Admin getAdminById(String adminId) throws ExecutionException, InterruptedException {
        CollectionReference adminCollection = firestore.collection("Admin");
        ApiFuture<DocumentSnapshot> future = adminCollection.document(adminId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToAdmin(document);
    }
}