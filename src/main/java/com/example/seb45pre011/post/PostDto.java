package com.example.seb45pre011.post;

import com.example.seb45pre011.tag.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


public class PostDto {
  @NoArgsConstructor
  @Getter
  public static class Post{
    private String title;
    private String content;
  }
  @NoArgsConstructor
  @Setter
  @Getter
  public static class Response {
    private Long postId;

    private String title;
    private String content;
    private int views = 0;
    private int upVotes = 0;
    private int downVotes = 0;
    private int reports = 0;

    private LocalDateTime createdAt;
    private Long tagId;
    private Long userId;
  }

}

