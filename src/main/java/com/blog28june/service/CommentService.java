package com.blog28june.service;

import com.blog28june.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> findAllComments(long postId);

    String deleteComments(long postId, long id);

    CommentDto getOneComments(long postId, long id);

    CommentDto updateComments(long postId, long id, CommentDto commentDto);
}
