package ru.clevertec.ecl.clevertecfinaltask.unit.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotCreateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.mapper.CommentMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.CommentRepository;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;
import ru.clevertec.ecl.clevertecfinaltask.service.impl.CommentServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private Cache<String, Object> commentCache;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreate_NonExistingNews_ThrowsCannotCreateCommentError() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUsername("John");
        commentDTO.setTime(LocalDateTime.now());
        commentDTO.setText("Test comment");
        commentDTO.setNewsId(1L);
        when(newsRepository.findById(commentDTO.getNewsId())).thenReturn(Optional.empty());
        assertThrows(CannotCreateCommentError.class, () -> commentService.create(commentDTO));
    }
    @Test
    void testUpdate_NonExistingComment_ThrowsCannotUpdateCommentError() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setUsername("John");
        commentDTO.setTime(LocalDateTime.now());
        commentDTO.setText("Updated comment");
        commentDTO.setNewsId(1L);
        when(commentRepository.findById(commentDTO.getId())).thenReturn(Optional.empty());
        assertThrows(CannotUpdateCommentError.class, () -> commentService.update(commentDTO));
    }

    @Test
    void testUpdate_NonExistingNews_ThrowsCannotUpdateCommentError() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setUsername("John");
        commentDTO.setTime(LocalDateTime.now());
        commentDTO.setText("Updated comment");
        commentDTO.setNewsId(1L);
        Comment existingComment = new Comment();
        existingComment.setId(1L);
        when(commentRepository.findById(commentDTO.getId())).thenReturn(Optional.of(existingComment));
        when(newsRepository.findById(commentDTO.getNewsId())).thenReturn(Optional.empty());
        assertThrows(CannotUpdateCommentError.class, () -> commentService.update(commentDTO));
    }

    @Test
    void testDelete_ExistingComment_DeletesComment() {
        Long commentId = 1L;
        Comment existingComment = new Comment();
        existingComment.setId(commentId);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        commentService.delete(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
        verify(commentCache, times(1)).remove("comment_" + commentId);
    }

    @Test
    void testDelete_NonExistingComment_ThrowsCannotDeleteCommentError() {
        Long commentId = 1L;
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        assertThrows(CannotDeleteCommentError.class, () -> commentService.delete(commentId));
    }
}

