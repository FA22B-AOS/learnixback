package de.szut.learnixback.dto;

import de.szut.learnixback.entities.WorkspaceJoinRequest;
import de.szut.learnixback.entities.WorkspaceLection;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WorkspaceGetDto {
    private Long workspaceId;
    private String title;
    private String ownerId;
    private boolean publicWorkspace;
    private boolean inviteOnly;
    private Set<String> memberIds;
    private Set<String> moderatorIds;
    private Set<WorkspaceLection> workspaceLections;
    private Set<WorkspaceJoinRequest> joinRequests;
}
