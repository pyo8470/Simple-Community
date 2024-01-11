package com.example.seb45pre011;

import com.example.seb45pre011.comment.CommentRepository;
import com.example.seb45pre011.member.MemberRepository;
import com.example.seb45pre011.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public DataLoader(PostRepository postRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 게시물 생성
//        Post post1 = new Post();
//        Post post2 = new Post();
//        postRepository.save(post1);
//        postRepository.save(post2);
        //Member member = new Member();
    }
}