package de.szut.learnixback.services;

import de.szut.learnixback.entities.QuizQuestion;
import de.szut.learnixback.repositories.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public QuizQuestion createQuizQuestion(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    public QuizQuestion getQuizQuestionById(int id) {
        return quizQuestionRepository.findById(id).orElse(null);
    }

    public QuizQuestion updateQuizQuestion(int id, QuizQuestion updatedQuizQuestion) {
        QuizQuestion existingQuizQuestion = quizQuestionRepository.findById(id).orElse(null);
        if (existingQuizQuestion != null) {
            existingQuizQuestion.setQuiz(updatedQuizQuestion.getQuiz());
            // Weitere Aktualisierungen hier, falls erforderlich
            return quizQuestionRepository.save(existingQuizQuestion);
        }
        return null;
    }

    public void deleteQuizQuestion(int id) {
        quizQuestionRepository.deleteById(id);
    }
}
