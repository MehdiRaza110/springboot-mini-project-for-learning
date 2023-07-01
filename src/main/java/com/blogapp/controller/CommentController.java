package com.blogapp.controller;

import com.blogapp.payload.CommentDto;
import com.blogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//rest controller with rest api
@RequestMapping("/api/comments")//request mapping with the servelet name as /api/comments/1 
public class CommentController { //comment conroller class

    private CommentService commentService; //CommentService interface assign to variable name commentService..

    public CommentController(CommentService commentService) { //bean creation without autowired here...
        this.commentService = commentService; //this referce to the current object address..
    }

    @PostMapping("/{postId}")//post mapping with the postId in pathvariable, comming from postman boot path..
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, //postId comming form the path
                                                    @RequestBody CommentDto commentDto){// request comming from postman body..

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);//create comment in CommentService 
        												//layer to createComment.
    }

    @GetMapping("/{postId}")
    List<CommentDto> getAllComments(@PathVariable long postId){//return list of posts with the postId as a foregn key..
        System.out.println(postId);//checking the comming postid no use with code..
        List<CommentDto> allComments = commentService.getAllComments(postId);//give all comment with the postId..
        return allComments;//return back to the list of comment for the specific user...
    }
}
