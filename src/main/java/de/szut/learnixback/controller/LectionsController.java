package de.szut.learnixback.controller;

import de.szut.learnixback.entities.Lections;
import de.szut.learnixback.services.LectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lections")
public class LectionsController {

    @Autowired
    private LectionsService lectionsService;

    @PostMapping
    public ResponseEntity<Lections> createLections(@RequestBody Lections lections) {
        Lections createdLections = lectionsService.createLections(lections);
        return new ResponseEntity<>(createdLections, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Lections>> getAllLections() {
        List<Lections> lections = lectionsService.getAllLections();
        return new ResponseEntity<>(lections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lections> getLectionsById(@PathVariable int id) {
        Lections lections = lectionsService.getLectionsById(id);
        return new ResponseEntity<>(lections, lections != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lections> updateLections(@PathVariable int id, @RequestBody Lections updatedLections) {
        Lections lections = lectionsService.updateLections(id, updatedLections);
        return new ResponseEntity<>(lections, lections != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLections(@PathVariable int id) {
        lectionsService.deleteLections(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}