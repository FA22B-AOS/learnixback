package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "workspace_lection")
public class WorkspaceLection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceLectionId;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "lection_id")
    private Lection lection;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "addedAt", nullable = false, updatable = false)
    private LocalDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
    }

}
