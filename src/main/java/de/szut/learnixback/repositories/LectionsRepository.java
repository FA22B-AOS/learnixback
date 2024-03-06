package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.Lections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectionsRepository extends JpaRepository<Lections, Integer> {
}
