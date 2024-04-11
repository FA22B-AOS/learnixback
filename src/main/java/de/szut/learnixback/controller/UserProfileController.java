package de.szut.learnixback.controller;

import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.entities.UserProfile;
import de.szut.learnixback.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/{userGUID}")
    public ResponseEntity<UserProfile> createUserProfile(@PathVariable UUID userGUID){
        try {
            UserProfile createdUserProfile = userProfileService.createUserProfile(userGUID);
            return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @PostMapping("/{userGUID}/profile-picture")
    public ResponseEntity<String> saveProfilePicture(@PathVariable UUID userGUID, @RequestParam("file") MultipartFile file) {
        try {
            byte[] profilePicture = file.getBytes();
            userProfileService.saveProfilePicture(userGUID, profilePicture);
            return ResponseEntity.ok("Profilbild erfolgreich gespeichert");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Lesen des Profilbilds");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}
