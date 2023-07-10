package ru.clevertec.ecl.clevertecfinaltask.unit.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.mapper.CommentMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentMapperTest {

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Test
    void testToDto() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setTime(LocalDateTime.now());
        comment.setText("This is a comment");
        comment.setUsername("JohnDoe");
        News news = new News();
        news.setId(10L);
        comment.setNews(news);
        CommentDTO commentDTO = commentMapper.toDto(comment);
        assertEquals(comment.getId(), commentDTO.getId());
        assertEquals(comment.getTime(), commentDTO.getTime());
        assertEquals(comment.getText(), commentDTO.getText());
        assertEquals(comment.getUsername(), commentDTO.getUsername());
        assertEquals(comment.getNews().getId(), commentDTO.getNewsId());
    }

    @Test
    void testToEntity() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setTime(LocalDateTime.now());
        commentDTO.setText("This is a comment");
        commentDTO.setUsername("JohnDoe");
        commentDTO.setNewsId(10L);
        Comment comment = commentMapper.toEntity(commentDTO);
        assertEquals(commentDTO.getId(), comment.getId());
        assertEquals(commentDTO.getTime(), comment.getTime());
        assertEquals(commentDTO.getText(), comment.getText());
        assertEquals(commentDTO.getUsername(), comment.getUsername());
    }
}

