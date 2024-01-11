package com.example.seb45pre011.answer;

import com.example.seb45pre011.member.Member;

import java.util.List;

public interface AnswerService {
  Answer addAnswer(Member author,Long postId, AnswerDto answerDto);
  Answer getAnswerById(Long answerId);
  Answer updateAnswer(Long answerId, AnswerDto updatedAnswerDto);
  void deleteAnswer(Long answerId);
  List<Answer> getAllAnswersByPost(Long postId);
}
