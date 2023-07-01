package com.blogapp.controller;

import com.blogapp.payload.PostDto;
import com.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //using rest api
@RequestMapping("/api")//mapping the controller with localhost:8080/api
public class PostController { //post controller class
@Autowired //dependency injection with autowired
private PostService postService; //private bean creation..

    @PostMapping//post request come from post man
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){//give the response in entity on the postman.
        PostDto savedDto = postService.createPost(postDto);// createPost() method in PostService layer.. 
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//Status code 201 created
    }

    @GetMapping("/{id}")//get request send back to postman..
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){//give the response in entity on the postman.
        PostDto dto=postService.getPostById(id);//getPostById() method in PostService layer..
        return new ResponseEntity<>(dto,HttpStatus.OK);// Status code 200 ok
    }

    @GetMapping// get request send back to post man..
    public List<PostDto> getAllPost(){// get PostDto as a List of all entries..
        List<PostDto> allPost = postService.getAllPost();// getAllPost() method in PostService
        return allPost; //return allPosts
    }

    @DeleteMapping("/{id}")//detete by id..
    public ResponseEntity<String> deletePost(@PathVariable long id){//give respone back to the postman
        postService.deletePost(id);//deletePost() method in PostService layer..
        return new ResponseEntity<>("Post Deleted!!",HttpStatus.OK); //give message if deleted.
    }
}
