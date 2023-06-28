package ru.clevertec.ecl.clevertecfinaltask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    NewsDTO create(NewsDTO newsDTO);

    NewsDTO update(NewsDTO newsDTO);

    void delete(Long id);

    Optional<NewsDTO> getById(Long id);

    List<NewsDTO> findAll(Pageable pageable);

    List<NewsDTO> searchByTitleOrText(String keyword, Pageable pageable);
}
