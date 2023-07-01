package com.blog28june.service.impl;

import com.blog28june.entity.Post;
import com.blog28june.exception.ResourceNotFoundException;
import com.blog28june.payload.PostDto;
import com.blog28june.repository.PostRepository;
import com.blog28june.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


@Autowired
private ModelMapper modelMapper;
@Autowired
private PostRepository postRepo;

    @Override
    public PostDto saveData(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post save = postRepo.save(post);
        PostDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<PostDto> findALlPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();//sortDir ascending and descending order as well as sort by title, cntent, id..
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);//sort in pageNo and pageSize..
        Page<Post> all = postRepo.findAll(pageable);//all posts return the Page<Post> ...
        List<Post> content = all.getContent();//convert Page<Post> into List of posts..
        List<PostDto> collect = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());//conver list of posts into the PostDto.
        return collect;
    }

    @Override
    public String deletePost(long id) {
        postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );

        postRepo.deleteById(id);
        return "Post Deleted successfully!!";
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        postDto.setId(id);
        PostDto dto = saveData(postDto);
        return dto;
    }

    Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }

    PostDto mapToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }
}
