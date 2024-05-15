package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

}
