package de.szut.learnixback.services;

import de.szut.learnixback.entities.Quiz;
import de.szut.learnixback.repositories.LectionRepository;
import de.szut.learnixback.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public List<Quiz> getQuizzesByLectionId(Long lectionId) {
        List<Quiz> quizzes = quizRepository.findAll();
        quizzes.removeIf(quiz -> !Objects.equals(quiz.getLectionId(), lectionId));
        Collections.shuffle(quizzes);
        return  quizzes;
    }
}
