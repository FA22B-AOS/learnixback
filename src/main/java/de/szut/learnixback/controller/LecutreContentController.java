package de.szut.learnixback.controller;

import de.szut.learnixback.entities.LectureContent;
import de.szut.learnixback.services.LectureContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LecutreContentController {

    @Autowired
    private LectureContentService lectureContentService;

    @PostMapping("/lectureContents")
    public ResponseEntity<LectureContent> createLectureContent(@RequestBody LectureContent lectureContent) {
        LectureContent createdLectureContent = lectureContentService.createLectureContent(lectureContent);
        return new ResponseEntity<>(createdLectureContent, HttpStatus.CREATED);
    }

    @GetMapping("/lectureContents")
    public ResponseEntity<List<LectureContent>> getAllLectureContents() {
        List<LectureContent> lectureContents = lectureContentService.getAllLectureContents();
        return new ResponseEntity<>(lectureContents, HttpStatus.OK);
    }

    @GetMapping("/lectureContents/{id}")
    public ResponseEntity<LectureContent> getLectureContentById(@PathVariable int id) {
        LectureContent lectureContent = lectureContentService.getLectureContentById(id);
        return new ResponseEntity<>(lectureContent, lectureContent != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/lectureContents/{id}")
    public ResponseEntity<LectureContent> updateLectureContent(@PathVariable int id, @RequestBody LectureContent updatedLectureContent) {
        LectureContent lectureContent = lectureContentService.updateLectureContent(id, updatedLectureContent);
        return new ResponseEntity<>(lectureContent, lectureContent != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/lectureContents/{id}")
    public ResponseEntity<Void> deleteLectureContent(@PathVariable int id) {
        lectureContentService.deleteLectureContent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
