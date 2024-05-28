package de.szut.learnixback.services;

import de.szut.learnixback.customExceptionHandling.MemberAlreadyExistsException;
import de.szut.learnixback.customExceptionHandling.WorkspaceNotFoundException;
import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.entities.WorkspaceLection;
import de.szut.learnixback.repositories.LectionRepository;
import de.szut.learnixback.repositories.WorkspaceLectionRepository;
import de.szut.learnixback.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final LectionRepository lectionRepository;
    private final WorkspaceLectionRepository workspaceLectionRepository;


    // Basic functionality
    public Workspace createWorkspace(WorkspaceSetDto workspaceSetDto){
        Workspace workspace = new Workspace();
        workspace.setTitle(workspaceSetDto.getTitle());
        workspace.setOwnerId(workspaceSetDto.getOwnerId());
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
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with ID: " + workspaceId));
        if (String.valueOf(workspace.getOwnerId()).equals(ownerId)) {
            workspaceRepository.delete(workspace);
            return true;
        }
        return false;
    }

    // Get Workspaces where userId is in MemberList
    public List<Workspace> getWorkspacesWhereMember(String userId) {
        return workspaceRepository.findAll().stream()
                .filter(workspace -> workspace.getMemberIds().contains(userId))
                .collect(Collectors.toList());
    }

    // Administer workspace-members and moderators
    public void addMemberToWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException, WorkspaceNotFoundException, MemberAlreadyExistsException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));

        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to add members to this workspace.");
        }

        if (workspace.getMemberIds().contains(memberId)) {
            throw new MemberAlreadyExistsException("Member is already part of the workspace.");
        } else {
            workspace.getMemberIds().add(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public void removeMemberFromWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
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
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to add moderators to this workspace.");
        }
        if (workspace.getMemberIds().contains(memberId) && !workspace.getModeratorIds().contains(memberId)) {
            workspace.getModeratorIds().add(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public void removeModeratorFromWorkspace(Long workspaceId, String memberId, String userId) throws AccessDeniedException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to remove moderators from this workspace.");
        }
        if (workspace.getModeratorIds().contains(memberId)) {
            workspace.getModeratorIds().remove(memberId);
            workspaceRepository.save(workspace);
        }
    }

    public Set<String> getMembersOfWorkspace(Long workspaceId, String userId) throws AccessDeniedException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId) && !workspace.getMemberIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to view members of this workspace.");
        }
        return workspace.getMemberIds();
    }

    public Set<String> getModeratorsOfWorkspace(Long workspaceId, String userId) throws AccessDeniedException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId) && !workspace.getMemberIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to view moderators of this workspace.");
        }
        return workspace.getModeratorIds();
    }

    public void setPublicWorkspace(Long workspaceId, String userId, boolean publicWorkspace) throws AccessDeniedException, WorkspaceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
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

    public void addLectionToWorkspace(Long workspaceId, Long lectionId, String userId) throws AccessDeniedException, WorkspaceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        Lection lection = lectionRepository.findById(lectionId).orElse(null);
        if (lection != null) {
            if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
                throw new AccessDeniedException("You are not authorized to add lections to this workspace.");
            }
            WorkspaceLection workspaceLection = new WorkspaceLection();
            workspaceLection.setWorkspace(workspace);
            workspaceLection.setLection(lection);
            workspaceLectionRepository.save(workspaceLection);
            workspace.getWorkspaceLections().add(workspaceLection);
            workspaceRepository.save(workspace);
        }
    }

    public void removeLectionFromWorkspace(Long workspaceId, Long lectionId, String userId) throws AccessDeniedException, WorkspaceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));
        if (!workspace.getOwnerId().equals(userId) && !workspace.getModeratorIds().contains(userId)) {
            throw new AccessDeniedException("You are not authorized to remove lections from this workspace.");
        }

        for (WorkspaceLection workspaceLection : workspace.getWorkspaceLections()) {
            if (workspaceLection.getLection().getLectionId().equals(lectionId)) {
                workspace.getWorkspaceLections().remove(workspaceLection);
                workspaceRepository.save(workspace);
                workspaceLectionRepository.delete(workspaceLection);
                break;
            }
        }
    }
}
