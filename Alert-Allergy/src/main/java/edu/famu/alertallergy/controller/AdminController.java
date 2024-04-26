package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.Admin;
import edu.famu.alertallergy.service.AdminService;
import edu.famu.alertallergy.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Admin>>> getAllAdmin() {
        try {
            List<Admin> adminList = adminService.getAllAdmins();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Admins retrieved successfully", adminList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving admins", null, e.getMessage()));
        }
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<ApiResponseFormat<Admin>> getAdminById(@PathVariable String adminId) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            if (admin != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Admin retrieved successfully", admin, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Admin not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving admin", null, e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<Admin>> createAdmin(@RequestBody Admin admin) {
        try {
            Admin createdAdmin = adminService.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Admin created successfully", createdAdmin, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating admin", null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<Admin>> updateAdmin(@PathVariable String id, @RequestBody Admin updatedAdmin) {
        try {
            Admin admin = adminService.updateAdmin(id, updatedAdmin);
            if (admin != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Admin updated successfully", admin, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Admin not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating admin", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<String>> deleteAdmin(@PathVariable String id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Admin deleted successfully", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting admin", null, e.getMessage()));
        }
    }
}



