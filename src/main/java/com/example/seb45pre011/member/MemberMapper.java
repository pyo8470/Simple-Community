package com.example.seb45pre011.member;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.PostDto postDto);
    Member memberloginDtoToMember(MemberDto.LoginDto loginDto);
}