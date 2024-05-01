package edu.famu.alertallergy.controller;
import edu.famu.alertallergy.models.User.User;
import edu.famu.alertallergy.service.UsersService;
import edu.famu.alertallergy.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<User>> createUser(@RequestBody User user) {
        try {
            User createdUser = usersService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User created successfully.", createdUser,null));
        } catch (Exception  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null,e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<User>>> getAllUsers() {
        try {
            List<User> userList = usersService.getAllUsers();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", userList,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null,e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<User>> getUserById(@PathVariable String userId) {
        try {
            User user = usersService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User retrieved successfully", user,null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null,null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null,e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<String>> deleteUser(@PathVariable String userId)
    {
        try {
            usersService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User deleted successfully", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false,"Error deleting user", null,e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<User>> updateUser(@PathVariable String userId, @RequestBody User updateValues) {
        try {
            User user = usersService.updateUser(userId, updateValues);
            return ResponseEntity.ok(new ApiResponseFormat<>( true, "User updated successfully",user,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating user", null,e.getMessage()));
        }
    }
}
