package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Lection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectionId;
    private String title;
    private String description;
    private String creatorGuid;}
