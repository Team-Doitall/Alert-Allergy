package edu.famu.alertallergy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.alertallergy.models.Product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private final Firestore firestore;

    public ProductService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Product documentSnapshotToProduct(DocumentSnapshot document) {
        return document.exists() ? document.toObject(Product.class) : null;
    }

    public List<Product> getAllProducts() throws ExecutionException, InterruptedException {
        CollectionReference productCollection = firestore.collection("Product");
        ApiFuture<QuerySnapshot> future = productCollection.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Product> productList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            Product product = documentSnapshotToProduct(document);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }

    public Product getProductById(String productId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("Product").document(productId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToProduct(document);
    }

    public Product addProduct(Product product) throws ExecutionException, InterruptedException {
        CollectionReference productCollection = firestore.collection("Product");
        ApiFuture<DocumentReference> future = productCollection.add(product);
        return new Product(future.get().getId(), product.getProductName(), product.getIngredients(), product.getAllergenWarnings(), product.getCreatedAt(), product.getUpdatedAt());
    }

    public Product updateProduct(String productId, Product updatedProduct) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("Product").document(productId);
        ApiFuture<WriteResult> future = docRef.set(updatedProduct);
        future.get(); // Wait for the update operation to complete
        return updatedProduct;
    }

    public void deleteProduct(String productId) {
        firestore.collection("Product").document(productId).delete();
    }
}


