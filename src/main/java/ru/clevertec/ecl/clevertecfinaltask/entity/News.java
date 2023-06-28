package ru.clevertec.ecl.clevertecfinaltask.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", time=" + time + ", title='" + title + '\'' + ", text='" + text + '\'' + ", commentsCount=" + comments.size() + '}';
    }
}