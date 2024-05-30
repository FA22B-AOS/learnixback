package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class QuizAnswerDTO {
    private Long id;
    private Boolean isCorrect;
    private String userGUID;
    private Long quizId;
    private Integer selectedOption;
}
