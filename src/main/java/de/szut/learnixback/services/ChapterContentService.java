package de.szut.learnixback.services;

import de.szut.learnixback.entities.ChapterContent;
import de.szut.learnixback.repositories.ChapterContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChapterContentService {

    @Autowired
    private ChapterContentRepository chapterContentRepository;

    public List<ChapterContent> getAllChapterContents() {
        return chapterContentRepository.findAll();
    }

    public List<ChapterContent> getAllChapterContentsFromChapter(Long id){
        List<ChapterContent> contents = chapterContentRepository.findAll();
        contents.removeIf(content -> !Objects.equals(content.getChapterId(), id));
        contents.sort(Comparator.comparingLong(ChapterContent::getContentOrder));
        return contents;
    }

    public boolean moveChapterContent(Long id, Boolean moveUp){
        ChapterContent chapterContentToMove = null;
        ChapterContent chapterContentToSwitch = null;

        try{
            chapterContentToMove = getChapterContentById(id).get();
            List<ChapterContent> contents = getAllChapterContentsFromChapter(chapterContentToMove.getChapterId());
            if(moveUp){
                for(int i = 0; i < contents.size(); i++){
                    if(contents.get(i).equals(chapterContentToMove)) {
                        chapterContentToSwitch = contents.get(i - 1);
                        break;
                    }
                }
            } else {
                for(int i = 0; i < contents.size(); i++){
                    if(contents.get(i).equals(chapterContentToMove)) {
                        chapterContentToSwitch = contents.get(i + 1);
                        break;
                    }
                }
            }
        }catch (Exception e){
            return false;
        }

        if(chapterContentToSwitch == null)
            return false;

        int tmpPos = chapterContentToSwitch.getContentOrder();
        chapterContentToSwitch.setContentOrder(chapterContentToMove.getContentOrder());
        chapterContentToMove.setContentOrder(tmpPos);

        chapterContentRepository.save(chapterContentToMove);
        chapterContentRepository.save(chapterContentToSwitch);

        return true;
    }

    public Optional<ChapterContent> getChapterContentById(Long id) {
        return chapterContentRepository.findById(id);
    }

    public ChapterContent createChapterContent(ChapterContent chapterContent) {
        List<ChapterContent> contents = getAllChapterContentsFromChapter(chapterContent.getChapterId());
        if (!contents.isEmpty()) {
            chapterContent.setContentOrder(contents.get(contents.size() - 1).getContentOrder() + 1);
        }
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
