package de.szut.learnixback.services;

import de.szut.learnixback.entities.UserProfile;
import de.szut.learnixback.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createUserProfile(UUID userGUID){
        Optional<UserProfile> userProfile = userProfileRepository.findById(userGUID);
        if(userProfile.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Benutzerprofil existiert bereits");
        else
            return this.userProfileRepository.save(new UserProfile(userGUID));
    }

    public void saveProfilePicture(UUID userGUID, byte[] profilePicture) {
        // Überprüfen, ob das Benutzerprofil existiert
        UserProfile userProfile = userProfileRepository.findById(userGUID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzerprofil nicht gefunden"));

        // Profilbild setzen
        userProfile.setProfilePicture(profilePicture);

        // Profilbild speichern
        userProfileRepository.save(userProfile);
    }
}
