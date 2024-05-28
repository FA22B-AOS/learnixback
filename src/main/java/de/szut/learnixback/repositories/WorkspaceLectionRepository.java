package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.WorkspaceLection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceLectionRepository extends JpaRepository<WorkspaceLection, Long> {
}
