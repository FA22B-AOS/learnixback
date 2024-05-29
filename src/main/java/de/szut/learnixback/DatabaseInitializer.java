package de.szut.learnixback;

import de.szut.learnixback.entities.Chapter;
import de.szut.learnixback.entities.ChapterContent;
import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.entities.Quiz;
import de.szut.learnixback.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {

   private final LectionRepository lectionRepository;
    private final ChapterRepository chapterRepository;
    private final ChapterContentRepository chapterContentRepository;
    private final QuizRepository quizRepository;
    private final LectionProgressRepository lectionProgressRepository;

    public DatabaseInitializer(LectionRepository lectionRepository,
                               ChapterRepository chapterRepository,
                               ChapterContentRepository chapterContentRepository,
                               LectionProgressRepository lectionProgressRepository,
                               QuizRepository quizRepository) {
        this.lectionRepository = lectionRepository;
        this.chapterRepository = chapterRepository;
        this.chapterContentRepository = chapterContentRepository;
        this.lectionProgressRepository = lectionProgressRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public void run(String... args) {
/*

        this.lectionRepository.deleteAll();
        this.chapterContentRepository.deleteAll();
        this.chapterRepository.deleteAll();
        this.lectionProgressRepository.deleteAll();
        this.quizRepository.deleteAll();;

        Long javaID = 0L;

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
                javaID = lection.getLectionId();
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

        if(javaID > 0){

        }
        Quiz q1 = new Quiz();
        q1.setQuestion("What is the size of int in Java?");
        q1.setOptions(Arrays.asList("8-bit", "16-bit", "32-bit", "64-bit"));
        q1.setCorrectAnswer(2);
        q1.setLectionId(javaID);

        Quiz q2 = new Quiz();
        q2.setQuestion("What is the default value of a boolean variable in Java?");
        q2.setOptions(Arrays.asList("true", "false", "0", "1"));
        q2.setCorrectAnswer(1);
        q2.setLectionId(javaID);

        Quiz q3 = new Quiz();
        q3.setQuestion("Which method must be implemented by all threads?");
        q3.setOptions(Arrays.asList("start()", "stop()", "run()", "main()"));
        q3.setCorrectAnswer(2);
        q3.setLectionId(javaID);

        Quiz q4 = new Quiz();
        q4.setQuestion("Which keyword is used to inherit a class in Java?");
        q4.setOptions(Arrays.asList("this", "super", "extends", "implements"));
        q4.setCorrectAnswer(2);
        q4.setLectionId(javaID);

        Quiz q5 = new Quiz();
        q5.setQuestion("Which class is the parent class of all classes in Java?");
        q5.setOptions(Arrays.asList("Object", "Class", "System", "None of the above"));
        q5.setCorrectAnswer(0);
        q5.setLectionId(javaID);

        Quiz q6 = new Quiz();
        q6.setQuestion("Which keyword is used to declare a constant in Java?");
        q6.setOptions(Arrays.asList("const", "final", "static", "volatile"));
        q6.setCorrectAnswer(1);
        q6.setLectionId(javaID);

        Quiz q7 = new Quiz();
        q7.setQuestion("Which of the following is not an OOP concept in Java?");
        q7.setOptions(Arrays.asList("Inheritance", "Encapsulation", "Polymorphism", "Compilation"));
        q7.setCorrectAnswer(3);
        q7.setLectionId(javaID);

        Quiz q8 = new Quiz();
        q8.setQuestion("Which exception is thrown when a method cannot find the referenced class?");
        q8.setOptions(Arrays.asList("ClassNotFoundException", "IOException", "NoSuchMethodException", "RemoteException"));
        q8.setCorrectAnswer(0);
        q8.setLectionId(javaID);

        Quiz q9 = new Quiz();
        q9.setQuestion("Which package contains the Random class?");
        q9.setOptions(Arrays.asList("java.util", "java.lang", "java.io", "java.net"));
        q9.setCorrectAnswer(0);
        q9.setLectionId(javaID);

        Quiz q10 = new Quiz();
        q10.setQuestion("What is the return type of the hashCode() method in the Object class?");
        q10.setOptions(Arrays.asList("Object", "int", "long", "void"));
        q10.setCorrectAnswer(1);
        q10.setLectionId(javaID);
        quizRepository.saveAll(Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10));*/
    }
}