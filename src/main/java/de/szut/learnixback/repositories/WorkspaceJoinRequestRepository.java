package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.WorkspaceJoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceJoinRequestRepository extends JpaRepository<WorkspaceJoinRequest, Long> {
}
