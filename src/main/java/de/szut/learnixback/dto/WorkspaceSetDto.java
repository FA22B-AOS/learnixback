package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspaceSetDto {
    private String name;
    private Long ownerId;
    private boolean publicWorkspace;
    private List<Long> memberIds;
}
