package de.szut.learnixback.keyclasses;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LectionProgressKey implements Serializable {
    private final UUID foreignKey1;
    private final Long foreignKey2;


    public LectionProgressKey(UUID foreignKey1, Long foreignKey2) {
        this.foreignKey1 = foreignKey1;
        this.foreignKey2 = foreignKey2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectionProgressKey that = (LectionProgressKey) o;
        return Objects.equals(foreignKey1, that.foreignKey1) && Objects.equals(foreignKey2, that.foreignKey2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foreignKey1, foreignKey2);
    }
}