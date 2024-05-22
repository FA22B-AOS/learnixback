package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserProfile {

    @Id
    private UUID userGUID;

    @Lob
    private byte[] profilePicture;

    private String name;
}
