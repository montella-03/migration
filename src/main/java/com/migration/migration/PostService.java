package com.migration.migration;

import java.util.List;

public interface PostService {
    PostDTO getPost(Long id);
    List<PostDTO> getPosts();
    List<PostDTO> savePosts();
}
