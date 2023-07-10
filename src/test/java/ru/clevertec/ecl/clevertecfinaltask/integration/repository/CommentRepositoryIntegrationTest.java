package ru.clevertec.ecl.clevertecfinaltask.integration.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;
import ru.clevertec.ecl.clevertecfinaltask.repository.CommentRepository;

import java.util.List;

@Testcontainers
@DataJpaTest
class CommentRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test-db")
            .withUsername("test-user")
            .withPassword("test-password");
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CommentRepository commentRepository;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeEach
    void setup() {
        Comment comment1 = new Comment();
        comment1.setUsername("User1");
        comment1.setText( "Comment 1");
        Comment comment2 = new Comment();
        comment2.setUsername("User2");
        comment2.setText( "Comment 2");
        entityManager.persist(comment1);
        entityManager.persist(comment2);
        entityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        Assertions.assertEquals(2, comments.size());
    }

    @Test
    void testSave() {
        Comment comment = new Comment();
        comment.setUsername("User3");
        comment.setText( "New Comment");
        Comment savedComment = commentRepository.save(comment);
        Assertions.assertNotNull(savedComment.getId());
        Assertions.assertEquals("User3", savedComment.getUsername());
        Assertions.assertEquals("New Comment", savedComment.getText());
    }

    @Test
    void testUpdate() {
        Comment comment = commentRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(comment);
        comment.setText("Updated Comment");
        Comment updatedComment = commentRepository.save(comment);
        Assertions.assertEquals("Updated Comment", updatedComment.getText());
    }

    @Test
    void testDelete() {
        commentRepository.deleteById(1L);
        Assertions.assertEquals(1, commentRepository.findAll().size());
    }
}


