package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.Quiz;
import de.szut.learnixback.entities.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
}
