package ru.clevertec.ecl.clevertecfinaltask.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.cache.CommentsCacheManager;
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotCreateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.mapper.CommentMapper;
import ru.clevertec.ecl.clevertecfinaltask.repository.CommentRepository;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;
import ru.clevertec.ecl.clevertecfinaltask.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;

    private final CommentsCacheManager commentsCacheManager;


    @Override
    @Transactional
    public CommentDTO create(CommentDTO commentDTO) {
        commentDTO.setTime(LocalDateTime.now());
        Optional<News> newsOptional = newsRepository.findById(commentDTO.getNewsId());
        if (newsOptional.isPresent()) {
            Comment comment = new Comment();
            comment.setUsername(commentDTO.getUsername());
            comment.setTime(commentDTO.getTime());
            comment.setText(commentDTO.getText());
            comment.setNews(newsOptional.get());
            Comment commentAfetSave = commentRepository.save(comment);
            Hibernate.initialize(commentAfetSave.getNews().getId());
            commentsCacheManager.getCache().put(commentAfetSave.getId(), commentAfetSave);
            return CommentMapper.INSTANCE.toDto(commentAfetSave);
        }
        throw new CannotCreateCommentError(commentDTO.toString());
    }

    @Override
    @Transactional
    public CommentDTO update(CommentDTO commentDTO) {
        Optional<Comment> existingComment = commentRepository.findById(commentDTO.getId());
        Optional<News> existingNews = newsRepository.findById(commentDTO.getNewsId());
        if (existingComment.isPresent() && existingNews.isPresent()) {
            Comment comment = existingComment.get();
            comment.setTime(LocalDateTime.now());
            comment.setNews(existingNews.get());
            comment.setText(commentDTO.getText());
            comment.setUsername(commentDTO.getUsername());
            Comment commentAfterSave = commentRepository.save(comment);
            commentsCacheManager.getCache().put(commentAfterSave.getId(), commentAfterSave);
            return CommentMapper.INSTANCE.toDto(commentAfterSave);
        }
        throw new CannotUpdateCommentError(commentDTO.toString());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.deleteById(id);
            commentsCacheManager.getCache().remove(id);
            return;
        }
        throw new CannotDeleteCommentError(id.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDTO> getById(Long id) {
        Optional<Comment> commentOptional = commentsCacheManager.getCache().get(id);
        if (commentOptional.isPresent()) {
            return Optional.of(CommentMapper.INSTANCE.toDto(commentOptional.get()));
        }
        Comment comment = commentRepository.getReferenceById(id);
        if (comment != null) {
            commentsCacheManager.getCache().put(id, comment);
        }
        return Optional.ofNullable(CommentMapper.INSTANCE.toDto(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> findAll(Pageable pageable) {
        Page<Comment> commentsPage = commentRepository.findAll(pageable);
        commentsPage.forEach(comment -> Hibernate.initialize(comment.getNews()));
        return commentsPage.getContent().stream().map(CommentMapper.INSTANCE::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> searchByTextOrUsername(String keyword, Pageable pageable) {
        Page<Comment> commentsPage = commentRepository.searchByTextOrUsername(keyword, pageable);
        commentsPage.forEach(comment -> Hibernate.initialize(comment.getNews()));
        return commentsPage.getContent().stream().map(CommentMapper.INSTANCE::toDto).toList();
    }
}
