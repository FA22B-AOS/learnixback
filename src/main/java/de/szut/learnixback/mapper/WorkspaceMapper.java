package de.szut.learnixback.mapper;

import de.szut.learnixback.dto.WorkspaceGetDto;
import de.szut.learnixback.entities.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {

    public WorkspaceGetDto mapWorkspaceToDto(Workspace workspace) {
        WorkspaceGetDto workspaceDto = new WorkspaceGetDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setName(workspace.getName());
        workspaceDto.setOwnerId(workspace.getOwnerId());
        workspaceDto.setMemberIds(workspace.getMemberIds());
        return workspaceDto;
    }
}
