package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectionProgress {

    @Id
    private Long userGUID;
    private Float progress;

    @Id
    @Column(name = "lection_Id")
    private Long lection;
}
