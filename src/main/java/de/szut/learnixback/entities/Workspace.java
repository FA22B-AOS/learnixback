package de.szut.learnixback.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "public")
    private boolean publicWorkspace;

    @Column(name = "inviteonly")
    private boolean inviteOnly;

    @ElementCollection
    @Column(name = "member_ids")
    private Set<String> memberIds = new HashSet<>();

    @ElementCollection
    @Column(name = "moderator_ids")
    private Set<String> moderatorIds = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkspaceLection> workspaceLections = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
