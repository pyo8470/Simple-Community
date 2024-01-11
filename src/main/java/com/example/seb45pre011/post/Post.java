package com.example.seb45pre011.post;

import com.example.seb45pre011.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  @NotBlank
  private String title;

  @NotBlank
  private String content;

  private int views = 0;
  private int upVotes = 0;
  private int downVotes = 0;
  private int reports = 0;

  @CreationTimestamp
  private LocalDateTime createdAt;

    @ManyToOne
  @JoinColumn(name = "user_id")
  private Member member;
//
//  @OneToMany(mappedBy = "post")
//  private Set<Comment> comments = new HashSet<>();

}
