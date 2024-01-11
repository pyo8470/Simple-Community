package com.example.seb45pre011.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentDto.PostDto postDto);
    Comment commentPatchDtoToComment(CommentDto.PatchDto patchDto);

    //@Mapping(target = "memberId", source = "member.userId")
    @Mapping(target = "postId", source = "post.postId")
    @Mapping(target = "userId", source = "member.userId")
    CommentDto.Response commentToResponseDTO(Comment comment);
}
