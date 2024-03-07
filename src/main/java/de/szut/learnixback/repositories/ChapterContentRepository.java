package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.ChapterContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterContentRepository extends JpaRepository<ChapterContent, Long> {
}
