package com.migration.migration;

import java.util.List;

public interface PostService {
    PostDTO getPost(int id);
    List<PostDTO> getPosts();
    List<PostDTO> savePosts();
}
