package ru.clevertec.ecl.clevertecfinaltask.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.mapper.CommentMapper;
import ru.clevertec.ecl.clevertecfinaltask.mapper.NewsMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class NewsProcessor {
    private final NewsRepository newsRepository;
    private final Cache<Long,News> newsCache;
    @Transactional
    public NewsDTO proceedCreateOrUpdateNews(NewsDTO newsDTO) {
        newsDTO.setTime(LocalDateTime.now());
        newsDTO.getComments().forEach(commentDTO -> commentDTO.setTime(LocalDateTime.now()));
        News newsAfterMapping = NewsMapper.INSTANCE.toEntity(newsDTO);
        newsAfterMapping.setComments(newsDTO.getComments().stream().map(CommentMapper.INSTANCE::toEntity).toList());
        newsAfterMapping.getComments().forEach(comment -> comment.setNews(newsAfterMapping));
        News news = newsRepository.save(newsAfterMapping);
        Hibernate.initialize(news.getComments().stream().map(Comment::getId));
        newsCache.put(news.getId(),news);
        return NewsMapper.INSTANCE.toDto(news);
    }
}
