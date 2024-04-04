package de.szut.learnixback.services;

import de.szut.learnixback.entities.QuizQuestion;
import de.szut.learnixback.repositories.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    public Optional<QuizQuestion> getQuizQuestionById(Long id) {
        return quizQuestionRepository.findById(id);
    }

    public QuizQuestion createQuizQuestion(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    public QuizQuestion updateQuizQuestion(Long id, QuizQuestion updatedQuizQuestion) {
        if (quizQuestionRepository.existsById(id)) {
            updatedQuizQuestion.setQuizQuestionId(id);
            return quizQuestionRepository.save(updatedQuizQuestion);
        } else {
            return null;
        }
    }

    public boolean deleteQuizQuestion(Long id) {
        if (quizQuestionRepository.existsById(id)) {
            quizQuestionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
