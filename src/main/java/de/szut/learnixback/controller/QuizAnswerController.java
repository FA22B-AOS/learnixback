package de.szut.learnixback.controller;

import de.szut.learnixback.dto.QuizAnswerDTO;
import de.szut.learnixback.entities.QuizAnswer;
import de.szut.learnixback.services.QuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/answers")
public class QuizAnswerController {
    @Autowired
    private QuizAnswerService quizAnswerService;

    @PostMapping
    public ResponseEntity<QuizAnswer> submitAnswer(@RequestBody QuizAnswerDTO dto) {
        QuizAnswer savedAnswer = quizAnswerService.saveAnswer(dto.getQuizId(), dto.getSelectedOption(), dto.getUserGUID());
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @GetMapping("/score")
    public int getTotalScore(@RequestParam String userId) {
        return quizAnswerService.getTotalScore(userId,0L);
    }
}

