package de.szut.learnixback.services;

import de.szut.learnixback.customExceptionHandling.WorkspaceNotFoundException;
import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    public Workspace createWorkspace(WorkspaceSetDto workspaceSetDto){
        Workspace workspace = new Workspace();
        workspace.setName(workspaceSetDto.getName());
        workspace.setOwnerId(workspaceSetDto.getOwnerId());
        workspace.setMemberIds(workspaceSetDto.getMemberIds());
        workspace.setPublicWorkspace(workspaceSetDto.isPublicWorkspace());
        return workspaceRepository.save(workspace);
    }

    public Workspace getWorkspaceById(Long id){
        return workspaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Workspace not found"));
    }

    public List<Workspace> getAllWorkspaces(){
        return this.workspaceRepository.findAll();
    }

    public List<Workspace> getWorkspacesCreatedByOwner(String ownerId) {
        List<Workspace> allWorkspaces = workspaceRepository.findAll();
        return allWorkspaces.stream()
                .filter(workspace -> String.valueOf(workspace.getOwnerId()).equals(ownerId))
                .collect(Collectors.toList());
    }

    public boolean deleteWorkspace(Long workspaceId, String ownerId) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            if (String.valueOf(workspace.getOwnerId()).equals(ownerId)) {
                workspaceRepository.delete(workspace);
                return true;
            }
        }
        throw new WorkspaceNotFoundException("Workspace not found with ID: " + workspaceId);
    }

    public List<Workspace> getWorkspacesWhereMember(String userId) {
        return workspaceRepository.findAll().stream()
                .filter(workspace -> workspace.getMemberIds().contains(userId))
                .collect(Collectors.toList());
    }
}
