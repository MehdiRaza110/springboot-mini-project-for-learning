package com.blog28june.service;

import com.blog28june.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> findAllComments(long postId);
}
