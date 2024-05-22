package de.szut.learnixback.dto;

import de.szut.learnixback.entities.Lection;
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
    private List<String> moderatorIds;
    private List<Lection> lections;
}
