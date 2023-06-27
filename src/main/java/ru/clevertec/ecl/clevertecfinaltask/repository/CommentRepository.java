package ru.clevertec.ecl.clevertecfinaltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM comments WHERE to_tsvector('english', text || ' ' || username) @@ to_tsquery('english', ?1)", countQuery = "SELECT COUNT(*) FROM comments WHERE to_tsvector('english', text || ' ' || username) @@ to_tsquery('english', ?1)", nativeQuery = true)
    Page<Comment> searchByTextOrUsername(String keyword, Pageable pageable);
}
