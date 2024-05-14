package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class WorkspaceGetDto {
    private Long id;
    private String name;
    private UUID ownerId;
    private List<UUID> memberIds;
}
