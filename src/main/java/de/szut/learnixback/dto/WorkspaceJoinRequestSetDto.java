package de.szut.learnixback.dto;

import de.szut.learnixback.entities.Workspace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceJoinRequestSetDto {
    private Workspace workspace;
    private String requesterUserId;
    private String status;
}
