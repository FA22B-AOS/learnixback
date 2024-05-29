package de.szut.learnixback.mapper;

import de.szut.learnixback.dto.WorkspaceJoinRequestGetDto;
import de.szut.learnixback.entities.WorkspaceJoinRequest;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceJoinRequestMapper {

    public WorkspaceJoinRequestGetDto mapToWorkspaceJoinRequestGetDto(WorkspaceJoinRequest request) {
        WorkspaceJoinRequestGetDto dto = new WorkspaceJoinRequestGetDto();
        dto.setId(request.getId());
        dto.setWorkspace(request.getWorkspace());
        dto.setRequesterUserId(request.getRequesterUserId());
        dto.setStatus(request.getStatus());
        return dto;
    }

}
