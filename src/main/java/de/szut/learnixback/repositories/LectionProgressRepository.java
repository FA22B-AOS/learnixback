package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.LectionProgress;
import de.szut.learnixback.keyclasses.LectionProgressKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectionProgressRepository extends JpaRepository<LectionProgress, LectionProgressKey> {
}
