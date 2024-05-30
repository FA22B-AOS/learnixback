package de.szut.learnixback.mapper;

import de.szut.learnixback.dto.WorkspaceJoinRequestGetDto;
import de.szut.learnixback.entities.WorkspaceJoinRequest;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceJoinRequestMapper {

    public WorkspaceJoinRequestGetDto mapToWorkspaceJoinRequestGetDto(WorkspaceJoinRequest request) {
        WorkspaceJoinRequestGetDto dto = new WorkspaceJoinRequestGetDto();
        dto.setRequestId(request.getRequestId());
        dto.setWorkspace(request.getWorkspace());
        dto.setRequesterUserId(request.getRequesterUserId());
        return dto;
    }
}
