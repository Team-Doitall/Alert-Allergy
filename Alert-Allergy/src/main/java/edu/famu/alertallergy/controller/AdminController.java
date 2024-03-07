package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.Admin;
import edu.famu.alertallergy.service.AdminService;
import edu.famu.alertallergy.util.ErrorMessage;
import edu.famu.alertallergy.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admins")
public class AdminController {

    AdminService adminService;

    @Value("${response.status}")
    private int statusCode;

    @Value("${response.name}")
    private String name;
    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "CategoryService";

    //@Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
        payload = null;
    }

    @GetMapping
    public ResponseEntity List<Admin> getAllAdmins() {
        try {
            payload = adminService.getAllAdmins();
            statusCode = 200;
            name = "admin";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch admins from database.", CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode,name,payload);

    }

    @GetMapping("/{adminId}")
    public ResponseEntity<ResponseWrapper<Admin>> getAdminById(@PathVariable String adminId) {
        ResponseWrapper<Admin> response;
        try {
            Admin admin = adminService.getAdminById(adminId);
            if (admin != null) {
                response = new ResponseWrapper<>(HttpStatus.OK.value(), name, admin);
                return ResponseEntity.ok(response);
            } else {
                response = new ResponseWrapper<>(HttpStatus.NOT_FOUND.value(), name, null);
                response.setError(new ErrorMessage("Admin not found with ID: " + adminId));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            response = new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), name, null);
            response.setError(new ErrorMessage("Error occurred while fetching admin with ID: " + adminId, e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

