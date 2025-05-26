package com.gelengelsin.gelengelsin.repository;

import com.gelengelsin.gelengelsin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
