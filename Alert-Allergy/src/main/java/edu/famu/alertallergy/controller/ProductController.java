package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.Product.Product;
import edu.famu.alertallergy.service.ProductService;
import edu.famu.alertallergy.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Product>>> getAllProducts() {
        try {
            List<Product> productList = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Products retrieved successfully", productList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving products", null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Product>> getProductById(@PathVariable(name = "id") String productId) {
        try {
            Product product = productService.getProductById(productId);
            if (product != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Product retrieved successfully", product, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Product not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving product", null, e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<Product>> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Product created successfully", createdProduct, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating product", null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Product>> updateProduct(@PathVariable(name = "id") String productId, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(productId, updatedProduct);
            if (product != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Product updated successfully", product, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Product not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating product", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<String>> deleteProduct(@PathVariable(name = "id") String productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Product deleted successfully", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting product", null, e.getMessage()));
        }
    }
}
