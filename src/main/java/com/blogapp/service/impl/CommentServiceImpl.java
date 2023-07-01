package com.blogapp.service.impl;

import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.payload.CommentDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment=new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);
        System.out.println(comment.getEmail());//test
        Comment save = commentRepo.save(comment);

        CommentDto dto=new CommentDto();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setEmail(save.getEmail());
        dto.setBody(save.getBody());

        return dto;
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        System.out.println(postId);
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        List<Comment> comments=commentRepo.findByPostId(postId);
        List<CommentDto> dto=comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());

        return dto;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        return dto;
    }
}
