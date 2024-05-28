package de.szut.learnixback.services;

import de.szut.learnixback.entities.Quiz;
import de.szut.learnixback.entities.QuizAnswer;
import de.szut.learnixback.repositories.QuizAnswerRepository;
import de.szut.learnixback.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizAnswerService {
    @Autowired
    private QuizAnswerRepository quizAnswerRepository;
    @Autowired
    private QuizRepository quizRepository;

    public QuizAnswer saveAnswer(Long quizId, int selectedOption, String userId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) {
            return null;
        }
        QuizAnswer answer = new QuizAnswer();
        answer.setQuizId(quizId);
        answer.setSelectedOption(selectedOption);
        answer.setCorrect(selectedOption == quiz.getCorrectAnswer());
        answer.setUserGUID(userId);
        return quizAnswerRepository.save(answer);
    }

    public int getTotalScore(String userId, Long lectionId) {
        //List<QuizAnswer> answers = quizAnswerRepository.


        return  0;
    }
}

