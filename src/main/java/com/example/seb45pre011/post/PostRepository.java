package com.example.seb45pre011.post;

import com.example.seb45pre011.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    int countPostsByMember(Member member);
}
