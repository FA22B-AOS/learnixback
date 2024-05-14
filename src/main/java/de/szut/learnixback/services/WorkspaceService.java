package de.szut.learnixback.services;

import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    public Workspace createWorkspace(WorkspaceSetDto workspaceSetDto){
        Workspace workspace = new Workspace();
        workspace.setName(workspaceSetDto.getName());
        workspace.setOwnerId(workspaceSetDto.getOwnerId());
        workspace.setMemberIds(workspaceSetDto.getMemberIds());
        return workspaceRepository.save(workspace);
    }

    public Workspace getWorkspaceById(Long id){
        return workspaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Workspace not found"));
    }
}
