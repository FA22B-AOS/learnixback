package de.szut.learnixback.services;

import de.szut.learnixback.entities.Chapter;
import de.szut.learnixback.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
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

    public List<Chapter> getAllChaptersByLectionId(Long id){
        List<Chapter> chatpers = chapterRepository.findAll();
        chatpers.removeIf(chapter -> !Objects.equals(chapter.getLectionId(), id));
        return chatpers;
    }

    public int getChapterCountByLectionId(Long id){
        List<Chapter> chatpers = chapterRepository.findAll();
        chatpers.removeIf(chapter -> chapter.getLectionId().longValue() != id.longValue());
        return chatpers.size();
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
