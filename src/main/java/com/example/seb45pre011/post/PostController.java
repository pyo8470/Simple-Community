package com.example.seb45pre011.post;

import com.example.seb45pre011.member.Member;
import com.example.seb45pre011.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
  private final PostService postService;
  private final MemberService memberService;
  private final PostMapper postMapper;
  public PostController(PostService postService, MemberService memberService, PostMapper postMapper) {
    this.postService = postService;
    this.memberService = memberService;
    this.postMapper = postMapper;
  }

  @PostMapping
  public ResponseEntity createPost(@RequestBody PostDto.Post postDto) {
    Member member = memberService.getUserByAuthentication();
    // 사용자 정보 넣기
    Post createdPost = postService.createPost(member,postDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.postToPostResponseDTO(createdPost));
  }

  @PutMapping("/{post_id}")
  public ResponseEntity updatePost(@PathVariable("post_id") Long postId, @RequestBody PostDto.Post updatedPostDto) {
    Member member = memberService.getUserByAuthentication();
    Post post = postService.getPostById(postId);
    if (post.getMember().getUserId() != member.getUserId()){
      // 아디가 맞지 않아도 관리자인 경우 통과
      if (!member.getRoles().get(0).equals("ADMIN"))
        return new ResponseEntity<>("You don't have permission to delete this comment.", HttpStatus.UNAUTHORIZED);
    }
    Post updated = postService.updatePost(postId, updatedPostDto);
    if (updated != null) {
      return ResponseEntity.ok(updated);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{post_id}")
  public ResponseEntity deletePost(@PathVariable("post_id") Long postId) {
    Member member = memberService.getUserByAuthentication();
    Post post = postService.getPostById(postId);
    //아이디가 맞지 않으면
    if (post.getMember().getUserId() != member.getUserId()){
      // 아디가 맞지 않아도 관리자인 경우 통과
      if (!member.getRoles().get(0).equals("ADMIN"))
        return new ResponseEntity<>("You don't have permission to delete this comment.", HttpStatus.UNAUTHORIZED);
    }
    postService.deletePost(postId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{post_id}")
  public ResponseEntity<Post> getPostById(@PathVariable("post_id") Long postId) {
    Post post = postService.getPostById(postId);
    if (post != null) {
      return ResponseEntity.ok(post);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }
}