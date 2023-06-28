package ru.clevertec.ecl.clevertecfinaltask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.ecl.clevertecfinaltask.dto.NewsDTO;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;

import java.util.Collections;

@Mapper(uses = CommentMapper.class)
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO toDto(News news);

    News toEntity(NewsDTO newsDTO);

    @Mapping(target = "comments", ignore = true)
    NewsDTO toDtoWithoutComments(News news);

    default NewsDTO toDtoWithoutCommentsAndNull(News news) {
        NewsDTO newsDTO = toDtoWithoutComments(news);
        if (newsDTO.getComments() == null) {
            newsDTO.setComments(Collections.emptyList());
        }
        return newsDTO;
    }
}
