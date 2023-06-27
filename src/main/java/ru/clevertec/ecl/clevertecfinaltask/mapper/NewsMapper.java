package ru.clevertec.ecl.clevertecfinaltask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;

@Mapper(uses = CommentMapper.class)
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO toDto(News news);
    News toEntity(NewsDTO newsDTO);
}
