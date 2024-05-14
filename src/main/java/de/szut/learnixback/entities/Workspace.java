package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "owner_id")
    private UUID ownerId;

    @ElementCollection
    @Column(name = "member_id")
    private List<UUID> memberIds = new ArrayList<>();

    public Workspace(String name, UUID ownerId, List<UUID> memberIds) {
        this.name = name;
        this.ownerId = ownerId;
        this.memberIds = memberIds;
    }

}
