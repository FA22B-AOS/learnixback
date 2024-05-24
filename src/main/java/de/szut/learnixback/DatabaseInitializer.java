package de.szut.learnixback;

import de.szut.learnixback.entities.Chapter;
import de.szut.learnixback.entities.ChapterContent;
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
    private final ChapterContentRepository chapterContentRepository;

    private final LectionProgressRepository lectionProgressRepository;

    public DatabaseInitializer(LectionRepository lectionRepository,
                               ChapterRepository chapterRepository,
                               ChapterContentRepository chapterContentRepository,
                               LectionProgressRepository lectionProgressRepository) {
        this.lectionRepository = lectionRepository;
        this.chapterRepository = chapterRepository;
        this.chapterContentRepository = chapterContentRepository;
        this.lectionProgressRepository = lectionProgressRepository;
    }

    @Override
    public void run(String... args) {

        this.lectionRepository.deleteAll();
        this.chapterContentRepository.deleteAll();
        this.chapterRepository.deleteAll();
        this.lectionProgressRepository.deleteAll();


        List<Lection> lections = new ArrayList<>();
        List<Chapter> chapters = new ArrayList<>();
        List<ChapterContent> chapterContents = new ArrayList<>();

        Lection javaLection = new Lection();
        javaLection.setTitle("Java for Beginners");
        javaLection.setCreatorGuid( UUID.randomUUID().toString());
        javaLection.setTopicType("CS");
        javaLection.setDescription("In diesem Kurs werden dir die grundlegenden Kenntnisse der Java Programmiersprache beigebracht.");
        lections.add(lectionRepository.save(javaLection));

        Lection pythonLection = new Lection();
        pythonLection.setTitle("Python for Beginners");
        pythonLection.setCreatorGuid( UUID.randomUUID().toString());
        pythonLection.setTopicType("CS");
        pythonLection.setDescription("In diesem Kurs werden dir die grundlegenden Kenntnisse der Python Programmiersprache beigebracht.");
        lections.add(lectionRepository.save(pythonLection));

        Lection englishLection = new Lection();
        englishLection.setTitle("English 101");
        englishLection.setCreatorGuid( UUID.randomUUID().toString());
        englishLection.setTopicType("Lang");
        englishLection.setDescription("Starte mit dem Kurs deinen Einstieg in die Englische Sprache.");
        lections.add(lectionRepository.save(englishLection));

        Lection analysisLection = new Lection();
        analysisLection.setTitle("Simple Analysis");
        analysisLection.setCreatorGuid( UUID.randomUUID().toString());
        analysisLection.setTopicType("Math");
        analysisLection.setDescription("Bereite dich auf deine Mathe Abiturprüfung mit dem Analysis Mathekurs vor.");
        lections.add(lectionRepository.save(analysisLection));

        Lection gimpLection = new Lection();
        gimpLection.setTitle("How to GIMP");
        gimpLection.setCreatorGuid( UUID.randomUUID().toString());
        gimpLection.setTopicType("Art");
        gimpLection.setDescription("Lerne das Grafiktool GIMP kennen um deine Kunstprojekte umzusetzen.");
        lections.add(lectionRepository.save(gimpLection));

        for(Lection lection: lections) {
            if (lection.getTitle().equals("Java for Beginners")){
                Chapter dataTypes = new Chapter("Datentypen",lection.getLectionId());
                Chapter kontrollstrukturen = new Chapter("Kontrollstrukturen", lection.getLectionId());
                Chapter schleifen = new Chapter("Schleifen", lection.getLectionId());
                Chapter keywords = new Chapter("Keywords", lection.getLectionId());
                chapterRepository.save(dataTypes);
                chapterRepository.save(kontrollstrukturen);
                chapterRepository.save(schleifen);
                chapterRepository.save(keywords);
                chapterContentRepository.save(new ChapterContent("Primitive DatenTypen|h3", 0, (byte) 0, dataTypes.getChapterId()));
                chapterContentRepository.save(new ChapterContent("char,byte,short,int,long,float,double,boolean", 1, (byte) 2, dataTypes.getChapterId()));
                continue;
            }
            for (int j = 1; j <= 10; j++) {
                chapters.add(chapterRepository.save(new Chapter("Chapter " + j, lection.getLectionId())));
            }
        }
    }
}