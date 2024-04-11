package de.szut.learnixback.controller;

import de.szut.learnixback.entities.LectionProgress;
import de.szut.learnixback.services.LectionProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/progress")
public class LectionProgressController {

    @Autowired
    private LectionProgressService lectionProgressService;

    @GetMapping("/{userGUID}/{lectionID}")
    public ResponseEntity<LectionProgress> getLectionProgress(@PathVariable UUID userGUID, @PathVariable Long lectionID){
        LectionProgress lectionProgress = this.lectionProgressService.getLectionProgress(userGUID, lectionID);
        return new ResponseEntity<>(lectionProgress, HttpStatus.OK);
    }

    @GetMapping("/{userGUID}")
    public ResponseEntity<List<LectionProgress>> getUsersProgress(@PathVariable UUID userGUID){
        List<LectionProgress> lectionProgressList = this.lectionProgressService.getUserProgress(userGUID);
        return new ResponseEntity<>(lectionProgressList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LectionProgress> createLectionProgress(@RequestBody UUID userGUID, @RequestBody Long lectionID) {
        LectionProgress lectionProgress = this.lectionProgressService.createLectionProgress(userGUID, lectionID);
        return new ResponseEntity<>(lectionProgress, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LectionProgress> uodateProgress(@RequestBody UUID userGUID, @RequestBody Long lectionID, @RequestBody Float newProgress){
        LectionProgress lectionProgress = this.lectionProgressService.updateProgress(userGUID, lectionID, newProgress);
        return new ResponseEntity<>(lectionProgress, HttpStatus.OK);
    }

    @DeleteMapping("/{userGUID}/{lectionID}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID userGUID, @PathVariable Long lectionID) {
        boolean deleted = this.lectionProgressService.deleteProgress(userGUID, lectionID);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
