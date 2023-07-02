package ru.clevertec.ecl.clevertecfinaltask.unit.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.mapper.NewsMapper;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class NewsMapperTest {
    private NewsMapper newsMapper = NewsMapper.INSTANCE;
    @Test
    void testToDto() {
        News news = new News();
        news.setId(1L);
        news.setTitle("Test News");
        NewsDTO newsDTO = newsMapper.toDto(news);
        assertNotNull(newsDTO);
        assertEquals(news.getId(), newsDTO.getId());
        assertEquals(news.getTitle(), newsDTO.getTitle());
    }

    @Test
     void testToEntity() {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(1L);
        newsDTO.setTitle("Test News");
        News news = newsMapper.toEntity(newsDTO);
        assertNotNull(news);
        assertEquals(newsDTO.getId(), news.getId());
        assertEquals(newsDTO.getTitle(), news.getTitle());
    }

    @Test
    void testToDtoWithoutCommentsAndNull() {
        News news = new News();
        news.setId(1L);
        news.setTitle("Test News");
        NewsDTO newsDTO = newsMapper.toDtoWithoutCommentsAndNull(news);
        assertNotNull(newsDTO);
        assertEquals(news.getId(), newsDTO.getId());
        assertEquals(news.getTitle(), newsDTO.getTitle());
        assertNotNull(newsDTO.getComments());
        assertEquals(Collections.emptyList(), newsDTO.getComments());
    }
}
