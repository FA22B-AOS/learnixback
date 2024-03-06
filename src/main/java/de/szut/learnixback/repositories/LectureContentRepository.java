package de.szut.learnixback.repositories;

import de.szut.learnixback.entities.LectureContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureContentRepository extends JpaRepository<LectureContent, Integer> {
}
