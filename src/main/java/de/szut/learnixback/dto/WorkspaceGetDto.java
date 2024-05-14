package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspaceGetDto {
    private Long id;
    private String name;
    private Long ownerId;
    private List<Long> memberIds;
}
