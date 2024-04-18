package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChapterContent {

    public ChapterContent(String title, String content, int contentOrder, int contentType, Long chapterId){
        this.title = title;
        this.content = content;
        this.contentOrder = contentOrder;
        this.contentType = contentType;
        this.chapterId = chapterId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterContentId;

    private String title;
    private String content;
    private int contentOrder;
    private int contentType;

    @Column(name = "chapter_Id")
    private Long chapterId;
}
