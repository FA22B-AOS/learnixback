package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceSetDto {
    private String title;
    private String ownerId;
    private boolean publicWorkspace;
    private boolean inviteOnly;
}
