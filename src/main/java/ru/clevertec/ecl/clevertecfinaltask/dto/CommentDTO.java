package ru.clevertec.ecl.clevertecfinaltask.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    @NotNull(message = "Time cannot be null")
    private LocalDateTime time;
    @NotEmpty(message = "Text cannot be empty")
    private String text;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    private Long newsId;
}
