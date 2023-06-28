package ru.clevertec.ecl.clevertecfinaltask;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.repository.CommentRepository;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;

import java.util.List;


@SpringBootTest
class NewRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Transactional
    void findAll() {

//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder().forEntity(News.class).get();
//
//        org.apache.lucene.search.Query luceneQuery = queryBuilder
//                .keyword()
//                .onFields("title", "text")
//                .matching("test")
//                .createQuery();
//
//        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, News.class);
//        System.out.println(jpaQuery.getResultList());
        Page<Comment> list =  commentRepository.findAll(Pageable.ofSize(2));
        list.forEach(comment -> Hibernate.initialize(comment.getNews()));
        for(Comment news : list){
            System.out.println(news);
        }
        System.out.println(list);
    }
}
