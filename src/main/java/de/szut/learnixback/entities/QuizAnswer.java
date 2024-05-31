package de.szut.learnixback.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer selectedOption;
    private Boolean isCorrect;

    @Column(name = "quiz_id")
    private Long quizId;
    private String userGUID;
}