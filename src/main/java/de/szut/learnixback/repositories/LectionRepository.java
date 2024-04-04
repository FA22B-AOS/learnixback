package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.Lection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectionRepository extends JpaRepository<Lection, Long> {
}
