package de.szut.learnixback.services;

import de.szut.learnixback.customExceptionHandling.MemberAlreadyExistsException;
import de.szut.learnixback.customExceptionHandling.WorkspaceNotFoundException;
import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;


    // Basic functionality
    public Workspace createWorkspace(WorkspaceSetDto workspaceSetDto){
        Workspace workspace = new Workspace();
        workspace.setTitle(workspaceSetDto.getTitle());
        workspace.setOwnerId(workspaceSetDto.getOwnerId());
        workspace.setMemberIds(workspaceSetDto.getMemberIds());
        workspace.setPublicWorkspace(workspaceSetDto.isPublicWorkspace());
        return workspaceRepository.save(workspace);
    }

    public Workspace getWorkspaceById(Long id){
        return workspaceRepository.findById(id).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
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

    // Get Workspaces where userId is in MemberList
    public List<Workspace> getWorkspacesWhereMember(String userId) {
        return workspaceRepository.findAll().stream()
                .filter(workspace -> workspace.getMemberIds().contains(userId))
                .collect(Collectors.toList());
    }

    // Administer workspace-members and moderators
    public void addMemberToWorkspace(Long workspaceId, String memberId, String userId)
            throws AccessDeniedException, WorkspaceNotFoundException, MemberAlreadyExistsException {
        Workspace workspace = getWorkspaceById(workspaceId);

        if (workspace == null) {
            throw new WorkspaceNotFoundException("Workspace not found");
        }

        // Check if the user is authorized to add members
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to add members to this workspace.");
        }

        // Check if the member is already part of the workspace
        if (workspace.getMemberIds().contains(memberId)) {
            throw new MemberAlreadyExistsException("Member is already part of the workspace.");
        } else {
            workspace.getMemberIds().add(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public void removeMemberFromWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to remove members from this workspace.");
        }
        if (workspace.getMemberIds().contains(memberId)) {
            workspace.getMemberIds().remove(memberId);
            workspace.getModeratorIds().remove(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public void addModeratorToWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to add moderators to this workspace.");
        }
        if (workspace.getMemberIds().contains(memberId) && !workspace.getModeratorIds().contains(memberId)) {
            workspace.getModeratorIds().add(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public void removeModeratorFromWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to remove moderators from this workspace.");
        }
        if (workspace.getModeratorIds().contains(memberId)) {
            workspace.getModeratorIds().remove(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public List<String> getMembersOfWorkspace(Long workspaceId, String userId) throws AccessDeniedException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId) && !workspace.getMemberIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to view members of this workspace.");
        }
        return workspace.getMemberIds();
    }

    public List<String> getModeratorsOfWorkspace(Long workspaceId, String userId) throws AccessDeniedException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId) && !workspace.getMemberIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to view moderators of this workspace.");
        }
        return workspace.getModeratorIds();
    }

    public void setPublicWorkspace(Long workspaceId, String userId, boolean publicWorkspace) throws AccessDeniedException, WorkspaceNotFoundException {
        Workspace workspace = getWorkspaceById(workspaceId);
        if (!workspace.getOwnerId().equals(userId)) {
            throw new AccessDeniedException("You are not authorized to update this workspace.");
        }
        workspace.setPublicWorkspace(publicWorkspace);
        workspaceRepository.save(workspace);
    }

    public void setInviteOnly(Long workspaceId, boolean inviteOnly, String userId) throws AccessDeniedException, WorkspaceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));

        if (!workspace.getOwnerId().equals(userId)) {
            throw new AccessDeniedException("You are not authorized to update this workspace");
        }

        workspace.setInviteOnly(inviteOnly);
        workspaceRepository.save(workspace);
    }
}
