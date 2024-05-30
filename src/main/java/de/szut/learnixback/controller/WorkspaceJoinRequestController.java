package de.szut.learnixback.controller;

import de.szut.learnixback.customExceptionHandling.RessourceNotFoundException;
import de.szut.learnixback.dto.WorkspaceJoinRequestGetDto;
import de.szut.learnixback.dto.WorkspaceJoinRequestSetDto;
import de.szut.learnixback.entities.WorkspaceJoinRequest;
import de.szut.learnixback.mapper.WorkspaceJoinRequestMapper;
import de.szut.learnixback.services.KeycloakService;
import de.szut.learnixback.services.WorkspaceJoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workspaces/join-request")
public class WorkspaceJoinRequestController {

    @Autowired
    private WorkspaceJoinRequestService workspaceJoinRequestService;

    @Autowired
    private WorkspaceJoinRequestMapper workspaceJoinRequestMapper;

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping
    public ResponseEntity<?> createJoinRequest(@RequestBody WorkspaceJoinRequestSetDto dto) {
        String userId = keycloakService.getCurrentUserId();
        dto.setRequesterUserId(userId);
        try {
            WorkspaceJoinRequest request = workspaceJoinRequestService.createJoinRequest(dto);
            WorkspaceJoinRequestGetDto responseDto = workspaceJoinRequestMapper.mapToWorkspaceJoinRequestGetDto(request);
            return ResponseEntity.ok(responseDto);
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceJoinRequestGetDto>> getAllJoinRequests() {
            List<WorkspaceJoinRequest> workspaceJoinRequests = workspaceJoinRequestService.getAllJoinRequests();
            List<WorkspaceJoinRequestGetDto> dtoList;
            dtoList = workspaceJoinRequests.stream()
                    .map(this.workspaceJoinRequestMapper::mapToWorkspaceJoinRequestGetDto)
                    .toList();
            return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/{requestId}/accept")
    public ResponseEntity<?> acceptJoinRequest(@PathVariable Long requestId) {
        String userId = keycloakService.getCurrentUserId();
        try {
            workspaceJoinRequestService.acceptJoinRequest(requestId, userId);

            return ResponseEntity.ok().build();
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/{requestId}/deny")
    public ResponseEntity<?> denyJoinRequest(@PathVariable Long requestId) {
        String userId = keycloakService.getCurrentUserId();
        try {
            workspaceJoinRequestService.denyJoinRequest(requestId, userId);
            return ResponseEntity.ok().build();
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteJoinRequest(@PathVariable Long requestId) throws AccessDeniedException, RessourceNotFoundException {
        String userId = keycloakService.getCurrentUserId();
        workspaceJoinRequestService.deleteJoinRequest(requestId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/requestByWorkspaceId")
    public ResponseEntity<List<WorkspaceJoinRequestGetDto>> getJoinRequestsForWorkspace(@RequestParam Long workspaceId) throws AccessDeniedException, RessourceNotFoundException {
        String userId = keycloakService.getCurrentUserId();
        List<WorkspaceJoinRequest> requests = workspaceJoinRequestService.getJoinRequestsForWorkspace(workspaceId, userId);
        List<WorkspaceJoinRequestGetDto> responseDtos = requests.stream()
                .map(workspaceJoinRequestMapper::mapToWorkspaceJoinRequestGetDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }
}
