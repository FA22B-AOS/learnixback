package de.szut.learnixback.controller;

import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.services.LectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lections")
public class LectionController {

    @Autowired
    private LectionService lectionService;

    @GetMapping
    public ResponseEntity<List<Lection>> getAllLections() {
        List<Lection> lections = lectionService.getAllLections();
        return new ResponseEntity<>(lections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lection> getLectionById(@PathVariable Long id) {
        Optional<Lection> lection = lectionService.getLectionById(id);
        return lection.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Lection> createLection(@RequestBody Lection lection) {
        Lection createdLection = lectionService.createLection(lection);
        return new ResponseEntity<>(createdLection, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lection> updateLection(@PathVariable Long id, @RequestBody Lection updatedLection) {
        Lection lection = lectionService.updateLection(id, updatedLection);
        if (lection != null) {
            return new ResponseEntity<>(lection, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLection(@PathVariable Long id) {
        boolean deleted = lectionService.deleteLection(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
