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
            AdminService adminService = new AdminService();
            List<Admin> adminList = adminService.getAllAdmins();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Admins retrieved successfully", adminList,null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null,e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<List<Admin>>> getAdminById(String adminId) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            if(admin != null)
            {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", admin,null));
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


