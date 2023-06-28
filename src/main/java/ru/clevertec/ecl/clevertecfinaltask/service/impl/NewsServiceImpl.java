package ru.clevertec.ecl.clevertecfinaltask.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteNewsError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateNewsError;
import ru.clevertec.ecl.clevertecfinaltask.mapper.CommentMapper;
import ru.clevertec.ecl.clevertecfinaltask.mapper.NewsMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;
import ru.clevertec.ecl.clevertecfinaltask.service.NewsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    @Transactional
    public NewsDTO create(NewsDTO newsDTO) {
        return proceedCreateOrUpdateNews(newsDTO);
    }

    @Override
    @Transactional
    public NewsDTO update(NewsDTO newsDTO) {
        Optional<News> newsOptional = newsRepository.findById(newsDTO.getId());
        if (newsOptional.isPresent()) {
            return proceedCreateOrUpdateNews(newsDTO);
        }
        throw new CannotUpdateNewsError(newsDTO.toString());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
            return;
        }
        throw new CannotDeleteNewsError(id.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsDTO> getById(Long id) {
        return Optional.of(NewsMapper.INSTANCE.toDto(newsRepository.getReferenceById(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> findAll(Pageable pageable) {
        Page<News> commentsPage = newsRepository.findAll(pageable);
        return commentsPage.getContent().stream().map(NewsMapper.INSTANCE::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> searchByTitleOrText(String keyword, Pageable pageable) {
        Page<News> commentsPage = newsRepository.searchByTitleOrText(keyword, pageable);
        return commentsPage.getContent().stream().map(NewsMapper.INSTANCE::toDto).toList();
    }

    private NewsDTO proceedCreateOrUpdateNews(NewsDTO newsDTO) {
        newsDTO.setTime(LocalDateTime.now());
        newsDTO.getComments().forEach(commentDTO -> commentDTO.setTime(LocalDateTime.now()));
        News newsAfterMapping = NewsMapper.INSTANCE.toEntity(newsDTO);
        newsAfterMapping.setComments(newsDTO.getComments().stream().map(CommentMapper.INSTANCE::toEntity).toList());
        newsAfterMapping.getComments().forEach(comment -> comment.setNews(newsAfterMapping));
        News news = newsRepository.save(newsAfterMapping);
        Hibernate.initialize(news.getComments().stream().map(Comment::getId));
        return NewsMapper.INSTANCE.toDto(news);
    }
}
