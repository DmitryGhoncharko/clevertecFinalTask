package ru.clevertec.ecl.clevertecfinaltask.integration.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;
import ru.clevertec.ecl.clevertecfinaltask.repository.NewsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
class NewsRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test-db")
            .withUsername("test-user")
            .withPassword("test-password");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NewsRepository newsRepository;

    @BeforeEach
    void setup() {
        News news1 = new News();
        news1.setTitle("Title 1");
        news1.setText("Text 1");
        News news2 = new News();
        news2.setTitle("Title 2");
        news2.setText("Text 2");
        entityManager.persist(news1);
        entityManager.persist(news2);
        entityManager.flush();
    }

    @Test
    void testFindAll() {
        Page<News> newsPage = newsRepository.findAll(PageRequest.of(0, 10));
        assertThat(newsPage.getTotalElements()).isEqualTo(2);
    }

    @Test
    void testSave() {
        News news = new News();
        news.setTitle("New Title");
        news.setText("New Text");
        News savedNews = newsRepository.save(news);
        assertThat(savedNews.getId()).isNotNull();
        assertThat(savedNews.getTitle()).isEqualTo("New Title");
        assertThat(savedNews.getText()).isEqualTo("New Text");
    }

    @Test
    void testUpdate() {
        News news = newsRepository.findById(1L).orElse(null);
        assertThat(news).isNotNull();
        news.setTitle("Updated Title");
        News updatedNews = newsRepository.save(news);
        assertThat(updatedNews.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testDelete() {
        newsRepository.deleteById(1L);
        assertThat(newsRepository.count()).isEqualTo(1);
    }
}

