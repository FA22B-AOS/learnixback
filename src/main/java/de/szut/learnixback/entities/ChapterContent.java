package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChapterContent {

    public ChapterContent(String content, int contentOrder, byte contentType, Long chapterId){
        this.content = content;
        this.contentOrder = contentOrder;
        this.contentType = contentType;
        this.chapterId = chapterId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterContentId;

    @Column(columnDefinition="TEXT", length = 2048)
    private String content;

    private int contentOrder;
    /**
     * 0: Header
     * 1: Text
     * 2: List
     * 3: Table
      */
    private byte contentType;

    @Column(name = "chapter_Id")
    private Long chapterId;
}
