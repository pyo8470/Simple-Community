package com.example.seb45pre011.answer;

import com.example.seb45pre011.member.Member;
import com.example.seb45pre011.post.Post;
import com.example.seb45pre011.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public Answer addAnswer(Member author,Long postId, AnswerDto answerDto) {
    Post parentPost = postRepository.findById(postId).orElse(null);
    if (parentPost != null) {
      Answer answer = new Answer();
      answer.setContent(answerDto.getContent());
      answer.setParentPost(parentPost);
      answer.setMember(author);
      return answerRepository.save(answer);
    }
    return null;
  }

  @Override
  public Answer getAnswerById(Long answerId) {
    return answerRepository.findById(answerId).orElse(null);
  }

  @Override
  public Answer updateAnswer(Long answerId, AnswerDto updatedAnswerDto) {
    Answer existingAnswer = answerRepository.findById(answerId).orElse(null);
    if (existingAnswer != null) {
      existingAnswer.setContent(updatedAnswerDto.getContent());
      return answerRepository.save(existingAnswer);
    }
    return null;
  }

  @Override
  public void deleteAnswer(Long answerId) {
    answerRepository.deleteById(answerId);
  }

  @Override
  public List<Answer> getAllAnswersByPost(Long postId) {
    return answerRepository.findByParentPostPostId(postId);
  }
}