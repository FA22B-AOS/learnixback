package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectionProgress {

    public LectionProgress(UUID userGUID, Long lectionId){
        this.userGUID = userGUID;
        this.lectionId = lectionId;
        this.progress = 0f;
    }

    @Id
    private UUID userGUID;
    private Float progress;

    @Id
    @Column(name = "lection_Id")
    private Long lectionId;
}
