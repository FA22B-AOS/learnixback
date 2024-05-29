package de.szut.learnixback.dto;

import de.szut.learnixback.entities.Workspace;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkspaceJoinRequestGetDto {
    private Long id;
    private Workspace workspace;
    private String requesterUserId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
}
