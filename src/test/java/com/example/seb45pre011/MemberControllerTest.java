package com.example.seb45pre011;

import com.example.seb45pre011.member.*;
import com.example.seb45pre011.security.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberMapper memberMapper;

    @Mock
    private MemberService memberService;

    @Mock
    private JwtProvider jwtProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostMember() {
        MemberDto.post postDto = new MemberDto.post();
        postDto.setEmail("test@example.com");
        postDto.setUsername("TestUser");
        postDto.setPassword("password");
        postDto.setGender("male");
        postDto.setPhone("123-456-7890");
        postDto.setNick("TestNick");
        Member mappedMember = memberMapper.memberPostDtoToMember(postDto);


        // Check the responseEntity and expected behavior
        // For example:
        assertEquals(mappedMember.getEmail(),postDto.getEmail());
        assertEquals(mappedMember.getUsername(),postDto.getUsername());
        assertEquals(mappedMember.getPassword(),postDto.getPassword());
        assertEquals(mappedMember.getGender(),postDto.getGender());
        assertEquals(mappedMember.getPhone(),postDto.getPhone());
        assertEquals(mappedMember.getNick(),postDto.getNick());
    }

    // Add more test methods for other controller methods
}