package com.example.seb45pre011.answer;

import com.example.seb45pre011.member.Member;
import com.example.seb45pre011.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{post_id}/answers")
public class AnswerController {

  private final AnswerService answerService;
  private final MemberService memberService;

  public AnswerController(AnswerService answerService, MemberService memberService) {
    this.answerService = answerService;
    this.memberService = memberService;
  }

  @PostMapping
  public ResponseEntity<Answer> createAnswer(@PathVariable("post_id") Long postId, @RequestBody AnswerDto answerDto) {
    Member author = memberService.getUserByAuthentication();
    Answer createdAnswer = answerService.addAnswer(author,postId, answerDto);
    if (createdAnswer != null) {
      return ResponseEntity.ok(createdAnswer);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{answer_id}")
  public ResponseEntity<Answer> getAnswerById(@PathVariable("answer_id") Long answerId) {
    Answer answer = answerService.getAnswerById(answerId);
    if (answer != null) {
      return ResponseEntity.ok(answer);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{answer_id}")
  public ResponseEntity updateAnswer(
          @PathVariable("answer_id") Long answerId,
          @RequestBody AnswerDto updatedAnswerDto) {
    Member author = memberService.getUserByAuthentication();
    Answer answer = answerService.getAnswerById(answerId);
    if (answer.getMember().getUserId() != author.getUserId()){
      // 아디가 맞지 않아도 관리자인 경우 통과
      if (!author.getRoles().get(0).equals("ADMIN"))
        return new ResponseEntity<>("You don't have permission to update this comment.", HttpStatus.UNAUTHORIZED);
    }
    Answer updatedAnswer = answerService.updateAnswer(answerId, updatedAnswerDto);
    if (updatedAnswer != null) {
      return ResponseEntity.ok(updatedAnswer);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{answer_id}")
  public ResponseEntity deleteAnswer(@PathVariable("answer_id") Long answerId) {
    Member author = memberService.getUserByAuthentication();
    Answer answer = answerService.getAnswerById(answerId);
    if (answer.getMember().getUserId() != author.getUserId()){
      // 아디가 맞지 않아도 관리자인 경우 통과
      if (!author.getRoles().get(0).equals("ADMIN"))
        return new ResponseEntity<>("You don't have permission to delete this comment.", HttpStatus.UNAUTHORIZED);
    }
    answerService.deleteAnswer(answerId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<Answer>> getAllAnswersByPost(@PathVariable("post_id") Long postId) {
    List<Answer> answers = answerService.getAllAnswersByPost(postId);
    return ResponseEntity.ok(answers);
  }
}
