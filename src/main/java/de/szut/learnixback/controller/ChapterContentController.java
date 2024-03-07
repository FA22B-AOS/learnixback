package de.szut.learnixback.controller;

import de.szut.learnixback.entities.ChapterContent;
import de.szut.learnixback.services.ChapterContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chapter-contents")
public class ChapterContentController {

    @Autowired
    private ChapterContentService chapterContentService;

    @GetMapping
    public ResponseEntity<List<ChapterContent>> getAllChapterContents() {
        List<ChapterContent> chapterContents = chapterContentService.getAllChapterContents();
        return new ResponseEntity<>(chapterContents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterContent> getChapterContentById(@PathVariable Long id) {
        Optional<ChapterContent> chapterContent = chapterContentService.getChapterContentById(id);
        return chapterContent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChapterContent> createChapterContent(@RequestBody ChapterContent chapterContent) {
        ChapterContent createdChapterContent = chapterContentService.createChapterContent(chapterContent);
        return new ResponseEntity<>(createdChapterContent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterContent> updateChapterContent(@PathVariable Long id, @RequestBody ChapterContent updatedChapterContent) {
        ChapterContent chapterContent = chapterContentService.updateChapterContent(id, updatedChapterContent);
        if (chapterContent != null) {
            return new ResponseEntity<>(chapterContent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapterContent(@PathVariable Long id) {
        boolean deleted = chapterContentService.deleteChapterContent(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
