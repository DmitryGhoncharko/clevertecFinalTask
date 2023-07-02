package ru.clevertec.ecl.clevertecfinaltask.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewsDTO {
    private Long id;
    @NotNull(message = "Time cannot be null")
    @JsonView
    private LocalDateTime time;
    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 255)
    private String title;
    @NotEmpty(message = "Text cannot be empty")
    @Size(max = 255)
    private String text;
    private List<CommentDTO> comments;
}
