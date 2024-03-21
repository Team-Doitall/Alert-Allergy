package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers() {
        try {
            UsersServices usersServices = new UsersServices();
            List<Users> userList = userService.getAllUsers();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", userList,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null,e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Users>> getUserById(@PathVariable(name = "id") String userId) {
        try {
            Users user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", user,null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Users not found", null,null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null,e));
        }
    }

}
