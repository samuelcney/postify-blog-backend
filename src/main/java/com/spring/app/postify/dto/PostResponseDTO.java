package com.spring.app.postify.dto;

import com.spring.app.postify.model.Post;
import java.time.LocalDateTime;

public class PostResponseDTO {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
    private Integer categoryId;
    private boolean isFavorite;

    public PostResponseDTO(Post post, boolean isFavorite) {
        this.id = post.getId();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUser().getId();
        this.categoryId = post.getCategory().getId();
        this.isFavorite = isFavorite;
    }

    public Integer getId() { return id; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Integer getUserId() { return userId; }
    public Integer getCategoryId() { return categoryId; }
    public boolean getIsFavorite() { return isFavorite; }
}
