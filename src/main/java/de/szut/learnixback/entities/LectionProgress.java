package de.szut.learnixback.entities;

import de.szut.learnixback.keyclasses.LectionProgressKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
@IdClass(LectionProgressKey.class)
public class LectionProgress {

    public LectionProgress(String userGUID, Long lectionID){
        this.userGUID = userGUID;
        this.lectionID = lectionID;
        this.progress = 0f;
    }

    @Id
    private String userGUID;
    private Float progress;

    @Id
    @Column(name = "lection_Id")
    private Long lectionID;
}
