package com.migration.migration;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final RestClient restClient;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.restClient= RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Override
    public List<PostDTO> savePosts() {
        List<Post> posts = restClient
                .get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {
                });

        assert posts != null;
        postRepository.saveAll(posts);
        return posts.stream()
                .map(post -> {
                    return modelMapper.map(post, PostDTO.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    return modelMapper.map(post, PostDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public List<PostDTO> getPosts() {
     List<Post> posts = (List<Post>) postRepository.findAll();
        return posts.stream()
                .map(post -> {
                    return modelMapper.map(post, PostDTO.class);
                })
                .collect(Collectors.toList());
    }
}
