package com.spring.app.postify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"category", "createdAt", "updatedAt"})
    private Post post;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"updatedAt","createdAt","firstName","lastName"})
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}