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
@RequestMapping("/join-request")
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

    @PutMapping("/{requestId}/respond")
    public ResponseEntity<?> respondToJoinRequest(@PathVariable Long requestId, @RequestParam String status) {
        String userId = keycloakService.getCurrentUserId();
        try {
            WorkspaceJoinRequest request = workspaceJoinRequestService.respondToJoinRequest(requestId, status, userId);
            WorkspaceJoinRequestGetDto responseDto = workspaceJoinRequestMapper.mapToWorkspaceJoinRequestGetDto(request);
            return ResponseEntity.ok(responseDto);
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

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<WorkspaceJoinRequestGetDto>> getJoinRequestsForWorkspace(@PathVariable Long workspaceId) throws AccessDeniedException, RessourceNotFoundException {
        String userId = keycloakService.getCurrentUserId();
        List<WorkspaceJoinRequest> requests = workspaceJoinRequestService.getJoinRequestsForWorkspace(workspaceId, userId);
        List<WorkspaceJoinRequestGetDto> responseDtos = requests.stream()
                .map(workspaceJoinRequestMapper::mapToWorkspaceJoinRequestGetDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }
}
