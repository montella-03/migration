package com.migration.migration;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public String savePosts(){
        postService.savePosts();
        return "Posts saved";
    }

    @GetMapping("/posts")
    public List<PostDTO> getPosts(){
        return postService.getPosts();
    }
    @GetMapping("/post/{id}")
    public PostDTO getPost(@PathVariable  int id){
        return postService.getPost(id);
    }
}
