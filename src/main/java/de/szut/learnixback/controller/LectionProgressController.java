package de.szut.learnixback.controller;

import de.szut.learnixback.dto.LectureProgressDTO;
import de.szut.learnixback.entities.LectionProgress;
import de.szut.learnixback.services.LectionProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/progress")
public class LectionProgressController {

    @Autowired
    private LectionProgressService lectionProgressService;

    @GetMapping("/{userGUID}/{lectionID}")
    public ResponseEntity<LectionProgress> getLectionProgress(@PathVariable String userGUID, @PathVariable Long lectionID){
        LectionProgress lectionProgress = this.lectionProgressService.getLectionProgress(userGUID, lectionID);
        return new ResponseEntity<>(lectionProgress, HttpStatus.OK);
    }

    @GetMapping("/{userGUID}")
    public ResponseEntity<List<LectionProgress>> getUsersProgress(@PathVariable String userGUID){
        List<LectionProgress> lectionProgressList = this.lectionProgressService.getUserProgress(userGUID);
        return new ResponseEntity<>(lectionProgressList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LectionProgress> createLectionProgress(@RequestBody LectureProgressDTO dto) {
        LectionProgress lectionProgress = this.lectionProgressService.createLectionProgress(dto.getUserGUID(), dto.getLectionID());
        return new ResponseEntity<>(lectionProgress, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LectionProgress> uodateProgress(@RequestBody LectureProgressDTO dto){
        LectionProgress lectionProgress = this.lectionProgressService.updateProgress(dto.getUserGUID(), dto.getLectionID(), dto.getProgress());
        return new ResponseEntity<>(lectionProgress, HttpStatus.OK);
    }

    @DeleteMapping("/{userGUID}/{lectionID}")
    public ResponseEntity<Void> deleteProgress(@PathVariable String userGUID, @PathVariable Long lectionID) {
        boolean deleted = this.lectionProgressService.deleteProgress(userGUID, lectionID);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
