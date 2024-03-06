package de.szut.learnixback.controller;

import de.szut.learnixback.entities.QuizQuestion;
import de.szut.learnixback.services.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizQuestions")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    @PostMapping
    public ResponseEntity<QuizQuestion> createQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        QuizQuestion createdQuizQuestion = quizQuestionService.createQuizQuestion(quizQuestion);
        return new ResponseEntity<>(createdQuizQuestion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuizQuestion>> getAllQuizQuestions() {
        List<QuizQuestion> quizQuestions = quizQuestionService.getAllQuizQuestions();
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestion> getQuizQuestionById(@PathVariable int id) {
        QuizQuestion quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return new ResponseEntity<>(quizQuestion, quizQuestion != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizQuestion> updateQuizQuestion(@PathVariable int id, @RequestBody QuizQuestion updatedQuizQuestion) {
        QuizQuestion quizQuestion = quizQuestionService.updateQuizQuestion(id, updatedQuizQuestion);
        return new ResponseEntity<>(quizQuestion, quizQuestion != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizQuestion(@PathVariable int id) {
        quizQuestionService.deleteQuizQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
