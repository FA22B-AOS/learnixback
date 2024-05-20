package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspaceGetDto {
    private Long id;
    private String name;
    private String ownerId;
    private boolean publicWorkspace;
    private List<String> memberIds;
}
