package de.szut.learnixback.controller;

import de.szut.learnixback.entities.QuizQuestion;
import de.szut.learnixback.services.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz-questions")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    @GetMapping
    public ResponseEntity<List<QuizQuestion>> getAllQuizQuestions() {
        List<QuizQuestion> quizQuestions = quizQuestionService.getAllQuizQuestions();
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestion> getQuizQuestionById(@PathVariable Long id) {
        Optional<QuizQuestion> quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return quizQuestion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<QuizQuestion> createQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        QuizQuestion createdQuizQuestion = quizQuestionService.createQuizQuestion(quizQuestion);
        return new ResponseEntity<>(createdQuizQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizQuestion> updateQuizQuestion(@PathVariable Long id, @RequestBody QuizQuestion updatedQuizQuestion) {
        QuizQuestion quizQuestion = quizQuestionService.updateQuizQuestion(id, updatedQuizQuestion);
        if (quizQuestion != null) {
            return new ResponseEntity<>(quizQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizQuestion(@PathVariable Long id) {
        boolean deleted = quizQuestionService.deleteQuizQuestion(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
