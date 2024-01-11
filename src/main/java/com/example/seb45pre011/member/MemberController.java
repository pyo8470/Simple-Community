package com.example.seb45pre011.member;


import com.example.seb45pre011.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
@Slf4j
public class MemberController {

    private final MemberMapper membermapper;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public MemberController(MemberMapper mapper, MemberService service, JwtProvider jwtProvider) {
        this.membermapper = mapper;
        this.memberService = service;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/users/signup")
    public ResponseEntity postMember(@RequestBody MemberDto.PostDto postDto){


        Member member = membermapper.memberPostDtoToMember(postDto);
        Member saveMember = memberService.createMember(member);
        return new ResponseEntity(saveMember, HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity loginMember(@RequestBody MemberDto.LoginDto loginDto) {
        String jwtToken = memberService.loginMember(membermapper.memberloginDtoToMember(loginDto));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-AUTH-TOKEN", "Bearer " + jwtToken);

        return ResponseEntity.ok()
                .body("Bearer " + jwtToken);
    }

    @PostMapping("/users/findpassword")
    public ModelAndView findPassword(@RequestParam("email") String email, @RequestParam("username") String username) {
        Member member = new Member();
        member.setEmail(email);
        member.setUsername(username);
        Member findMember = memberService.findPassword(member);
        ModelAndView modelAndView = new ModelAndView("users/reset.html");
        modelAndView.addObject("member", member);

        return modelAndView;
    }

    @PostMapping("/users/password/reset")
    public ResponseEntity resetPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        Member saveMember = memberService.resetPassword(member);
        return new ResponseEntity(saveMember.getNick(), HttpStatus.OK);

    }

    @PatchMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){
        String accessToken = jwtProvider.resolveToken(request);
        memberService.logoutMember(accessToken);
        return ResponseEntity.ok("logout successs!!");
    }
}