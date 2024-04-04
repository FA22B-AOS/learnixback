package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Chapter {

    public Chapter(String chapterName, Long lectionId){
        this.chapterName = chapterName;
        this.lectionId = lectionId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;
    private String chapterName;

    @Column(name = "lection_Id")
    private Long lectionId;
}
