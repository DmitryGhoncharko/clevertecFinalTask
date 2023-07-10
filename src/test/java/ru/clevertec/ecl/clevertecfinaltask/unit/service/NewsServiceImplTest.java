package ru.clevertec.ecl.clevertecfinaltask.unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteNewsError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateNewsError;
import ru.clevertec.ecl.clevertecfinaltask.mapper.NewsMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;
import ru.clevertec.ecl.clevertecfinaltask.service.impl.NewsProcessor;
import ru.clevertec.ecl.clevertecfinaltask.service.impl.NewsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NewsServiceImplTest {

    private NewsServiceImpl newsService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsProcessor newsProcessor;

    @Mock
    private Cache<String, Object> newsCache;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        newsService = new NewsServiceImpl(newsRepository, newsProcessor, newsCache);
    }

    @Test
    void testCreate() {
        NewsDTO newsDTO = new NewsDTO();
        NewsDTO expectedResult = new NewsDTO();
        when(newsProcessor.proceedCreateOrUpdateNews(newsDTO)).thenReturn(expectedResult);

        NewsDTO result = newsService.create(newsDTO);

        assertEquals(expectedResult, result);
        verify(newsProcessor, times(1)).proceedCreateOrUpdateNews(newsDTO);
    }

    @Test
    void testUpdateExistingNews() {
        Long id = 1L;
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(id);
        News existingNews = new News();
        when(newsRepository.findById(id)).thenReturn(Optional.of(existingNews));
        NewsDTO expectedResult = new NewsDTO();
        when(newsProcessor.proceedCreateOrUpdateNews(newsDTO)).thenReturn(expectedResult);

        NewsDTO result = newsService.update(newsDTO);

        assertEquals(expectedResult, result);
        verify(newsRepository, times(1)).findById(id);
        verify(newsProcessor, times(1)).proceedCreateOrUpdateNews(newsDTO);
    }

    @Test
    void testUpdateNonExistingNews() {
        Long id = 1L;
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(id);
        when(newsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CannotUpdateNewsError.class, () -> newsService.update(newsDTO));

        verify(newsRepository, times(1)).findById(id);
        verify(newsProcessor, never()).proceedCreateOrUpdateNews(newsDTO);
    }

    @Test
    void testDeleteExistingNews() {
        Long id = 1L;
        News existingNews = new News();
        when(newsRepository.findById(id)).thenReturn(Optional.of(existingNews));

        newsService.delete(id);

        verify(newsRepository, times(1)).findById(id);
        verify(newsRepository, times(1)).deleteById(id);
        verify(newsCache, times(1)).remove("news_" + id);
    }

    @Test
    void testDeleteNonExistingNews() {
        Long id = 1L;
        when(newsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CannotDeleteNewsError.class, () -> newsService.delete(id));

        verify(newsRepository, times(1)).findById(id);
        verify(newsRepository, never()).deleteById(id);
        verify(newsCache, never()).remove(anyString());
    }

    @Test
    void testGetByIdFromCache() {
        Long id = 1L;
        News news = new News();
        NewsDTO expectedDTO = NewsMapper.INSTANCE.toDto(news);
        when(newsCache.get("news_" + id)).thenReturn(Optional.of(news));

        Optional<NewsDTO> result = newsService.getById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedDTO, result.get());
        verify(newsCache, times(1)).get("news_" + id);
        verify(newsRepository, never()).getReferenceById(id);
    }

    @Test
    void testGetByIdFromRepository() {
        Long id = 1L;
        News news = new News();
        NewsDTO expectedDTO = NewsMapper.INSTANCE.toDto(news);
        when(newsCache.get("news_" + id)).thenReturn(Optional.empty());
        when(newsRepository.getReferenceById(id)).thenReturn(news);

        Optional<NewsDTO> result = newsService.getById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedDTO, result.get());
        verify(newsCache, times(1)).get("news_" + id);
        verify(newsRepository, times(1)).getReferenceById(id);
        verify(newsCache, times(1)).put("news_" + id, news);
    }

    @Test
    void testGetByIdNonExisting() {
        Long id = 1L;
        when(newsCache.get("news_" + id)).thenReturn(Optional.empty());
        when(newsRepository.getReferenceById(id)).thenReturn(null);

        Optional<NewsDTO> result = newsService.getById(id);

        assertFalse(result.isPresent());
        verify(newsCache, times(1)).get("news_" + id);
        verify(newsRepository, times(1)).getReferenceById(id);
        verify(newsCache, never()).put(anyString(), any());
    }

    @Test
    void testFindAll() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());
        PageImpl<News> newsPage = new PageImpl<>(newsList);
        Pageable pageable = Pageable.ofSize(10);
        when(newsRepository.findAll(pageable)).thenReturn(newsPage);

        List<NewsDTO> result = newsService.findAll(pageable);

        assertEquals(newsList.size(), result.size());
        verify(newsRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindAllWithoutComments() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());
        PageImpl<News> newsPage = new PageImpl<>(newsList);
        Pageable pageable = Pageable.ofSize(10);
        when(newsRepository.findAll(pageable)).thenReturn(newsPage);

        List<NewsDTO> result = newsService.findAllWithoutComments(pageable);

        assertEquals(newsList.size(), result.size());
        verify(newsRepository, times(1)).findAll(pageable);
    }

    @Test
    void testSearchByTitleOrText() {
        String keyword = "test";
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());
        PageImpl<News> newsPage = new PageImpl<>(newsList);
        Pageable pageable = Pageable.ofSize(10);
        when(newsRepository.searchByTitleOrText(keyword, pageable)).thenReturn(newsPage);

        List<NewsDTO> result = newsService.searchByTitleOrText(keyword, pageable);

        assertEquals(newsList.size(), result.size());
        verify(newsRepository, times(1)).searchByTitleOrText(keyword, pageable);
    }
}
