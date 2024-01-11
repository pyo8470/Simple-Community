package com.example.seb45pre011.comment;

import com.example.seb45pre011.exception.BusinessLogicException;
import com.example.seb45pre011.exception.ExceptionCode;
import com.example.seb45pre011.member.Member;
import com.example.seb45pre011.post.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Member member,Post post, Comment newComment){
        newComment.setMember(member);
        //postID를 통해 댓글 찾기
        newComment.setPost(post);
        return commentRepository.save(newComment);
    }
    public Comment updateComment(Long commentId,Comment comment){
        //  존재하는 댓글인지 확인
        Comment findComment = findVerifiedComment(commentId);
        findComment.setCommentId(commentId);
        findComment.setContent(comment.getContent());
        return commentRepository.save(findComment);
    }
    public void deleteComment(long commentId){
        Comment comment = findVerifiedComment(commentId);
        commentRepository.delete(comment);
    }
    public List<Comment> findComments(Long postId, Long cursor, int pageSize)  {
        List<Comment> foundComments;
        if (cursor == null) {
            foundComments = commentRepository.findByPost_PostIdOrderByCommentId(postId, PageRequest.of(0, pageSize));
        } else {
            foundComments = commentRepository.findCommentsByPost_PostIdAndCommentIdGreaterThan(postId, cursor, PageRequest.of(0, pageSize));
        }
        return foundComments;
    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPost_PostIdOrderByCreatedAt(postId);
    }
    public int getUserCommentCount(Member member){
        return commentRepository.countCommentsByMember(member);
    }
}
