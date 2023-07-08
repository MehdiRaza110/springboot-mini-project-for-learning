package com.blog28june.repository;

import com.blog28june.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Object> findByName(String roleAdmin);
}
