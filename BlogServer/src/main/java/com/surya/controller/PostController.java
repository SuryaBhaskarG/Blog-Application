package com.surya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.surya.dto.PostDto;
import com.surya.model.Post;
import com.surya.service.PostService;



@RestController
@RequestMapping("/api/posts/")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController 
{

    @Autowired
    private PostService postService;
    

    @PostMapping("/addPost")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto)
    {
        Post addedPost =postService.createPost(postDto);
        return new ResponseEntity<>(addedPost,HttpStatus.OK);
    }
    

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() 
    {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    
    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) 
    {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
    
}