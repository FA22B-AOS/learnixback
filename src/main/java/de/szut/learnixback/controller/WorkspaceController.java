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
        List<WorkspaceGetDto> workspacesList = new ArrayList<>();
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


}
