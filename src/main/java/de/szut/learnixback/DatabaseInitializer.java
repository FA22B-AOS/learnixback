package de.szut.learnixback;

import de.szut.learnixback.entities.Chapter;
import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final LectionRepository lectionRepository;
    private final ChapterRepository chapterRepository;

    public DatabaseInitializer(LectionRepository lectionRepository,
                               ChapterRepository chapterRepository) {
        this.lectionRepository = lectionRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public void run(String... args) {

        this.lectionRepository.deleteAll();
        this.chapterRepository.deleteAll();

        List<Lection> lections = new ArrayList<>();
        List<Chapter> chapters = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            UUID randomGuid = UUID.randomUUID();
            Lection lection = new Lection();
            lection.setTitle("Lecture " + i);
            lection.setCreatorGuid(randomGuid.toString());
            lections.add(lectionRepository.save(lection));
            for (int j = 1; j <= 10; j++){
                Chapter chapter = new Chapter();
                chapter.setLection(lection.getLectionId());
                chapter.setChapterName("Chapter"+j);
                chapters.add(chapterRepository.save(chapter));
            }
        }
    }
}
