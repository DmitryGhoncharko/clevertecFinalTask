package ru.clevertec.ecl.clevertecfinaltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM news WHERE to_tsvector('english', title || ' ' || text) @@ to_tsquery('english', ?1)", countQuery = "SELECT COUNT(*) FROM news WHERE to_tsvector('english', title || ' ' || text) @@ to_tsquery('english', ?1)", nativeQuery = true)
    Page<News> searchByTitleOrText(String keyword, Pageable pageable);

}
