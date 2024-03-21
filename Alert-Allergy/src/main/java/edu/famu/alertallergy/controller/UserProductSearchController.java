package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.UserProductSearch.UserProductSearch;
import edu.famu.alertallergy.service.UserProductSearchService;
import edu.famu.alertallergy.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user-product-search")
public class UserProductSearchController {

    private final UserProductSearchService userProductSearchService;

    public UserProductSearchController(UserProductSearchService userProductSearchService) {
        this.userProductSearchService = userProductSearchService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<UserProductSearch>>> getAllUserProductSearches() {
        try {
            List<UserProductSearch> uspsList = userProductSearchService.getAllUserProductSearches();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users product search retrieved", uspsList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    @GetMapping("/{searchId}")
    public ResponseEntity<ApiResponseFormat<UserProductSearch>> getUserProductSearchById(@PathVariable String searchId) {
        try {
            UserProductSearch userProductSearch = userProductSearchService.getUserProductSearchById(searchId);
            if (userProductSearch != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", userProductSearch, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Product not Found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving User Product Search", null, e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<String>> createUserProductSearch(@RequestBody UserProductSearch userProductSearch) {
        try {
            userProductSearchService.addUserProductSearch(userProductSearch);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User product search created successfully", null, null));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Failed to create user product search", null, e.getMessage()));
        }
    }

    @PutMapping("/{searchId}")
    public ResponseEntity<ApiResponseFormat<String>> updateUserProductSearch(@PathVariable String searchId, @RequestBody UserProductSearch userProductSearch) {
        try {
            userProductSearchService.updateUserProductSearch(searchId, userProductSearch);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User product search updated successfully", null, null));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Failed to update user product search", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{searchId}")
    public ResponseEntity<ApiResponseFormat<String>> deleteUserProductSearch(@PathVariable String searchId) {
        try {
            userProductSearchService.deleteUserProductSearch(searchId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User product search deleted successfully", null, null));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Failed to delete user product search", null, e.getMessage()));
        }
    }
}
