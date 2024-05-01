package edu.famu.alertallergy.controller;

import edu.famu.alertallergy.models.UserSettings.UserSettings;
import edu.famu.alertallergy.service.UserSettingsService;
import edu.famu.alertallergy.util.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user-settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<UserSettings>>> getAllUserSettings() {
        try {
            List<UserSettings> userSettingsList = userSettingsService.getAllUserSettings();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User settings retrieved successfully", userSettingsList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user settings", null, e.getMessage()));
        }
    }

    @GetMapping("/{settingId}")
    public ResponseEntity<ApiResponseFormat<UserSettings>> getUserSettingsById(@PathVariable String settingId) {
        try {
            UserSettings userSettings = userSettingsService.getUserSettingsById(settingId);
            if (userSettings != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User settings retrieved successfully", userSettings, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User settings not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user settings", null, e.getMessage()));
        }
    }

    @PutMapping("/{settingId}")
    public ResponseEntity<ApiResponseFormat<UserSettings>> updateUserSettings(@PathVariable String settingId, @RequestBody UserSettings updatedSettings) {
        try {
            UserSettings userSettings = userSettingsService.updateUserSettings(settingId, updatedSettings);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User settings updated successfully", userSettings, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating user settings", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{settingId}")
    public ResponseEntity<ApiResponseFormat<String>> deleteUserSettings(@PathVariable String settingId) {
        try {
            userSettingsService.deleteUserSettings(settingId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "User settings deleted successfully", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting user settings", null, e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<UserSettings>> createUserSettings(@RequestBody UserSettings userSettings) {
        try {
            UserSettings createdSettings = userSettingsService.createUserSettings(userSettings);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User settings created successfully", createdSettings, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user settings", null, e.getMessage()));
        }
    }
}
