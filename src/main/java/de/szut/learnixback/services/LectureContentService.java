package de.szut.learnixback.services;

import de.szut.learnixback.entities.LectureContent;
import de.szut.learnixback.repositories.LectureContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureContentService {
    @Autowired
    private LectureContentRepository lectureContentRepository;

    public LectureContent createLectureContent(LectureContent lectureContent) {
        return lectureContentRepository.save(lectureContent);
    }

    public List<LectureContent> getAllLectureContents() {
        return lectureContentRepository.findAll();
    }

    public LectureContent getLectureContentById(int id) {
        return lectureContentRepository.findById(id).orElse(null);
    }

    public LectureContent updateLectureContent(int id, LectureContent updatedLectureContent) {
        LectureContent existingLectureContent = lectureContentRepository.findById(id).orElse(null);
        if (existingLectureContent != null) {
            existingLectureContent.setTitle(updatedLectureContent.getTitle());
            existingLectureContent.setContent(updatedLectureContent.getContent());
            existingLectureContent.setContentOrder(updatedLectureContent.getContentOrder());
            existingLectureContent.setType(updatedLectureContent.getType());
            existingLectureContent.setLections(updatedLectureContent.getLections()); // Falls nötig
            return lectureContentRepository.save(existingLectureContent);
        }
        return null;
    }

    public void deleteLectureContent(int id) {
        lectureContentRepository.deleteById(id);
    }
}
