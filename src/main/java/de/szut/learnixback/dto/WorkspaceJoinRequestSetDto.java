package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceJoinRequestSetDto {
    private Long workspaceId;
    private String requesterUserId;
}
