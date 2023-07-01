package com.blog28june.controller;

import com.blog28june.entity.Comment;
import com.blog28june.payload.PostDto;
import com.blog28june.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

@Autowired
private PostService postService;


    @PostMapping
    ResponseEntity<?> saveData(@Valid @RequestBody PostDto postDto , BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto=postService.saveData(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //localhost:8080/api?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
    @GetMapping
    List<PostDto> findAllPosts(
            @RequestParam(value="pageNo", defaultValue = "0", required=false)int pageNo,
            @RequestParam(value="pageSize", defaultValue = "5", required=false)int pageSize,
            @RequestParam(value="sortBy", defaultValue = "id", required=false)String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required=false)String sortDir
    ){
        System.out.println(pageNo);
        List<PostDto> dto=postService.findALlPosts(pageNo,pageSize,sortBy,sortDir);
        return dto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id,@RequestBody PostDto postDto){
        PostDto dto=postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        String response = postService.deletePost(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
