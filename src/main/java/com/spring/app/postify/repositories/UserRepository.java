package com.spring.app.postify.repositories;

import com.spring.app.postify.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
