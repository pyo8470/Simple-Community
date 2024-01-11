package com.example.seb45pre011.post;

import com.example.seb45pre011.comment.Comment;
import com.example.seb45pre011.comment.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "userId", source = "member.userId")
    PostDto.Response postToPostResponseDTO(Post post);

}
