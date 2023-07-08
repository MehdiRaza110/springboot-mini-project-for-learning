package com.blog28june.repository;

import com.blog28june.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
