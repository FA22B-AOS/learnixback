package de.szut.learnixback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectionRequestId;

    @Column(columnDefinition="TEXT", length = 256)
    private String requestDescription;

    private String applicantGUID;
}
