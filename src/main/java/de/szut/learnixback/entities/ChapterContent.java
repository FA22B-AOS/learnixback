package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ChapterContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterContentId;

    private String title;
    private String contnent;
    private int contentOrder;
    private int contentType;

    @Column(name = "chapter_Id")
    private Long chapterId;
}
