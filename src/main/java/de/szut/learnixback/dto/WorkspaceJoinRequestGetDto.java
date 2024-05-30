package de.szut.learnixback.dto;

import de.szut.learnixback.entities.Workspace;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkspaceJoinRequestGetDto {
    private Long requestId;
    private Workspace workspace;
    private String requesterUserId;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
}
