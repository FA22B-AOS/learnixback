package de.szut.learnixback.services;

import de.szut.learnixback.entities.Chapter;
import de.szut.learnixback.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Optional<Chapter> getChapterById(Long id) {
        return chapterRepository.findById(id);
    }

    public Chapter createChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(Long id, Chapter updatedChapter) {
        if (chapterRepository.existsById(id)) {
            updatedChapter.setChapterId(id);
            return chapterRepository.save(updatedChapter);
        } else {
            return null;
        }
    }

    public boolean deleteChapter(Long id) {
        if (chapterRepository.existsById(id)) {
            chapterRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
