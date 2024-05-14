package de.szut.learnixback.controller;

import de.szut.learnixback.dto.WorkspaceGetDto;
import de.szut.learnixback.dto.WorkspaceSetDto;
import de.szut.learnixback.entities.Workspace;
import de.szut.learnixback.mapper.WorkspaceMapper;
import de.szut.learnixback.services.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    @Autowired
    private final WorkspaceService workspaceService;

    private final WorkspaceMapper workspaceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceGetDto> getWorkspaceById(@PathVariable Long id) {
        Workspace workspace = workspaceService.getWorkspaceById(id);
        WorkspaceGetDto workspaceDto = workspaceMapper.mapWorkspaceToDto(workspace);
        return ResponseEntity.ok(workspaceDto);
    }

    @PostMapping
    public ResponseEntity<WorkspaceGetDto> createWorkspace(@RequestBody WorkspaceSetDto workspaceSetDto) {
        Workspace workspace = workspaceService.createWorkspace(workspaceSetDto);
        WorkspaceGetDto workspaceDto = workspaceMapper.mapWorkspaceToDto(workspace);
        return ResponseEntity.ok(workspaceDto);
    }


}
