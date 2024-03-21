package edu.famu.alertallergy.controller;

import com.google.cloud.firestore.DocumentReference;
import edu.famu.alertallergy.models.Users.Users;
import edu.famu.alertallergy.service.UsersService;
import edu.famu.alertallergy.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    UsersService usersService;
    @Value("${response.status}")
    private int statusCode;
    @Value("${response.name}")
    private String name;
    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "UserService";

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
        payload = null;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllUsers(){
        try{
            payload = usersService.getUsers();
            statusCode = 200;
            name = "users";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch users from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getUsers(DocumentReference id){
        try{
            payload = usersService.getUser(id);
            statusCode = 200;
            name = "user";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch user with id " + id + " from database.",CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();
    }

    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody Users users){
        try{
            payload = usersService.createUsers(users);
            statusCode = 201;
            name = "userId";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot create new user in database.", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable(name="userId") String id, @RequestBody Map<String, String> updateValues){


        try{

            usersService.updateUsers(id, updateValues);
            statusCode = 201;
            name = "message";
            payload = "Update successful for user with id " + id;

        } catch (Exception e) {
            payload = new ErrorMessage("Cannot update user with id " + id,CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();
    }

}
