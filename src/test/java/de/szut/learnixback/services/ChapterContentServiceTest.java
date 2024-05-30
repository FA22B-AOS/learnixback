package de.szut.learnixback.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.szut.learnixback.entities.ChapterContent;
import de.szut.learnixback.repositories.ChapterContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ChapterContentServiceTest {

    @InjectMocks
    private ChapterContentService chapterContentService;

    @Mock
    private ChapterContentRepository chapterContentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetChapterContentById() {
        ChapterContent mockContent = new ChapterContent();
        mockContent.setChapterContentId(1L);
        when(chapterContentRepository.findById(1L)).thenReturn(Optional.of(mockContent));

        ChapterContent content = chapterContentService.getChapterContentById(1L).get();

        assertNotNull(content);
        assertEquals(1L, content.getChapterContentId());
    }

    @Test
    public void testGetChapterContentById_NotFound() {
        when(chapterContentRepository.findById(1L)).thenReturn(Optional.empty());
        assertFalse(chapterContentService.getChapterContentById(1L).isPresent());
    }
}
