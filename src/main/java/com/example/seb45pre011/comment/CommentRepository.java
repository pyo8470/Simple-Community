package com.example.seb45pre011.comment;

import com.example.seb45pre011.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost_PostIdOrderByCreatedAt(Long postId);
    List<Comment> findCommentsByPost_PostIdAndCommentIdGreaterThan(
            Long postId, Long commentId, Pageable pageable);
    List<Comment> findByPost_PostIdOrderByCommentId(Long postId,Pageable pageable);

    int countCommentsByMember(Member member);
}
