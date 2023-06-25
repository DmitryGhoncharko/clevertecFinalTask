package ru.clevertec.ecl.clevertecfinaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;

public interface NewsRepository extends JpaRepository<News,Long> {
}
