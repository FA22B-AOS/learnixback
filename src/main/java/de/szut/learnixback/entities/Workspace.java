package de.szut.learnixback.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "public")
    private boolean publicWorkspace;

    @ElementCollection
    @Column(name = "member_ids")
    private List<String> memberIds = new ArrayList<>();

    @ElementCollection
    @Column(name = "moderator_ids")
    private List<String> moderatorIds = new ArrayList<>();

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lection> lections = new ArrayList<>();

}