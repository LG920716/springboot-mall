package com.ray.springmail.repository;

import com.ray.springmail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUserId(Integer userId);

    User findByEmail(String email);
}
