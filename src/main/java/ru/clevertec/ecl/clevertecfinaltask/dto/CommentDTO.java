package ru.clevertec.ecl.clevertecfinaltask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    @NotNull(message = "Time cannot be null")
    @JsonView
    private LocalDateTime time;
    @NotEmpty(message = "Text cannot be empty")
    private String text;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    private Long newsId;
}
