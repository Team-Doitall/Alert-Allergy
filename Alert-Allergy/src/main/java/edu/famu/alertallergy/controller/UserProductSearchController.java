package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.UserProductSearch.UserProductSearch;
import edu.famu.alertallergy.service.UserProductSearchService;
import edu.famu.alertallergy.util.ErrorMessage;
import edu.famu.alertallergy.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user-product-search")
public class UserProductSearchController {

    private final UserProductSearchService userProductSearchService;
    @Value("${response.status}")
    private int statusCode;
    @Value("${response.name}")
    private String name;
    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "userProductSearchService";


    public UserProductSearchController(UserProductSearchService userProductSearchService) {
        this.userProductSearchService = userProductSearchService;
        payload = null;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllUserProductSearches() {
        try {
            payload = UserProductSearchService.getAllUserProductSearches();
            statusCode = 200;
            name = "userProductSearch";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch search from database", CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();

    }

    /*
    @GetMapping("/{searchId}")
    public ResponseEntity<Map<String,Object>> getUserProductSearchById(@PathVariable(name="searchId") String id) {
        try {
            payload = UserProductSearch.getUserProductSearchById(id);
            statusCode = 200;
            name = "userProductSearch";
            } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch product for user " + id + " from database.",CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode, name,payload);

        return response.getResponse();

    }
     */

    @PostMapping("/")
    public ResponseEntity<ResponseWrapper<String>> createUserProductSearch(@RequestBody UserProductSearch userProductSearch) {
        try {
            userProductSearchService.addUserProductSearch(userProductSearch);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "message", "User product search created successfully"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", "Failed to create user product search"));
        }
    }

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
