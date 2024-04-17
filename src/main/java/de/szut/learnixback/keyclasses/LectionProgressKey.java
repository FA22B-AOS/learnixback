package de.szut.learnixback.keyclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class LectionProgressKey implements Serializable {
    private String userGUID;
    private Long lectionID;


    public LectionProgressKey(String userGUID, Long lectionID) {
        this.userGUID = userGUID;
        this.lectionID = lectionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectionProgressKey that = (LectionProgressKey) o;
        return Objects.equals(userGUID, that.userGUID) && Objects.equals(lectionID, that.lectionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGUID, lectionID);
    }
}