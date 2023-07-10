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
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.dto.Views;
import ru.clevertec.ecl.clevertecfinaltask.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/comments")
@Validated
@RequiredArgsConstructor
@Api(tags = "Comment Controller")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ApiOperation("Create a new comment")
    @ApiResponse(code = 201, message = "Comment created successfully", response = CommentDTO.class)
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody @Validated(Views.SaveView.class) @Valid CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(commentDTO));
    }

    @PutMapping
    @ApiOperation("Update an existing comment")
    @ApiResponse(code = 200, message = "Comment updated successfully", response = CommentDTO.class)
    public ResponseEntity<CommentDTO> updateComment(
            @RequestBody @Validated(Views.SaveView.class) @Valid CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(commentDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a comment by ID")
    @ApiResponse(code = 204, message = "Comment deleted successfully")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a comment by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comment found", response = CommentDTO.class),
            @ApiResponse(code = 404, message = "Comment not found")
    })
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        return commentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{pageNumber}")
    @ApiOperation("Get all comments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comments retrieved successfully", response = CommentDTO.class, responseContainer = "List")
    })
    public ResponseEntity<List<CommentDTO>> getAllComments(
            @PathVariable int pageNumber,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(commentService.findAll(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/search/{keyword}/{pageNumber}")
    @ApiOperation("Search comments by text or username")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comments found", response = CommentDTO.class, responseContainer = "List")
    })
    public ResponseEntity<List<CommentDTO>> searchCommentsByTextOrUsername(
            @PathVariable String keyword,
            @PathVariable int pageNumber,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(commentService.searchByTextOrUsername(keyword, PageRequest.of(pageNumber, pageSize)));
    }
}
