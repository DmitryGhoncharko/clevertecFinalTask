package ru.clevertec.ecl.clevertecfinaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
