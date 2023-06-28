package ru.clevertec.ecl.clevertecfinaltask.controller;

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
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.dto.Views;
import ru.clevertec.ecl.clevertecfinaltask.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody @Validated(Views.SaveView.class) CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(commentDTO));
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody @Validated(Views.SaveView.class) CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(commentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        return commentService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable int pageNumber, @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(commentService.findAll(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/search/{keyword}/{pageNumber}")
    public ResponseEntity<List<CommentDTO>> searchCommentsByTextOrUsername(@PathVariable String keyword, @PathVariable int pageNumber, @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(commentService.searchByTextOrUsername(keyword, PageRequest.of(pageNumber, pageSize)));
    }
}
