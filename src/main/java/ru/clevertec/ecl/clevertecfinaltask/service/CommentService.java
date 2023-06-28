package ru.clevertec.ecl.clevertecfinaltask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentDTO create(CommentDTO commentDTO);

    CommentDTO update(CommentDTO commentDTO);

    void delete(Long id);

    Optional<CommentDTO> getById(Long id);

    List<CommentDTO> findAll(Pageable pageable);

    List<CommentDTO> searchByTextOrUsername(String keyword, Pageable pageable);
}
