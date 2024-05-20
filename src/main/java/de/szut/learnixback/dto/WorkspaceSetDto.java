package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspaceSetDto {
    private String name;
    private String ownerId;
    private boolean publicWorkspace;
    private List<String> memberIds;
}
