package de.szut.learnixback.controller;

import de.szut.learnixback.entities.LectionRequest;
import de.szut.learnixback.services.LectionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class LectionRequestController {

    @Autowired
    private LectionRequestService lectionRequestService;

    @PostMapping
    public ResponseEntity<LectionRequest> createUserProfile(@RequestBody LectionRequest lectionRequest){
        try {
            LectionRequest createdLectionRequest = lectionRequestService.createLectionRequest(lectionRequest);
            return new ResponseEntity<>(createdLectionRequest, HttpStatus.CREATED);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<LectionRequest>> getAllLectionRequests(){
        return new ResponseEntity<>(lectionRequestService.getAllLectionRequests(), HttpStatus.OK);
    }

    @GetMapping("/byUser/{userGUID}")
    public ResponseEntity<List<LectionRequest>> getAllLectionRequestsByUser(@PathVariable String userGUID){
        return new ResponseEntity<>(lectionRequestService.getAllLectionRequestByUser(userGUID), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLectionRequest(@PathVariable Long id){
        boolean deleted = lectionRequestService.deleteLectionRequest(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
