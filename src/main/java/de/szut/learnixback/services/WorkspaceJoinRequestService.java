package de.szut.learnixback.services;

import de.szut.learnixback.customExceptionHandling.RessourceNotFoundException;
import de.szut.learnixback.dto.WorkspaceJoinRequestSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.entities.WorkspaceJoinRequest;
import de.szut.learnixback.repositories.WorkspaceJoinRequestRepository;
import de.szut.learnixback.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceJoinRequestService {
    private final WorkspaceJoinRequestRepository workspaceJoinRequestRepository;
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceJoinRequest createJoinRequest(WorkspaceJoinRequestSetDto dto) throws RessourceNotFoundException {
        Workspace workspace = workspaceRepository.findById(dto.getWorkspace().getWorkspaceId())
                .orElseThrow(() -> new RessourceNotFoundException("Workspace not found"));

        WorkspaceJoinRequest request = new WorkspaceJoinRequest();
        request.setWorkspace(workspace);
        request.setRequesterUserId(dto.getRequesterUserId());
        request.setStatus("PENDING");

        return workspaceJoinRequestRepository.save(request);
    }

    public WorkspaceJoinRequest respondToJoinRequest(Long requestId, String status, String userId) throws AccessDeniedException, RessourceNotFoundException {
        WorkspaceJoinRequest request = workspaceJoinRequestRepository.findById(requestId)
                .orElseThrow(() -> new RessourceNotFoundException("Join request not found"));

        Workspace workspace = request.getWorkspace();
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to respond to this join request.");
        }

        request.setStatus(status);
        return workspaceJoinRequestRepository.save(request);
    }

    public void deleteJoinRequest(Long requestId, String userId) throws AccessDeniedException, RessourceNotFoundException {
        WorkspaceJoinRequest request = workspaceJoinRequestRepository.findById(requestId)
                .orElseThrow(() -> new RessourceNotFoundException("Join request not found"));

        if (!request.getRequesterUserId().equals(userId)) {
            throw new AccessDeniedException("You are not authorized to delete this join request.");
        }

        workspaceJoinRequestRepository.delete(request);
    }

    public List<WorkspaceJoinRequest> getJoinRequestsForWorkspace(Long workspaceId, String userId) throws AccessDeniedException, RessourceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RessourceNotFoundException("Workspace not found"));

        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to view join requests for this workspace.");
        }

        return workspaceJoinRequestRepository.findAll()
                .stream()
                .filter(request -> request.getWorkspace().equals(workspace))
                .collect(Collectors.toList());
    }
}
