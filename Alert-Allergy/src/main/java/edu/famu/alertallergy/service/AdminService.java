package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Admin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

            Date createdAt = Objects.requireNonNull(document.getTimestamp("createdAt")).toDate();
            Date updatedAt = Objects.requireNonNull(document.getTimestamp("updatedAt")).toDate();

            return new Admin(id, username, password, email, role, permissions, createdAt, updatedAt);
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

    public Admin createAdmin(Admin admin) {
        CollectionReference adminCollection = firestore.collection("Admin");
        DocumentReference docRef = adminCollection.document();
        admin.setAdminId(docRef.getId()); // Set the adminId to the generated Firestore document ID
        docRef.set(admin); // Add the admin to Firestore
        return admin;
    }

    public Admin updateAdmin(String adminId, Admin updatedAdmin) throws ExecutionException, InterruptedException {
        CollectionReference adminCollection = firestore.collection("Admin");
        DocumentReference docRef = adminCollection.document(adminId);
        ApiFuture<WriteResult> future = docRef.set(updatedAdmin);
        future.get(); // Wait for the update operation to complete
        return updatedAdmin;
    }

    public void deleteAdmin(String adminId) {
        CollectionReference adminCollection = firestore.collection("Admin");
        adminCollection.document(adminId).delete();
    }
}
