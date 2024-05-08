package de.szut.learnixback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LectureProgressDTO {
    private String userGUID;
    private Long lectionID;
    private Integer progress;
}
