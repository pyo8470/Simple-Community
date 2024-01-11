package com.example.seb45pre011.post;

import com.example.seb45pre011.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepository postRepository;

  @Override
  public Post createPost(Member member,PostDto.Post postDto) {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setContent(postDto.getContent());
    post.setMember(member);

    return postRepository.save(post);
  }



  @Override
  public Post updatePost(Long postId, PostDto.Post updatedPostDto) {
    Post existingPost = postRepository.findById(postId).orElse(null);
    if (existingPost != null) {
      existingPost.setTitle(updatedPostDto.getTitle());
      existingPost.setContent(updatedPostDto.getContent());

      return postRepository.save(existingPost);
    }
    return null; // Handle case where post with given id doesn't exist
  }

  @Override
  public void deletePost(Long postId) {
    postRepository.deleteById(postId);
  }

  @Override
  public Post getPostById(Long postId) {
    return postRepository.findById(postId).orElse(null);
  }

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  public int getUserPostCount(Member member) {
    return postRepository.countPostsByMember(member);
  }
}