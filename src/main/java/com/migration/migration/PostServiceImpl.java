package com.migration.migration;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

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
        List<PostDTO> posts = restClient
                .get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PostDTO>>() {
                });

        assert posts != null;
        posts.forEach(System.out::println);


        posts.forEach(post -> {
            postRepository.save(modelMapper.map(post, Post.class));
        });
        return posts.stream()
                .map(post -> {
                    return modelMapper.map(post, PostDTO.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(int id) {
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
