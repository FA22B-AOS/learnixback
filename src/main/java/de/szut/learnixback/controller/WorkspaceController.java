package de.szut.learnixback.controller;

import de.szut.learnixback.customExceptionHandling.WorkspaceNotFoundException;
import de.szut.learnixback.dto.WorkspaceGetDto;
import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.mapper.WorkspaceMapper;
import de.szut.learnixback.services.KeycloakService;
import de.szut.learnixback.services.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    private final WorkspaceMapper workspaceMapper;

    private final KeycloakService keycloakService;

    @PostMapping
    public ResponseEntity<?> createWorkspace(@RequestBody WorkspaceSetDto workspaceSetDto) {
        try {
            Workspace workspace = this.workspaceService.createWorkspace(workspaceSetDto);
            WorkspaceGetDto workspaceDto = this.workspaceMapper.mapWorkspaceToDto(workspace);
            return ResponseEntity.status(HttpStatus.CREATED).body(workspaceDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create workspace: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceGetDto>> getAllWorkspaces() {
        List<WorkspaceGetDto> workspacesList;
        List<Workspace> workspaces = this.workspaceService.getAllWorkspaces();
        workspacesList = workspaces.stream()
                .map(this.workspaceMapper::mapWorkspaceToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(workspacesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkspaceById(@PathVariable Long id) {
        try {
            Workspace workspace = this.workspaceService.getWorkspaceById(id);
            WorkspaceGetDto workspaceDto = this.workspaceMapper.mapWorkspaceToDto(workspace);
            return ResponseEntity.ok(workspaceDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workspace not found with ID: " + id);
        }
    }

    @GetMapping("/createdby/{ownerId}")
    public ResponseEntity<?> getWorkspacesCreatedByOwner(@PathVariable String ownerId) {
        try {
            List<Workspace> workspaces = this.workspaceService.getWorkspacesCreatedByOwner(ownerId);
            List<WorkspaceGetDto> workspaceGetDtoList = workspaces.stream()
                    .map(this.workspaceMapper::mapWorkspaceToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(workspaceGetDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workspaces not found for ownerId: " + ownerId);
        }
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long workspaceId, @RequestHeader("Authorization") String token) {
        String ownerId = keycloakService.getUserIdFromToken(token);

        try {
            boolean deleted = workspaceService.deleteWorkspace(workspaceId, ownerId);
            if (deleted) {
                return ResponseEntity.ok("Workspace successfully deleted");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this workspace");
            }
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/member")
    public ResponseEntity<?> getWorkspacesWhereMember(@RequestHeader("Authorization") String token) {
        try {
            String userId = keycloakService.getUserIdFromToken(token);

            List<Workspace> workspaces = this.workspaceService.getWorkspacesWhereMember(userId);
            if (workspaces.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No workspaces found for the given user.");
            }

            List<WorkspaceGetDto> workspaceGetDtoList = workspaces.stream()
                    .map(this.workspaceMapper::mapWorkspaceToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(workspaceGetDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    @PostMapping("/{workspaceId}/members")
    public ResponseEntity<?> addMemberToWorkspace(@PathVariable Long workspaceId, @RequestParam String memberId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            workspaceService.addMemberToWorkspace(workspaceId, memberId, userId);
            return ResponseEntity.ok("Member added successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to add members to this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{workspaceId}/members")
    public ResponseEntity<?> removeMemberFromWorkspace(@PathVariable Long workspaceId, @RequestParam String memberId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            workspaceService.removeMemberFromWorkspace(workspaceId, memberId, userId);
            return ResponseEntity.ok("Member removed successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to remove members from this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{workspaceId}/moderators")
    public ResponseEntity<?> addModeratorToWorkspace(@PathVariable Long workspaceId, @RequestParam String memberId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            workspaceService.addModeratorToWorkspace(workspaceId, memberId, userId);
            return ResponseEntity.ok("Moderator added successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to add moderators to this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{workspaceId}/moderators")
    public ResponseEntity<?> removeModeratorFromWorkspace(@PathVariable Long workspaceId, @RequestParam String memberId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            workspaceService.removeModeratorFromWorkspace(workspaceId, memberId, userId);
            return ResponseEntity.ok("Moderator removed successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to remove moderators from this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{workspaceId}/members")
    public ResponseEntity<?> getMembersOfWorkspace(@PathVariable Long workspaceId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            List<String> members = workspaceService.getMembersOfWorkspace(workspaceId, userId);
            return ResponseEntity.ok(members);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to view members of this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{workspaceId}/moderators")
    public ResponseEntity<?> getModeratorsOfWorkspace(@PathVariable Long workspaceId, @RequestHeader("Authorization") String token) {
        String userId = keycloakService.getUserIdFromToken(token);
        try {
            List<String> moderators = workspaceService.getModeratorsOfWorkspace(workspaceId, userId);
            return ResponseEntity.ok(moderators);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to view moderators of this workspace");
        } catch (WorkspaceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}