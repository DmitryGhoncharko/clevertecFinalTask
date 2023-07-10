package ru.clevertec.ecl.clevertecfinaltask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.dto.Views;
import ru.clevertec.ecl.clevertecfinaltask.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/news")
@Validated
@Api(tags = "News API")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    @ApiOperation("Create news")
    @ApiResponses({
            @ApiResponse(code = 201, message = "News created successfully", response = NewsDTO.class),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<NewsDTO> createNews(@RequestBody @Validated(Views.SaveView.class) @Valid NewsDTO newsDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.create(newsDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update news")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News updated successfully", response = NewsDTO.class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<NewsDTO> updateNews(@RequestBody @Validated(Views.SaveView.class) @Valid NewsDTO newsDTO) {
        return ResponseEntity.ok(newsService.update(newsDTO));

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete news")
    @ApiResponses({
            @ApiResponse(code = 204, message = "News deleted successfully"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get news by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News found", response = NewsDTO.class),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        return newsService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{pageNumber}")
    @ApiOperation("Get all news")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News retrieved successfully", response = NewsDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<NewsDTO>> getAllNews(@PathVariable int pageNumber, @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(newsService.findAll(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/all-without-comments/{pageNumber}")
    @ApiOperation("Get all news without comments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News retrieved successfully", response = NewsDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<NewsDTO>> getAllNewsWithoutComments(@PathVariable int pageNumber, @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(newsService.findAllWithoutComments(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/search/{keyword}/{pageNumber}")
    @ApiOperation("Search news by title or text")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News found", response = NewsDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<NewsDTO>> searchNewsByTitleOrText(@PathVariable String keyword, @PathVariable int pageNumber, @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(newsService.searchByTitleOrText(keyword, PageRequest.of(pageNumber, pageSize)));
    }
}

