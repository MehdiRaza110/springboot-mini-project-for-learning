package com.blogapp.service;

import com.blogapp.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPost();

    void deletePost(long id);
}
