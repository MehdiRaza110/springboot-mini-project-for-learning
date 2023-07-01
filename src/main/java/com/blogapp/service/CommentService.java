package com.blogapp.service;

import com.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getAllComments(long postId);
}
