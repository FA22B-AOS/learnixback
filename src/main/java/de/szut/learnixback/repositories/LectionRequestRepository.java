package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.LectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectionRequestRepository extends JpaRepository<LectionRequest, Long> {
}
