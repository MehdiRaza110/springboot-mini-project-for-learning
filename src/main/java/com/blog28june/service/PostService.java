package com.blog28june.service;

import com.blog28june.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto saveData(PostDto postDto);

    List<PostDto> findALlPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    String deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);
}
