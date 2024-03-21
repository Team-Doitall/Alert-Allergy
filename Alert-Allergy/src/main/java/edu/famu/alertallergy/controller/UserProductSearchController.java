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
            UserProductSearchService userProductSearchService = new UserProductSearchService();
            List<UserProductSearch> uspsList = userProductSearchService.getAllUserProductSearches();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users product search retrieve", uspsList,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null,e));
        }

    }

    /*
    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<String>> createUserProductSearch(@RequestBody UserProductSearch userProductSearch) {
        try {
            userProductSearchService.addUserProductSearch(userProductSearch);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "message", "User product search created successfully"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", "Failed to create user product search"));
        }
    }
*/
    @PutMapping("/{searchId}")
    public ResponseEntity<ResponseWrapper<String>> updateUserProductSearch(@PathVariable String searchId, @RequestBody UserProductSearch userProductSearch) {
        try {
            userProductSearchService.updateUserProductSearch(searchId, userProductSearch);
            return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "message", "User product search updated successfully"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", "Failed to update user product search"));
        }
    }

    @DeleteMapping("/{searchId}")
    public ResponseEntity<ResponseWrapper<String>> deleteUserProductSearch(@PathVariable String searchId) {
        try {
            userProductSearchService.deleteUserProductSearch(searchId);
            return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "message", "User product search deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", "Failed to delete user product search"));
        }
    }
}
}
