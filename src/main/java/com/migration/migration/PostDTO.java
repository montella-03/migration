package com.migration.migration;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
