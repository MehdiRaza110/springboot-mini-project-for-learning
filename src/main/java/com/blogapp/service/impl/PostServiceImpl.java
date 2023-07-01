package com.blogapp.service.impl;

import com.blogapp.entity.Post;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.payload.PostDto;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post=mapToEntity(postDto);

        Post saved = postRepo.save(post);

        PostDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post results = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
        PostDto dto = this.mapToDto(results);

        return dto;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPosts = postRepo.findAll();
        List<PostDto> dto = allPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
        postRepo.deleteById(id);
    }

    Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    PostDto mapToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}
