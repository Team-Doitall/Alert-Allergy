package edu.famu.alertallergy.controller;
import edu.famu.alertallergy.models.User.Users;
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

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<Users>> createUser(@RequestBody Users user) {
        try {
            Users createdUser = usersService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Users successfully created.", createdUser,null));
        } catch (Exception  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null,e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers() {
        try {
            UsersService usersService = new UsersService();
            List<Users> userList = usersService.getAllUsers();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", userList,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null,e));
        }
    }

    /*
    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found."),
            @ApiResponse(responseCode = "404", description = "Users not found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve user.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))),
    })

     */

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Users>> getUserById(@PathVariable(name = "id") String userId) {
        try {
            Users user = usersService.getUserById(userId);
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

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<String>> deleteUser(@PathVariable String userId)
    {
        try {
            usersService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users deleted successfuly", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false,"Error deleting Users", null,e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseFormat<Users>> updateUsers(@PathVariable String userId, @RequestBody Users updateValues) {
        try {
           Users users = usersService.updateUsers(userId, updateValues);
            return ResponseEntity.ok(new ApiResponseFormat<>( true, "User updated successfully",users,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating users", null,e));
        }
    }


}
