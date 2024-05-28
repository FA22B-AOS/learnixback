package de.szut.learnixback.controller;

import de.szut.learnixback.entities.QuizAnswer;
import de.szut.learnixback.services.QuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/answers")
public class QuizAnswerController {
    @Autowired
    private QuizAnswerService quizAnswerService;

    @PostMapping
    public ResponseEntity<QuizAnswer> submitAnswer(@RequestParam Long quizId, @RequestParam int selectedOption, @RequestParam String userId) {
        QuizAnswer savedAnswer = quizAnswerService.saveAnswer(quizId, selectedOption, userId);
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @GetMapping("/score")
    public int getTotalScore(@RequestParam String userId) {
        return quizAnswerService.getTotalScore(userId,0L);
    }
}

