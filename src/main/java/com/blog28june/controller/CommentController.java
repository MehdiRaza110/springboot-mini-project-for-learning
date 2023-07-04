package com.blog28june.controller;

import com.blog28june.payload.CommentDto;
import com.blog28june.repository.CommentRepository;
import com.blog28june.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class CommentController {
@Autowired
private CommentService commentService;

    @PostMapping("/{postId}/comment")
    ResponseEntity<?> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(commentDto.getBody());
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    List<CommentDto> findAllComments(@PathVariable long postId){
        List<CommentDto> dto= commentService.findAllComments(postId);
        return dto;
    }

    @DeleteMapping("/{postId}/comment/{id}")
    ResponseEntity<String> deleteComments(@PathVariable long postId, @PathVariable long id){
        String str=commentService.deleteComments(postId,id);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/{postId}/comment/{id}")
    ResponseEntity<CommentDto> getOneComments(@PathVariable long postId, @PathVariable long id){
        CommentDto dto=commentService.getOneComments(postId,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @PutMapping("/{postId}/comment/{id}")
    ResponseEntity<CommentDto> updateComments(@PathVariable long postId, @PathVariable long id,@RequestBody CommentDto commentDto){
        CommentDto dto=commentService.updateComments(postId,id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
