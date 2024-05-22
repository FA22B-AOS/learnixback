package de.szut.learnixback.mapper;

import de.szut.learnixback.dto.WorkspaceGetDto;
import de.szut.learnixback.entities.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {

    public WorkspaceGetDto mapWorkspaceToDto(Workspace workspace) {
        WorkspaceGetDto workspaceDto = new WorkspaceGetDto();
        workspaceDto.setWorkspaceId(workspace.getWorkspaceId());
        workspaceDto.setTitle(workspace.getTitle());
        workspaceDto.setOwnerId(workspace.getOwnerId());
        workspaceDto.setPublicWorkspace(workspace.isPublicWorkspace());
        workspaceDto.setInviteOnly(workspace.isInviteOnly());
        workspaceDto.setMemberIds(workspace.getMemberIds());
        workspaceDto.setModeratorIds(workspace.getModeratorIds());
        return workspaceDto;
    }
}
