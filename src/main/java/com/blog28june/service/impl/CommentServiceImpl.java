package com.blog28june.service.impl;

import com.blog28june.entity.Comment;
import com.blog28june.entity.Post;
import com.blog28june.exception.ResourceNotFoundException;
import com.blog28june.payload.CommentDto;
import com.blog28june.repository.CommentRepository;
import com.blog28june.repository.PostRepository;
import com.blog28june.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepo;


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment = mapToEntity(commentDto);

        comment.setPost(post);

        Comment save = commentRepo.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;

    }

    @Override
    public List<CommentDto> findAllComments(long postId) {
        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        List<Comment> comments= commentRepo.findByPostId(postId);
        List<CommentDto> collect = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return collect;
    }

    Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

}
