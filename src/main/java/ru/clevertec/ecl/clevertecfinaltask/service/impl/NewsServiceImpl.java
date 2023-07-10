package ru.clevertec.ecl.clevertecfinaltask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteNewsError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateNewsError;
import ru.clevertec.ecl.clevertecfinaltask.mapper.NewsMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;
import ru.clevertec.ecl.clevertecfinaltask.service.NewsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Profile("redis")
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsProcessor newsProcessor;

    private final Cache<String,Object> newsCache;

    @Override
    @Transactional
    public NewsDTO create(NewsDTO newsDTO) {
        return newsProcessor.proceedCreateOrUpdateNews(newsDTO);
    }

    @Override
    @Transactional
    public NewsDTO update(NewsDTO newsDTO) {
        Optional<News> newsOptional = newsRepository.findById(newsDTO.getId());
        if (newsOptional.isPresent()) {
            return newsProcessor.proceedCreateOrUpdateNews(newsDTO);
        }
        throw new CannotUpdateNewsError(newsDTO.toString());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
            newsCache.remove("news_" + id);
            return;
        }
        throw new CannotDeleteNewsError(id.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsDTO> getById(Long id) {
        Optional<Object> newsOptional = newsCache.get("news_" + id);
        if (newsOptional.isPresent() && newsOptional.get() instanceof News) {
            return Optional.of(NewsMapper.INSTANCE.toDto((News) newsOptional.get()));
        }
        News news = newsRepository.getReferenceById(id);
        if (news != null) {
            newsCache.put("news_" + id, news);
        }
        return Optional.ofNullable(NewsMapper.INSTANCE.toDto(news));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> findAll(Pageable pageable) {
        Page<News> commentsPage = newsRepository.findAll(pageable);
        return commentsPage.getContent().stream().map(NewsMapper.INSTANCE::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> findAllWithoutComments(Pageable pageable) {
        Page<News> commentsPage = newsRepository.findAll(pageable);
        return commentsPage.getContent().stream().map(NewsMapper.INSTANCE::toDtoWithoutCommentsAndNull).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> searchByTitleOrText(String keyword, Pageable pageable) {
        Page<News> commentsPage = newsRepository.searchByTitleOrText(keyword, pageable);
        return commentsPage.getContent().stream().map(NewsMapper.INSTANCE::toDto).toList();
    }
}
