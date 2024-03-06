package de.szut.learnixback.services;

import de.szut.learnixback.entities.Quiz;
import de.szut.learnixback.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz updateQuiz(int id, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(id).orElse(null);
        if (existingQuiz != null) {
            existingQuiz.setLections(updatedQuiz.getLections()); // Falls nötig, um andere Eigenschaften zu aktualisieren, füge weitere Aktualisierungen hinzu
            return quizRepository.save(existingQuiz);
        }
        return null;
    }

    public void deleteQuiz(int id) {
        quizRepository.deleteById(id);
    }
}
