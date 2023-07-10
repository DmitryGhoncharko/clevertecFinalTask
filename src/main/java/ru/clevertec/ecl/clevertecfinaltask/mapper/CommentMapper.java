package ru.clevertec.ecl.clevertecfinaltask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.clevertecfinaltask.dto.CommentDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(target = "newsId", source = "news.id")
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}
