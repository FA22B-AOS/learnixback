package de.szut.learnixback.services;

import de.szut.learnixback.entities.ChapterContent;
import de.szut.learnixback.repositories.ChapterContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterContentService {

    @Autowired
    private ChapterContentRepository chapterContentRepository;

    public List<ChapterContent> getAllChapterContents() {
        return chapterContentRepository.findAll();
    }

    public Optional<ChapterContent> getChapterContentById(Long id) {
        return chapterContentRepository.findById(id);
    }

    public ChapterContent createChapterContent(ChapterContent chapterContent) {
        return chapterContentRepository.save(chapterContent);
    }

    public ChapterContent updateChapterContent(Long id, ChapterContent updatedChapterContent) {
        if (chapterContentRepository.existsById(id)) {
            updatedChapterContent.setChapterContentId(id);
            return chapterContentRepository.save(updatedChapterContent);
        } else {
            return null;
        }
    }

    public boolean deleteChapterContent(Long id) {
        if (chapterContentRepository.existsById(id)) {
            chapterContentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
